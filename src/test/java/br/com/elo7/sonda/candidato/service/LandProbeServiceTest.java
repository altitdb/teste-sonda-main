package br.com.elo7.sonda.candidato.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.elo7.sonda.candidato.model.Direction;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbeCommands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class LandProbeServiceTest {
	
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

		List<Probe> moves = subject.probe(planet, probes);

		Assertions.assertEquals(moves.get(0).getX(), 1);
		Assertions.assertEquals(moves.get(0).getY(), 3);
		Assertions.assertEquals(moves.get(0).getDirection(), Direction.NORTH);
		Assertions.assertEquals(moves.get(0).getPlanet().getWidth(), 10);
		Assertions.assertEquals(moves.get(0).getPlanet().getHeight(), 10);

		Assertions.assertEquals(moves.get(1).getX(), 5);
		Assertions.assertEquals(moves.get(1).getY(), 1);
		Assertions.assertEquals(moves.get(1).getDirection(), Direction.EAST);
		Assertions.assertEquals(moves.get(1).getPlanet().getWidth(), 10);
		Assertions.assertEquals(moves.get(1).getPlanet().getHeight(), 10);
	}

}
