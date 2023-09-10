package repository;

import model.EmployeeEntity;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeEntity, Integer>,
        PagingAndSortingRepository<EmployeeEntity, Integer> {
    @Override
    Collection<EmployeeEntity> findAll();
    @Query(value = "SELECT * FROM employee WHERE name= :name", nativeQuery = true)
    List<EmployeeEntity> getEmployeesByName(@Param("name") Optional position  );
}