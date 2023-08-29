package com.cg.api;

import com.cg.model.Customer;
import com.cg.model.HairService;
import com.cg.model.dto.CustomerResDTO;
import com.cg.model.dto.HairServiceResDTO;
import com.cg.service.customer.ICustomerService;
import com.cg.service.hairService.IHairService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class ServicesAPI {

    @Autowired
    private IHairService hairServices;

    @GetMapping
    public ResponseEntity<?> getAllServices() {
        List<HairService> hairService = hairServices.findAll();

        if (hairService.size() == 0) {
            Map<String, String> result = new HashMap<>();
            result.put("message", "Không có dịch vụ trong danh sách");

            return new ResponseEntity<>(result, HttpStatus.OK);
        }

        return new ResponseEntity<>(hairService, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HairService> getById(@PathVariable Long id) {

        Optional<HairService> hairServiceOptional = hairServices.findById(id);

        if (hairServiceOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        HairService hairService= hairServiceOptional.get();

        return new ResponseEntity<>(hairService, HttpStatus.OK);
    }

}
