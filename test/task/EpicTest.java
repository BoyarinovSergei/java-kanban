package task;

import level.Statuses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EpicTest {
    private InMemoryTaskManager manager;

    @BeforeEach
    public void reloadData() {
        manager = new InMemoryTaskManager();
    }


    @Test
    @DisplayName("проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;")
    public void impossibleToAddEpicIntoItselfInFormOfSubtask() {
        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW));
        epic1.addIdOfSubtask(epic1.getId());

        assertEquals(1, epic1.getIdOfSubtasks().get(0));
    }
}