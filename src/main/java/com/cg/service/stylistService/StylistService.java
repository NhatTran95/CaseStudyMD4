package com.cg.service.stylistService;

import com.cg.domain.HairDetail;
import com.cg.domain.Stylist;
import com.cg.repository.IStylistRepository;
import com.cg.service.dto.response.SelectOptionResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StylistService {
    private final IStylistRepository stylistRepository;

    public List<SelectOptionResponse> findAll(){
        return stylistRepository.findAllByStatusFree().stream()
                .map(stylist -> new SelectOptionResponse(stylist.getId().toString(), stylist.getName()))
                .collect(Collectors.toList());
    }

    public Stylist findById(Long id){
        var stylist = stylistRepository.findById(id).orElse(new Stylist());
        return stylist;
    }}
