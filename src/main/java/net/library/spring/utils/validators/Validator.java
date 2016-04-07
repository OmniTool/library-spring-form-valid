package net.library.spring.utils.validators;

public interface Validator <E> {

    boolean exists(E entity);
}
