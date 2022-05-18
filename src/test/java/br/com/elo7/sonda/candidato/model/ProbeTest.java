package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.constants.Command;
import br.com.elo7.sonda.candidato.constants.Direction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProbeTest {

    @Test
    void should_change_probe_direction_from_N_To_W_when_receive_the_command_L() {
        Probe probe = new Probe(null, null, Direction.NORTH);
        probe.executeCommand(Command.L);
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_W_To_S_when_receive_the_command_L() {
        Probe probe = new Probe(null, null, Direction.WEST);
        probe.executeCommand(Command.L);
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_S_To_E_when_receive_the_command_L() {
        Probe probe = new Probe(null, null, Direction.SOUTH);
        probe.executeCommand(Command.L);
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_E_To_N_when_receive_the_command_L() {
        Probe probe = new Probe(null, null, Direction.EAST);
        probe.executeCommand(Command.L);
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_N_To_E_when_receive_the_command_R() {
        Probe probe = new Probe(null, null, Direction.NORTH);
        probe.executeCommand(Command.R);
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_E_To_S_when_receive_the_command_R() {
        Probe probe = new Probe(null, null, Direction.EAST);
        probe.executeCommand(Command.R);
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_S_To_W_when_receive_the_command_R() {
        Probe probe = new Probe(null, null, Direction.SOUTH);
        probe.executeCommand(Command.R);
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_W_To_N_when_receive_the_command_R() {
        Probe probe = new Probe(null, null, Direction.WEST);
        probe.executeCommand(Command.R);
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_N_To_1_2_N_when_receive_the_command_M() {
        Probe probe = new Probe(null, new Coordinate(1, 1), Direction.NORTH);
        probe.executeCommand(Command.M);
        assertEquals(new Coordinate(1, 2), probe.getCoordinate());
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_S_To_1_0_S_when_receive_the_command_M() {
        Probe probe = new Probe(null, new Coordinate(1, 1), Direction.SOUTH);
        probe.executeCommand(Command.M);
        assertEquals(new Coordinate(1, 0), probe.getCoordinate());
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_W_To_0_1_W_when_receive_the_command_M() {
        Probe probe = new Probe(null, new Coordinate(1, 1), Direction.WEST);
        probe.executeCommand(Command.M);
        assertEquals(new Coordinate(0, 1), probe.getCoordinate());
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_E_To_2_1_E_when_receive_the_command_M() {
        Probe probe = new Probe(null, new Coordinate(1, 1), Direction.EAST);
        probe.executeCommand(Command.M);
        assertEquals(new Coordinate(2, 1), probe.getCoordinate());
        assertEquals(Direction.EAST, probe.getDirection());
    }
}
