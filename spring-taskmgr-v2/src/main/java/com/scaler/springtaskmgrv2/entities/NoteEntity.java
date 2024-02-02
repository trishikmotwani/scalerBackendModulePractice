package com.scaler.springtaskmgrv2.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "notes")
@Getter
@Setter
public class NoteEntity extends BaseEntity {

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JsonBackReference
    private TaskEntity task;
}
