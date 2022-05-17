package br.com.elo7.sonda.candidato.controller;

import br.com.elo7.sonda.candidato.dto.ProbeResponseDTO;
import br.com.elo7.sonda.candidato.dto.ProbesRequestDTO;
import br.com.elo7.sonda.candidato.dto.ProbesResponseDTO;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.service.ProbeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PlanetAndProbeController {
    @Autowired
    private ProbeService probeService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/planet-with-probes")
    public @ResponseBody ProbesResponseDTO register(@RequestBody ProbesRequestDTO probesRequestDTO) {
        List<Probe> probes = probeService.landProbes(probesRequestDTO);
        List<ProbeResponseDTO> probesResponseDTO = probes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ProbesResponseDTO(probesResponseDTO);
    }

    private ProbeResponseDTO convertToDto(Probe probe) {
        return this.modelMapper.map(probe, ProbeResponseDTO.class);
    }
}
