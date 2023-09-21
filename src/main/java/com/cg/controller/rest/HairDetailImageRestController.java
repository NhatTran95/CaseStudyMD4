package com.cg.controller.rest;

import com.cg.domain.HairDetailImage;
import com.cg.service.hairDetailImageService.HairDetailImageService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
@RestController
@RequestMapping("/api/hairDetailImages")
@AllArgsConstructor
public class HairDetailImageRestController {
    private final HairDetailImageService uploadFileService;

    @PostMapping
    public HairDetailImage upload(@RequestParam("avatar") MultipartFile avatar) throws IOException {
        return uploadFileService.saveAvatar(avatar);
    }
}
