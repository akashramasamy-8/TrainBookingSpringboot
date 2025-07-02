package com.project.app.repository;

import com.project.app.model.Trains;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainsRepository extends JpaRepository<Trains,Long> {

}
