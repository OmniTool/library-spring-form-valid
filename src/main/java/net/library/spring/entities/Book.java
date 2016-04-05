package net.library.spring.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books", schema = "public", catalog = "library_test")
public class Book extends EntityBase {

    @Basic @Column(name = "title")
    @Size(min=1, max=64, message="Название: от 1 до 64 символов")
    private String title;
    @Basic @Column(name = "pub_year")
    private Integer pubYear;
    @ManyToOne(targetEntity = Genre.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "genere_id")
    private Genre genre;
    @OneToMany(targetEntity=BookAuthor.class, mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookAuthor> authorsList = new ArrayList<>();

    public Book() {}

    public List<BookAuthor> getAuthorsList() {
        return authorsList;
    }
    public void setAuthorsList(List<BookAuthor> authorsList) {
        this.authorsList = authorsList;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Integer getPubYear() {
        return pubYear;
    }
    public void setPubYear(Integer pubYear) {
        this.pubYear = pubYear;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
