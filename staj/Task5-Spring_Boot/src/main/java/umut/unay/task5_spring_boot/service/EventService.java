package umut.unay.task5_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umut.unay.task5_spring_boot.entity.EventInfo;
import umut.unay.task5_spring_boot.exceptions.EventNotFoundException;
import umut.unay.task5_spring_boot.repository.EventInfoRepository;
import umut.unay.task5_spring_boot.repository.UserInfoRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.System.currentTimeMillis;

@Service
public class EventService
{
    @Autowired
    private EventInfoRepository eventRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    // Get
    public EventInfo loadEventByName(String name)
    {
        Optional<EventInfo> event = eventRepository.findByName(name);
        return event.map(EventInfo::new).orElseThrow(
                () -> new EventNotFoundException("Event not found" + name)
        );
    }

    public List<EventInfo> findAll()
    {
        return eventRepository.findAll();
    }

    public List<EventInfo> getAttendingEvents(long id)
    {
        return eventRepository.userSpecificEvents(id);
    }

    public List<EventInfo> getCategorySpecificEvents(int categoryId)
    {
        return eventRepository.categorySpecificEvents(categoryId);
    }


    // Post
    public void addEvent(EventInfo eventInfo)
    {
        eventRepository.save(eventInfo);
    }

    // Put
    public void updateEvent(int id, EventInfo eventInfo)
    {
        EventInfo event = eventRepository.findById(id);
        if (event == null)
        {
            throw new EventNotFoundException("Event not found " + id);
        }
        event.setName(eventInfo.getName());
        event.setAttendingUsers(eventInfo.getAttendingUsers());
        event.setOwnerName(eventInfo.getOwnerName());
        event.setCategoryId(eventInfo.getCategoryId());
        event.setDate(eventInfo.getDate());
        event.setDescription(eventInfo.getDescription());

        // --WILL DELETE IF FINISHED OR PAST ITS DATE --
        Date currentDate = new Date(currentTimeMillis());
        if (eventInfo.isFinished() || event.getDate().after(currentDate))
        {
            event.setFinished(true); // Meaningless but I like it this way
            eventRepository.deleteById(id);
            return;
        }

        eventRepository.save(event);
    }

    public void attendEvent(int userId, int eventId)
    {
        EventInfo event = eventRepository.findById(eventId);
        if (event == null)
        {
            throw new EventNotFoundException("Event not found " + eventId);
        }
        event.getAttendingUsers().add(userInfoRepository.findById(userId).orElseThrow(
                () -> new EventNotFoundException("User not found " + userId))
        );
        eventRepository.save(event);
    }

    // Delete
    public void deleteEvent(int id)
    {
        eventRepository.deleteById(id);
    }


}
