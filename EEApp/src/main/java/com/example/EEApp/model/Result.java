package com.example.EEApp.model;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "result")
@XmlType(propOrder = { "balance", "percent"})
public class Result {

        private long balance;

        private float percent;

        public long getBalance() {
                return balance;
        }

        public void setBalance(long balance) {
                this.balance = balance;
        }

        public float getPercent() {
                return percent;
        }

        public void setPercent(float percent) {
                this.percent = percent;
        }
}
