package pojo;

import javax.persistence.*;

@Table
@Entity
public class Position {
    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer position_id;
    @Column(name = "role")
    private String role;


    public Position() {
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

    @Override
    public String toString() {
        return "Position{" +
                "position_id=" + position_id +
                ", role='" + role + '\'' +
                '}';
    }

}

