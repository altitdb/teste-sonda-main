package br.com.elo7.sonda.candidato.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.annotations.VisibleForTesting;
import br.com.elo7.sonda.candidato.dto.ProbesRequestDTO;
import br.com.elo7.sonda.candidato.dto.ProbeRequestDTO;
import br.com.elo7.sonda.candidato.model.Command;
import br.com.elo7.sonda.candidato.model.Direction;
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
	
	@VisibleForTesting
	void applyCommandToProbe(Probe probe, char command) {
		switch (command) {
			case Command.R:
				turnProbeRight(probe);
				break;
			case Command.L:
				turnProbeLeft(probe);
				break;
			case Command.M:
				moveProbeForward(probe);
				break;
		}
	}

	private void moveProbeForward(Probe probe) {
		int newX = probe.getX();
		int newY = probe.getY();
		switch (probe.getDirection()) {
			case Direction.N:
				newY++;
				break;
			case Direction.W:
				newX--;
				break;
			case Direction.S:
				newY--;
				break;
			case Direction.E:
				newX++;
				break;
		}
		probe.setX(newX);
		probe.setY(newY);
	}

	private void turnProbeLeft(Probe probe) {
		char newDirection = Direction.N;
		switch (probe.getDirection()) {
			case Direction.N:
				newDirection = Direction.W;
				break;
			case Direction.W:
				newDirection = Direction.S;
				break;
			case Direction.S:
				newDirection = Direction.E;
				break;
			case Direction.E:
				newDirection = Direction.N;
				break;
		}
		probe.setDirection(newDirection);
	}
	
	private void turnProbeRight(Probe probe) {
		char newDirection = Direction.N;
		switch (probe.getDirection()) {
			case Direction.N:
				newDirection = Direction.E;
				break;
			case Direction.E:
				newDirection = Direction.S;
				break;
			case Direction.S:
				newDirection = Direction.W;
				break;
			case Direction.W:
				newDirection = Direction.N;
				break;
		}
		probe.setDirection(newDirection);
		
	}
	
	private List<Probe> convertAndMoveProbes(ProbesRequestDTO probesRequestDTO, Planet planet) {
		return probesRequestDTO.getProbes()
						.stream().map(probeDto -> {
							Probe probe = convertProbe(probeDto, planet);
							moveProbeWithAllCommands(probe, probeDto);
							return probe;
						}).collect(Collectors.toList());
	}

	private void moveProbeWithAllCommands(Probe probe, ProbeRequestDTO probeRequestDTO) {
		for (char command : probeRequestDTO.getCommands().toCharArray()) {
			applyCommandToProbe(probe, command);
		}
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
