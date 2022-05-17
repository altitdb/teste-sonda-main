package br.com.elo7.sonda.candidato.controller;

import br.com.elo7.sonda.candidato.dto.ProbeRequestDTO;
import br.com.elo7.sonda.candidato.dto.ProbeResponseDTO;
import br.com.elo7.sonda.candidato.dto.ProbesRequestDTO;
import br.com.elo7.sonda.candidato.dto.ProbesResponseDTO;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbeCommands;
import br.com.elo7.sonda.candidato.service.LandProbeService;
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
    private LandProbeService landProbeService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/planet-with-probes")
    public @ResponseBody ProbesResponseDTO register(@RequestBody ProbesRequestDTO probesRequestDTO) {
        Planet planet = new Planet(probesRequestDTO.getHeight(), probesRequestDTO.getWidth());

        List<ProbeCommands<String, Probe>> requestProbes = probesRequestDTO.getProbes().stream()
                .map(probeRequestDTO ->
                        new ProbeCommands<>(probeRequestDTO.getCommands(), convertToModel(planet, probeRequestDTO)))
                .collect(Collectors.toList());
        List<Probe> probes = landProbeService.probe(planet, requestProbes);
        List<ProbeResponseDTO> probesResponseDTO = probes.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ProbesResponseDTO(probesResponseDTO);
    }

    private ProbeResponseDTO convertToDto(Probe probe) {
        return this.modelMapper.map(probe, ProbeResponseDTO.class);
    }

    private Probe convertToModel(Planet planet, ProbeRequestDTO probeRequestDTO) {
        Probe probe = new Probe();
        probe.setDirection(probeRequestDTO.getDirection());
        probe.setX(probeRequestDTO.getX());
        probe.setY(probeRequestDTO.getY());
        probe.setPlanet(planet);
        return probe;
    }
}
