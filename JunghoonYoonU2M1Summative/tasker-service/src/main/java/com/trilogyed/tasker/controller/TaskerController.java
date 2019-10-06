package com.trilogyed.tasker.controller;

import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value="/tasks")
@RefreshScope
public class TaskerController {

    @Autowired
    TaskerServiceLayer service;


    public TaskerController(TaskerServiceLayer service) {
        this.service = service;
    }

        @PostMapping(value = "" )
        @ResponseStatus(value = HttpStatus.CREATED)
        public TaskViewModel postTaskViewModel(@RequestBody TaskViewModel taskViewModel) {
            return service.newTask(taskViewModel);
        }

        @GetMapping(value = "")
        @ResponseStatus(value = HttpStatus.OK)
        public java.util.List<TaskViewModel> getAllTaskViewModels() {
            return service.fetchAllTasks();
        }

         @GetMapping(value ="/{id}")
         @ResponseStatus(value = HttpStatus.OK)
         public TaskViewModel getTaskViewModel(@PathVariable (name="id") Integer task_id) {
            return service.fetchTask(task_id);
         }

          @PutMapping(value = "")
          @ResponseStatus(HttpStatus.OK)
          public void updateTaskViewModel(@RequestBody @Valid TaskViewModel taskViewModel) {
            service.updateTask(taskViewModel);
          }

          @GetMapping(value={"/{category}"})
          @ResponseStatus(value = HttpStatus.OK)
          public List<TaskViewModel> getTaskViewModelByCategory(@PathVariable (name="category") String category) {
              return service.fetchTasksByCategory(category);
          }

          @DeleteMapping(value = "/tasks{id}")
          public void deleteTask(@PathVariable int id) {
              service.deleteTask(id);
          }

}
