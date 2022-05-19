package br.com.elo7.sonda.candidato.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Planet {

    @Id
    private UUID id;
    @Column(nullable = false)
    private Integer width;
    @Column(nullable = false)
    private Integer height;

    protected Planet() {}

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
