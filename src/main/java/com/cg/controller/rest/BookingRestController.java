package com.cg.controller.rest;

import com.cg.exception.DataInputException;
import com.cg.service.bookingService.BookingService;
import com.cg.service.bookingService.bookingRequest.BookingSaveRequest;
import com.cg.service.bookingService.bookingResponse.BookingListResponse;
import com.cg.service.stylistService.StylistService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/bookings")
@AllArgsConstructor
public class BookingRestController {
    private final BookingService bookingService;

    private final StylistService stylistService;

    @GetMapping
    public ResponseEntity<Page<BookingListResponse>> list(@PageableDefault(size = 5) Pageable pageable,
                                                          @RequestParam(defaultValue = "") String search) {
        return new ResponseEntity<>(bookingService.getAll(pageable, search), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody BookingSaveRequest request){
        var idStylist = request.getStylist().getId();

        if (stylistService.findById(Long.valueOf(idStylist)).getStatus().toString().equals("BUSY")){
            throw new DataInputException("Thợ đang bận!");
        }
        bookingService.create(request);

        return ResponseEntity.ok().build();
    }


}
