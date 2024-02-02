package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.repositories.NotesRepository;
import com.scaler.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    NotesRepository notesRepository;

    public NoteEntity createNote(NoteEntity newNoteEntity) {
        return notesRepository.save(newNoteEntity);
    }

    public NoteEntity getNoteById(Integer noteId) {
        Optional<NoteEntity> noteEntity = notesRepository.findById(noteId);
        if(noteEntity.isPresent()) {
            return noteEntity.get();
        }
        throw new NoteNotFoundException(noteId);
    }

    public static class NoteNotFoundException extends IllegalArgumentException {
        public NoteNotFoundException(Integer id) {
            super("Task with id " + id + " not found");
        }
    }
}
