package br.com.elo7.sonda.candidato.controller;

import br.com.elo7.sonda.candidato.constants.Direction;
import br.com.elo7.sonda.candidato.dto.CoordinateDTO;
import br.com.elo7.sonda.candidato.dto.ProbeRequestDTO;
import br.com.elo7.sonda.candidato.dto.ProbesRequestDTO;
import br.com.elo7.sonda.candidato.model.Coordinate;
import br.com.elo7.sonda.candidato.model.Planet;
import br.com.elo7.sonda.candidato.model.Probe;
import br.com.elo7.sonda.candidato.service.LandProbeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@WebMvcTest(PlanetAndProbeController.class)
class PlanetAndProbeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LandProbeService landProbeService;

    @Test
    void should_return_bad_request_when_not_informe_probes() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("/planet-with-probes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("")));
    }

    @Test
    void should_return_ok_when_informe_probes() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        ProbesRequestDTO probesRequestDTO = new ProbesRequestDTO();
        probesRequestDTO.setHeight(10);
        probesRequestDTO.setWidth(10);
        ProbeRequestDTO probeRequestDTO = new ProbeRequestDTO();
        probeRequestDTO.setCommands("LMLMLMLMM");
        CoordinateDTO coordinateDTO = new CoordinateDTO();
        coordinateDTO.setX(1);
        coordinateDTO.setY(2);
        probeRequestDTO.setCoordinate(coordinateDTO);
        probeRequestDTO.setDirection(Direction.NORTH);
        probesRequestDTO.setProbes(new ArrayList<>(Collections.singleton(probeRequestDTO)));

        List<Probe> probeCommandsList2 = new ArrayList<>();
        Probe probe2 = new Probe(new Planet(10, 10), new Coordinate(1, 3), Direction.NORTH);
        probeCommandsList2.add(probe2);
        Mockito.when(landProbeService.probe(Mockito.anyList())).thenReturn(probeCommandsList2);

        String requestJson = ow.writeValueAsString(probesRequestDTO);
        this.mockMvc.perform(MockMvcRequestBuilders.post("/planet-with-probes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.probes[0].coordinate.x").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.probes[0].coordinate.y").value(3))
                .andExpect(MockMvcResultMatchers.jsonPath("$.probes[0].direction").value("NORTH"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.probes[0].planet.width").value(10))
                .andExpect(MockMvcResultMatchers.jsonPath("$.probes[0].planet.height").value(10));
    }
}