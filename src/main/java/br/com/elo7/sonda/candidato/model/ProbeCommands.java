package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.exception.ValidationException;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class ProbeCommands<String, Probe> implements Map.Entry<String, Probe> {

    private final String key;
    private Probe value;

    public ProbeCommands(String key, Probe value) {
        validateKey(key);
        validateValue(value);
        this.key = key;
        this.value = value;
    }

    private void validateKey(String key) {
        if (StringUtils.isBlank((CharSequence) key) || !StringUtils.containsOnly((CharSequence) key, "MLR")) {
            throw new ValidationException("Invalid commands");
        }
    }

    private void validateValue(Probe value) {
        if (value == null) {
            throw new ValidationException("Invalid probe");
        }
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public Probe getValue() {
        return this.value;
    }

    @Override
    public Probe setValue(Probe value) {
        validateValue(value);
        Probe old = this.value;
        this.value = value;
        return old;
    }
}
