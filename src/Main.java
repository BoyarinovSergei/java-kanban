import level.Statuses;
import service.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();

        Task task1 = manager.createTask(new Task("Task 1", "", Statuses.NEW, 0));
        Task task2 = manager.createTask(new Task("Task 2", "", Statuses.NEW, 1));


        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW, 0));
        Epic epic2 = manager.createEpic(new Epic("Epic 2", "", Statuses.NEW, 1));

        Subtask subtask1 = manager.createSubtask(new Subtask("Subtask 1", "", epic1.getId(), Statuses.NEW, 0));
        Subtask subtask2 = manager.createSubtask(new Subtask("Subtask 2", "", epic1.getId(), Statuses.NEW, 1));
        Subtask subtask3 = manager.createSubtask(new Subtask("Subtask 3", "", epic2.getId(), Statuses.NEW, 2));


        manager.updateEpic(new Epic("Epic 1", "", Statuses.NEW, 0, List.of(subtask1.getId(), subtask2.getId())), epic1.getId());
        manager.updateEpic(new Epic("Epic 2", "", Statuses.NEW, 0, List.of(subtask3.getId())), epic2.getId());


//        epic1.setIdOfSubtasks(List.of(subtask1.getId(), subtask2.getId()));
//        epic2.setIdOfSubtasks(List.of(subtask3.getId()));

        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());


        manager.updateTask(new Task("Task 1", "", Statuses.DONE, task1.getId()), task1.getId());
        manager.updateTask(new Task("Task 2", "", Statuses.DONE, task2.getId()), task2.getId());
        manager.updateSubtask(new Subtask("Subtask 1", "", epic1.getId(), Statuses.DONE, subtask1.getId()), subtask1.getId());
        manager.updateSubtask(new Subtask("Subtask 2", "", epic1.getId(), Statuses.DONE, subtask2.getId()), subtask2.getId());
        manager.updateSubtask(new Subtask("Subtask 3", "", epic2.getId(), Statuses.DONE, subtask3.getId()), subtask3.getId());

        System.out.println();
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println(manager.getAllEpics());

        manager.deleteTask(task1.getId());
        manager.deleteEpic(epic1.getId());

        System.out.println();
        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllSubtasks());
        System.out.println(manager.getAllEpics());
    }
}
