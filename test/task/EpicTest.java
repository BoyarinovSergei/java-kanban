package task;

import level.Statuses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.HistoryManager;
import service.InMemoryTaskManager;
import service.TaskManager;

import static org.junit.jupiter.api.Assertions.*;
import static util.Managers.getDefault;
import static util.Managers.getDefaultHistory;

class EpicTest {
    private InMemoryTaskManager manager;

    @BeforeEach
    public void reloadData() {
        manager = new InMemoryTaskManager();
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

    @Test
    @DisplayName("проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;")
    public void impossibleToAddEpicIntoItselfInFormOfSubtask() {
        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW));
        epic1.addIdOfSubtask(epic1.getId());

        assertEquals(1, epic1.getIdOfSubtasks().get(0));
    }

    @Test
    @DisplayName("проверьте, что объект Subtask нельзя сделать своим же эпиком;")
    public void impossibleToAddSubtaskInFormOfItsEpic() {
        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW));
        Subtask subtask1 = manager.createSubtask(new Subtask("Subtask 1", "", epic1.getId(), Statuses.NEW));

        // Не понял как это проверить от слова совсем
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
    @DisplayName("проверьте, что InMemoryTaskManager действительно добавляет задачи разного типа и может найти их по id;")
    public void inMemoryTaskManagerAddsDifferentTypesOfTasks() {
        Task task1 = manager.createTask(new Task("Task 1", "", Statuses.NEW));
        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW));
        Subtask subtask1 = manager.createSubtask(new Subtask("Subtask 1", "", epic1.getId(), Statuses.NEW));

        assertEquals(task1, manager.getTaskById(task1.getId()));
        assertEquals(epic1, manager.getEpicById(epic1.getId()));
        assertEquals(subtask1, manager.getSubtaskById(subtask1.getId()));
    }

    @Test
    @DisplayName("проверьте, что задачи с заданным id и сгенерированным id не конфликтуют внутри менеджера;")
    public void thereAreNoConflictsInManager() {
        Task task1 = manager.createTask(new Task("Task 1", "", Statuses.NEW));
        Task task2 = manager.createTask(new Task("Task 2", "", Statuses.NEW));

        assertNotEquals(task1.getId(), task2.getId(), "Id у задач оказались одинаковыми");
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