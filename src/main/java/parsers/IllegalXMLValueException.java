package parsers;

/**
 * Исключение выдается, когда значение тега или атрибута недопустимо.
 */
public class IllegalXMLValueException extends Exception{
    private static final long serialVersionUID = 1L;
    private String element;
    private boolean isAttribute;

    public IllegalXMLValueException(String name, String value, boolean isAttr) {
        super(value);
        element = name;
        isAttribute = isAttr;
    }

    @Override
    public String getMessage() {
        return "Value " + super.getMessage() + " is not allowed for " +
                (isAttribute ? "attribute" : "element") + element;
    }
}
