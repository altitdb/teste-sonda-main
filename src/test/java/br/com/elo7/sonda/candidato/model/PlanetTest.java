package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import static org.junit.jupiter.api.Assertions.*;

class PlanetTest {

    @Test
    void should_create_planet() {
        Planet planet = new Planet(1, 3);
        assertNotNull(planet.getId());
        assertEquals(1, planet.getHeight());
        assertEquals(3, planet.getWidth());
    }

    @Test
    void shouldnt_create_planet_without_height() {
        ValidationException exception = assertThrows(ValidationException.class, () -> new Planet(null, 3));
        assertEquals("Height is required", exception.getMessage());
    }

    @Test
    void shouldnt_create_planet_without_width() {
        ValidationException exception = assertThrows(ValidationException.class, () -> new Planet(3, null));
        assertEquals("Width is required", exception.getMessage());
    }

}