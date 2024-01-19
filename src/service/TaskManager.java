package service;

import level.Statuses;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Subtask> subtasks;
    private HashMap<Integer, Epic> epics;
    private static int id;

    public TaskManager() {
        this.tasks = new HashMap<>();
        this.subtasks = new HashMap<>();
        this.epics = new HashMap<>();
    }

    //Tasks

    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public Task createTask(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    public void updateTask(Task taskToUpdate, int id) {
        if (tasks.containsKey(id)) {
            tasks.put(taskToUpdate.getId(), taskToUpdate);
        } else {
            throw new RuntimeException("The key doesn't exist in the map");
        }
    }

    public void deleteTask(Integer id) {
        tasks.remove(id);
    }


    //Epics

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void deleteAllEpics() {
        epics.clear();
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Epic createEpic(Epic epic) {
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void updateEpic(Epic epic, Integer id) {
        if (epics.containsKey(id)) {
            epics.put(id, epic);
        } else {
            throw new RuntimeException("The key doesn't exist in the map");
        }
    }

    public void deleteEpic(Integer id) {
        epics.get(id).getIdOfSubtasks().forEach(s -> subtasks.remove(s));
        epics.remove(id);
    }

    public List<Integer> getAllSubtasksByEpic(Epic epic) {
        return epic.getIdOfSubtasks();
    }

    //Subtasks

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteAllSubtask() {
        subtasks.clear();
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public Subtask createSubtask(Subtask subtask) {
        subtasks.put(subtask.getId(), subtask);
        return subtask;
    }

    public void updateSubtask(Subtask subtaskToUpdate, int id) {
        if (subtasks.containsKey(id)) {
            subtasks.put(subtaskToUpdate.getId(), subtaskToUpdate);
        } else {
            throw new RuntimeException("The key doesn't exist in the map");
        }

        calculateEpicStatus(subtaskToUpdate);
    }


    public void calculateEpicStatus(Subtask subtask) {
        if (subtasks.containsKey(subtask.getId())) {

            if (epics.get(subtask.getEpicId()).getIdOfSubtasks().isEmpty()) {
                epics.get(subtask.getEpicId()).setStatus(String.valueOf(Statuses.NEW));
            } else if (epics.get(subtask.getEpicId()).getIdOfSubtasks().stream().allMatch(s -> subtasks.get(s).getStatus().equals(String.valueOf(Statuses.NEW)))) {
                epics.get(subtask.getEpicId()).setStatus(String.valueOf(Statuses.NEW));
            } else if (epics.get(subtask.getEpicId()).getIdOfSubtasks().stream().allMatch(s -> subtasks.get(s).getStatus().equals(String.valueOf(Statuses.DONE)))) {
                epics.get(subtask.getEpicId()).setStatus(String.valueOf(Statuses.DONE));
            } else {
                epics.get(subtask.getEpicId()).setStatus(String.valueOf(Statuses.IN_PROGRESS));
            }

        } else {
            throw new RuntimeException("The key doesn't exist in the map");
        }
    }


    public void deleteSubtask(Integer id) {
        subtasks.remove(id);
    }
}