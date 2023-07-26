package com.budkevych.uniserviceapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "grades")
@Data
@Builder
@AllArgsConstructor
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Subject subject;
    @ManyToOne
    private Student student;
    @Column(name = "grade")
    @Range(min = 1, max = 5)
    private Integer grade;

    public Grade() {
    }
}
