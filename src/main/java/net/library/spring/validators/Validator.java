package net.library.spring.validators;

public interface Validator <E> {

    boolean exists(E entity);
    void trim(E entity);
    boolean isNumber(String str);
    boolean isEmptyString(String str);
}
