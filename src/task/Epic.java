package task;

import level.Statuses;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<Integer> idOfSubtasks;

    public Epic(String name, String description, Statuses status, int id) {
        super(name, description, status, id);
        this.idOfSubtasks = new ArrayList<>();
    }

    public Epic(String name, String description, Statuses status, int id, List<Integer> idOfSubtasks) {
        super(name, description, status, id);
        this.idOfSubtasks = idOfSubtasks;
    }

    public List<Integer> getIdOfSubtasks() {
        return idOfSubtasks;
    }

    public void setIdOfSubtasks(List<Integer> idOfSubtasks) {
        this.idOfSubtasks = idOfSubtasks;
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
}
