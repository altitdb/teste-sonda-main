package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void should_create_coordinate() {
        Coordinate coordinate = new Coordinate(2, 3);
        assertEquals(2, coordinate.getX());
        assertEquals(3, coordinate.getY());
    }

    @Test
    void should_create_same_coordinates() {
        Coordinate coordinate = new Coordinate(1, 3);
        assertEquals(new Coordinate(1, 3), coordinate);
    }

    @Test
    void shouldnt_create_coordinate_with_position_x() {
        ValidationException exception = assertThrows(ValidationException.class, () -> new Coordinate(null, 1));
        assertEquals("Position x is required", exception.getMessage());
    }

    @Test
    void shouldnt_create_coordinate_with_position_y() {
        ValidationException exception = assertThrows(ValidationException.class, () -> new Coordinate(1, null));
        assertEquals("Position y is required", exception.getMessage());
    }

}