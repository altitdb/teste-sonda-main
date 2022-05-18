package br.com.elo7.sonda.candidato.dto;

import io.swagger.annotations.ApiModel;

import java.util.List;

@ApiModel("probes")
public record ProbesResponseDTO(List<ProbeResponseDTO> probes) {
}
