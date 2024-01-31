package service;

import task.Epic;
import task.Subtask;
import task.Task;

import java.util.List;

public interface TaskManager {
    List<Task> getAllTasks();

    void deleteAllTasks();

    Task getTaskById(int id);

    Task createTask(Task task);

    void updateTask(Task taskToUpdate);

    void deleteTask(Integer id);

    List<Epic> getAllEpics();

    void deleteAllEpics();

    Epic getEpicById(int id);

    Epic createEpic(Epic epic);

    void updateEpic(Epic epic);

    void deleteEpic(Integer id);

    List<Subtask> getAllSubtasksByEpic(int epicId);

    List<Subtask> getAllSubtasks();

    void deleteAllSubtask();

    Subtask getSubtaskById(int id);

    Subtask createSubtask(Subtask subtask);

    void updateSubtask(Subtask subtaskToUpdate);

    void deleteSubtask(Integer id);
}
