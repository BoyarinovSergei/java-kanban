package service;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> tenElementsOfMemory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (tenElementsOfMemory.size() == 10) {
            tenElementsOfMemory.remove(0);
            tenElementsOfMemory.add(task);
        } else {
            tenElementsOfMemory.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return tenElementsOfMemory;
    }
}
