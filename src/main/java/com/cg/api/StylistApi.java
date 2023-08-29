package com.cg.api;

import com.cg.model.HairService;
import com.cg.model.Stylist;
import com.cg.service.hairService.IHairService;
import com.cg.service.stylist.IStylistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stylists")
public class StylistApi {

    @Autowired
    private IStylistService stylistService;
    @GetMapping
    public ResponseEntity<?> getAllStylists() {
        List<Stylist> stylists = stylistService.findAll();

        if (stylists.size() == 0) {
            Map<String, String> result = new HashMap<>();
            result.put("message", "Không có thợ trong danh sách");

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(stylists, HttpStatus.OK);
    }
}
