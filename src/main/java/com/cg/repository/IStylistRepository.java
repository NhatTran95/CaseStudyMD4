package com.cg.repository;

import com.cg.domain.Stylist;
import com.cg.service.dto.response.SelectOptionResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IStylistRepository extends JpaRepository<Stylist,Long> {
    @Query(value = "SELECT st from Stylist st WHERE st.status = 'FREE' ")
    List<Stylist> findAllByStatusFree();

}
