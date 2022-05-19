package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.constants.Direction;
import br.com.elo7.sonda.candidato.model.Coordinate;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbeCommands;
import br.com.elo7.sonda.candidato.repository.ProbesRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LandProbeServiceTest {
	
	@Autowired
	private LandProbeService subject;

	@MockBean
	private ProbesRepository probesRepository;

	@Test
	void should_move_land_probe() {
		Planet planet = new Planet(10, 10);

		List<ProbeCommands> probes = new ArrayList<>();
		Probe probe01 = new Probe(planet, new Coordinate(1, 2), Direction.NORTH);
		probes.add(new ProbeCommands("LMLMLMLMM", probe01));
		Probe probe02 = new Probe(planet, new Coordinate(3, 3), Direction.EAST);
		probes.add(new ProbeCommands("MMRMMRMRRM", probe02));

		List<Probe> moves = subject.probe(probes);

		assertEquals(new Coordinate(1, 3), moves.get(0).getCoordinate());
		assertEquals(Direction.NORTH, moves.get(0).getDirection());
		assertEquals(10, moves.get(0).getPlanet().getWidth());
		assertEquals(10, moves.get(0).getPlanet().getHeight());

		assertEquals(new Coordinate(5, 1), moves.get(1).getCoordinate());
		assertEquals(Direction.EAST, moves.get(1).getDirection());
		assertEquals(10, moves.get(1).getPlanet().getWidth());
		assertEquals(10, moves.get(1).getPlanet().getHeight());
	}

}
