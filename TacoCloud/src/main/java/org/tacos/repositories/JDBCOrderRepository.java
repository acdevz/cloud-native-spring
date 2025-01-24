package org.tacos.repositories;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.tacos.models.Ingredient;
import org.tacos.models.Taco;
import org.tacos.models.TacoOrder;

import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JDBCOrderRepository implements OrderRepository {
    private final JdbcTemplate jdbc;
    public JDBCOrderRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into taco_order (delivery_name, delivery_zip, upiId, placed_at) values (?, ?, ?, ?)",
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);
        order.setPlacedAt(new Date());

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                order.getDeliveryName(),
                order.getDeliveryZip(),
                order.getUpiId(),
                order.getPlacedAt()
        ));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        Long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        for(Taco taco : tacos)
            saveTaco(taco, orderId);

        return order;
    }

    private Taco saveTaco(Taco taco, Long orderId){
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(
                "insert into taco (name, createdAt, taco_order) values (?, ?, ?)",
                Types.VARCHAR, Types.TIMESTAMP, Types.BIGINT
        );
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(Arrays.asList(
                taco.getName(),
                taco.getCreatedAt(),
                orderId
        ));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(psc, keyHolder);
        Long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);

        saveIngredientRef(tacoId, taco.getIngredients());
        return taco;
    }

    private void saveIngredientRef(Long tacoId, List<Ingredient> ingredients){
        for(Ingredient ingredient : ingredients){
            jdbc.update(
                    "insert into ingredient_ref (taco, ingredient) values (?, ?)",
                    tacoId, ingredient.getId()
            );
        }
    }
}
