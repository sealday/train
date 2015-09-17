package me.theegg.train.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

/**
 * Created by seal on 9/16/15.
 */
@Entity
public class Ticket {

    /**
     * 为了hibernate和json生成
     */
    public Ticket() {}
    /**
     * 编号
     */
    @Id
    @GeneratedValue
    private Long id;

    /**
     * 商务座
     */
    private int business;

    /**
     * 特等座
     */
    private int state;

    /**
     * 一等座
     */
    private int firstClass;

    /**
     * 二等座
     */
    private int secondClass;

    /**
     * 站票
     */
    private int standing;

    /**
     * 对应车次
     */
    @ManyToOne
    @JoinColumn
    private Train train;

    /**
     * 出发日期
     */
    @Temporal(TemporalType.DATE)
    private Date departDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public int getBusiness() {
        return business;
    }

    public void setBusiness(int business) {
        this.business = business;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(int firstClass) {
        this.firstClass = firstClass;
    }

    public int getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(int secondClass) {
        this.secondClass = secondClass;
    }

    public int getStanding() {
        return standing;
    }

    public void setStanding(int standing) {
        this.standing = standing;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", business=" + business +
                ", state=" + state +
                ", firstClass=" + firstClass +
                ", secondClass=" + secondClass +
                ", standing=" + standing +
                ", train=" + train +
                ", departDate=" + departDate +
                '}';
    }
}
