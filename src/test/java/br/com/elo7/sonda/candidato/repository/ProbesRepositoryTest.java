package br.com.elo7.sonda.candidato.repository;

import br.com.elo7.sonda.candidato.constants.Direction;
import br.com.elo7.sonda.candidato.model.Coordinate;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class ProbesRepositoryTest {

    @Autowired
    private ProbesRepository probesRepository;

    @Test
    void should_save_probe() {
        Probe expected = new Probe(new Planet(10, 10), new Coordinate(3, 3), Direction.NORTH);
        Probe saved = probesRepository.save(expected);
        Optional<Probe> probeInDatabase = probesRepository.findById(saved.getId());
        assertEquals(expected, probeInDatabase.get());
    }

}