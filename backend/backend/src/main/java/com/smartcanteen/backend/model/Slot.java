package com.smartcanteen.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "slots")
public class Slot {

    @Id
    private String id;
    private String time;
    private int capacity;
    private int booked;

    public Slot() {}

    public Slot(String time, int capacity) {
        this.time = time;
        this.capacity = capacity;
        this.booked = 0;
    }

    public String getId() { return id; }
    public String getTime() { return time; }
    public int getCapacity() { return capacity; }
    public int getBooked() { return booked; }

    public void setBooked(int booked) { this.booked = booked; }
}
