
var timeBooking;
// $(document).ready(function() {
//     const btnTimeBooking = $('.time-booking button');
//
//     btnTimeBooking.on('click', function() {
//         // Đổi màu của nút được click
//         $(this).css('background-color', '#D19F68');
//         $(this).addClass('booking')
//         timeBooking = $(this).text();
//
//         // Vô hiệu hóa các nút khác
//         btnTimeBooking.not(this).css('background-color','#3498DB')
//     });
// });
function addService() {
    // Lấy giá trị đã chọn từ ô select
    var selectedService = document.getElementById("serviceBooker");
    var serviceText = selectedService.options[selectedService.selectedIndex].text;
    var serviceValue = selectedService.options[selectedService.selectedIndex].value;

    // Tạo một thẻ div mới để hiển thị dịch vụ đã chọn
    var serviceElement = document.createElement("div");
    serviceElement.classList.add("selected-service");

    // Tạo một thẻ span để chứa biểu tượng "X" và đặt thuộc tính onclick
    var deleteIcon = document.createElement("span");
    deleteIcon.classList.add("delete-icon");
    deleteIcon.innerHTML = '<i class="fa fa-times" onclick="deleteService(this); updateTotalPrice();"></i>';

    // Tạo một thẻ span để chứa nội dung dịch vụ
    var serviceTextSpan = document.createElement("span");
    serviceTextSpan.textContent = serviceText;

    serviceTextSpan.setAttribute("valueData", serviceValue);
    serviceTextSpan.setAttribute("name", "idHairDetails");


    // Thêm biểu tượng và nội dung dịch vụ vào thẻ div
    serviceElement.appendChild(serviceTextSpan);
    serviceElement.appendChild(deleteIcon);

    // Thêm thẻ div đã chọn vào một phần khác của trang (ví dụ: một div có id="selectedServices")
    var selectedServicesDiv = document.getElementById("selectedServices");
    selectedServicesDiv.appendChild(serviceElement);
}


function updateTotalPrice() {
    // Lấy tất cả các phần tử dịch vụ đã chọn
    const selectedServices = document.getElementById("selectedServices").children;

    // Tính tổng tiền từ các dịch vụ đã chọn
    let totalPrice = 0;
    for (let i = 0; i < selectedServices.length; i++) {
        let serviceText = selectedServices[i].textContent;
        let price = parseFloat(serviceText.split(":")[1]);
        totalPrice += price;
    }
    if (selectedServices.length < 1) {
        document.getElementById("totalPriceValue").textContent = "0 Đ";
    }
    // Cập nhật giá trị tổng tiền vào #totalPriceValue
    document.getElementById("totalPriceValue").textContent = totalPrice.toFixed(0) + " Đ";

}

function formatInputDate() {
    const dateInput = document.getElementById("dayBooking");
    const selectedDate = new Date(dateInput.value);

    const year = selectedDate.getFullYear();
    let month = (selectedDate.getMonth() + 1).toString().padStart(2, "0");
    let day = selectedDate.getDate().toString().padStart(2, "0");

    const formattedDate = `${year}-${month}-${day}`;
    dateInput.value = formattedDate;
}

async function showTimeFree(element) {
    var id = element.value;
    const dateInput = document.getElementById("dayBooking");
    var date = dateInput.value;

    const res = await fetch('/api/bookings/' + id + '/' + date);
    console.log(res);
    const responseJson = await res.json();
    console.log(responseJson)

    $(document).ready(function() {
        const btnTimeBooking = $('.time-booking button');
        btnTimeBooking.css('background-color', '#3498DB');
        btnTimeBooking.prop('disabled', false);
        btnTimeBooking.each(function() {
            var btnValue = $(this).text();

            if (responseJson.includes(btnValue)) {

                $(this).prop('disabled', true);
                $(this).css('background-color', 'red');
                $(this).addClass('red-button')
            }
        });

        btnTimeBooking.on('click', function() {
            // Đổi màu của nút được click
            $(this).css('background-color', '#D19F68');
            $(this).addClass('booking');
            timeBooking = $(this).text();

            // Vô hiệu hóa các nút khác
            btnTimeBooking.not(this).not('.red-button').css('background-color', '#3498DB');

            // Vô hiệu hóa các nút có giá trị giống với các phần tử trong mảng res

        });
    });
}

