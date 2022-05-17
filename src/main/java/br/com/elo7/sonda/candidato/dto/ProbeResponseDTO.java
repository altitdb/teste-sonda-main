package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.model.Direction;

import java.util.UUID;

public class ProbeResponseDTO {
	private UUID id;
	private int x;
	private int y;
	private Direction direction;
	private PlanetResponseDTO planet;

	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
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
	public PlanetResponseDTO getPlanet() {
		return planet;
	}
	public void setPlanet(PlanetResponseDTO planet) {
		this.planet = planet;
	}
}
