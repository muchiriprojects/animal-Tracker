package com.app.animalTracker.controllers;

import com.app.animalTracker.models.Animal;
import com.app.animalTracker.models.Sighting;
import com.app.animalTracker.repository.AnimalRepository;
import com.app.animalTracker.repository.SightingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private AnimalRepository animalRepository;

    @Autowired
    private SightingsRepository sightingsRepository;

    @GetMapping("/")
    public String index(Model model){
        List<Animal> animals = new ArrayList<>();
        animals.addAll(animalRepository.findAll());
        model.addAttribute("animals", animals);
        return "index";
    }

    @GetMapping("/sightings")
    public String sighting(Model model){
        List<Animal> animals = new ArrayList<>();
        List<Sighting> sightings=new ArrayList<>();
        animals.addAll(animalRepository.findAll());
        sightings.addAll(sightingsRepository.findAll());
        model.addAttribute("animals", animals);
        model.addAttribute("sightings", sightings);
        return "sightings";
    }

//    post animal sighting
    @PostMapping("/sightings")
    public String addSighting(@ModelAttribute Sighting sighting, Model model){
//        System.out.println(animal.toString());
        try {
            sightingsRepository.save(sighting);
            model.addAttribute("message","Animal Sighting added successfully.");
            List<Sighting> sightings=new ArrayList<>();
            sightings.addAll(sightingsRepository.findAll());

            List<Animal> animals = new ArrayList<>();
            animals.addAll(animalRepository.findAll());

            model.addAttribute("animals", animals);
            model.addAttribute("sightings", sightings);
        }catch (Exception e){
            System.out.println(e);
        }

        return "sightings";
    }

//    add animal
    @PostMapping("/")
    public String addAnimal(@ModelAttribute Animal animal, Model model){
//        System.out.println(animal.toString());
        try {
            Animal added_animal=animalRepository.save(animal);
            model.addAttribute("message","Animal with name "+added_animal.getName()+" added successfully.");
            List<Animal> animals = new ArrayList<>();
            animals.addAll(animalRepository.findAll());
            model.addAttribute("animals", animals);
        }catch (Exception e){
            System.out.println(e);
        }

        return "index";
    }

//    edit animal
    @GetMapping("animal/edit/{id}")
    public String editAnimal(@PathVariable Long id, Model model){
        List<Animal> animals = new ArrayList<>();
        animals.addAll(animalRepository.findAll());
        model.addAttribute("animals", animals);
        model.addAttribute("animal", animalRepository.getById(id));
        return "edit_animal";
    }

//    Update animal
    @PostMapping("animal/edit/{id}")
    public String updateAnimal(@PathVariable Long id, Animal animal, Model model){
        Animal fetchedDbAnimal=animalRepository.getById(id);
        fetchedDbAnimal.setName(animal.getName());
        fetchedDbAnimal.setAge(animal.getAge());
        fetchedDbAnimal.setHealth(animal.getHealth());
        animalRepository.save(fetchedDbAnimal);

        List<Animal> animals = new ArrayList<>();
        animals.addAll(animalRepository.findAll());
        model.addAttribute("animals", animals);
        model.addAttribute("message","Animal Updated successfully.");
        return "index";
    }

    //    delete animal
    @GetMapping("animal/delete/{id}")
    public String deleteAnimal(@PathVariable Long id,  Model model){
        animalRepository.deleteById(id);

        List<Animal> animals = new ArrayList<>();
        animals.addAll(animalRepository.findAll());

        model.addAttribute("animals", animals);
        model.addAttribute("message","Animal Deleted successfully.");
        return "index";
    }

    //    delete animal sighting
    @GetMapping("sightings/delete/{id}")
    public String deleteSighting(@PathVariable Long id,  Model model){
        sightingsRepository.deleteById(id);

        List<Animal> animals = new ArrayList<>();
        animals.addAll(animalRepository.findAll());

        List<Sighting> sightings = new ArrayList<>();
        sightings.addAll(sightingsRepository.findAll());

        model.addAttribute("animals", animals);
        model.addAttribute("sightings", sightings);
        model.addAttribute("message","Sighting Deleted successfully.");
        return "sightings";
    }

}
