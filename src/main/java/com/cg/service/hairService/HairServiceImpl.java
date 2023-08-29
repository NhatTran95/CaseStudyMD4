package com.cg.service.hairService;

import com.cg.model.HairService;
import com.cg.model.dto.HairServiceResDTO;
import com.cg.repository.ICustomerRepository;
import com.cg.repository.IHairServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class HairServiceImpl implements IHairService{

    @Autowired
    private IHairServiceRepository hairServiceRepository;
    @Override
    public List<HairService> findAll() {
        return hairServiceRepository.findAll();
    }

    @Override
    public Optional<HairService> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public HairService save(HairService hairService) {
        return null;
    }

    @Override
    public void delete(HairService hairService) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public List<HairServiceResDTO> findAllHairServiceResDTO() {
        return null;
    }

    @Override
    public HairService create(HairService hairService) {
        return null;
    }

    @Override
    public HairService update(HairService hairService) {
        return null;
    }
}
