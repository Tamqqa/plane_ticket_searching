package ru.ubusheev.springtest.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "skyscanner")
public class Aircraft {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    private LocalDate outbounddate;
    private LocalDate inbounddate;
    private int airfare;
    private String departure;
    private String destination;
    private String carrier;
    private String depCode;
    private String desCode;

    public Aircraft(){
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDesCode() {
        return desCode;
    }

    public void setDesCode(String desCode) {
        this.desCode = desCode;
    }



    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public LocalDate getOutbounddate() {
        return outbounddate;
    }

    public void setOutbounddate(LocalDate outbounddate) {
        this.outbounddate = outbounddate;
    }


    public void setAirfare(int airfare) {
        this.airfare = airfare;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }


    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public LocalDate getInbounddate() {
        return inbounddate;
    }

    public void setInbounddate(LocalDate inbounddate) {
        this.inbounddate = inbounddate;
    }

    public int getAirfare() {
        return airfare;
    }

    public boolean isInbounddateExist(){
        return inbounddate != null;
    }
}
