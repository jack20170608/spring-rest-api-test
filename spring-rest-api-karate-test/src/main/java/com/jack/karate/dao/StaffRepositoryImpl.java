package com.jack.karate.dao;

import com.google.common.collect.Maps;
import com.jack.karate.model.Staff;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

@Component
public class StaffRepositoryImpl {

    private static final Logger logger = LoggerFactory.getLogger(StaffRepositoryImpl.class);

    private static Map<Long, Staff> staffDB = Maps.newConcurrentMap();

    @PostConstruct
    private void initSomeData(){
        staffDB.put(1L, new Staff(1L,"JACK", new BigDecimal("100000")));
        staffDB.put(2L, new Staff(2L,"LYLIAN", new BigDecimal("100000")));
    }

    public void put(Staff staff) {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            throw new RuntimeException("Persistance failure...");
        }
        staffDB.put(staff.getId(), staff);
        logger.info("Staff persist successfully.");
    }

    public Staff get(Long id){
        return staffDB.get(id);
    }

    public Collection<Staff> getAll(){
        return staffDB.values();
    }

    public Staff remove(Long id){
        return staffDB.remove(id);
    }
}
