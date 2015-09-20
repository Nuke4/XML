package parsers;

/**
 * Исключение выдается, когда синтаксический анализатор
 * не может найти атрибут именем.
 */
public class NoSuchAttributeException extends Exception{
    private static final long serialVersionUID = 1L;

    public NoSuchAttributeException(String attribute) {
        super(attribute);
    }

    @Override
    public String getMessage() {
        return "Attribute \'" + super.getMessage() + "\' not found";
    }
}
