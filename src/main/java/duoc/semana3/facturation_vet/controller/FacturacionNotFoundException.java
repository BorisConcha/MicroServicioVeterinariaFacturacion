package duoc.semana3.facturation_vet.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FacturacionNotFoundException extends RuntimeException {
    public FacturacionNotFoundException(String message) {
        super(message);
    }
}
