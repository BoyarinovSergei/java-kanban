package task;

import level.Statuses;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String description, int epic, Statuses status, int id) {
        super(name, description, status, id);
        this.epicId = epic;
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "epic=" + epicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", id=" + id +
                '}';
    }
}
