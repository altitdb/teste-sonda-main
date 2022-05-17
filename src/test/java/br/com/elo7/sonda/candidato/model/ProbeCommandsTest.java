package br.com.elo7.sonda.candidato.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ProbeCommandsTest {

    @Test
    void should_create_new_probe_with_commands() {
        Probe probe = new Probe();
        ProbeCommands<String, Probe> probeCommands = new ProbeCommands<>("LMLMLMLMM", probe);
        Assertions.assertEquals("LMLMLMLMM", probeCommands.getKey());
        Assertions.assertEquals(probe, probeCommands.getValue());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "ABC", "LM LM"})
    void should_not_create_probe_with_invalid_command(String commands) {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () ->
                new ProbeCommands<>(commands, new Probe()));
        Assertions.assertEquals("Invalid commands", exception.getMessage());
    }

    @Test
    void should_not_create_probe_with_invalid_probe() {
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () ->
                new ProbeCommands<>("LMR", null));
        Assertions.assertEquals("Invalid probe", exception.getMessage());
    }

    @Test
    void should_not_update_probe_with_incorret_value() {
        ProbeCommands<String, Probe> probeCommands = new ProbeCommands<>("LMR", new Probe());
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () ->
                probeCommands.setValue(null));
        Assertions.assertEquals("Invalid probe", exception.getMessage());
    }

}
