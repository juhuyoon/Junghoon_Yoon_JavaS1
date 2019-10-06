package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.util.feign.AdServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskerServiceLayer {

    TaskerDao dao;

    @Autowired
    AdServerClient client;

    @Autowired
    public TaskerServiceLayer(TaskerDao dao, AdServerClient client) {
        this.dao = dao;
        this.client = client;
    }

    public TaskViewModel fetchTask(int id) {

        Task task = dao.getTask(id);
        TaskViewModel tvm = new TaskViewModel();

        tvm.setId(task.getId());
        tvm.setDescription(task.getDescription());
        tvm.setCreateDate(task.getCreateDate());
        tvm.setDueDate(task.getDueDate());
        tvm.setCategory(task.getCategory());
        tvm.setAdvertisement(client.getAd());

        return tvm;
    }

    public List<TaskViewModel> fetchAllTasks() {
        List<Task> tList = dao.getAllTasks();
        List<TaskViewModel> tvmList = new ArrayList<>();
        for(Task task: tList) {
            TaskViewModel tvm = buildTaskViewModel(task);
            tvmList.add(tvm);
        }

        return tvmList;
    }

    public List<TaskViewModel> fetchTasksByCategory(String category) {
        List<Task> tList = dao.getTasksByCategory(category);
        List<TaskViewModel> tvmList = new ArrayList<>();
        for(Task task : tList) {
            TaskViewModel tvm = buildTaskViewModel(task);
            tvmList.add(tvm);
        }

        return tvmList;
    }

    public TaskViewModel newTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        task = dao.createTask(task);
        taskViewModel.setId(task.getId());
        taskViewModel.setAdvertisement(client.getAd());

        return taskViewModel;
    }

    public void deleteTask(int id) {
        dao.deleteTask(id);

    }

    public void updateTask(TaskViewModel taskViewModel) {
        Task task = new Task();
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        dao.updateTask(task);

    }

    private TaskViewModel buildTaskViewModel(Task task){
        TaskViewModel tvm = new TaskViewModel();
        tvm.setId(task.getId());
        tvm.setDescription(task.getDescription());
        tvm.setCreateDate(task.getCreateDate());
        tvm.setDueDate(task.getDueDate());
        tvm.setCategory(task.getCategory());
        tvm.setAdvertisement(client.getAd());

        return tvm;
    }
}
