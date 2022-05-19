package br.com.elo7.sonda.candidato.model;

import java.util.Objects;
import java.util.UUID;

public class Planet {
    private UUID id;
    private Integer width;
    private Integer height;

    public Planet(Integer height, Integer width) {
        this.id = UUID.randomUUID();
        this.width = width;
        this.height = height;
    }

    public UUID getId() {
        return id;
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Planet planet = (Planet) o;
        return Objects.equals(id, planet.id) && Objects.equals(width, planet.width) && Objects.equals(height, planet.height);
    }
}
