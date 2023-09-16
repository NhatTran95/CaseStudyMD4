package com.cg.service.stylistImageService;

import com.cg.domain.StylistImage;
import com.cg.repository.IStylistImageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class StylistImageService implements IStylistImageService{

    private final IStylistImageRepository stylistImageRepository;
    @Override
    public List<StylistImage> findAll() {
        return null;
    }

    @Override
    public Optional<StylistImage> findById(String id) {
        return Optional.empty();
    }

    @Override
    public StylistImage save(StylistImage stylistImage) {
        return null;
    }

    @Override
    public void delete(StylistImage stylistImage) {

    }

    @Override
    public void deleteById(String s) {

    }
}
