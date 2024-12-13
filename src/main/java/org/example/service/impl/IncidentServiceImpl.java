package org.example.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.example.dto.IncidentDto;
import org.example.model.Incident;
import org.example.repository.IncidentRepository;
import org.example.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class IncidentServiceImpl implements IncidentService {
    private final IncidentRepository repository;

    @Autowired
    public IncidentServiceImpl(IncidentRepository repository) {
        this.repository = repository;
    }

    @Override
    @Cacheable(value = "incidents")
    public List<IncidentDto> getAllIncidents() {
        return repository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public IncidentDto createIncident(IncidentDto incidentDto) {
        Incident incident = convertFromDto(incidentDto);
        repository.save(incident);
        return incidentDto;
    }

    @Override
    public IncidentDto updateIncident(String id, IncidentDto incidentDto) {
        Optional<Incident> incidentOptional = repository.findById(id);
        if (incidentOptional.isPresent()) {
            Incident incident = incidentOptional.get();
            incident.setTitle(incidentDto.getTitle());
            incident.setBody(incidentDto.getBody());
            repository.save(incident);
        }
        return incidentDto;
    }

    @Override
    public void deleteIncidentById(String id) {
        repository.deleteById(id);
    }

    Incident convertFromDto(IncidentDto dto) {
        Incident incident = new Incident();
        incident.setId(dto.getId());
        incident.setTitle(dto.getTitle());
        incident.setBody(dto.getBody());
        return incident;
    }

    IncidentDto convertToDto(Incident incident) {
        IncidentDto dto = new IncidentDto();
        dto.setId(incident.getId());
        dto.setTitle(incident.getTitle());
        dto.setBody(incident.getBody());
        return dto;
    }

}
