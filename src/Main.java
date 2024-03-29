import level.Statuses;
import service.InMemoryTaskManager;
import service.TaskManager;
import task.Epic;
import task.Subtask;
import task.Task;

public class Main {

    public static void main(String[] args) {

        TaskManager manager = new InMemoryTaskManager();

        Task task1 = manager.createTask(new Task("Task 1", "", Statuses.NEW));
        Task task2 = manager.createTask(new Task("Task 2", "", Statuses.NEW));


        Epic epic1 = manager.createEpic(new Epic("Epic 1", "", Statuses.NEW));
        Epic epic2 = manager.createEpic(new Epic("Epic 2", "", Statuses.NEW));

        Subtask subtask1 = manager.createSubtask(new Subtask("Subtask 1", "", epic1.getId(), Statuses.NEW));
        Subtask subtask2 = manager.createSubtask(new Subtask("Subtask 2", "", epic1.getId(), Statuses.NEW));
        Subtask subtask3 = manager.createSubtask(new Subtask("Subtask 3", "", epic2.getId(), Statuses.NEW));


        System.out.println(manager.getAllTasks());
        System.out.println(manager.getAllEpics());
        System.out.println(manager.getAllSubtasks());


        manager.updateTask(new Task("Task 1", "", Statuses.DONE, task1.getId()));
        manager.updateTask(new Task("Task 2", "", Statuses.DONE, task2.getId()));
        manager.updateSubtask(new Subtask("Subtask 1", "", epic1.getId(), Statuses.DONE, subtask1.getId()));
        manager.updateSubtask(new Subtask("Subtask 2", "", epic1.getId(), Statuses.DONE, subtask2.getId()));
        manager.updateSubtask(new Subtask("Subtask 3", "", epic2.getId(), Statuses.DONE, subtask3.getId()));

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
