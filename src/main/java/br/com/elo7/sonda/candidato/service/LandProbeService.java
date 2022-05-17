package br.com.elo7.sonda.candidato.service;

import java.util.List;
import java.util.stream.Collectors;

import br.com.elo7.sonda.candidato.model.ProbeCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.persistence.Planets;
import br.com.elo7.sonda.candidato.persistence.Probes;

@Service
public class LandProbeService {
    @Autowired
    private Planets planetsRepository;
    @Autowired
    private Probes probesRepository;

    public List<Probe> probe(Planet planet, List<ProbeCommands<String, Probe>> probes) {
        planetsRepository.save(planet);
        List<Probe> executedMoves = probes.stream().map(register -> {
            Probe probe = register.getValue();
            probe.executeCommands(register.getKey().toCharArray());
            return probe;
        }).collect(Collectors.toList());
        executedMoves.forEach(probe -> probesRepository.save(probe));
        return executedMoves;
    }

}
