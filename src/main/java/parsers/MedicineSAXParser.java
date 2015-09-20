package parsers;

import generated.Medicine;

import org.apache.log4j.Logger;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.List;


/**
 * Это - обработчик документа для синтаксического анализатора SAX.
 */
class SimpleMedicineHandler extends DefaultHandler {
    private static final Logger log = Logger.getLogger(SimpleMedicineHandler.class);
    @Override
    public void startDocument() {
        System.out.println("SAX parser start");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attrs) {
        String s = localName;
        // получение и вывод информации об атрибутах элемента
        for (int i = 0; i < attrs.getLength(); i++) {
            s += " " + attrs.getLocalName(i) + " = " + attrs.getValue(i);
        }
        log.info("Это информационое сообщение");

        System.out.print(s.trim());
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        System.out.print(new String(ch, start, length));
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        System.out.print(qName);
    }

    @Override
    public void endDocument() {
        System.out.println("\nSAX Parsing ended");
    }



}

public class MedicineSAXParser {
    /**
     * Анализирует XML-документ и создает список.
     */
    public List<Medicine> parseDocument() {
        final Logger log = Logger.getLogger(MedicineSAXParser.class);
        try {
            // создание SAX-анализатора
            XMLReader reader = XMLReaderFactory.createXMLReader();
            SimpleMedicineHandler handler = new SimpleMedicineHandler();
            reader.setContentHandler(handler);
            reader.parse("src/main/resources/medicine.xml");
        }catch (SAXException e) {
            e.printStackTrace();
            System.out.print("ошибка SAX парсера");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("ошибка I/О потока");
            log.error("Это сообщение ошибки");
        }
        return null;
    }
}