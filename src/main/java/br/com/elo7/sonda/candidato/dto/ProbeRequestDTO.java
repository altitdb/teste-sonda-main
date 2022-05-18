package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.constants.Direction;

public class ProbeRequestDTO {
    private CoordinateDTO coordinate;
    private Direction direction;
    private String commands;

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

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }
}
