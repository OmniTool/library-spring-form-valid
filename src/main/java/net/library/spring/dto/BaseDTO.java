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

//    protected List<BookAuthorDTO> getBookAuthorDTOList(List<BookAuthor> listOfBookAuthor) {
//        List<BookAuthorDTO> listOfBookAuthorDTO = new ArrayList<>();
//        for (BookAuthor bookAuthor : listOfBookAuthor) listOfBookAuthorDTO.add(new BookAuthorDTO(bookAuthor));
//        return listOfBookAuthorDTO;
//    }
//    protected List<BookAuthor> getBookAuthorList(List<BookAuthorDTO> listOfBookAuthorDTO) {
//        List<BookAuthor> listOfBookAuthor = new ArrayList<>();
//        for (BookAuthorDTO bookAuthorDTO : listOfBookAuthorDTO) listOfBookAuthor.add(bookAuthorDTO.getEntity());
//        return listOfBookAuthor;
//    }
//    public abstract T getEntity();
//    public abstract void setEntity(T entity);
}