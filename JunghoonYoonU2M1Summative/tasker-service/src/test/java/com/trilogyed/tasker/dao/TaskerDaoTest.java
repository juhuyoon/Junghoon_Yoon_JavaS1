package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskerDaoTest {

    @Autowired
    private TaskerDao taskerDao;

    private Task task1, task2;

    @Before
    public void setUp() throws Exception {
        taskerDao.getAllTasks().forEach(t -> {
            taskerDao.deleteTask(t.getId());
        });

        task1 = new Task();
        task1.setDescription("This is a work to be done");
        task1.setCreateDate(LocalDate.of(2019, 5, 15));
        task1.setDueDate(LocalDate.of(2019,8,22));
        task1.setCategory("Work");

        task2 = new Task();
        task2.setDescription("Coding for fun");
        task2.setCreateDate(LocalDate.of(2018, 10, 30));
        task2.setDueDate(LocalDate.of(2020,1,1));
        task2.setCategory("Coding");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAddDeleteTask() {
        task1 = taskerDao.createTask(task1);
        assertEquals(1, taskerDao.getAllTasks().size());
        Task fromDao = taskerDao.getTask(task1.getId());
        assertEquals(task1, fromDao);
        taskerDao.deleteTask(task1.getId());
        fromDao = taskerDao.getTask(task1.getId());
        assertNull(fromDao);
    }

    @Test
    public void getAllTasks() {
        taskerDao.createTask(task1);
        taskerDao.createTask(task2);
        List<Task> tList = taskerDao.getAllTasks();
        assertEquals(2, tList.size());
    }

    @Test
    public void getTasksByCategory() {
        taskerDao.createTask(task1);
        taskerDao.getTasksByCategory(task1.getCategory());

        Task fromDao = taskerDao.getTask(task1.getId());

        assertEquals(fromDao, task1);
    }

    @Test
    public void updateTask() {
        taskerDao.createTask(task1);
        task1.setDescription("NEW DESC");
        Task fromDao = taskerDao.getTask(task1.getId());

        assertNotEquals(task1, fromDao);
    }
}