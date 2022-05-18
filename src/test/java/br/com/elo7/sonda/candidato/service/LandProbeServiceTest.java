package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.model.Direction;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbeCommands;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class LandProbeServiceTest {
	
	@Autowired
	private LandProbeService subject;

	@Test
	void should_move_land_probe() {
		Planet planet = new Planet(10, 10);

		List<ProbeCommands<String, Probe>> probes = new ArrayList<>();
		Probe probe01 = new Probe();
		probe01.setPlanet(planet);
		probe01.setX(1);
		probe01.setY(2);
		probe01.setDirection(Direction.NORTH);
		probes.add(new ProbeCommands<>("LMLMLMLMM", probe01));
		Probe probe02 = new Probe();
		probe02.setPlanet(planet);
		probe02.setX(3);
		probe02.setY(3);
		probe02.setDirection(Direction.EAST);
		probes.add(new ProbeCommands<>("MMRMMRMRRM", probe02));

		List<Probe> moves = subject.probe(probes);

		assertEquals(1, moves.get(0).getX());
		assertEquals(3, moves.get(0).getY());
		assertEquals(Direction.NORTH, moves.get(0).getDirection());
		assertEquals(10, moves.get(0).getPlanet().getWidth());
		assertEquals(10, moves.get(0).getPlanet().getHeight());

		assertEquals(5, moves.get(1).getX());
		assertEquals(1, moves.get(1).getY());
		assertEquals(Direction.EAST, moves.get(1).getDirection());
		assertEquals(10, moves.get(1).getPlanet().getWidth());
		assertEquals(10, moves.get(1).getPlanet().getHeight());
	}

}
