package br.com.elo7.sonda.candidato.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class Coordinate {
    @Column(nullable = false)
    private Integer x;
    @Column(nullable = false)
    private Integer y;

    protected Coordinate() {
    }

    public Coordinate(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public Integer getX() {
        return this.x;
    }

    public Integer getY() {
        return this.y;
    }

    public void addPositionY() {
        this.y++;
    }

    public void removePositionY() {
        this.y--;
    }

    public void addPositionX() {
        this.x++;
    }

    public void removePositionX() {
        this.x--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return Objects.equals(x, that.x) && Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
