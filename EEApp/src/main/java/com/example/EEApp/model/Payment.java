package com.example.EEApp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;


@XmlRootElement(name = "payment")
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlType(propOrder = { "name", "supplyDate", "state", "part","value"})
public class Payment {

    private String name;

    private Date supplyDate;

    private boolean state;

    private char part;

    private long value;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSupplyDate() {
        return supplyDate;
    }

    public void setSupplyDate(Date supplyDate) {
        this.supplyDate = supplyDate;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public char getPart() {
        return part;
    }

    public void setPart(char part) {
        this.part = part;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "name='" + name + '\'' +
                ", supplyDate=" + supplyDate +
                ", state=" + state +
                ", part=" + part +
                ", value=" + value +
                '}';
    }
}



