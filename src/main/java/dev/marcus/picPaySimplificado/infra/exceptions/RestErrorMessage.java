package dev.marcus.picPaySimplificado.infra.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RestErrorMessage {
    private HttpStatus status;
    private String message;
}
