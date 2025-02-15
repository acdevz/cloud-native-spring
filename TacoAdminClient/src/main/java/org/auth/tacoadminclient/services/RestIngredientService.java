package org.auth.tacoadminclient.services;

import org.auth.tacoadminclient.models.Ingredient;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class RestIngredientService implements IngredientService {
    private final RestTemplate restTemplate;
    private final String BASE_URL = "http://localhost:8080/api";

    public RestIngredientService(String accessToken) {
        this.restTemplate = new RestTemplate();
        if(accessToken != null){
            this.restTemplate
                    .getInterceptors()
                    .add(new ClientHttpRequestInterceptor() {
                        @Override
                        public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
                            request.getHeaders().add("Authorization", "Bearer" + accessToken);
                            return execution.execute(request, body);
                        }
                    });
        }
    }

    @Override
    public Iterable<Ingredient> findAll() {
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
                BASE_URL + "/ingredients",
                Ingredient[].class
        )));
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        return restTemplate.postForObject(
                BASE_URL + "/ingredients",
                ingredient,
                Ingredient.class
        );
    }
}
