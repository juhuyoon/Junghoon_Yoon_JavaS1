package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TaskerDaoJdbcTemplateImpl implements TaskerDao {

    public static final String INSERT_TASK =
            "insert into task (task_description, create_date, due_date, category) values (?, ?, ?, ?)";
    public static final String SELECT_TASK_BY_ID =
            "select * from task where task_id = ?";
    public static final String SELECT_ALL_TASKS =
            "select * from task";
    public static final String SELECT_TASKS_BY_CATEGORY =
            "select * from task where category = ?";
    public static final String UPDATE_TASK =
            "update task set task_description = ?, create_date = ?, due_date = ?, category = ? where task_id = ?";
    public static final String DELETE_TASK =
            "delete from task WHERE task_id = ?";


    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TaskerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * Creates a Task Object to push to the Database
     * @param task
     * @return
     */
    @Override
    @Transactional
    public Task createTask(Task task) {
        jdbcTemplate.update(INSERT_TASK,
                task.getDescription(),
                task.getCreateDate(),
                task.getDueDate(),
                task.getCategory());

        Integer taskId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        task.setId(taskId);
        return task;
    }

    /**
     * Retrieves a Task Object from the database.
     * @param id
     * @return
     */
    @Override
    public Task getTask(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_TASK_BY_ID, this::mapTaskToRow, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    /**
     * Retrieves a list of Tasks from the database.
     * @return
     */
    @Override
    public List<Task> getAllTasks() {
        return jdbcTemplate.query(SELECT_ALL_TASKS, this::mapTaskToRow);
    }

    /**
     * Retrieves a list of Tasks based on the category.
     * @param category
     * @return
     */
    @Override
    public List<Task> getTasksByCategory(String category) {
        return jdbcTemplate.query(SELECT_TASKS_BY_CATEGORY, this::mapTaskToRow, category);
    }

    /**
     * Updates a Task object in the database.
     * @param task
     */
    @Override
    @Transactional
    public void updateTask(Task task) {
        jdbcTemplate.update(UPDATE_TASK,
                task.getDescription(),
                task.getCreateDate(),
                task.getDueDate(),
                task.getCategory(),
                task.getId());
    }

    /**
     * Deletes a Task in the database based on the ID.
     * @param id
     */
    @Override
    public void deleteTask(int id) {
        jdbcTemplate.update(DELETE_TASK, id);
    }

    private Task mapTaskToRow(ResultSet rs, int rowNumber) throws SQLException {
        Task task = new Task();
        task.setId(rs.getInt("task_id"));
        task.setDescription(rs.getString("task_description"));
        task.setCreateDate(rs.getDate("create_date").toLocalDate());
        task.setDueDate(rs.getDate("due_date").toLocalDate());
        task.setCategory(rs.getString("category"));

        return task;
    }
}
