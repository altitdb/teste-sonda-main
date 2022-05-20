package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.constants.Direction;
import io.swagger.annotations.ApiModel;

import java.util.UUID;

@ApiModel("probe")
public record ProbeResponseDTO(UUID id, PlanetResponseDTO planet, CoordinateDTO coordinate, Direction direction) {
}