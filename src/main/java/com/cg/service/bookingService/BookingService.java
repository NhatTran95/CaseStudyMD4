package com.cg.service.bookingService;

import com.cg.domain.Booking;
import com.cg.domain.BookingDetail;
import com.cg.domain.Customer;
import com.cg.domain.Enum.EStatusBooking;
import com.cg.domain.HairDetail;
import com.cg.repository.*;
import com.cg.service.bookingService.bookingRequest.BookingSaveRequest;
import com.cg.service.bookingService.bookingResponse.BookingListResponse;
import com.cg.utils.AppUtils;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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

    private final UserRepository userRepository;

    public Page<BookingListResponse> getAll(Pageable pageable, String search){
        search = "%" + search + "%";
        return bookingRepository.searchEverything(search ,pageable).map(e -> {
            var result = AppUtils.mapper.map(e, BookingListResponse.class);
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
        var book = AppUtils.mapper.map(request, Booking.class);
        String dateTimeBooking = request.getDayBooking() +'T'+ request.getTimeBooking();
        LocalDateTime dateTimeBook = LocalDateTime.parse(dateTimeBooking);
        book.setDayTimeBooking(dateTimeBook);
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (var idHairDetail:request.getIdHairDetails()) {
            totalPrice = hairDetailRepository.findById(Long.valueOf(idHairDetail)).get().getPrice().add(totalPrice);
        }
        book.setTotalPrice(totalPrice);
        book.setStatus(EStatusBooking.valueOf("UNPAID"));
        var customer = AppUtils.mapper.map(request, Customer.class);
        customerRepository.save(customer);
        book.setCustomer(customer);
        bookingRepository.save(book);
        Booking finalBook = book;
        bookingDetailRepository.saveAll(request
                .getIdHairDetails()
                .stream()
                .map(id -> new BookingDetail(finalBook, new HairDetail(Long.valueOf(id)),request.getName(),hairDetailRepository.findById(Long.valueOf(id)).get().getPrice()))
                .collect(Collectors.toList()));
    }

}
