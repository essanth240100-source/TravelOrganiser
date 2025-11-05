package com.travelorganizer.controller;

import com.travelorganizer.model.Trip;
import com.travelorganizer.repository.TripRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class TripController {

    private final TripRepository repo;

    public TripController(TripRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/trips")
    public String listTrips(Model model) {
        List<Trip> trips = repo.findAll();
        model.addAttribute("trips", trips);
        model.addAttribute("count", trips.size());
        return "trips";
    }

    @PostMapping("/addTrip")
    public String addTrip(
            @RequestParam String tripName,
            @RequestParam String destination,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam String transportMode,
            @RequestParam(required = false) String notes,
            Model model
    ) {
        Trip t = new Trip();
        t.setTripName(tripName);
        t.setDestination(destination);
        t.setStartDate(startDate);
        t.setEndDate(endDate);
        t.setTransportMode(transportMode);
        t.setNotes(notes);
        repo.save(t);
        return "redirect:/trips";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        repo.deleteById(id);
        return "redirect:/trips";
    }

    @GetMapping("/calendar")
    public String calendar(Model model) {
        List<Trip> trips = repo.findAll();
        Map<LocalDate, List<Trip>> byDate = trips.stream()
                .collect(Collectors.groupingBy(Trip::getStartDate));
        model.addAttribute("byDate", byDate);
        return "calendar";
    }
}
