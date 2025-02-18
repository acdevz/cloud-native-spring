package org.auth.tacoadminclient.controllers;

import org.auth.tacoadminclient.models.Ingredient;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class GlobalControllerAdvice {
    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String handleUnauthorized(HttpClientErrorException.Unauthorized e, Model model) {
        model.addAttribute("ingredient", new Ingredient());
        model.addAttribute("error", "You are not authorized to perform this action.");
        return "ingredientsAdmin";
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String handleGeneralException(Exception e, Model model) {
        model.addAttribute("error", "An unexpected error occurred. Please try again.");
        return "home";
    }
}
