package me.theegg.train.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by seal on 9/16/15.
 */
@Entity
@JsonIgnoreProperties({"tickets"})
public class Train {

    public Train() {}

    @Id
    /**
     * 车次
     */
    private String number;
    /**
     * 始发站
     */
    private String start;
    /**
     * 到达站
     */
    private String end;
    /**
     * 出发时间
     */
    @Temporal(TemporalType.TIME)
    private Date departTime;
    /**
     * 到达时间
     */
    @Temporal(TemporalType.TIME)
    private Date arrivalTime;

    /**
     * 车次相关的出发（车次每天都有嘛）
     */
    @OneToMany
    private Set<Ticket> tickets = new HashSet<>();

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public Date getDepartTime() {
        return departTime;
    }

    public void setDepartTime(Date departTime) {
        this.departTime = departTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Set<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Set<Ticket> tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return "Train{" +
                "number='" + number + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", departTime=" + departTime +
                ", arrivalTime=" + arrivalTime +
                ", tickets=" + tickets +
                '}';
    }
}
