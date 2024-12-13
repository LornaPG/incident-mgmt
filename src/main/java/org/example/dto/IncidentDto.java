package org.example.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class IncidentDto {
    private String id;

    private String title;

    private String body;
}
