package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.util.feign.AdServerClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

//This would be needed to run the annotations.
//@RunWith(MockitoJUnitRunner.class)

public class TaskerServiceLayerTest {

    //This is the annotation to say (this is where the Mock Beans will be injected into)
    @InjectMocks
    private TaskerServiceLayer service;
    //The ones that you are mocking
    @Mock
    private TaskerDao taskDao;
    @Mock
    private static AdServerClient client;

    //By doing so, you will now have to use the mock() in the setups.

    //Client -> Controller <- (VM) -> Service  <- (DTO) -> DAO < -(RS) -> DB
// The Mocks themselves are just prerequisites to what you expect to have before you are testing the service layer's logic.

    //int add(int n1, int n2)
    // return n1 + n2;
    //Would not want to have any mocks because there are no dependencies to the mock and it'll just be service layer logic.

    //When there ARE dependencies to something outside...
    /*  ...if there was dao.addTask(Task t) returns Task... (Returns Task when task2 was added onto the addTask())
    TVM addTask(TVM tvm) {
        ...
        dao.addTask(t); Now this will be something to call onto the dependency, so this dependency must exist.
        Internally, this will call onto the dao to get that result.

        The conversion from the TVM to the task itself will be done under assumption.
    }
    If this is under TDD...
    TaskViewModel addTask(TaskViewModel tvm) {
        return null;
    }
     */
    @Test
    public void testAddTask() {
        TaskViewModel tvm = new TaskViewModel();
        //expect the method to go and set up the ID
        tvm.setDescription("DESCRIPTION");
        tvm.setCreateDate(LocalDate.of(2019, 5, 5));
        tvm.setDueDate(LocalDate.of(2019,5,5));
        tvm.setCategory("CATEGORY");

        //This is what we push in and the one that is coming back
        //TaskViewModel newTaskViewModel = service.newTask(tvm);

        // Serves the same functionality
        // tvm = service.newTask(tvm);

        //To test to make sure that the IDs and ad were created through the service.
        TaskViewModel created = service.newTask(tvm);
        tvm.setId(created.getId());
        tvm.setAdvertisement(created.getAdvertisement());

        //Testing that they should be equal.
        assertEquals(tvm, created);
    }









    @Before
    public void setUp() throws Exception {
        //Put this when I'm not running with @RunWith
        MockitoAnnotations.initMocks(this);
        setUpTaskDaoMock();
        setUpFeignClient();


        //service = new TaskerServiceLayer(taskDao, client);
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

        Task expected = new Task();
        expected.setId(1);
        expected.setDescription(tvm.getDescription());
        expected.setCreateDate(tvm.getCreateDate());
        expected.setDueDate(tvm.getDueDate());
        expected.setCategory(tvm.getCategory());

//        tvm.setCategory("NEW CATEGORY");

//        ArgumentCaptor<Task> taskArgumentCaptor = ArgumentCaptor.forClass(Task.class);

        service.updateTask(tvm);

        verify(taskDao, times(1)).updateTask(expected);
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
        //taskDao = mock(TaskerDaoJdbcTemplateImpl.class);
        Task task = new Task();
        task.setId(1);
        task.setDescription("DESCRIPTION");
        task.setCreateDate(LocalDate.of(2019, 5, 5));
        task.setDueDate(LocalDate.of(2019,5,5));
        task.setCategory("CATEGORY");

        //This is the value that you're passing in, so you would not want an id here
        // since it will be autogenerated from the DB.
        Task task2 = new Task();
        task2.setDescription("DESCRIPTION");
        task2.setCreateDate(LocalDate.of(2019,5,5));
        task2.setDueDate(LocalDate.of(2019,5,5));
        task.setCategory("CATEGORY");

        List<Task> tList = new ArrayList<>();
        tList.add(task);

        //Put the one that is being added onto first and the return after.
        doReturn(task).when(taskDao).createTask(task2);
        doReturn(task).when(taskDao).getTask(1);
        doReturn(tList).when(taskDao).getTasksByCategory("CATEGORY");
        doReturn(tList).when(taskDao).getAllTasks();
    }
    private void setUpFeignClient() {
        //client = mock(AdServerClient.class);

        String ads = "SOME AD";

        doReturn(ads).when(client).getAd();
    }



    @After
    public void tearDown() throws Exception {
    }
}