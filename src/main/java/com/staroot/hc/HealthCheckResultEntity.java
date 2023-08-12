package com.staroot.hc;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class HealthCheckResultEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;
    private String status;
    @Column(length = 65536)
    private String response;

    public HealthCheckResultEntity(String url, String status, String response) {
        this.url = url;
        this.status = status;
        this.response = response;
    }

    public HealthCheckResultEntity(String url, String status) {
        this.url = url;
        this.status = status;
    }
    // Constructors, getters, and setters
}

