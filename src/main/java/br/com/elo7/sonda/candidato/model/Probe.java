package br.com.elo7.sonda.candidato.model;

import br.com.elo7.sonda.candidato.dto.ProbeRequestDTO;

public class Probe {
    private int id;
    private int x;
    private int y;
    private char direction;
    private Planet planet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public void executeCommands(char[] commands) {
        for (char command : commands) {
            this.executeCommand(command);
        }
    }

    public void executeCommand(char command) {
        switch (command) {
            case Command.R:
                this.turnProbeRight();
                break;
            case Command.L:
                this.turnProbeLeft();
                break;
            case Command.M:
                this.moveProbeForward();
                break;
        }
    }

    private void moveProbeForward() {
        int newX = this.getX();
        int newY = this.getY();
        switch (this.getDirection()) {
            case Direction.N:
                newY++;
                break;
            case Direction.W:
                newX--;
                break;
            case Direction.S:
                newY--;
                break;
            case Direction.E:
                newX++;
                break;
        }
        this.x = newX;
        this.y = newY;
    }

    private void turnProbeLeft() {
        char newDirection = Direction.N;
        switch (this.getDirection()) {
            case Direction.N:
                newDirection = Direction.W;
                break;
            case Direction.W:
                newDirection = Direction.S;
                break;
            case Direction.S:
                newDirection = Direction.E;
                break;
            case Direction.E:
                newDirection = Direction.N;
                break;
        }
        this.direction = newDirection;
    }

    private void turnProbeRight() {
        char newDirection = Direction.N;
        switch (this.getDirection()) {
            case Direction.N:
                newDirection = Direction.E;
                break;
            case Direction.E:
                newDirection = Direction.S;
                break;
            case Direction.S:
                newDirection = Direction.W;
                break;
            case Direction.W:
                newDirection = Direction.N;
                break;
        }
        this.direction = newDirection;
    }
}
