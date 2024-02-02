package service;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private final List<Task> elementsOfMemory = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (task != null) {
            if (elementsOfMemory.size() == 10) {
                elementsOfMemory.remove(0);
            }
            elementsOfMemory.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(elementsOfMemory);
    }
}
