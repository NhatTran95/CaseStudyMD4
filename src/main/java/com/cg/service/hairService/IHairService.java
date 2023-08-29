package com.cg.service.hairService;

import com.cg.model.Customer;
import com.cg.model.HairService;
import com.cg.model.dto.CustomerResDTO;
import com.cg.model.dto.HairServiceResDTO;
import com.cg.service.IGeneralService;

import java.util.List;

public interface IHairService extends IGeneralService<HairService,Long> {
    List<HairServiceResDTO> findAllHairServiceResDTO();

    HairService create(HairService hairService);

    HairService update(HairService hairService);
}
