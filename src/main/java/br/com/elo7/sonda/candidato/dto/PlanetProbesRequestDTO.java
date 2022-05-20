package br.com.elo7.sonda.candidato.dto;

import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("planetProbes")
public record PlanetProbesRequestDTO(Integer height, Integer width, List<ProbeRequestDTO> probes) {
}