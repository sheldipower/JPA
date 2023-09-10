package com.example.demo.dto;

import com.example.demo.pojo.Position;
@AllArgsConstructor
@NoArgsConstructor
public class PositionDTO {
    private Integer positionId;
    private String role;

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


    @Override
    public String toString() {
        return "PositionDTO{" +
                "positionId=" + positionId +
                ", role='" + role + '\'' +
                '}';
    }
}

