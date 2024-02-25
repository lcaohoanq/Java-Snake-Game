package models.ui;

public record MenuModel(String tittle, String btn_1, String btn_2, String btn_3, String btn_4, String btn_5,
                        String btn_6, String btn_7, String btn_8, String btn_9) {
    public MenuModel() {
        this("Menu Game", "", "", "", "", "", "", "", "", "");
    }
}
