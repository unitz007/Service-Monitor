package com.charles.servicemonitor.repository;

import java.util.List;
import java.util.Optional;

import com.charles.servicemonitor.entity.Unit;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends CrudRepository<Unit, Long> {

    Optional<Unit> findByName(String name);

    // List<Unit> findByOwner(String owner);

}
