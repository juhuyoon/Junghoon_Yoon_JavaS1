package com.trilogyed.tasker.controller;

import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/tasks")
public class TaskerController {

    @Autowired
    TaskerServiceLayer service;

    private TaskViewModel taskViewModel;

    public TaskerController(TaskerServiceLayer service) {
        this.service = service;
    }

//      @PostMapping(value = "" )
//      @ResponseStatus(value = HttpStatus.OK)
//      public TaskViewModel postTaskViewModel(TaskViewModel taskViewModel) {
//        return service.postTaskViewModel;
//      }
//
//    @GetMapping(value = "")
//    @ResponseStatus(value = HttpStatus.OK)
//    public List<TaskViewModel> getAllTaskViewModels() {
//        return service.
//    }
//
//         @GetMapping(value ="/{id}")
//     @ResponseStatus(value = HttpStatus.OK)
//     public TaskViewModel getTaskViewModel(@PathVariable (name="id") Integer task_id) {
//        return service.getTaskViewModel(task_id);
//     }
//
//      @PutMapping(value = "")
//      @ResponseStatus(HttpStatus.OK)
//      public TaskViewModel updateTaskViewModel(@RequestBody @Valid TaskViewModel taskViewModel) {
//        return service.addTaskViewModel(taskViewModel);
//      }
//
//        @GetMapping(value={"/{category}"})
//        @ResponseStatus(value = HttpStatus.OK)
//        public List<TaskViewModel> getTaskViewModelByCategory(@PathVariable (name="category") String category) {
//            return
//        }

    @DeleteMapping(value = "/tasks{id}")
    public void deleteTask(@PathVariable int id) {
        service.deleteTask(id);
    }
}
