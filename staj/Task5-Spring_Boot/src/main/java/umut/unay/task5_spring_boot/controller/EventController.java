package umut.unay.task5_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umut.unay.task5_spring_boot.entity.EventInfo;
import umut.unay.task5_spring_boot.entity.UserInfo;
import umut.unay.task5_spring_boot.service.EventService;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/event")
public class EventController
{
    @Autowired
    private EventService service;

    // Get
    @GetMapping("/all-events")
    public List<EventInfo> getAllEvents()
    {
        return service.findAll();
    }

    @GetMapping("/close-events")
    public List<EventInfo> getCloseEvents() throws ParseException {
        return service.getCloseEvents();
    }

    @GetMapping("/get-event-date-{eventId}")
    public Date getEventDate(@PathVariable long eventId)
    {
        return service.getEventDate(eventId);
    }
    
    @GetMapping("/get-attendees-{eventId}")
    public List<UserInfo> getAttendees(@PathVariable long eventId)
    {
        return service.getAttendees(eventId);
    }

    @GetMapping("/attending-events-{eventId}")
    public List<EventInfo> getAttendingEvents(@PathVariable long eventId)
    {
        return service.getAttendingEvents(eventId);
    }

    @GetMapping("/category-{categoryId}")
    public List<EventInfo> getCategorySpecificEvents(@RequestBody int categoryId)
    {
        return service.getCategorySpecificEvents(categoryId);
    }

    @GetMapping("/{id}")
    public EventInfo getEvent(@PathVariable int id)
    {
        return service.getEvent(id);
    }

    // Post
    @PostMapping("/add-event")
    public void addNewEvent(@RequestBody EventInfo eventInfo)
    {
        service.addEvent(eventInfo);
    }

    // Put
    @PostMapping("/update-event-{id}")
    public void updateEvent(@PathVariable int id,@RequestBody EventInfo eventInfo)
    {
        service.updateEvent(id, eventInfo);
    }

    @PostMapping("/attend-event-{userId}-{eventId}")
    public void attendEvent(@PathVariable int userId,@PathVariable int eventId)
    {
        service.attendEvent(userId, eventId);
    }

    // Delete
    @PostMapping("/delete-event-{id}")
    public void deleteEvent(@PathVariable int id)
    {
        service.deleteEvent(id);
    }

}
