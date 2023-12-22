package tdtu.edu.project_ghn.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

public class DeliverOrder implements Serializable {
    private String customerPhoneNumber;
    private String customerName;
    private String customerAddress;
    private String receiverAddress;
    private Receiver receiver;
    private LocalDateTime dateTime;
    private String service;
    private String typeOfTransport;
    private Product product;

    public DeliverOrder() {}

    public DeliverOrder(String customerAddress, Receiver receiver, LocalDateTime dateTime, String service, String typeOfTransport, Product product) {
        this.customerAddress = customerAddress;
        this.receiver = receiver;
        this.dateTime = dateTime;
        this.service = service;
        this.typeOfTransport = typeOfTransport;
        this.product = product;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(String customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public Receiver getReceiver() {
        return receiver;
    }

    public void setReceiver(Receiver receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTypeOfTransport() {
        return typeOfTransport;
    }

    public void setTypeOfTransport(String typeOfTransport) {
        this.typeOfTransport = typeOfTransport;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
