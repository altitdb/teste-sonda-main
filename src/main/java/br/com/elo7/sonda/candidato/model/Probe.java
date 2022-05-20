package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.constants.Command;
import br.com.elo7.sonda.candidato.constants.Direction;
import br.com.elo7.sonda.candidato.exception.ValidationException;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Probe {

    @Id
    private UUID id;
    @ManyToOne(optional = false, cascade = CascadeType.MERGE)
    private Planet planet;
    @Embedded
    private Coordinate coordinate;
    @Column(nullable = false)
    private Direction direction;

    protected Probe() {
    }

    public Probe(Planet planet, Coordinate coordinate, Direction direction) {
        validateRequired(planet, coordinate, direction);
        validateCoordinatesInPlanet(planet, coordinate);
        this.id = UUID.randomUUID();
        this.planet = planet;
        this.coordinate = coordinate;
        this.direction = direction;
    }

    private void validateCoordinatesInPlanet(Planet planet, Coordinate coordinate) {
        if (planet.getHeight() < coordinate.getX()) {
            throw new ValidationException(String.format("Coordinate x '%s' cannot be greater than height '%s'", coordinate.getX(), planet.getHeight()));
        }
        if (planet.getWidth() < coordinate.getY()) {
            throw new ValidationException(String.format("Coordinate y '%s' cannot be greater than width '%s'", coordinate.getY(), planet.getWidth()));
        }
    }

    private void validateRequired(Planet planet, Coordinate coordinate, Direction direction) {
        if (planet == null) {
            throw  new ValidationException("Planet is required");
        }
        if (coordinate == null) {
            throw  new ValidationException("Coordinate is required");
        }
        if (direction == null) {
            throw  new ValidationException("Direction is required");
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Probe probe = (Probe) o;
        return Objects.equals(id, probe.id) && Objects.equals(planet, probe.planet) && Objects.equals(coordinate, probe.coordinate) && direction == probe.direction;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, planet, coordinate, direction);
    }
}
