package bot.dto.Broadcast;

public class TargetingUsers {
    private Labels labels;

    public TargetingUsers() {
        this.labels = new Labels();
    }

    public TargetingUsers(Labels labels) {
        this.labels = labels;
    }

    public Labels getLabels() {
        return labels;
    }

    public void setLabels(Labels labels) {
        this.labels = labels;
    }
}
