package net.library.spring.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books", schema = "public", catalog = "library_test")
public class Book extends EntityBase {

    @Basic
    @Column(name = "title")
    private String title;
    @Basic
    @Column(name = "pub_year")
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
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book books = (Book) o;
        if (title != null ? !title.equals(books.title) : books.title != null) return false;
        if (pubYear != null ? !pubYear.equals(books.pubYear) : books.pubYear != null) return false;
        return true;
    }
    @Override
    public int hashCode() {
        int result = 31;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (pubYear != null ? pubYear.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "BookHiber{" +
                "id='" + id + '\'' +
                "title='" + title + '\'' +
                ", pubYear=" + pubYear +
                ", genre=" + genre +
                '}';
    }
}
