package com.cg.controller.rest;

import com.cg.service.bookingService.BookingService;
import com.cg.service.bookingService.bookingResponse.BookingListResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingRestController {
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<Page<BookingListResponse>> list(@PageableDefault(size = 5) Pageable pageable,
                                                          @RequestParam(defaultValue = "") String search) {
        return new ResponseEntity<>(bookingService.getAll(pageable, search), HttpStatus.OK);
    }


}
