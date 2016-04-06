package net.library.spring.dto;

import net.library.spring.entities.BookAuthor;
import net.library.spring.entities.EntityBase;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseDTO<T extends EntityBase> {

    private int id;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}