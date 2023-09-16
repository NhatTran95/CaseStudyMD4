package com.cg.service.hairDetailImageService;

import com.cg.domain.HairDetailImage;
import com.cg.repository.IHairDetailImageRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
@Transactional
public class HairDetailImageService implements IHairDetailImageService{

    private final IHairDetailImageRepository hairDetailImageRepository;
    @Override
    public List<HairDetailImage> findAll() {
        return null;
    }

    @Override
    public Optional<HairDetailImage> findById(String id) {
        return Optional.empty();
    }

    @Override
    public HairDetailImage save(HairDetailImage hairDetailImage) {
        return null;
    }

    @Override
    public void delete(HairDetailImage hairDetailImage) {

    }

    @Override
    public void deleteById(String s) {

    }
}