async function showTimeFreeDate(element) {
    var date = element.value;
    const idE = document.getElementById("stylistBooker");
    var id = idE.value;

    const res = await fetch('/api/bookings/' + id + '/' + date);
    console.log(res);
    const responseJson = await res.json();
    console.log(responseJson)

    $(document).ready(function() {
        const btnTimeBooking = $('.time-booking button');
        btnTimeBooking.css('background-color', '#3498DB');
        btnTimeBooking.prop('disabled', false);
        btnTimeBooking.each(function() {
            var btnValue = $(this).text();

            if (responseJson.includes(btnValue)) {

                $(this).prop('disabled', true);
                $(this).css('background-color', 'red');
                $(this).addClass('red-button')
            }
        });

        btnTimeBooking.on('click', function() {
            // Đổi màu của nút được click
            $(this).css('background-color', '#D19F68');
            $(this).addClass('booking');
            timeBooking = $(this).text();

            // Vô hiệu hóa các nút khác
            btnTimeBooking.not(this).not('.red-button').css('background-color', '#3498DB');

            // Vô hiệu hóa các nút có giá trị giống với các phần tử trong mảng res

        });
    });
}

function deleteService(element) {
    // Lấy thẻ div chứa biểu tượng "X"
    var serviceDiv = element.parentElement;

    // Lấy phần tử cha của thẻ div để xóa thẻ div khỏi nó
    var selectedServicesDiv = serviceDiv.parentElement;

    // Xóa thẻ div chứa dịch vụ đã chọn
    selectedServicesDiv.remove(serviceDiv);
}

const bookingForm = document.getElementById('form-booking');

const eSelectedHairDetails = document.getElementsByName('idHairDetails');

const tBody = document.getElementById("tBody");
const eSelectedStylist = document.getElementsByName('selectedStylist');
const name = document.getElementById('nameBooker')
const phone = document.getElementById('phoneBooker')
// const dayBooking = document.getElementById()
// const timeBooking  = document.getElementById()

const ePagination = document.getElementById('pagination')
const eSearch = document.getElementById('search');
const eModalTitle = document.getElementById("staticBackdropLabel");
const submitBtn = document.getElementById("submit-btn");
// const eOptionsType = eSelectType.querySelectorAll("option");
const formBody = document.getElementById('formBody');
const eHeaderPrice = document.getElementById('header-price')


let bookingSelected = {};
let roomDetail;
let pageable = {
    page: 1,
    sort: 'id,desc',
    search: ''
}
let categories;
let types;
let rooms = [];


bookingForm.onsubmit = async (e) => {
    var selectedOptions = [];
    for (var i = 0; i < eSelectedHairDetails.length; i++) {
        var value = eSelectedHairDetails[i].getAttribute("valueData");
        selectedOptions.push(value);
    }

    e.preventDefault();
    let data = getDataFromForm(bookingForm);
    console.log(data)

    data = {
        ...data,
        stylist: {
            id: data.stylist
        },
        idHairDetails: selectedOptions,
        timeBooking: timeBooking + ":00",
        id: bookingSelected.id
    }

    console.log(data)
        await createBooking(data)

        webToast.Success({
            status: 'Thêm thành công',
            message: '',
            delay: 2000,
            align: 'topright'
        });

    // await renderTable();
    // $('#staticBackdrop').modal('hide');

}

function getDataFromForm(form) {
    // event.preventDefault()
    const data = new FormData(form);
    return Object.fromEntries(data.entries())
}

async function createBooking(data) {
    console.log(data)
    const res = await fetch('/api/bookings', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}




