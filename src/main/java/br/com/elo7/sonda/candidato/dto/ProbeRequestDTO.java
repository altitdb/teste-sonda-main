package br.com.elo7.sonda.candidato.dto;

import br.com.elo7.sonda.candidato.constants.Direction;
import io.swagger.annotations.ApiModel;

@ApiModel("probe")
public record ProbeRequestDTO (CoordinateDTO coordinate, Direction direction, String commands){
}
