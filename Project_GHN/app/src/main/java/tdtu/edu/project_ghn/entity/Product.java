package tdtu.edu.project_ghn.entity;

import android.media.Image;

public class Product {
    private float weight;
    private String productSize;
    private String productType;
    private byte[] imgData;
    private String typeOfInsurance;

    public Product() {
    }

    public Product(float weight, String productSize, String productType, byte[] imgData, String typeOfInsurance) {
        this.weight = weight;
        this.productSize = productSize;
        this.productType = productType;
        this.imgData = imgData;
        this.typeOfInsurance = typeOfInsurance;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public byte[] getImgData() {
        return imgData;
    }

    public void setImgData(byte[] imgData) {
        this.imgData = imgData;
    }

    public String getTypeOfInsurance() {
        return typeOfInsurance;
    }

    public void setTypeOfInsurance(String typeOfInsurance) {
        this.typeOfInsurance = typeOfInsurance;
    }
}
