package task;

import level.Statuses;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {
    private final List<Integer> idOfSubtasks;

    public Epic(String name, String description, Statuses status) {
        super(name, description, status);
        this.idOfSubtasks = new ArrayList<>();
    }

    public Epic(String name, String description, Statuses status, int id) {
        super(name, description, status, id);
        this.idOfSubtasks = new ArrayList<>();
    }

    public void addIdOfSubtask(int id) {
        idOfSubtasks.add(id);
    }

    public void deleteAllIdOfSubtasks() {
        idOfSubtasks.clear();
    }

    public void deleteSubtaskIdById(int id) {
        idOfSubtasks.remove(id);
    }

    public List<Integer> getIdOfSubtasks() {
        return idOfSubtasks;
    }

    @Override
    public String toString() {
        return "Epic{" +
                "subtasks=" + idOfSubtasks +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Epic)) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(idOfSubtasks, epic.idOfSubtasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), idOfSubtasks);
    }
}
