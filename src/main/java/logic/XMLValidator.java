package logic;
import java.io.File;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
/**
 * Этот класс проверяет XML-документ согласно схеме XSD.
 */
public class XMLValidator {
    /**
     * Проверяет XML-документ согласно схеме XSD.
     * @param xsdSchema имя файла схемы XSD
     * @param xmlFile XML-документ для проверки
     */
    public void validate(String xsdSchema, String xmlFile) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance(
                    XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = schemaFactory.newSchema(new File(xsdSchema));
            Validator validator = schema.newValidator();

            validator.validate(new StreamSource(new File(xmlFile)));

        } catch (Exception e) {
            System.out.println("Файл " + xmlFile + " не валиден!\n" + e.getMessage());
            return;
        }

        System.out.println("Файл " + xmlFile + " валиден!");
    }
}

