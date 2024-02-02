package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.repositories.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Service
public class TaskService {

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private NoteService notesService;
    public List<TaskEntity> getAllTasks() {

        return tasksRepository.findAll();
    }
    public TaskEntity getTaskById(Integer taskId) {
        Optional<TaskEntity> optionalTaskEntity = tasksRepository.findById(taskId);
        if(optionalTaskEntity.isPresent()) {
            return optionalTaskEntity.get();
        }
        throw new TaskNotFoundException(taskId);
    }

    public TaskEntity saveTask(TaskEntity newTask) {

        return tasksRepository.save(newTask);
    }
    public TaskEntity createOrReplaceTask(TaskEntity task) {

        return null;
    }

    public TaskEntity updateTask(TaskEntity task) {
        return null;
    }

    public void deleteTask(Integer taskId) {
        this.getTaskById(taskId);
        tasksRepository.deleteById(taskId);
    }

    public List<TaskEntity> getAllTasksByTitle(String title) {
        return tasksRepository.findAllByTitle(title);
    }

    public List<TaskEntity> getAllTasksByCompleted(Boolean isCompleted) {
        return tasksRepository.findAllByCompleted(isCompleted);
    }

    public NoteEntity createNoteForTask(Integer taskId, NoteEntity newNoteEntity) {
        TaskEntity task = getTaskById(taskId);
        newNoteEntity.setTask(task);
        return notesService.createNote(newNoteEntity);
    }

    public TaskEntity deleteNoteFromTask(Integer taskId, Integer noteId) {
        TaskEntity task = getTaskById(taskId);
        NoteEntity note = this.notesService.getNoteById(noteId);
        task.removeNote(note);
        return this.saveTask(task);
    }

    public static class TaskNotFoundException extends IllegalArgumentException {
        public TaskNotFoundException(Integer id) {
            super("Task with id " + id + " not found");
        }
    }
}
