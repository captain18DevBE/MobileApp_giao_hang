package tdtu.edu.project_ghn.model;

import java.time.LocalDateTime;


public class OrderDTO {
    private String address;

    private LocalDateTime dateTime;
    private String type;
    private String phoneNumber;
    private String state;

    public OrderDTO() {
    }

    public OrderDTO(String address, LocalDateTime dateTime, String type, String phoneNumber, String state) {
        this.address = address;
        this.dateTime = dateTime;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getType() {
        return type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getState() { return state; }
}
