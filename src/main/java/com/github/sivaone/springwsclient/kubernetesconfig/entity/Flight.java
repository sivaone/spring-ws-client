package com.github.sivaone.springwsclient.kubernetesconfig.entity;

import java.time.LocalDateTime;

public class Flight {
    private String id;
    private String departure;
    private String arrival;
    private LocalDateTime departureDate;
    private LocalDateTime arrivalDate;
    private String aboutFlight;

    private String className;

    private String capacity;

    public Flight() {
        super();
    }

    public Flight(String id, String departure, String arrival, LocalDateTime departureDate, LocalDateTime arrivalDate,
            String aboutFlight, String className, String capacity) {
        this.id = id;
        this.departure = departure;
        this.arrival = arrival;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.aboutFlight = aboutFlight;
        this.className = className;
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public LocalDateTime getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDateTime departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDateTime getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDateTime arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public String getAboutFlight() {
        return aboutFlight;
    }

    public void setAboutFlight(String aboutFlight) {
        this.aboutFlight = aboutFlight;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getCapacity() {
        return capacity;
    }

    public void setCapacity(String capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id='" + id + '\'' +
                ", departure='" + departure + '\'' +
                ", arrival='" + arrival + '\'' +
                ", departureDate=" + departureDate +
                ", arrivalDate=" + arrivalDate +
                ", aboutFlight='" + aboutFlight + '\'' +
                ", className='" + className + '\'' +
                ", capacity='" + capacity + '\'' +
                '}';
    }
}
