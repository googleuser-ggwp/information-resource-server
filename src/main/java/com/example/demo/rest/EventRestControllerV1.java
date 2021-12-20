package com.example.demo.rest;

import com.example.demo.entity.Event;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventRestControllerV1 {

    @Autowired
    private EventService eventService;

    @GetMapping
    public ResponseEntity<List<Event>> getAllEvents() {
        return new ResponseEntity<>(eventService.getAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"{id}"})
    public ResponseEntity<Event> getOneEvent(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(eventService.getOne(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Event> addEvent(@RequestBody Event newEvent) {
        return new ResponseEntity<>(eventService.add(newEvent), HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        eventService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
