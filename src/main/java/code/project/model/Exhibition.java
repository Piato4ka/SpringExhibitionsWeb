package code.project.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "exhibitions")
public class Exhibition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "exhibition_id")
    private Integer id;

    @Column(name = "exhibition_theme")
    private String themeOfExhibition;

    // TODO correct currency
    @Column(name = "exhibition_ticketPrice")
    private double ticketPrice;

    @Column(name = "exhibition_stageNumbe")
    private int stageNumber;
    // TODO period

    @Column(name = "exhibition_date")
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
