package com.smartcanteen.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcanteen.backend.model.Slot;
import com.smartcanteen.backend.repository.SlotRepository;

@RestController
@RequestMapping("/api/slots")
@CrossOrigin
public class SlotController {

    private final SlotRepository slotRepo;

    public SlotController(SlotRepository slotRepo) {
        this.slotRepo = slotRepo;
    }

    @GetMapping
    public List<Slot> getSlots() {
        if (slotRepo.count() == 0) {
            slotRepo.save(new Slot("08:00 - 09:00", 20));
            slotRepo.save(new Slot("09:00 - 10:00", 20));
            slotRepo.save(new Slot("12:00 - 01:00", 30));
        }
        return slotRepo.findAll();
    }

    @PostMapping("/book/{id}")
    public String bookSlot(@PathVariable String id) {
        Slot slot = slotRepo.findById(id).orElse(null);
        if (slot == null) return "Slot not found";

        if (slot.getBooked() >= slot.getCapacity()) {
            return "Slot full";
        }

        slot.setBooked(slot.getBooked() + 1);
        slotRepo.save(slot);
        return "Slot booked successfully";
    }
}
