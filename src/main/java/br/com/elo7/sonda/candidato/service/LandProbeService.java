package br.com.elo7.sonda.candidato.service;

import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbeCommands;
import br.com.elo7.sonda.candidato.repository.ProbesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LandProbeService {

    private static final Logger LOG = LoggerFactory.getLogger(LandProbeService.class);
    @Autowired
    private ProbesRepository probesRepository;

    public List<Probe> probe(List<ProbeCommands> probes) {
        LOG.info("Processing amount probes: {}", probes.size());
        return probes.stream().map(register -> {
            Probe probe = register.getValue();
            LOG.info("Processing probe: {}", probe.getId());
            probe.executeCommands(register.getKey().toCharArray());
            LOG.info("Saving probe: {}", probe.getId());
            probesRepository.save(probe);
            return probe;
        }).toList();

    }

}
