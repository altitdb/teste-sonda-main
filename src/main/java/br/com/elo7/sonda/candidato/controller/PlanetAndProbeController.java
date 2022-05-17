package br.com.elo7.sonda.candidato.controller;

import java.util.List;
import java.util.stream.Collectors;

import br.com.elo7.sonda.candidato.dto.PlanetResponseDTO;
import br.com.elo7.sonda.candidato.dto.ProbeResponseDTO;
import br.com.elo7.sonda.candidato.dto.ProbesResponseDTO;
import br.com.elo7.sonda.candidato.model.Planet;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.elo7.sonda.candidato.dto.ProbesRequestDTO;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.service.ProbeService;
import org.springframework.web.bind.annotation.ResponseBody;

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
