package service;

import level.Statuses;
import task.Epic;
import task.Subtask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private final HashMap<Integer, Task> tasks = new HashMap<>();
    private final HashMap<Integer, Subtask> subtasks = new HashMap<>();
    private final HashMap<Integer, Epic> epics = new HashMap<>();

    private int id = 0;

    private int generateId() {
        return ++id;
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
        task.setId(generateId());
        tasks.put(task.getId(), task);
        return task;
    }

    public void updateTask(Task taskToUpdate) {
        if (tasks.containsKey(taskToUpdate.getId())) {
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
        subtasks.clear();
    }

    public Epic getEpicById(int id) {
        return epics.get(id);
    }

    public Epic createEpic(Epic epic) {
        epic.setId(generateId());
        epics.put(epic.getId(), epic);
        return epic;
    }

    public void updateEpic(Epic epic) {
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
        } else {
            throw new RuntimeException("The key doesn't exist in the map");
        }

        //рассчитать статус
    }

    public void deleteEpic(Integer id) {
        if (epics.containsKey(id)) {
            epics.get(id).getIdOfSubtasks().forEach(s -> subtasks.remove(s));
            epics.remove(id);
        }
    }

    public List<Subtask> getAllSubtasksByEpic(Epic epic) {
        List<Subtask> list = new ArrayList<>();

        epic.getIdOfSubtasks().forEach(s -> list.add(subtasks.get(s)));

        return list;
    }

    //Subtasks

    public List<Subtask> getAllSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteAllSubtask() {
        subtasks.clear();
        epics.values().forEach(e -> e.setStatus(Statuses.NEW));
    }

    public Subtask getSubtaskById(int id) {
        return subtasks.get(id);
    }

    public Subtask createSubtask(Subtask subtask) {
        if (epics.containsKey(subtask.getEpicId())) {
            subtask.setId(generateId());
            subtasks.put(subtask.getId(), subtask);
            calculateEpicStatus(epics.get(subtask.getEpicId()));
            return subtask;
        } else {
            throw new RuntimeException("The epic that the incoming subtask contains doesn't exist.");
        }
    }

    public void updateSubtask(Subtask subtaskToUpdate, int id) {
        if (epics.containsKey(subtaskToUpdate.getEpicId())) {
            if (subtasks.containsKey(id)) {
                subtasks.put(subtaskToUpdate.getId(), subtaskToUpdate);
                calculateEpicStatus(epics.get(subtaskToUpdate.getEpicId()));
            } else {
                throw new RuntimeException("The key doesn't exist in the map");
            }
        } else {
            throw new RuntimeException("The epic id in the incoming subtask doesn't exist.");
        }
    }


    private void calculateEpicStatus(Epic epic) {
        if (epics.containsKey(epic.getId())) {

            if (epic.getIdOfSubtasks().isEmpty()) {
                epic.setStatus(Statuses.NEW);
            } else if (epic.getIdOfSubtasks().stream().allMatch(s -> subtasks.get(s).getStatus().equals(Statuses.NEW))) {
                epic.setStatus(Statuses.NEW);
            } else if (epic.getIdOfSubtasks().stream().allMatch(s -> subtasks.get(s).getStatus().equals(Statuses.DONE))) {
                epic.setStatus(Statuses.DONE);
            } else {
                epic.setStatus(Statuses.IN_PROGRESS);
            }

        } else {
            throw new RuntimeException("The key doesn't exist in the map");
        }
    }

    public void deleteSubtask(Integer id) {
        Epic epic = epics.get(subtasks.get(id).getEpicId());
        subtasks.remove(id);
        calculateEpicStatus(epic);
    }
}