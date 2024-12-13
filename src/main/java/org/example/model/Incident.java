package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@Table(name = "hsbc_incident")
public class Incident {
    @Id
    private String id;

    private String title;

    private String body;
}
