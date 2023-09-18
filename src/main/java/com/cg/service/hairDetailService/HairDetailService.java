package com.cg.service.hairDetailService;

import com.cg.domain.HairDetail;
import com.cg.repository.IHairDetailRepository;
import com.cg.service.dto.response.SelectOptionResponse;
import com.cg.service.dto.response.SelectOptionServiceResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class HairDetailService {
    private final IHairDetailRepository hairDetailRepository;
    public List<SelectOptionServiceResponse> findAll(){
        return hairDetailRepository.findAll().stream()
                .map(hairDetail -> new SelectOptionServiceResponse(hairDetail.getId().toString(), hairDetail.getName(),hairDetail.getPrice().toString()))
                .collect(Collectors.toList());
    }

    public HairDetail findById(Long id){
        var hairDetail = hairDetailRepository.findById(id).orElse(new HairDetail());
        return hairDetail;
    }

}
