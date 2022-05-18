package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ProbeCommandsTest {

    @Test
    void should_create_new_probe_with_commands() {
        Probe probe = new Probe(null, null, null);
        ProbeCommands<String, Probe> probeCommands = new ProbeCommands<>("LMLMLMLMM", probe);
        Assertions.assertEquals("LMLMLMLMM", probeCommands.getKey());
        Assertions.assertEquals(probe, probeCommands.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ABC", "LM LM"})
    void should_not_create_probe_with_invalid_command(String commands) {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                new ProbeCommands<>(commands, new Probe(null, null, null)));
        Assertions.assertEquals("Invalid commands", exception.getMessage());
    }

    @Test
    void should_not_create_probe_with_invalid_probe() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                new ProbeCommands<>("LMR", null));
        Assertions.assertEquals("Invalid probe", exception.getMessage());
    }

    @Test
    void should_not_update_probe_with_incorret_value() {
        ProbeCommands<String, Probe> probeCommands = new ProbeCommands<>("LMR", new Probe(null, null, null));
        ValidationException exception = assertThrows(ValidationException.class, () ->
                probeCommands.setValue(null));
        Assertions.assertEquals("Invalid probe", exception.getMessage());
    }

}
