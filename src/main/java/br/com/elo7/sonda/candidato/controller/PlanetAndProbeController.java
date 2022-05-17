package br.com.elo7.sonda.candidato.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.elo7.sonda.candidato.dto.ProbesRequestDTO;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.service.ProbeService;

@Controller
public class PlanetAndProbeController {
	@Autowired
	private ProbeService probeService;

	@PostMapping("/planet-with-probes")
    public ResponseEntity<List<Probe>> register(@RequestBody ProbesRequestDTO probesRequestDTO) {
		return ResponseEntity.ok(probeService.landProbes(probesRequestDTO));
    }
}
