package com.example.demo.dto;

import com.example.demo.pojo.Position;

public class PositionDTO {
    private Integer position_id;
    private String role;

    public static PositionDTO fromPosition(Position position) {
        PositionDTO positionDTO = new PositionDTO();
        positionDTO. setPosition_id (position.getPosition_id());
        positionDTO.setRole(position.getRole());
        return positionDTO;
    }
    public Position toPosition(){
        Position position = new Position();
        position.setPosition_id(this.getPosition_id());
        position.setRole(this.getRole());
        return position;
    }

    public Integer getPosition_id() {
        return position_id;
    }

    public void setPosition_id(Integer position_id) {
        this.position_id = position_id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

