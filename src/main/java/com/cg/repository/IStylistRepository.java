package com.cg.repository;

import com.cg.domain.Stylist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStylistRepository extends JpaRepository<Stylist,Long> {
}
