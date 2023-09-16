package com.cg.service.hairDetailService;

import com.cg.repository.IHairDetailRepository;
import com.cg.service.dto.response.SelectOptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HairDetailService {
    private final IHairDetailRepository hairDetailRepository;
    public List<SelectOptionResponse> findAll(){
        return hairDetailRepository.findAll().stream()
                .map(hairDetail -> new SelectOptionResponse(hairDetail.getId().toString(), hairDetail.getName()))
                .collect(Collectors.toList());
    }
}
