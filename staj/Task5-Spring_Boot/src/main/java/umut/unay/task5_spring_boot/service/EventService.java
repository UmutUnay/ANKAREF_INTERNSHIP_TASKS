package umut.unay.task5_spring_boot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umut.unay.task5_spring_boot.entity.EventInfo;
import umut.unay.task5_spring_boot.entity.UserInfo;
import umut.unay.task5_spring_boot.exceptions.EventNotFoundException;
import umut.unay.task5_spring_boot.repository.EventInfoRepository;
import umut.unay.task5_spring_boot.repository.UserInfoRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static java.lang.System.currentTimeMillis;

@Service
public class EventService
{
    private static final Logger log = LoggerFactory.getLogger(EventService.class);
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

    public List<EventInfo> getCloseEvents() throws ParseException {
        DateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        Date currentDate = new Date(currentTimeMillis());
        Date nextWeek = new Date(currentTimeMillis() + 604800000);

        Date startDate = formatter.parse(formatter.format(currentDate));
        Date endDate = parser.parse(formatter.format(nextWeek));

        log.info("Current date: " + startDate);
        log.info("Next week: " + endDate);
        return eventRepository.findEventsInBetween(startDate, endDate);
    }

    public Date getEventDate(long eventId)
    {
        EventInfo event = eventRepository.findById(eventId);
        if (event == null)
        {
            throw new EventNotFoundException("Event not found " + eventId);
        }
        log.info("Event date: " + event.getDate());
        return event.getDate();
    }

    public List<EventInfo> getAttendingEvents(long id)
    {
        return eventRepository.userSpecificEvents(id);
    }

    public List<EventInfo> getCategorySpecificEvents(int categoryId)
    {
        return eventRepository.categorySpecificEvents(categoryId);
    }

    public EventInfo getEvent(int id)
    {
        return eventRepository.findById(id);
    }

    public List<UserInfo> getAttendees(long eventId)
    {
        return eventRepository.getAttendees(eventId);
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
