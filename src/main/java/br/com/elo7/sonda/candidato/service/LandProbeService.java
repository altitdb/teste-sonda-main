package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbeCommands;
import br.com.elo7.sonda.candidato.persistence.Planets;
import br.com.elo7.sonda.candidato.persistence.Probes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandProbeService {
    @Autowired
    private Planets planetsRepository;
    @Autowired
    private Probes probesRepository;

    public List<Probe> probe(List<ProbeCommands<String, Probe>> probes) {
        List<Probe> executedMoves = probes.stream().map(register -> {
            Probe probe = register.getValue();
            probe.executeCommands(register.getKey().toCharArray());
            planetsRepository.save(probe.getPlanet());
            return probe;
        }).toList();
        executedMoves.forEach(probe -> probesRepository.save(probe));
        return executedMoves;
    }

}
