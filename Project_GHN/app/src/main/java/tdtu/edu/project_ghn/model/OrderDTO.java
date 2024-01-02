package tdtu.edu.project_ghn.model;

import java.io.Serializable;
import java.time.LocalDateTime;


public class OrderDTO implements Serializable {
    String key;
    private String email;
    private Long id;
    private String address;

    private String dateTime;
    private String type;
    private String phoneNumber;
    private String state;

    public OrderDTO() {
    }

    public OrderDTO(String key, String address, String dateTime, String type, String phoneNumber, String state) {
        this.key = key;
        this.address = address;
        this.dateTime = dateTime;
        this.type = type;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public String getDateTime() {
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

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getState() { return state; }

    public String getKey() {
        return key;
    }
}
