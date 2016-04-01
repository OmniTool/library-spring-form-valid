package net.library.spring.validators;

public interface Validator <E> {

    boolean exists(E entity);
    void trim(E entity);
}
