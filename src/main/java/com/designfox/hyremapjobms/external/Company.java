package com.designfox.hyremapjobms.external;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Company {
    private Long id;
    private String name;
    private String description;
    private int employeeCount;
    private String location;

    public Company(){}

    public Company(Long id, String name, String description, int employeeCount, String location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.employeeCount = employeeCount;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        this.employeeCount = employeeCount;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
