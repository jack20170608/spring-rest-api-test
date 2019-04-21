package com.jack.cucumberexplorer.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class User {

    private Long id;

    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equal(id, user.id) &&
                Objects.equal(name, user.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name);
    }
}
