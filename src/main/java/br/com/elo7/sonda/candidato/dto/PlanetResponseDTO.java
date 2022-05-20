package br.com.elo7.sonda.candidato.dto;

import io.swagger.annotations.ApiModel;

import java.util.UUID;

@ApiModel("planet")
public record PlanetResponseDTO(UUID id, Integer height, Integer width) {

}
