package task;

import level.Statuses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class TaskTest {
    private InMemoryTaskManager manager;

    @BeforeEach
    public void reloadData() {
        manager = new InMemoryTaskManager();
    }

    @Test
    @DisplayName("проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;")
    public void thereAreNoConflictsInManager() {
        Task task1 = manager.createTask(new Task("Task 1", "", Statuses.NEW));
        Task task2 = manager.createTask(new Task("Task 2", "", Statuses.NEW));

        assertNotEquals(task1.getId(), task2.getId(), "Id у задач оказались одинаковыми");
    }

    @Test
    @DisplayName("проверьте, что экземпляры класса Task равны друг другу, если равен их id")
    public void tasksEqualIfTheirIdsAreTheSame() {
        Task task1 = manager.createTask(new Task("Task 1", "Task 1", Statuses.NEW, 1));
        Task task2 = manager.createTask(new Task("Task 2", "Task 2", Statuses.DONE, 1));
        task1.setId(1);
        task2.setId(1);

        assertEquals(task1, task2, "Id у task1 и task2 оказались разными");
    }

    @Test
    @DisplayName("проверьте, что наследники класса Task равны друг другу, если равен их id")
    public void tasksInheritorsEqualIfTheirIdsEqual() {
        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW));
        Epic epic2 = manager.createEpic(new Epic("Epic 2", "", Statuses.NEW));
        epic1.setId(1);
        epic2.setId(1);

        assertEquals(epic1, epic2, "Разные id у эпиков");
    }
}
