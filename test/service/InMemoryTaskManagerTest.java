package service;

import level.Statuses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Subtask;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InMemoryTaskManagerTest {

    private InMemoryTaskManager manager;

    @BeforeEach
    public void reloadData() {
        manager = new InMemoryTaskManager();
    }

    @Test
    @DisplayName("проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;")
    public void inMemoryTaskManagerAddsDifferentTypesOfTasks() {
        Task task1 = manager.createTask(new Task("Task 1", "", Statuses.NEW));
        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW));
        Subtask subtask1 = manager.createSubtask(new Subtask("Subtask 1", "", epic1.getId(), Statuses.NEW));

        assertEquals(task1, manager.getTaskById(task1.getId()));
        assertEquals(epic1, manager.getEpicById(epic1.getId()));
        assertEquals(subtask1, manager.getSubtaskById(subtask1.getId()));
    }
}
