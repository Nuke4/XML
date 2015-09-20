package parsers;

import generated.Medicine;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;


class Analyzer {
    private static final Logger log = Logger.getLogger(Analyzer.class);
    public static ArrayList<Medicine> listBuilder(Element root)
            throws SAXException, IOException {
        ArrayList<Medicine> medicines
                = new ArrayList<>();
        //получение списка дочерних элементов <medicine>
        NodeList medicineNodes = root.getElementsByTagName("medicine");
        Medicine medicine = new Medicine();
        for (int i = 0; i < medicineNodes.getLength(); i++) {
            Element medicineElement = (Element) medicineNodes.item(i);
            //запонение объекта medicine
            medicine.setName(medicineElement.getAttribute("name"));
            medicine.setPrice(Integer.parseInt(getBabyValue(medicineElement, "price")));
            medicine.setDosage(Integer.parseInt(getBabyValue(medicineElement, "dosage")));
            Medicine.Visual visual = medicine.getVisual();
            //заполнение объекта visual
            Element visualElement =
                    getBaby(medicineElement, "visual");
            visual.setColor(
                    getBabyValue(visualElement, "color"));
            visual.setConsistency(
                    getBabyValue(visualElement, "consistency"));
            visual.setIndications(
                    getBabyValue(visualElement, "indications"));
            medicines.add(medicine);
            log.info("Это информационое сообщение");
        }
        return medicines;
    }

    //возвращает дочерний элемент по его имени и родителькому элементу
    private static Element getBaby(Element parent,
                                   String childName) {
        NodeList nlist =
                parent.getElementsByTagName(childName);
        Element child = (Element) nlist.item(0);
        return child;
    }

    //возвращает текст, содержащийся в элементе
    private static String getBabyValue(Element parent,
                                       String childName) {
        Element child = getBaby(parent, childName);
        Node node = child.getFirstChild();
        String value = node.getNodeValue();
        return value;
    }
}
    public class MedicineDOMParser{
        private static final Logger log = Logger.getLogger(MedicineDOMParser.class);

    public ArrayList<Medicine> parseDocument() {
        try {
            // создание DOM-анализатора
            DocumentBuilderFactory dbf =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            //распознавание XML-документа
            Document document = db.parse("src/main/resources/medicine.xml");

            //создание DOM-анализатора
            /*DOMParser parser = new DOMParser();
            parser.parse("src/main/resources/medicine.xml");
            Document document = parser.getDocument();*/

            Element root = document.getDocumentElement();
    ArrayList<Medicine>medicines = Analyzer.listBuilder(root);

            for (int i = 0; i < medicines.size(); i++){
                System.out.println(medicines.get(i));
            }
        }catch (SAXException e) {
            e.printStackTrace();
            System.out.print("ошибка SAX парсера");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("ошибка I/О потока");
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
            log.error("Это сообщение ошибки");
        }
        return null;
    }
}

    
