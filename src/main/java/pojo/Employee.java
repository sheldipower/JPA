package pojo;

import javax.persistence.*;

@Entity
@Table
public class Employee {
    public class Employee {
        @Id()
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
        @Column(name = "name")
        private String name;
        @Column(name = "salary")
        private Integer salary;
} @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id")
    private Position position;

    public Employee() {
    }

    public Employee(Integer id, String name, Integer salary, Position position){
        this.id=id;
        this.name = name;
        this.salary = salary;
        this.position = position;
    }
    public Employee(Integer id, String name, Integer salary){
        this.id=id;
        this.name = name;
        this.salary = salary;
    }
    public Employee( String name, Integer salary){
        this.name = name;
        this.salary = salary;
    }
    public Integer getId(){return id;}
    public String getName(){return name;}
    public Integer getSalary(){return salary;}
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setSalary(Integer salary) {
        this.salary = salary;
    }
    @Override
    public String toString() {
        return  "id=" + id +
                ", Имя' " + name + '\'' +
                ", Зарплата =" + salary +
                '}';
    }
}
