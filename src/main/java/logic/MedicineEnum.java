package logic;


public enum  MedicineEnum {
    MEDICINES("medicines"),
    MEDICINE("medicine"),
    NAME("name"),
    PRICE("price"),
    DOSAGE("dosage"),
    VISUAL("visual"),
    COLOR("color"),
    CONSISTENCY("consistency"),
    INDICATIONS("indications");
    private String value;
    private MedicineEnum(String value){
        this.value = value;
    }
    public String getValue(){
        return value;
    }

}
