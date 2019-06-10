package ua.barnacle.types;

public enum TabLabel {

    HISTORICAL_DATA("Historical Data"),
    CHART("Chart"),
    PROFILE("Profile");

    private String text;

    TabLabel(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
