package task;

import level.Statuses;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.InMemoryTaskManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SubtaskTest {
    private InMemoryTaskManager manager;

    @BeforeEach
    public void reloadData() {
        manager = new InMemoryTaskManager();
    }

    @Test
    @DisplayName("проверьте, что объект Subtask нельзя сделать своим же эпиком;")
    public void impossibleToAddSubtaskInFormOfItsEpic() {
        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW));
        Subtask subtask1 = manager.createSubtask(new Subtask("Subtask 1", "", epic1.getId(), Statuses.NEW));

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> manager.updateEpic(new Epic("Epic 1", "", Statuses.NEW, subtask1.getId())),
                "Expected updateEpic to throw, but it didn't"
        );

        assertEquals("The key doesn't exist in the map", thrown.getMessage());
    }
}
