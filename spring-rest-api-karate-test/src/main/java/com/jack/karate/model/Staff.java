package com.jack.karate.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.math.BigDecimal;

public class Staff implements Serializable {

    private Long id;
    private String name;
    private BigDecimal salary;

    public Staff() {
    }

    public Staff(Long id, String name, BigDecimal salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Staff staff = (Staff) o;
        return Objects.equal(id, staff.id) &&
                Objects.equal(name, staff.name) &&
                Objects.equal(salary, staff.salary);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, salary);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("salary", salary)
                .toString();
    }
}
