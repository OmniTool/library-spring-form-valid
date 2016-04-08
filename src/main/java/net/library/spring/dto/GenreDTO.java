package net.library.spring.dto;

import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.Size;

public class GenreDTO extends BaseDTO<GenreDTO> {

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

    @Override
    public boolean isIdenticalExceptId(GenreDTO genre) {
        return getTitle().toUpperCase().equals(genre.getTitle().toUpperCase())
                && getId() != genre.getId();
    }

    @Override
    public GenreDTO trim() {
        GenreDTO genreTrimmed = new GenreDTO();
        genreTrimmed.setTitle(StringUtils.trimToEmpty(getTitle()));
        genreTrimmed.setDescription(StringUtils.trimToEmpty(getDescription()));
        genreTrimmed.setId(getId());
        return genreTrimmed;
    }
}