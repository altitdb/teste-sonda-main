package br.com.elo7.sonda.candidato.model;

public class Probe {
    private int id;
    private int x;
    private int y;
    private Direction direction;
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

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
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
        int newX = this.getX();
        int newY = this.getY();
        switch (this.getDirection()) {
            case NORTH:
                newY++;
                break;
            case WEST:
                newX--;
                break;
            case SOUTH:
                newY--;
                break;
            case EAST:
                newX++;
                break;
        }
        this.x = newX;
        this.y = newY;
    }

    private void turnProbeLeft() {
        Direction newDirection = Direction.NORTH;
        switch (this.getDirection()) {
            case NORTH:
                newDirection = Direction.WEST;
                break;
            case WEST:
                newDirection = Direction.SOUTH;
                break;
            case SOUTH:
                newDirection = Direction.EAST;
                break;
            case EAST:
                newDirection = Direction.NORTH;
                break;
        }
        this.direction = newDirection;
    }

    private void turnProbeRight() {
        Direction newDirection = Direction.NORTH;
        switch (this.getDirection()) {
            case NORTH:
                newDirection = Direction.EAST;
                break;
            case EAST:
                newDirection = Direction.SOUTH;
                break;
            case SOUTH:
                newDirection = Direction.WEST;
                break;
            case WEST:
                newDirection = Direction.NORTH;
                break;
        }
        this.direction = newDirection;
    }
}
