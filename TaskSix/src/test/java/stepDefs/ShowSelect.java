package stepDefs;

public enum ShowSelect {
    Дешевле ("1"),
    Дороже ("2"),
    умолчанию ("101"),
    дате ("104");

    public String id;
    ShowSelect (String id) {
        this.id = id;
    }
}
