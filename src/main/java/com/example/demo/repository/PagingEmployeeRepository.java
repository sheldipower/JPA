package com.example.demo.repository;

import com.example.demo.pojo.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingEmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {

}
