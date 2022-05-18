package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.constants.Direction;

import java.util.UUID;

public class ProbeResponseDTO {
    private UUID id;
    private CoordinateDTO coordinate;
    private Direction direction;
    private PlanetResponseDTO planet;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public CoordinateDTO getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(CoordinateDTO coordinate) {
        this.coordinate = coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public PlanetResponseDTO getPlanet() {
        return planet;
    }

    public void setPlanet(PlanetResponseDTO planet) {
        this.planet = planet;
    }
}
