package org.auth.tacoadminclient.services;

import org.auth.tacoadminclient.models.Ingredient;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Objects;

@Service
public class RestIngredientService implements IngredientService {
    private final String BASE_URL = "http://localhost:8080/api/ingredients";
    private final OAuth2AuthorizedClientService clientService;

    public RestIngredientService(OAuth2AuthorizedClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public String getAccessToken(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken oauthToken) {
            OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(
                    oauthToken.getAuthorizedClientRegistrationId(),
                    oauthToken.getName()
            );
            return client != null ? client.getAccessToken().getTokenValue() : null;
        }
        return null;
    }

    private RestTemplate createAuthorizedRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        String accessToken = getAccessToken();
        if (accessToken != null) {
            restTemplate.getInterceptors().add((request, body, execution) -> {
                request.getHeaders().add("Authorization", "Bearer " + accessToken);
                return execution.execute(request, body);
            });
        }
        return restTemplate;
    }

    @Override
    public Iterable<Ingredient> findAll() {
        RestTemplate restTemplate = createAuthorizedRestTemplate();
        return Arrays.asList(Objects.requireNonNull(restTemplate.getForObject(
                BASE_URL,
                Ingredient[].class
        )));
    }

    @Override
    public Ingredient addIngredient(Ingredient ingredient) {
        RestTemplate restTemplate = createAuthorizedRestTemplate();
        return restTemplate.postForObject(
                BASE_URL,
                ingredient,
                Ingredient.class
        );
    }

    @Override
    public void deleteIngredient(String id){
        RestTemplate restTemplate = createAuthorizedRestTemplate();
        restTemplate.delete(BASE_URL + "/" + id);
    }

}
