package com.cg.repository;

import com.cg.model.HairService;
import com.cg.model.dto.CustomerResDTO;
import com.cg.model.dto.HairServiceResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IHairServiceRepository extends JpaRepository<HairService,Long> {

}
