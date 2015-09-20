package parsers;

import generated.Medicine;
import logic.MedicineEnum;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class MedicinesStAXParser {
    private static final Logger log = Logger.getLogger(MedicinesStAXParser.class);
    private HashSet<Medicine> medicines = new HashSet<>();
    private XMLInputFactory inputFactory;
    public MedicinesStAXParser() {
        inputFactory = XMLInputFactory.newInstance();
    }
    public Set<Medicine> getMedicines() {
        return medicines;
    }
    public void buildSetMedicines(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
// StAX parsing
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase()) == MedicineEnum.MEDICINE) {
                        Medicine st = buildMedicine(reader);
                        medicines.add(st);
                        log.info("Это информационое сообщение");
                    }
                }
            }
        } catch (XMLStreamException ex) {
            System.err.println("StAX parsing error! " + ex.getMessage());
        } catch (FileNotFoundException ex) {
            System.err.println("File " + fileName + " not found! " + ex);
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                System.err.println("Impossible close file "+fileName+" : "+e);
                log.error("Это сообщение ошибки");
            }
        }
    }
    private Medicine buildMedicine(XMLStreamReader reader) throws XMLStreamException {
        Medicine st = new Medicine();
        st.setName(reader.getAttributeValue(null, MedicineEnum.NAME.getValue()));// проверить на null
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (MedicineEnum.valueOf(name.toUpperCase())) {
                        case PRICE:
                            name = getXMLText(reader);
                            st.setPrice(Integer.parseInt(name));
                            break;
                        case DOSAGE:
                            name = getXMLText(reader);
                            st.setDosage(Integer.parseInt(name));
                            break;
                        case VISUAL:
                            st.setVisual(getXMLVisual(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase()) == MedicineEnum.MEDICINE) {
                        return st;

                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Student");
    }
    private Medicine.Visual getXMLVisual(XMLStreamReader reader) throws XMLStreamException {
        Medicine.Visual visual = new Medicine.Visual();
        int type;
        String name;
        while (reader.hasNext()) {
            type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    switch (MedicineEnum.valueOf(name.toUpperCase())) {
                        case COLOR:
                            visual.setColor(getXMLText(reader));
                            break;
                        case CONSISTENCY:
                            visual.setConsistency(getXMLText(reader));
                            break;
                        case INDICATIONS:
                            visual.setIndications(getXMLText(reader));
                            break;
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (MedicineEnum.valueOf(name.toUpperCase()) == MedicineEnum.VISUAL){
                        return visual;
                    }
                    break;
            }
        }
        throw new XMLStreamException("Unknown element in tag Visual");
    }
    private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
        String text = null;
        if (reader.hasNext()) {
            reader.next();
            text = reader.getText();
        }
        return text;
    }
}




