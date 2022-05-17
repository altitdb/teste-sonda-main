package br.com.elo7.sonda.candidato.persistence;

import java.util.*;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;

@Component
class InMemoryDatabase {
	private static Map<Planet, List<Probe>> probesPerPlanet = new HashMap<>();
	
	@Repository
	public class PlanetDAO implements Planets {
		public void save(Planet planet) {
			probesPerPlanet.put(planet, Lists.newArrayList());
		}

		public Optional<Planet> findById(UUID id) {
			return probesPerPlanet.keySet()
					.stream()
					.filter(planet -> planet.getId() == id)
					.findFirst();
		}
	}
	
	@Repository	
	public class ProbeDAO implements Probes {
		@Override
		public void save(Probe probe) {
			List<Probe> probes = probesPerPlanet.get(probe.getPlanet());
			probe.setId(UUID.randomUUID());
			probes.add(probe);
		}

		@Override
		public Optional<Probe> findById(UUID id) {
			return probesPerPlanet.entrySet().stream().flatMap(
						entry -> entry.getValue()
										.stream()
										.filter(probe -> probe.getId() == id)
					).findFirst();
		}
	}
}
