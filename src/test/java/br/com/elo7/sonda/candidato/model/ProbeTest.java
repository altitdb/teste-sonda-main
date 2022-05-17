package br.com.elo7.sonda.candidato.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProbeTest {

    @Test
    void should_change_probe_direction_from_N_To_W_when_receive_the_command_L() {
        Probe probe = new Probe();
        probe.setDirection(Direction.NORTH);
        probe.executeCommand(Command.L);
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_W_To_S_when_receive_the_command_L() {
        Probe probe = new Probe();
        probe.setDirection(Direction.WEST);
        probe.executeCommand(Command.L);
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_S_To_E_when_receive_the_command_L() {
        Probe probe = new Probe();
        probe.setDirection(Direction.SOUTH);
        probe.executeCommand(Command.L);
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_E_To_N_when_receive_the_command_L() {
        Probe probe = new Probe();
        probe.setDirection(Direction.EAST);
        probe.executeCommand(Command.L);
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_N_To_E_when_receive_the_command_R() {
        Probe probe = new Probe();
        probe.setDirection(Direction.NORTH);
        probe.executeCommand(Command.R);
        assertEquals(Direction.EAST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_E_To_S_when_receive_the_command_R() {
        Probe probe = new Probe();
        probe.setDirection(Direction.EAST);
        probe.executeCommand(Command.R);
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_S_To_W_when_receive_the_command_R() {
        Probe probe = new Probe();
        probe.setDirection(Direction.SOUTH);
        probe.executeCommand(Command.R);
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_direction_from_W_To_N_when_receive_the_command_R() {
        Probe probe = new Probe();
        probe.setDirection(Direction.WEST);
        probe.executeCommand(Command.R);
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_N_To_1_2_N_when_receive_the_command_M() {
        Probe probe = new Probe();
        probe.setX(1);
        probe.setY(1);
        probe.setDirection(Direction.NORTH);
        probe.executeCommand(Command.M);
        assertEquals(2, probe.getY());
        assertEquals(1, probe.getX());
        assertEquals(Direction.NORTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_S_To_1_0_S_when_receive_the_command_M() {
        Probe probe = new Probe();
        probe.setX(1);
        probe.setY(1);
        probe.setDirection(Direction.SOUTH);
        probe.executeCommand(Command.M);
        assertEquals(0, probe.getY());
        assertEquals(1, probe.getX());
        assertEquals(Direction.SOUTH, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_W_To_0_1_W_when_receive_the_command_M() {
        Probe probe = new Probe();
        probe.setX(1);
        probe.setY(1);
        probe.setDirection(Direction.WEST);
        probe.executeCommand(Command.M);
        assertEquals(0, probe.getX());
        assertEquals(1, probe.getY());
        assertEquals(Direction.WEST, probe.getDirection());
    }

    @Test
    void should_change_probe_position_from_1_1_E_To_2_1_E_when_receive_the_command_M() {
        Probe probe = new Probe();
        probe.setX(1);
        probe.setY(1);
        probe.setDirection(Direction.EAST);
        probe.executeCommand(Command.M);
        assertEquals(2, probe.getX());
        assertEquals(1, probe.getY());
        assertEquals(Direction.EAST, probe.getDirection());
    }
}
