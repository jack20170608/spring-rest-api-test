package com.jack.karate.web;

import com.jack.karate.model.Cat;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/cats")
public class CatsController {
    
    private final AtomicInteger counter = new AtomicInteger();
    private final Map<Integer, Cat> cats = new ConcurrentHashMap<>();
    
    @PostMapping
    public Cat create(@RequestBody Cat cat) {
        int id = counter.incrementAndGet();
        cat.setId(id);
        cats.put(id, cat);
        return cat;
    }
    
    @GetMapping
    public Collection<Cat> list() {
        return cats.values();
    }
    
    @GetMapping("/{id:.+}")
    public Cat get(@PathVariable int id) {
        return cats.get(id);
    }
    
    @GetMapping("/{id:.+}/kittens")
    public Collection<Cat> getKittens(@PathVariable int id) {
        return cats.get(id).getKittens();
    } 
    
    @PutMapping("/{id:.+}")
    public Cat put(@PathVariable int id, @RequestBody Cat cat) {
        cats.put(id, cat);
        return cat;        
    }    
    
    @DeleteMapping("/{id:.+}")
    public void delete(@PathVariable int id) {        
        Cat cat = cats.remove(id);
        if (cat == null) {
            throw new RuntimeException("cat not found, id: " + id);
        }
    }

    @DeleteMapping
    public void deleteWithBody(@RequestBody Cat cat) {
        int id = cat.getId();
        delete(id);
    }    
    
}