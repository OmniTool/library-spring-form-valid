package net.library.spring.dto;

import net.library.spring.entities.Genre;

import javax.validation.constraints.Size;

public class GenreDTO extends BaseDTO<Genre> {

    @Size(min=1, max=64, message="Название: от 1 до 64 символов")
    private String title;
    @Size(max=400, message="Описание: до 400 символов")
    private String description;

    public GenreDTO() {}

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}