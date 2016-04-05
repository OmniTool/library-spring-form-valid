package net.library.spring.validators;

public interface Validator <E> {

    boolean exists(E entity);
    E trim(E entity);
}
