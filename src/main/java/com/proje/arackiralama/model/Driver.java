package com.proje.arackiralama.model;

import jakarta.persistence.*;

@Entity
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    @Column(unique = true)
    private String tcNo;

    private Integer assignedCarId;

    public Driver() {}

    public Driver(Integer id, String username, String tcNo, Integer assignedCarId) {
        this.id = id;
        this.username = username;
        this.tcNo = tcNo;
        this.assignedCarId = assignedCarId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTcNo() {
        return tcNo;
    }

    public void setTcNo(String tcNo) {
        this.tcNo = tcNo;
    }

    public Integer getAssignedCarId() {
        return assignedCarId;
    }

    public void setAssignedCarId(Integer assignedCarId) {
        this.assignedCarId = assignedCarId;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", tcNo='" + tcNo + '\'' +
                ", assignedCarId=" + assignedCarId +
                '}';
    }
}
