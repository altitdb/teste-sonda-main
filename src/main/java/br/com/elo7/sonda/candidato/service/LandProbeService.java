package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbeCommands;
import br.com.elo7.sonda.candidato.repository.ProbesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandProbeService {
    @Autowired
    private ProbesRepository probesRepository;

    public List<Probe> probe(List<ProbeCommands> probes) {
        return probes.stream().map(register -> {
            Probe probe = register.getValue();
            probe.executeCommands(register.getKey().toCharArray());
            probesRepository.save(probe);
            return probe;
        }).toList();

    }

}
