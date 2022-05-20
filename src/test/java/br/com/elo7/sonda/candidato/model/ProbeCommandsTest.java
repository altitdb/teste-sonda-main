package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.constants.Direction;
import br.com.elo7.sonda.candidato.exception.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ProbeCommandsTest {

    @Test
    void should_create_new_probe_with_commands() {
        Probe probe = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.NORTH);
        ProbeCommands probeCommands = new ProbeCommands("LMLMLMLMM", probe);
        assertEquals("LMLMLMLMM", probeCommands.getKey());
        assertEquals(probe, probeCommands.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ABC", "LM LM"})
    void should_not_create_probe_with_invalid_command(String commands) {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                new ProbeCommands(commands, null));
        assertEquals("Invalid commands", exception.getMessage());
    }

    @Test
    void should_not_create_probe_with_invalid_probe() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                new ProbeCommands("LMR", null));
        assertEquals("Probe is required", exception.getMessage());
    }

    @Test
    void should_update_probe_value() {
        Probe actual = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.NORTH);
        ProbeCommands probeCommands = new ProbeCommands("LMR", actual);
        Probe expected = new Probe(new Planet(13, 13), new Coordinate(5, 5), Direction.SOUTH);
        assertEquals(actual, probeCommands.setValue(expected));
        assertEquals(expected, probeCommands.getValue());
    }

}
