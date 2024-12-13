package org.example.controller;

import org.example.dto.IncidentDto;
import org.example.exception.IncidentExceptionHandler;
import org.example.service.IncidentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class IncidentControllerTest {

    @Mock
    private IncidentService service;

    @InjectMocks
    private IncidentController controller;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).setControllerAdvice(new IncidentExceptionHandler()).build();
    }

    @Test
    public void testCreateIncident() throws Exception {
        IncidentDto incidentDto = new IncidentDto();
        incidentDto.setTitle("New Incident");
        incidentDto.setBody("This is a new incident");

        when(service.createIncident(any(IncidentDto.class))).thenReturn(incidentDto);

        mockMvc.perform(post("/api/incidents")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"title\":\"New Incident\",\"body\":\"This is a new incident\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("New Incident"))
                .andExpect(jsonPath("$.body").value("This is a new incident"));

        verify(service, times(1)).createIncident(any(IncidentDto.class));
    }

    @Test
    public void testDeleteIncident() throws Exception {
        mockMvc.perform(delete("/api/incidents/123"))
                .andExpect(status().isNoContent());

        verify(service, times(1)).deleteIncidentById("123");
    }

    @Test
    public void testUpdateIncident() throws Exception {
        IncidentDto incidentDto = new IncidentDto();
        incidentDto.setId("123");
        incidentDto.setTitle("Updated Incident");
        incidentDto.setBody("This is an updated incident");

        when(service.updateIncident("123", any(IncidentDto.class))).thenReturn(incidentDto);

        mockMvc.perform(put("/api/incidents/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"title\":\"Updated Incident\",\"body\":\"This is an updated incident\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Updated Incident"))
                .andExpect(jsonPath("$.body").value("This is an updated incident"));

        verify(service, times(1)).updateIncident("123", any(IncidentDto.class));
    }

    @Test
    public void testGetAllIncidents() throws Exception {
        IncidentDto incident1 = new IncidentDto();
        incident1.setId("1");
        incident1.setTitle("Incident 1");
        incident1.setBody("This is incident 1");

        IncidentDto incident2 = new IncidentDto();
        incident2.setId("2");
        incident2.setTitle("Incident 2");
        incident2.setBody("This is incident 2");

        List<IncidentDto> incidents = Arrays.asList(incident1, incident2);

        when(service.getAllIncidents()).thenReturn(incidents);

        mockMvc.perform(get("/api/incidents"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Incident 1"))
                .andExpect(jsonPath("$[0].body").value("This is incident 1"))
                .andExpect(jsonPath("$[1].title").value("Incident 2"))
                .andExpect(jsonPath("$[1].body").value("This is incident 2"));

        verify(service, times(1)).getAllIncidents();
    }
}
