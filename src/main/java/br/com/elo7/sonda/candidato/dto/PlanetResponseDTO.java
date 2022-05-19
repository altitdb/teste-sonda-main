package br.com.elo7.sonda.candidato.dto;

import io.swagger.annotations.ApiModel;

import java.util.Objects;
import java.util.UUID;

@ApiModel("planet")
public class PlanetResponseDTO {
    private UUID id;
    private int width;
    private int height;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlanetResponseDTO that = (PlanetResponseDTO) o;
        return width == that.width && height == that.height && id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, width, height);
    }
}
