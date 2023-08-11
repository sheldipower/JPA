package dto;

public class EmployeeFullInfo {
    private Integer id;
    private String name;
    private Integer salary;
    private String positionRole;

    public EmployeeFullInfo(Integer id,String name, Integer salary, String positionRole) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.positionRole = positionRole;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPositionRole() {
        return positionRole;
    }

    public void setPositionRole(String positionRole) {
        this.positionRole = positionRole;
    }
}
}
