package com.jack.cucumberexplorer.model;

import com.google.common.collect.Lists;

import java.util.List;

public class Bag {


    private final List<String> things = Lists.newArrayList();

    public Bag() {
    }

    public void add(final String something){
        things.add(something);
    }


    public List<String> getThings() {
        return things;
    }

    public boolean isEmpty() {
        return things.isEmpty();
    }

    public void removeEverything() {
        things.clear();
    }
}

