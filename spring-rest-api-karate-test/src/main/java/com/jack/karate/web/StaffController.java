package com.jack.karate.web;

import com.jack.karate.dao.StaffRepositoryImpl;
import com.jack.karate.model.Staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/staff")
public class StaffController {

    private final StaffRepositoryImpl staffRepository;

    private final AtomicLong counter = new AtomicLong(100);

    @Autowired
    public StaffController(StaffRepositoryImpl staffRepository) {
        this.staffRepository = staffRepository;
    }

    @PostMapping
    public Long create(final @RequestBody Staff staff) {
        Long id = counter.incrementAndGet();
        staff.setId(id);
        new Thread(() -> staffRepository.put(staff)).start();
        return id;
    }
    
    @GetMapping
    public Collection<Staff> list() {
        return staffRepository.getAll();
    }
    
    @GetMapping("/{id:.+}")
    public Staff get(@PathVariable long id) {
        return staffRepository.get(id);
    }
    

    @PutMapping("/{id:.+}")
    public Staff put(@PathVariable long id, @RequestBody Staff staff) {
        staffRepository.put(staff);
        return staff;
    }
    
    @DeleteMapping("/{id:.+}")
    public void delete(@PathVariable long id) {
        Staff staff = staffRepository.remove(id);
        if (staff == null) {
            throw new RuntimeException("staff not found, id: " + id);
        }
    }
}//:~