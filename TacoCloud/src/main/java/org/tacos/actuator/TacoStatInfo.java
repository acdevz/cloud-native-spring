package org.tacos.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import org.tacos.repositories.TacoRepository;

import java.util.HashMap;
import java.util.Map;

@Component
public class TacoStatInfo implements InfoContributor {
    private final TacoRepository tacoRepository;

    public TacoStatInfo(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> tacoStats = new HashMap<>();
        tacoStats.put("totalTacos", String.valueOf(tacoRepository.count()));
        builder.withDetails(tacoStats);
    }
}
