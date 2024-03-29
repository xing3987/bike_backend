package com.example.bike.dto;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bike")
public class Bike {

    private String id;
    private String status;
    private String latitude; //纬度
    private String longitude; //经度
    private String qrCode; //车辆身份码

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }
}
