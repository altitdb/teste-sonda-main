package br.com.elo7.sonda.candidato.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ValidationExceptionTest {

    @Test
    void should_create_a_validation_exception() {
        ValidationException exception = new ValidationException("My error message");
        assertEquals("My error message", exception.getMessage());
    }

}