package service;

import level.Statuses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static util.Managers.getDefault;
import static util.Managers.getDefaultHistory;

public class InMemoryHistoryManagerTest {
    private InMemoryTaskManager manager;

    @BeforeEach
    public void reloadData() {
        manager = new InMemoryTaskManager();
    }


    @Test
    @DisplayName("убедитесь, что утилитарный класс всегда возвращает проинициализированные и готовые к работе экземпляры менеджеров;")
    public void managersAlwaysReturnsReadyClass() {
        HistoryManager manager1 = getDefaultHistory();
        TaskManager manager2 = getDefault();

        assertNotNull(manager1);
        assertNotNull(manager2);
    }

    @Test
    @DisplayName("убедитесь, что задачи, добавляемые в HistoryManager, сохраняют предыдущую версию задачи и её данных.")
    public void historyManagerSavesPreviousVersionOfTaskAndItsData() {
        HistoryManager historyManager = getDefaultHistory();

        Task task1 = manager.createTask(new Task("Task 1", "", Statuses.NEW));

        historyManager.add(task1);

        manager.updateTask(new Task("Task 2", "", Statuses.DONE, task1.getId()));

        Task oldTask = historyManager.getHistory().get(0);
        Task updatedTask = manager.getTaskById(task1.getId());

        assertEquals(task1.getId(), updatedTask.getId());
        assertEquals(task1.getId(), oldTask.getId());
        assertEquals(task1.getName(), oldTask.getName());
        assertEquals(task1.getDescription(), oldTask.getDescription());
        assertEquals(task1.getStatus(), oldTask.getStatus());
    }
}
