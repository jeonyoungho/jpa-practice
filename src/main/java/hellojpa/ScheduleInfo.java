package hellojpa;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Data
@Entity
public class ScheduleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ZonedDateTime sendDateTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "notification_id")
    private Notification notification;

    public ScheduleInfo() {
    }

    public ScheduleInfo(Notification notification) {
        this.sendDateTime = ZonedDateTime.now();
        this.notification = notification;
    }


}