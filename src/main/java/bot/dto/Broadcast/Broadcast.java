package bot.dto.Broadcast;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Broadcast {
    private Long message_creative_id;
    private String notification_type;
    private String schedule_time;
    private TargetingUsers targeting;

    public Broadcast(Long message_creative_id) {
        this.message_creative_id = message_creative_id;
        this.notification_type = "NO_PUSH";
        this.schedule_time = "" + Timestamp.valueOf(LocalDateTime.now().plusMinutes(1)).getTime()/1000;
        System.out.println(schedule_time);
        this.targeting = new TargetingUsers();
    }

    public Long getMessage_creative_id() {
        return message_creative_id;
    }

    public String getSchedule_time() {
        return schedule_time;
    }

    public String getNotification_type() {
        return notification_type;
    }

    public TargetingUsers getTargeting() {
        return targeting;
    }

    public void setMessage_creative_id(Long message_creative_id) {
        this.message_creative_id = message_creative_id;
    }

    public void setNotification_type(String notification_type) {
        this.notification_type = notification_type;
    }

    public void setSchedule_time(String schedule_time) {
        this.schedule_time = schedule_time;
    }

    public void setTargeting(TargetingUsers targeting) {
        this.targeting = targeting;
    }
}
