package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.dao.TaskerDaoJdbcTemplateImpl;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.util.feign.AdServerClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class TaskerServiceLayerTest {

    private TaskerServiceLayer service;
    private TaskerDao taskDao;
    private static AdServerClient client;


    @Before
    public void setUp() throws Exception {
        setUpTaskDaoMock();
        setUpFeignClient();
        setUpService();

        service = new TaskerServiceLayer(taskDao, client);
        //System.out.println(client.getAd());
    }

    @Test
    public void addFetchTask() {
        TaskViewModel tvm = new TaskViewModel();
        tvm.setId(1);
        tvm.setDescription("DESCRIPTION");
        tvm.setCreateDate(LocalDate.of(2019, 5, 5));
        tvm.setDueDate(LocalDate.of(2019,5,5));
        tvm.setCategory("CATEGORY");
        tvm.setAdvertisement(client.getAd());

        Task task = new Task();
        task.setId(1);
        task.setDescription(tvm.getDescription());
        task.setCreateDate(tvm.getCreateDate());
        task.setDueDate(tvm.getDueDate());
        task.setCategory(tvm.getCategory());

//        System.out.println(task);

        TaskViewModel fromService = service.fetchTask(task.getId());


        assertEquals(tvm, fromService);
    }

    @Test
    public void fetchAllTasks() {
        TaskViewModel tvm = new TaskViewModel();
        tvm.setId(1);
        tvm.setDescription("DESCRIPTION");
        tvm.setCreateDate(LocalDate.of(2019, 5, 5));
        tvm.setDueDate(LocalDate.of(2019,5,5));
        tvm.setCategory("CATEGORY");
        tvm.setAdvertisement(client.getAd());

        Task task = new Task();
        task.setId(1);
        task.setDescription(tvm.getDescription());
        task.setCreateDate(tvm.getCreateDate());
        task.setDueDate(tvm.getDueDate());
        task.setCategory(tvm.getCategory());

        TaskViewModel fromService = service.fetchTask(task.getId());

        List<TaskViewModel> tvmList = service.fetchAllTasks();
        assertEquals(1, tvmList.size());

    }

    @Test
    public void fetchTasksByCategory() {
        TaskViewModel tvm = new TaskViewModel();
        tvm.setId(1);
        tvm.setDescription("DESCRIPTION");
        tvm.setCreateDate(LocalDate.of(2019, 5, 5));
        tvm.setDueDate(LocalDate.of(2019,5,5));
        tvm.setCategory("CATEGORY");
        tvm.setAdvertisement(client.getAd());

        Task task = new Task();
        task.setId(1);
        task.setDescription(tvm.getDescription());
        task.setCreateDate(tvm.getCreateDate());
        task.setDueDate(tvm.getDueDate());
        task.setCategory(tvm.getCategory());

        List<TaskViewModel> fromService = service.fetchTasksByCategory(task.getCategory());
        assertEquals(1, fromService.size());
    }

    @Test
    public void updateTask() {
        TaskViewModel tvm = new TaskViewModel();
        tvm.setId(1);
        tvm.setDescription("DESCRIPTION");
        tvm.setCreateDate(LocalDate.of(2019, 5, 5));
        tvm.setDueDate(LocalDate.of(2019,5,5));
        tvm.setCategory("CATEGORY");
        tvm.setAdvertisement(client.getAd());

        Task task = new Task();
        task.setId(1);
        task.setDescription(tvm.getDescription());
        task.setCreateDate(tvm.getCreateDate());
        task.setDueDate(tvm.getDueDate());
        task.setCategory(tvm.getCategory());

        tvm.setCategory("NEW DESCRIPTION");

        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);
        doNothing().when(taskDao).updateTask(taskArgumentCaptor.capture());

        service.updateTask(tvm);

        verify(taskDao, times(1)).updateTask(taskArgumentCaptor.getValue());
        assertEquals("NEW DESCRIPTION", taskArgumentCaptor.getValue().getCategory());

    }

    @Test
    public void deleteTask() {
        ArgumentCaptor<Integer> integerArgumentCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(taskDao).deleteTask(integerArgumentCaptor.capture());
        service.deleteTask(1);

        verify(taskDao, times(1)).deleteTask(integerArgumentCaptor.getValue());

        assertEquals(1, integerArgumentCaptor.getValue().intValue());

    }


    //Helper Methods

    private void setUpTaskDaoMock() {
        taskDao = mock(TaskerDaoJdbcTemplateImpl.class);
        Task task = new Task();
        task.setId(1);
        task.setDescription("DESCRIPTION");
        task.setCreateDate(LocalDate.of(2019, 5, 5));
        task.setDueDate(LocalDate.of(2019,5,5));
        task.setCategory("CATEGORY");

        Task task2 = new Task();
        task2.setId(1);
        task2.setDescription("DESCRIPTION");
        task2.setCreateDate(LocalDate.of(2019,5,5));
        task2.setDueDate(LocalDate.of(2019,5,5));
        task.setCategory("CATEGORY");

        List<Task> tList = new ArrayList<>();
        tList.add(task);

        doReturn(task).when(taskDao).createTask(task2);
        doReturn(task).when(taskDao).getTask(1);
        doReturn(tList).when(taskDao).getTasksByCategory("CATEGORY");
        doReturn(tList).when(taskDao).getAllTasks();
    }
    private void setUpFeignClient() {
        client = mock(AdServerClient.class);

        String ads = "SOME AD";

        doReturn(ads).when(client).getAd();
    }

    private void setUpService() {
        service = mock(TaskerServiceLayer.class);
        TaskViewModel tvm = new TaskViewModel();
        tvm.setId(1);
        tvm.setDescription("DESCRIPTION");
        tvm.setCreateDate(LocalDate.of(2019, 5, 5));
        tvm.setDueDate(LocalDate.of(2019,5,5));
        tvm.setCategory("CATEGORY");
        tvm.setAdvertisement(client.getAd());

        List<TaskViewModel> tvmList = new ArrayList<>();
        tvmList.add(tvm);

        doReturn(tvm).when(service).newTask(tvm);
        doReturn(tvm).when(service).fetchTask(1);
        doReturn(tvmList).when(service).fetchAllTasks();
        doReturn(tvmList).when(service).fetchTasksByCategory("CATEGORY");

    }



    @After
    public void tearDown() throws Exception {
    }
}