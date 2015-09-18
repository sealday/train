package me.theegg.train.model;


import javax.persistence.*;
import java.util.Date;

/**
 * Created by seal on 9/17/15.
 */
@Entity
public class Tessera {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn
    private Ticket ticket;

    @ManyToOne
    @JoinColumn
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date datetime;

    @Enumerated(EnumType.STRING)
    private TesseraStatus tesseraStatus = TesseraStatus.UNPAID;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    public Tessera() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "Tessera{" +
                "id=" + id +
                ", ticket=" + ticket +
                ", user=" + user +
                ", datetime=" + datetime +
                '}';
    }

    public TesseraStatus getTesseraStatus() {
        return tesseraStatus;
    }

    public void setTesseraStatus(TesseraStatus tesseraStatus) {
        this.tesseraStatus = tesseraStatus;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public static enum TesseraStatus {
        UNPAID,
        PAID,
        CHANGED,
        RETURNED
    }

    public static enum SeatType {
        BUSINESS,
        STATE,
        FIRST_CLASS,
        SECOND_CLASS,
        STANDING
    }
}
