package hellojpa;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notification_id")
    private Long id;

    private String contents;

    @OneToOne(mappedBy = "notification", fetch = FetchType.LAZY, orphanRemoval = true)
    private ScheduleInfo scheduleInfo;

    public Notification() {
    }

    public Notification(String contents) {
        this.contents = contents;
    }
}