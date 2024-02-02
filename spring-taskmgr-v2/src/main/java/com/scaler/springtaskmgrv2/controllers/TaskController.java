package com.scaler.springtaskmgrv2.controllers;

import com.scaler.springtaskmgrv2.dtos.ErrorResponse;
import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.services.NoteService;
import com.scaler.springtaskmgrv2.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/tasks")
public class TaskController {
    //    2nd way of injecting taskService dependency inside taskController
    //    public TaskController(@Autowired TaskService taskService) {
    //        this.taskService = taskService;
    //    }
    @Autowired
    public TaskService taskService;

    @GetMapping(path = "")
    public ResponseEntity<List<TaskEntity>> getAllTasks() {

        return ResponseEntity.ok(taskService.getAllTasks());
    }
    @GetMapping(path = "/{taskId}")
    public ResponseEntity<TaskEntity> getTaskById(@PathVariable Integer taskId) {
        return ResponseEntity.ok(taskService.getTaskById(taskId));
    }

    @PostMapping(path = "")
    public ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity newTask) {
        TaskEntity createdTaskEntity = taskService.saveTask(newTask);
        return ResponseEntity.created(URI.create("/tasks/" + createdTaskEntity.getId())).body(createdTaskEntity);
    }
    @PutMapping(path = "")
    public ResponseEntity<TaskEntity> createOrReplaceTask(@RequestBody TaskEntity task) {
        return null;
    }

    @PatchMapping(path = "")
    public ResponseEntity<TaskEntity> updateTask(@RequestBody TaskEntity task) {
        return null;
    }

    @DeleteMapping(path = "/{taskId}")
    public ResponseEntity deleteTask(@PathVariable Integer taskId) {

        taskService.deleteTask(taskId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping(path = "/title")
    public ResponseEntity<List<TaskEntity>> getAllTasksByTitle(@RequestParam String title) {

        return ResponseEntity.ok(taskService.getAllTasksByTitle(title));
    }
    @GetMapping(path = "/completed")
    public ResponseEntity<List<TaskEntity>> getAllCompletedTasks(@RequestParam(name = "completed") Boolean isCompleted) {

        return ResponseEntity.ok(taskService.getAllTasksByCompleted(isCompleted));
    }

//    Notes related operations start below
    @GetMapping(path = "/{id}/notes")
    public ResponseEntity<List<NoteEntity>> getNotesForTask(@PathVariable(name = "id") Integer taskId) {

        TaskEntity task = this.taskService.getTaskById(taskId);
        return ResponseEntity.ok(task.getNotes());
    }

    @PostMapping(path = "/{id}/notes")
    public ResponseEntity<NoteEntity> createNoteForTask(@PathVariable(name = "id") Integer taskId,
                                                        @RequestBody NoteEntity note) {
        return ResponseEntity.ok(taskService.createNoteForTask(taskId, note));
    }

    @DeleteMapping(path = "/{id}/notes/{noteId}")
    public ResponseEntity<TaskEntity> deleteNoteFromTask(@PathVariable(name="id") Integer taskId,
                                                     @PathVariable Integer noteId) {
        return ResponseEntity.ok(this.taskService.deleteNoteFromTask(taskId, noteId));
    }

    @ExceptionHandler(TaskService.TaskNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleTaskNotFoundException(TaskService.TaskNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

//    GET /tasks - returns all tasks
//    GET /tasks/{id} - returns a task with the given id
//    POST /tasks - creates a new task
//    PATCH /tasks/{id} - updates a task with the given id
//    DELETE /tasks/{id} - deletes a task with the given id
//    GET /tasks?title={title} - returns all tasks with the given title
//    GET /tasks?completed={completed} - returns all tasks with the given completed status

//    GET /tasks/{id}/notes - returns all notes for a task with the given id
//    POST /tasks/{id}/notes - creates a new note for a task with the given id
//    DELETE /tasks/{id}/notes/{noteId} - deletes a note with the given id for a task with the given id
}
