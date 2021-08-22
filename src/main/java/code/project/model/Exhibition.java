package code.project.model;

import java.time.LocalDate;


public class Exhibition {


    private String themeOfExhibition;
    // TODO correct currency
    private double ticketPrice;
    private int stageNumber;
    // TODO period
    LocalDate dateOfExhibition;

    public Exhibition() {
    }

    public Exhibition( String themeOfExhibition, double ticketPrice, int stageNumber, LocalDate dateOfExhibition) {
        this.themeOfExhibition = themeOfExhibition;
        this.ticketPrice = ticketPrice;
        this.stageNumber = stageNumber;
        this.dateOfExhibition = dateOfExhibition;
    }

    public void setThemeOfExhibition(String themeOfExhibition) {
        this.themeOfExhibition = themeOfExhibition;
    }

    public void setStageNumber(int stageNumber) {
        this.stageNumber = stageNumber;
    }

    public void setDateOfExhibition(LocalDate date) {
        this.dateOfExhibition = date;
    }
    public void setDateOfExhibition(int year, int month, int dayOfMonth) {
        this.dateOfExhibition = LocalDate.of(year, month, dayOfMonth);
    }

    public void setTicketPrice(double priceTicket) {
        this.ticketPrice = priceTicket;
    }



    public String getThemeOfExhibition() {
        return themeOfExhibition;
    }

    //TODO correct money

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int getStageNumber() {
        return stageNumber;
    }

    public LocalDate getDateOfExhibition() {
        return dateOfExhibition;
    }



}
