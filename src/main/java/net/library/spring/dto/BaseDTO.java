package net.library.spring.dto;

public abstract class BaseDTO<B extends BaseDTO> {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public abstract B trim();

    public abstract boolean isIdenticalExceptId(B dto);
}