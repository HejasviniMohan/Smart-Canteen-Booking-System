package com.smartcanteen.backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartcanteen.backend.model.MenuItem;
import com.smartcanteen.backend.repository.MenuRepository;

@RestController
@RequestMapping("/api/menu")
@CrossOrigin
public class MenuController {

    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @GetMapping
    public List<MenuItem> getMenu() {
        return menuRepository.findAll();
    }

    @GetMapping("/seed")
    public String seedMenu() {
        if (menuRepository.count() == 0) {
            menuRepository.save(new MenuItem("Veg Meals", "Veg", 50, 4, true));
            menuRepository.save(new MenuItem("Chicken Biryani", "Non-Veg", 120, 5, true));
            menuRepository.save(new MenuItem("Masala Dosa", "Veg", 40, 4, false));
            menuRepository.save(new MenuItem("Egg Fried Rice", "Non-Veg", 80, 4, false));
        }
        return "Menu seeded successfully";
    }
}
