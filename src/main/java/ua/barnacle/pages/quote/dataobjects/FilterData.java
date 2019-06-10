package ua.barnacle.pages.quote.dataobjects;

public class FilterData {

    private String timePeriod;
    private String show;
    private String frequency;
    private String startDate;
    private String endDate;

    public FilterData() {
    }

    public FilterData(String timePeriod, String show, String frequency) {
        this.timePeriod = timePeriod;
        this.show = show;
        this.frequency = frequency;
    }

    public FilterData(String startDate, String endDate, String show, String frequency) {
        this.show = show;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void setTimePeriod(String timePeriod) {
        this.timePeriod = timePeriod;
    }

    public void setShow(String show) {
        this.show = show;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getTimePeriod() {
        return timePeriod;
    }

    public String getShow() {
        return show;
    }

    public String getFrequency() {
        return frequency;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "time period: [" + this.timePeriod + "], "
                + "\nshow: [" + this.show + "], "
                + "\nfrequency: [" + this.frequency + "], "
                + "\nstart date: [" + this.startDate + "], "
                + "\nend date: [" + this.endDate + "];";
    }
}
