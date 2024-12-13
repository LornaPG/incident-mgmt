package org.example.controller;

import org.example.dto.IncidentDto;
import org.example.exception.NotFoundException;
import org.example.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    private final IncidentService service;

    @Autowired
    public IncidentController(IncidentService service) {
        this.service = service;
    }

    @GetMapping("/get")
    public List<IncidentDto> getAllIncidents() {
        return service.getAllIncidents();
    }

    @PostMapping("/create")
    public ResponseEntity<IncidentDto> createIncident(@RequestBody IncidentDto incidentDto) {
        return ResponseEntity.ok(service.createIncident(incidentDto));
    }

    @PutMapping("/update")
    public ResponseEntity<IncidentDto> updateIncident(@RequestParam String id, @RequestBody IncidentDto incidentDto) {
        IncidentDto updatedIncident = service.updateIncident(id, incidentDto);
        if (updatedIncident == null) {
            throw new NotFoundException("No incident found with id " + id);
        }
        return ResponseEntity.ok(updatedIncident);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteIncident(@RequestParam String id) {
        service.deleteIncidentById(id);
        return ResponseEntity.noContent().build();
    }
}
