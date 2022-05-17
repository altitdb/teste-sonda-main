package br.com.elo7.sonda.candidato.model;

import java.util.UUID;

public class Planet {
	private UUID id;
	private Integer width;
	private Integer height;

	public Planet(Integer height, Integer width) {
		this.id = UUID.randomUUID();
		this.width = width;
		this.height = height;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Planet) {
			return ((Planet) obj).id == this.id;
		}
		return false;
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
}
