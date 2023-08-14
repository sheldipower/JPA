package dto;

import pojo.Employee;

    public class EmployeeDTO {
        private Integer id;
        private String name;
        private Integer salary;

        public static EmployeeDTO fromEmployee (Employee employee) {
            EmployeeDTO employeeDTO = new EmployeeDTO();
            employeeDTO.setId(employee.getId());
            employeeDTO.setName(employee.getName());
            employeeDTO.setSalary(employee.getSalary());
            return employeeDTO;
        }

        public Employee toEmployee() {
            Employee employee = new Employee();
            employee.setId(this.getId());
            employee.setName(this.getName());
            employee.setSalary(this.getSalary());
            return employee;
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

        public int getSalary() {
            return salary;
        }

        public void setSalary(int salary) {
            this.salary = salary;
        }
    }

