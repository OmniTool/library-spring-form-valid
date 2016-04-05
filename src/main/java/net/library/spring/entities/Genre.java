package net.library.spring.entities;

import javax.persistence.*;

@Entity
@Table(name = "genres", schema = "public", catalog = "library_test")
public class Genre extends EntityBase {

    @Basic @Column(name = "title")
    private String title;
    @Basic @Column(name = "description")
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
}
