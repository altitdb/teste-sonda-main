package br.com.elo7.sonda.candidato.dto;

import io.swagger.annotations.ApiModel;

import java.util.UUID;

@ApiModel("planet")
public class PlanetResponseDTO {
	private UUID id;
	private int width;
	private int height;

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PlanetResponseDTO) {
			return ((PlanetResponseDTO) obj).id == this.id;
		}
		return false;
	}

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
}
