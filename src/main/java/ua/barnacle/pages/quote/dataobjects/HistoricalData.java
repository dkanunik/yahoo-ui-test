package ua.barnacle.pages.quote.dataobjects;

public class HistoricalData {

    private int rowCount;

    private String emptyResultMessage;

    private String[][] data;

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public String getEmptyResultMessage() {
        return emptyResultMessage;
    }

    public void setEmptyResultMessage(String emptyResultMessage) {
        this.emptyResultMessage = emptyResultMessage;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
