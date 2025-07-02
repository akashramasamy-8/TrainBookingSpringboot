package com.project.app.repository;

import com.project.app.model.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings,Long> {
        List<Bookings> findAllBytrain_id(Long id);
        List<Bookings> findByUserId(Long userId);
}
