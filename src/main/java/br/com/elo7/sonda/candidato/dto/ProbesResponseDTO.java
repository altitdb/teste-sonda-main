package br.com.elo7.sonda.candidato.dto;

import java.util.List;

public class ProbesResponseDTO {
    private final List<ProbeResponseDTO> probes;
    public ProbesResponseDTO(List<ProbeResponseDTO> probes) {
        this.probes = probes;
    }

    public List<ProbeResponseDTO> getProbes() {
        return this.probes;
    }
}
