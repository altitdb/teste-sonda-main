package br.com.elo7.sonda.candidato.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.sonda.candidato.dto.ProbesRequestDTO;
import br.com.elo7.sonda.candidato.dto.ProbeRequestDTO;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.persistence.Planets;
import br.com.elo7.sonda.candidato.persistence.Probes;

@Service
public class ProbeService {
	@Autowired
	private Planets planets;
	@Autowired
	private Probes probes;
	
	public List<Probe> landProbes(ProbesRequestDTO probesRequestDTO) {
		Planet planet = concertPlanet(probesRequestDTO);
		planets.save(planet);
		
		List<Probe> convertedProbes = convertAndMoveProbes(probesRequestDTO, planet);
		convertedProbes.forEach(probe -> probes.save(probe));
		
		return convertedProbes;
	}

	private List<Probe> convertAndMoveProbes(ProbesRequestDTO probesRequestDTO, Planet planet) {
		return probesRequestDTO.getProbes()
						.stream().map(probeDto -> {
							Probe probe = convertProbe(probeDto, planet);
							probe.executeCommands(probeDto.getCommands().toCharArray());
							return probe;
						}).collect(Collectors.toList());
	}

	private Probe convertProbe(ProbeRequestDTO probeRequestDTO, Planet planet) {
		Probe probe = new Probe();
		probe.setPlanet(planet);
		probe.setX(probeRequestDTO.getX());
		probe.setY(probeRequestDTO.getY());
		probe.setDirection(probeRequestDTO.getDirection());
		return probe;
	}
	
	private Planet concertPlanet(ProbesRequestDTO probesRequestDTO) {
		Planet planet = new Planet();
		planet.setHeight(probesRequestDTO.getHeight());
		planet.setWidth(probesRequestDTO.getWidth());
		return planet;
	}
}
