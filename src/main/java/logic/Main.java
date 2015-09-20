package logic;


import generated.Medicine;
import parsers.MedicineDOMParser;
import parsers.MedicineSAXParser;
import parsers.MedicinesStAXParser;

import java.util.ArrayList;



/**
 *Это класс запуска валидаций и парсеров
 */

public class Main {


    public static void SAXTest() {
        ArrayList<Medicine>medicines = (ArrayList<Medicine>) new MedicineSAXParser().parseDocument();
    }

    public static void DOMTest(){
        ArrayList<Medicine> medicines = new MedicineDOMParser().parseDocument();
    }

    public static void StAXTest(){
        MedicinesStAXParser stAXParser = new MedicinesStAXParser();
        stAXParser.buildSetMedicines("src/main/resources/medicine.xml");
        System.out.println(stAXParser.getMedicines());
    }


    public static void main(String[] args) {
        System.out.println("Начинаем проверять XML-документ...");
        new XMLValidator().validate("src/main/resources/medicine.xsd", "src/main/resources/medicine.xml");
        System.out.println("\nTest SAX...");
        SAXTest();
        System.out.println("\nTest DOM...");
        DOMTest();
        System.out.println("\nTest StAX");
        StAXTest();


    }

}
