package org.example.controller;

import org.example.dto.IncidentDto;
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

    @PutMapping("/update/{id}")
    public ResponseEntity<IncidentDto> updateIncident(@PathVariable String id, @RequestBody IncidentDto incidentDto) {
        IncidentDto updatedIncident = service.updateIncident(id, incidentDto);
        if (updatedIncident == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedIncident);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable String id) {
        service.deleteIncidentById(id);
        return ResponseEntity.noContent().build();
    }
}
