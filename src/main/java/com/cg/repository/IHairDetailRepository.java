package com.cg.repository;

import com.cg.domain.Booking;
import com.cg.domain.HairDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Range;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IHairDetailRepository extends JpaRepository<HairDetail,Long> {
    @Query(value = "SELECT b FROM HairDetail b " +
            "WHERE " +
            "b.name LIKE :search ")
    Page<HairDetail> searchEverything(String search, Pageable pageable);
}
