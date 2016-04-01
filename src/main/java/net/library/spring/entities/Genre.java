package net.library.spring.entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "genres", schema = "public", catalog = "library_test")
public class Genre extends EntityBase {
    @Basic
    @Column(name = "title")
    @Size(min=1, max=64, message="Название: от 1 до 64 символов")
    private String title;
    @Basic
    @Column(name = "description")
    @Size(max=400, message="Описание: до 400 символов")
    private String description;

    public Genre() {}

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genres = (Genre) o;
        if (title != null ? !title.equals(genres.title) : genres.title != null) return false;
        if (description != null ? !description.equals(genres.description) : genres.description != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "GenreHiber{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
