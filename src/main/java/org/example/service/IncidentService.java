package org.example.service;

import org.example.dto.IncidentDto;

import java.util.List;

public interface IncidentService {

    IncidentDto createIncident(IncidentDto incidentDto);

    void deleteIncidentById(String id);

    IncidentDto updateIncident(String id, IncidentDto incidentDto);

    List<IncidentDto> getAllIncidents();
}
