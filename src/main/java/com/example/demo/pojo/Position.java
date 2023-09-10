package com.example.demo.pojo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table
@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer positionId;

    private String role;
    @OneToMany(mappedBy = "position")
    private List<Employee> employeeList= new ArrayList<Employee>();

    public Position() {
    }

    public Position(String role) {
        this.role = role;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Position{" +
                "positionId=" + positionId +
                ", role='" + role + '\'' +
                '}';
    }
}
