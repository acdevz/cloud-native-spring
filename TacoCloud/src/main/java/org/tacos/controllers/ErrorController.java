package org.tacos.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Map;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {

    @RequestMapping("/error")
    public Object handleError(HttpServletRequest request, Model model) {
        String uri = (String) request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        int statusCode = 0;
        if (status != null) {
            statusCode = Integer.parseInt(status.toString());
        }

        if (uri != null && (uri.startsWith("/actuator") || uri.startsWith("/admin/api"))) {
            Map<String, Object> body = Map.of(
                    "timestamp", LocalDateTime.now(),
                    "status", statusCode,
                    "error", HttpStatus.valueOf(statusCode).getReasonPhrase()
            );
            return new ResponseEntity<>(body, HttpStatus.valueOf(statusCode));
        }

        String title;
        String message = switch (statusCode) {
            case 400 -> {
                title = "Bad Request! ❌";
                yield "Your request seems invalid. Please try again.";
            }
            case 401 -> {
                title = "Unauthorized! 🔒";
                yield "You’re not allowed to view this page.";
            }
            case 403 -> {
                title = "Permission Denied! 🛡️";
                yield "You don’t have permission to access this page.";
            }
            case 404 -> {
                title = "Broken! ⛓️‍💥";
                yield "The page you’re looking for doesn’t exist.";
            }
            case 500 -> {
                title = "Internal Server Error! 💥";
                yield "Oops! Something went wrong on our end.";
            }
            default -> {
                title = "Unexpected Error! ⚠️";
                yield "An unexpected error occurred. We're on it!";
            }
        };
        model.addAttribute("title", title);
        model.addAttribute("message", message);
        return "error";
    }
}
