package org.example.service.impl;

import org.example.dto.IncidentDto;
import org.example.model.Incident;
import org.example.repository.IncidentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IncidentServiceImplTest {

    @Mock
    private IncidentRepository repository;

    @InjectMocks
    private IncidentServiceImpl service;

    private Incident incident;
    private IncidentDto incidentDto;

    @BeforeEach
    public void setUp() {
        incident = new Incident();
        incident.setId("1");
        incident.setTitle("Test Incident");
        incident.setBody("This is a test incident.");

        incidentDto = new IncidentDto();
        incidentDto.setId("1");
        incidentDto.setTitle("Test Incident");
        incidentDto.setBody("This is a test incident.");
    }

    @Test
    public void testGetAllIncidents() {
        when(repository.findAll()).thenReturn(Collections.singletonList(incident));

        List<IncidentDto> dtos = service.getAllIncidents();

        assertEquals(1, dtos.size());
        assertEquals(incidentDto, dtos.get(0));
    }

    @Test
    public void testGetAllIncidentsWithCache() {
        List<Incident> incidents = Arrays.asList(incident, incident);
        when(repository.findAll()).thenReturn(incidents);

        List<IncidentDto> result1 = service.getAllIncidents();
        List<IncidentDto> result2 = service.getAllIncidents();

        assertEquals(result1, result2);
        verify(repository, times(1)).findAll();
    }

    @Test
    public void testCreateIncident() {
        when(repository.save(any(Incident.class))).thenReturn(incident);

        IncidentDto createdDto = service.createIncident(incidentDto);

        assertNotNull(createdDto);
        assertEquals(incidentDto, createdDto);
    }

    @Test
    public void testUpdateIncident() {
        when(repository.findById("1")).thenReturn(Optional.of(incident));
        when(repository.save(any(Incident.class))).thenReturn(incident);

        IncidentDto updatedDto = service.updateIncident("1", incidentDto);

        assertNotNull(updatedDto);
        assertEquals(incidentDto, updatedDto);
    }

    @Test
    public void testDeleteIncidentById() {
        service.deleteIncidentById("1");

        verify(repository, times(1)).deleteById("1");
    }

    @Test
    public void testConvertFromDto() {
        Incident converted = service.convertFromDto(incidentDto);

        assertNotNull(converted);
        assertEquals(incident, converted);
    }

    @Test
    public void testConvertToDto() {
        IncidentDto converted = service.convertToDto(incident);

        assertNotNull(converted);
        assertEquals(incidentDto, converted);
    }
}
