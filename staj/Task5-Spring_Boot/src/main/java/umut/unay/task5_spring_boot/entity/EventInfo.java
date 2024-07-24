package umut.unay.task5_spring_boot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventInfo
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private int categoryId;

    @ManyToMany
    @JoinTable(
            name = "event_user",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<UserInfo> attendingUsers;

    private String ownerName;
    private Date date;
    private boolean isFinished;

    public EventInfo(EventInfo eventInfo)
    {
        this.id = eventInfo.getId();
        this.name = eventInfo.getName();
        this.description = eventInfo.getDescription();
        this.categoryId = eventInfo.getCategoryId();
        this.attendingUsers = eventInfo.getAttendingUsers();
        this.ownerName = eventInfo.getOwnerName();
        this.date = eventInfo.getDate();
        this.isFinished = eventInfo.isFinished();
    }
}
