package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.constants.Command;
import br.com.elo7.sonda.candidato.constants.Direction;

import java.util.UUID;

public class Probe {
    private UUID id;

    private Planet planet;
    private Coordinate coordinate;
    private Direction direction;

    public Probe(Planet planet, Coordinate coordinate, Direction direction) {
        this.id = UUID.randomUUID();
        this.planet = planet;
        this.coordinate = coordinate;
        this.direction = direction;
    }

    public UUID getId() {
        return id;
    }

    public Planet getPlanet() {
        return planet;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public Direction getDirection() {
        return direction;
    }

    public void executeCommands(char[] commands) {
        for (char command : commands) {
            this.executeCommand(Command.valueOf(String.valueOf(command)));
        }
    }

    public void executeCommand(Command command) {
        switch (command) {
            case R:
                this.turnProbeRight();
                break;
            case L:
                this.turnProbeLeft();
                break;
            case M:
                this.moveProbeForward();
                break;
        }
    }

    private void moveProbeForward() {
        switch (this.direction) {
            case NORTH:
                this.coordinate.addPositionY();
                break;
            case WEST:
                this.coordinate.removePositionX();
                break;
            case SOUTH:
                this.coordinate.removePositionY();
                break;
            case EAST:
                this.coordinate.addPositionX();
                break;
        }
    }

    private void turnProbeLeft() {
        switch (this.direction) {
            case NORTH:
                this.direction = Direction.WEST;
                break;
            case WEST:
                this.direction = Direction.SOUTH;
                break;
            case SOUTH:
                this.direction = Direction.EAST;
                break;
            case EAST:
                this.direction = Direction.NORTH;
                break;
        }
    }

    private void turnProbeRight() {
        switch (this.direction) {
            case NORTH:
                this.direction = Direction.EAST;
                break;
            case EAST:
                this.direction = Direction.SOUTH;
                break;
            case SOUTH:
                this.direction = Direction.WEST;
                break;
            case WEST:
                this.direction = Direction.NORTH;
                break;
        }
    }
}
