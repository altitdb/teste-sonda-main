package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.constants.Command;
import br.com.elo7.sonda.candidato.constants.Direction;
import br.com.elo7.sonda.candidato.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProbeTest {

    @Test
    void shouldnt_create_probe_without_planet() {
        Coordinate coordinate = new Coordinate(1, 3);
        ValidationException exception = assertThrows(ValidationException.class, () -> new Probe(null, coordinate, Direction.NORTH));
        assertEquals("Planet is required", exception.getMessage());
    }

    @Test
    void shouldnt_create_probe_without_coordinate() {
        Planet planet = new Planet(1, 1);
        ValidationException exception = assertThrows(ValidationException.class, () -> new Probe(planet, null, Direction.NORTH));
        assertEquals("Coordinate is required", exception.getMessage());
    }

    @Test
    void shouldnt_create_probe_without_direction() {
        Planet planet = new Planet(3, 3);
        Coordinate coordinate = new Coordinate(1, 3);
        ValidationException exception = assertThrows(ValidationException.class, () -> new Probe(planet, coordinate, null));
        assertEquals("Direction is required", exception.getMessage());
    }

    @Test
    void shouldnt_accept_coordinate_x_gt_height() {
        Planet planet = new Planet(3, 3);
        Coordinate coordinate = new Coordinate(4, 3);
        ValidationException exception = assertThrows(ValidationException.class, () -> new Probe(planet, coordinate, Direction.NORTH));
        assertEquals("Coordinate x '4' cannot be greater than height '3'", exception.getMessage());
    }

    @Test
    void shouldnt_accept_coordinate_y_gt_width() {
        Planet planet = new Planet(3, 3);
        Coordinate coordinate = new Coordinate(3, 4);
        ValidationException exception = assertThrows(ValidationException.class, () -> new Probe(planet, coordinate, Direction.NORTH));
        assertEquals("Coordinate y '4' cannot be greater than width '3'", exception.getMessage());
    }

    @Test
    void should_change_probe_direction_from_N_To_W_when_receive_the_command_L() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.NORTH);
        probe.executeCommand(Command.L);
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_W_To_S_when_receive_the_command_L() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.WEST);
        probe.executeCommand(Command.L);
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_S_To_E_when_receive_the_command_L() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.SOUTH);
        probe.executeCommand(Command.L);
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_E_To_N_when_receive_the_command_L() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.EAST);
        probe.executeCommand(Command.L);
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_N_To_E_when_receive_the_command_R() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.NORTH);
        probe.executeCommand(Command.R);
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_E_To_S_when_receive_the_command_R() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.EAST);
        probe.executeCommand(Command.R);
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_S_To_W_when_receive_the_command_R() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.SOUTH);
        probe.executeCommand(Command.R);
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_W_To_N_when_receive_the_command_R() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.WEST);
        probe.executeCommand(Command.R);
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_N_To_1_2_N_when_receive_the_command_M() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(1, 1), Direction.NORTH);
        probe.executeCommand(Command.M);
        assertEquals(new Coordinate(1, 2), probe.getCoordinate());
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_S_To_1_0_S_when_receive_the_command_M() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(1, 1), Direction.SOUTH);
        probe.executeCommand(Command.M);
        assertEquals(new Coordinate(1, 0), probe.getCoordinate());
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_W_To_0_1_W_when_receive_the_command_M() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(1, 1), Direction.WEST);
        probe.executeCommand(Command.M);
        assertEquals(new Coordinate(0, 1), probe.getCoordinate());
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_E_To_2_1_E_when_receive_the_command_M() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(1, 1), Direction.EAST);
        probe.executeCommand(Command.M);
        assertEquals(new Coordinate(2, 1), probe.getCoordinate());
        assertEquals(Direction.EAST, probe.getDirection());
    }
}
