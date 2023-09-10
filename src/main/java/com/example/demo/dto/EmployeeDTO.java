package com.example.demo.dto;

import com.example.demo.pojo.Employee;
@Data
    public class EmployeeDTO {
        private String name;
        private int salary;
        private Integer position_id_employee_id;

        public  EmployeeDTO (String name, int salary, Integer position_id_employee_id) {
            this.name = name;
            this.salary = salary;
           // this position = position;
            this.position_id_employee_id = position_id_employee_id;
        }

    public EmployeeDTO() {
    }


    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", position_id_employee_id=" + position_id_employee_id +
                '}';
    }
}