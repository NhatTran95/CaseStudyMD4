package com.cg.service.bookingService;

import com.cg.domain.Booking;
import com.cg.domain.BookingDetail;
import com.cg.domain.HairDetail;
import com.cg.repository.*;
import com.cg.service.bookingService.bookingRequest.BookingSaveRequest;
import com.cg.service.bookingService.bookingResponse.BookingListResponse;
import com.cg.service.hairDetailService.HairDetailService;
import com.cg.utils.AppUtil;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookingService {
    private final IBookingRepository bookingRepository;

    private final IBookingDetailRepository bookingDetailRepository;

    private final IHairDetailRepository hairDetailRepository;

    private final IHairDetailImageRepository hairDetailImageRepository;

    private final IStylistRepository stylistRepository;

    private final IStylistImageRepository stylistImageRepository;

    private final ICustomerRepository customerRepository;

    private final IUserRepository userRepository;

    public Page<BookingListResponse> getAll(Pageable pageable, String search){
        search = "%" + search + "%";
        return bookingRepository.searchEverything(search ,pageable).map(e -> {
            var result = AppUtil.mapper.map(e, BookingListResponse.class);
            result.setDayTimeBooking(e.getDayTimeBooking().toString());
            result.setStylist(e.getStylist().getName());
            if(e.getCustomer() == null){
                result.setRole(e.getUser().getRole().toString());
            } else {
                result.setRole("Customer");
            }
            result.setBookingDetails(e.getBookingDetails()
                    .stream().map(c -> c.getHairDetail().getName())
                    .collect(Collectors.joining(", ")));
            return result;
        });
    }
    public void create(BookingSaveRequest request){
        var book = AppUtil.mapper.map(request, Booking.class);
        book = bookingRepository.save(book);

        Booking finalBook = book;
        bookingDetailRepository.saveAll(request
                .getIdHairDetails()
                .stream()
                .map(id -> new BookingDetail(finalBook, new HairDetail(Long.valueOf(id))))
                .collect(Collectors.toList()));
    }



}
