package repository;

import model.DepartmentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends CrudRepository<DepartmentEntity,Integer> {
    @Override
    List <DepartmentEntity> findAll();
}