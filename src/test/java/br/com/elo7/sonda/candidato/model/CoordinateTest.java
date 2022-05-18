package br.com.elo7.sonda.candidato.model;

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

}