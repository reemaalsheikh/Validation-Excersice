package com.example.q2validationexcersice.Controller;

import com.example.q2validationexcersice.Api.ApiResponse;
import com.example.q2validationexcersice.Model.Event;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;


    @RestController
    @RequestMapping("/api/v1/event")
    public class EventConroller {


        ArrayList<Event> events = new ArrayList<>();

        //• Create (add) a new event (ID , description , capacity, startDate , endDate)
        @PostMapping("/add")
        public ResponseEntity CreateEvent(@Valid @RequestBody Event event, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            events.add(event);
            return ResponseEntity.status(200).body(new ApiResponse("Event added successfully"));

        }

//• Display all event .

        @GetMapping("/get/events")
        public ResponseEntity getEvents() {
            return ResponseEntity.status(200).body(events);
        }

        //• Update a event
        @PutMapping("/update/{index}")
        public ResponseEntity updateEvent(@PathVariable int index, @Valid @RequestBody Event event, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }
            events.set(index, event);
            return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
        }


        //• Delete a event
        @DeleteMapping("/delete/{index}")
        public ResponseEntity deleteEvent(@PathVariable int index) {
            events.remove(index);
            return ResponseEntity.status(200).body(new ApiResponse("Event deleted successfully"));
        }


        //• Change capacity
        @PutMapping("/ucapacity/{index}")
        public ResponseEntity changeCapacity(@PathVariable int index, @Valid @RequestBody Event capacity, Errors errors) {
            if (errors.hasErrors()) {
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(400).body(message);
            }

            events.get(index).setCapacity(capacity.getCapacity());
            return ResponseEntity.status(200).body(new ApiResponse("Event updated successfully"));
        }


        //• Search for a event by given id
        @GetMapping("/getbyid/{id}")
        public ResponseEntity getEventById(@PathVariable String id ) {

            for (Event event : events) {
                if (event.getId().equals(id)) {
                    return ResponseEntity.status(200).body(event);
                }
            }
            return ResponseEntity.status(404).body(new ApiResponse("Event not found"));
        }



    }

