package br.com.elo7.sonda.candidato.controller;

import br.com.elo7.sonda.candidato.dto.*;
import br.com.elo7.sonda.candidato.model.Coordinate;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.model.ProbeCommands;
import br.com.elo7.sonda.candidato.service.LandProbeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class PlanetAndProbeController {
    @Autowired
    private LandProbeService landProbeService;

    @ApiOperation(value = "Realiza o controle de sondas em planetas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Retorna os comandos executados"),
            @ApiResponse(code = 404, message = "Dados inválidos informados na solicitação"),
    })
    @PostMapping(value = "/planet-with-probes", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public @ResponseBody ProbesResponseDTO register(@ApiParam("planetProbes") @RequestBody PlanetProbesRequestDTO planetProbesRequest) {
        Planet planet = new Planet(planetProbesRequest.height(), planetProbesRequest.width());

        List<ProbeCommands> requestProbes = planetProbesRequest.probes().stream()
                .map(probeRequest ->
                        new ProbeCommands(probeRequest.commands(), convertToModel(planet, probeRequest)))
                .toList();
        List<Probe> probes = landProbeService.probe(requestProbes);
        List<ProbeResponseDTO> probesResponse = probes.stream()
                .map(this::convertToDto)
                .toList();
        return new ProbesResponseDTO(probesResponse);
    }

    private ProbeResponseDTO convertToDto(Probe probe) {
        Planet planet = probe.getPlanet();
        PlanetResponseDTO planetResponse = new PlanetResponseDTO(planet.getId(), planet.getHeight(), planet.getWidth());
        Coordinate coordinate = probe.getCoordinate();
        CoordinateDTO coordinateResponse = new CoordinateDTO(coordinate.getX(), coordinate.getY());
        return new ProbeResponseDTO(probe.getId(), planetResponse, coordinateResponse, probe.getDirection());
    }

    private Probe convertToModel(Planet planet, ProbeRequestDTO probeRequestDTO) {
        CoordinateDTO coordinateDTO = probeRequestDTO.coordinate();
        Coordinate coordinate = new Coordinate(coordinateDTO.x(), coordinateDTO.x());
        return new Probe(planet, coordinate, probeRequestDTO.direction());
    }
}
