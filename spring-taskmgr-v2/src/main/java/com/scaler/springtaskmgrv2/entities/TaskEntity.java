package com.scaler.springtaskmgrv2.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Date;
import java.util.List;

@Entity(name = "tasks")
@Getter
@Setter
public class TaskEntity extends BaseEntity {

    @Column(name = "title", nullable = false, length = 200, unique = true)
    private String title;
    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "due_date")
    private Date dueDate;

    @Column(name = "completed", columnDefinition = "boolean default false")
    private Boolean completed;

//    Add below mapping only if you want to fetch notes when any task is fetched

    @OneToMany(mappedBy = "task", orphanRemoval = true, cascade = CascadeType.PERSIST)
    @JsonManagedReference
    private List<NoteEntity> notes;

    public void removeNote(NoteEntity noteEntity) {
        notes.remove(noteEntity);
    }
}
