package umut.unay.task5_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import umut.unay.task5_spring_boot.entity.EventInfo;
import umut.unay.task5_spring_boot.service.EventService;

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

    @GetMapping("/attending-events-{eventId}")
    public List<EventInfo> getAttendingEvents(@PathVariable long eventId)
    {
        return service.getAttendingEvents(eventId);
    }

    @GetMapping("/event-{categoryId}")
    public List<EventInfo> getCategorySpecificEvents(@RequestBody int categoryId)
    {
        return service.getCategorySpecificEvents(categoryId);
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
