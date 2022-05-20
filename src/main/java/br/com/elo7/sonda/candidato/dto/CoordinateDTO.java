package br.com.elo7.sonda.candidato.dto;

import io.swagger.annotations.ApiModel;

@ApiModel("coordinate")

public record CoordinateDTO(Integer x, Integer y) {
}