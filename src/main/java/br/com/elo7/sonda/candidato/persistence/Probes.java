package br.com.elo7.sonda.candidato.persistence;

import java.util.Optional;
import java.util.UUID;

import br.com.elo7.sonda.candidato.model.Probe;

public interface Probes {

	void save(Probe probe);

	Optional<Probe> findById(UUID id);

}
