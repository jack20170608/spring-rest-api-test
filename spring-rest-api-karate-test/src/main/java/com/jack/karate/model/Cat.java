package com.jack.karate.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cat implements Serializable {

    private int id;
    private String name;
    private List<Cat> kittens;

    public Cat(int id, String name, List<Cat> kittens) {
        this.id = id;
        this.name = name;
        this.kittens = kittens;
    }

    public void addKitten(Cat kitten) {
        if (kittens == null) {
            kittens = new ArrayList<>();
        }
        kittens.add(kitten);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Cat> getKittens() {
        return kittens;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("kittens", kittens)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return id == cat.id &&
                Objects.equal(name, cat.name) &&
                Objects.equal(kittens, cat.kittens);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, kittens);
    }
}
