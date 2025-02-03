package org.tacos.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.tacos.models.Ingredient;
import org.tacos.repositories.IngredientRepository;

@Configuration
public class ProjectConfig {
    @Bean
    @Profile("dev")
    public ApplicationRunner dataLoader(IngredientRepository repo) {
        return (args) -> {
            repo.save(Ingredient.of("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));
            repo.save(Ingredient.of("COTO", "Corn Tortilla", Ingredient.Type.WRAP));
            repo.save(Ingredient.of("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));
            repo.save(Ingredient.of("CARN", "Carnitas", Ingredient.Type.PROTEIN));
            repo.save(Ingredient.of("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));
            repo.save(Ingredient.of("LETC", "Lettuce", Ingredient.Type.VEGGIES));
            repo.save(Ingredient.of("CHED", "Cheddar", Ingredient.Type.CHEESE));
            repo.save(Ingredient.of("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));
            repo.save(Ingredient.of("SLSA", "Salsa", Ingredient.Type.SAUCE));
            repo.save(Ingredient.of("SRCR", "Sour Cream", Ingredient.Type.SAUCE));
        };
    }
}
