package loginApp.EnumFiles;

public enum option {
    admin , student;

    private option(){}
    public String value() {
        return name();
    }

    public static option formValue(String v) {
        return valueOf(v);
    }
}
