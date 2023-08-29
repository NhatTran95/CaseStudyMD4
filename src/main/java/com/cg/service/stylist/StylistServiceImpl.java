package com.cg.service.stylist;

import com.cg.model.Stylist;
import com.cg.repository.IHairServiceRepository;
import com.cg.repository.IStylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class StylistServiceImpl implements IStylistService {

    @Autowired
    private IStylistRepository stylistRepository;

    @Override
    public List<Stylist> findAll() {
        return stylistRepository.findAll();
    }

    @Override
    public Optional<Stylist> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Stylist save(Stylist stylist) {
        return null;
    }

    @Override
    public void delete(Stylist stylist) {

    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public Stylist create(Stylist hairService) {
        return null;
    }

    @Override
    public Stylist update(Stylist hairService) {
        return null;
    }
}
