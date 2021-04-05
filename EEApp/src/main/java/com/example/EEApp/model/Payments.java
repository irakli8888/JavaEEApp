package com.example.EEApp.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso(ArrayList.class)
@XmlRootElement(name = "payments")
public class Payments {

    @XmlElement(name = "payment")
    private List<Payment> paymentList = null;


    public List<Payment> getPaymentList() {
        return paymentList;
    }

    public void setPaymentList(List<Payment> paymentList) {
        this.paymentList = paymentList;
    }

}
