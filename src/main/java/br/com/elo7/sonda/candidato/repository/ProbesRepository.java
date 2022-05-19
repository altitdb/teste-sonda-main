package br.com.elo7.sonda.candidato.repository;

import java.util.UUID;

import br.com.elo7.sonda.candidato.model.Probe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProbesRepository extends JpaRepository<Probe, UUID> {

}
