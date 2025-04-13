package org.tacos.config;

import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.tacos.models.Ingredient;
import org.tacos.models.Taco;
import org.tacos.models.User;
import org.tacos.repositories.IngredientRepository;
import org.tacos.repositories.TacoRepository;
import org.tacos.repositories.UserRepository;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ProjectConfig {
    @Bean
    @Profile("dev")
    public ApplicationRunner dataLoader(
            IngredientRepository ingredientRepository,
            TacoRepository tacoRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return (args) -> {
            Ingredient flourTortilla = Ingredient.of( "FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            Ingredient cornTortilla = Ingredient.of( "COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            Ingredient groundBeef = Ingredient.of( "GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            Ingredient carnitas = Ingredient.of( "CARN", "Carnitas", Ingredient.Type.PROTEIN);
            Ingredient tomatoes = Ingredient.of( "TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
            Ingredient lettuce = Ingredient.of( "LETC", "Lettuce", Ingredient.Type.VEGGIES);
            Ingredient cheddar = Ingredient.of( "CHED", "Cheddar", Ingredient.Type.CHEESE);
            Ingredient jack = Ingredient.of( "JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            Ingredient salsa = Ingredient.of( "SLSA", "Salsa", Ingredient.Type.SAUCE);
            Ingredient sourCream = Ingredient.of( "SRCR", "Sour Cream", Ingredient.Type.SAUCE);
            ingredientRepository.save(flourTortilla);
            ingredientRepository.save(cornTortilla);
            ingredientRepository.save(groundBeef);
            ingredientRepository.save(carnitas);
            ingredientRepository.save(tomatoes);
            ingredientRepository.save(lettuce);
            ingredientRepository.save(cheddar);
            ingredientRepository.save(jack);
            ingredientRepository.save(salsa);
            ingredientRepository.save(sourCream);

            Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(List.of( flourTortilla, groundBeef, carnitas, sourCream, salsa, cheddar));
            tacoRepository.save(taco1);

            Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(List.of( cornTortilla, groundBeef, cheddar, jack, sourCream));
            tacoRepository.save(taco2);

            Taco taco3 = new Taco();
            taco3.setName("Veg-Out");
            taco3.setIngredients(List.of( flourTortilla, cornTortilla, tomatoes, lettuce, salsa));
            tacoRepository.save(taco3);

            /* Admin User */
            User adminUser = User.of(
                    "admin",
                    passwordEncoder.encode("admin"),
                    "Chef Acdevs",
                    "123456",
                    "555-1234-5678",
                    List.of("ROLE_USER", "ROLE_ADMIN")
            );
            userRepository.save(adminUser);

            /* Regular User */
            User regularUser = User.of(
                    "acdevs",
                    passwordEncoder.encode("4444"),
                    "Acdevs",
                    "443322",
                    "222-1234-5678"
            );
            userRepository.save(regularUser);
        };
    }
}
