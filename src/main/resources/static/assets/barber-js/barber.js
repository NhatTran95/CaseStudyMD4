
var timeBooking;
$(document).ready(function() {
    const btnTimeBooking = $('.time-booking button');

    btnTimeBooking.on('click', function() {
        // Đổi màu của nút được click
        $(this).css('background-color', '#D19F68');
        $(this).addClass('booking')
        timeBooking = $(this).text();

        // Vô hiệu hóa các nút khác
        btnTimeBooking.not(this).css('background-color','#3498DB')
    });
});
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
    // Thêm thuộc tính "value" vào thẻ <span>
    serviceTextSpan.setAttribute("value", `${serviceValue}`);

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
        let price = parseFloat(serviceText.split("-")[1]);
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


function deleteService(element) {
    // Lấy thẻ div chứa biểu tượng "X"
    var serviceDiv = element.parentElement;

    // Lấy phần tử cha của thẻ div để xóa thẻ div khỏi nó
    var selectedServicesDiv = serviceDiv.parentElement;

    // Xóa thẻ div chứa dịch vụ đã chọn
    selectedServicesDiv.remove(serviceDiv);
}

const bookingForm = document.getElementById('form-booking');

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
    e.preventDefault();
    let data = getDataFromForm(bookingForm);
    console.log(data)
    const eSelectedHairDetails = document.querySelectorAll('#selectedServices span');
    console.log(eSelectedHairDetails)
    data = {
        ...data,
        stylist: {
            id: data.stylist
        },
        idHairDetails: Array.from(eSelectedHairDetails)
            .map(e => e.value),
        id: bookingSelected.id
    }
    console.log(data)
        await createRoom(data)
        webToast.Success({
            status: 'Thêm thành công',
            message: '',
            delay: 2000,
            align: 'topright'
        });

    await renderTable();
    $('#staticBackdrop').modal('hide');

}

function getDataFromForm(form) {
    // event.preventDefault()
    const data = new FormData(form);
    return Object.fromEntries(data.entries())
}

async function getCategoriesSelectOption() {
    const res = await fetch('api/categories');
    return await res.json();
}

async function getTypesSelectOption() {
    const res = await fetch('api/types');
    return await res.json();
}

async function getList() {
    const response = await fetch(`/api/books?page=${pageable.page - 1 || 0}&sort=${pageable.sortCustom || 'id,asc'}&search=${pageable.search || ''}`);
    //response có status ok hoặc không, header và body
    //{page: 1, size: 10, content: []}
    //{ size: 15, content: [1,2,3]}
    //{page:1 , size: 15, content: [1,2,3]}

    if (!response.ok) {
        // Xử lý trường hợp không thành công ở đây, ví dụ: throw một lỗi hoặc trả về một giá trị mặc định
        throw new Error("Failed to fetch data");
    }

    const result = await response.json();
    pageable = {
        ...pageable,
        ...result
    };
    genderPagination();
    renderTBody(result.content);
    return result; // Trả về kết quả mà bạn đã lấy từ response.json()
    // addEventEditAndDelete();
}

function renderTBody(items) {
    let str = '';
    items.forEach(e => {
        str += renderItemStr(e);
    })
    tBody.innerHTML = str;
}

const genderPagination = () => {
    ePagination.innerHTML = '';
    let str = '';
    //generate preview truoc
    str += `<li class="page-item ${pageable.first ? 'disabled' : ''}">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Previous</a>
            </li>`
    //generate 1234

    for (let i = 1; i <= pageable.totalPages; i++) {
        str += ` <li class="page-item ${(pageable.page) === i ? 'active' : ''}" aria-current="page">
      <a class="page-link" href="#">${i}</a>
    </li>`
    }
    //
    //generate next truoc
    str += `<li class="page-item ${pageable.last ? 'disabled' : ''}">
              <a class="page-link" href="#" tabindex="-1" aria-disabled="true">Next</a>
            </li>`
    //generate 1234
    ePagination.innerHTML = str;

    const ePages = ePagination.querySelectorAll('li'); // lấy hết li mà con của ePagination
    const ePrevious = ePages[0];
    const eNext = ePages[ePages.length - 1]

    ePrevious.onclick = () => {
        if (pageable.page === 1) {
            return;
        }
        pageable.page -= 1;
        getList();
    }
    eNext.onclick = () => {
        if (pageable.page === pageable.totalPages) {
            return;
        }
        pageable.page += 1;
        getList();
    }
    for (let i = 1; i < ePages.length - 1; i++) {
        if (i === pageable.page) {
            continue;
        }
        ePages[i].onclick = () => {
            pageable.page = i;
            getList();
        }
    }
}
const onSearch = (e) => {
    e.preventDefault()
    pageable.search = eSearch.value;
    pageable.page = 1;
    getList();
}
const searchInput = document.querySelector('#search');
searchInput.addEventListener('search', () => {
    onSearch(event)
});
const onLoadSort = () => {
    eHeaderPrice.onclick = () => {
        let sort = 'price,desc'
        const chevronDown = document.querySelector('.bx-chevron-down');
        const chevronUp = document.querySelector('.bx-chevron-up');
        chevronDown.style.display = 'block';
        chevronUp.style.display = 'none';
        if (pageable.sortCustom?.includes('price') && pageable.sortCustom?.includes('desc')) {
            sort = 'price,asc';
            chevronUp.style.display = 'block';
            chevronDown.style.display = 'none';
        }
        pageable.sortCustom = sort;
        getList();
    }
}

function renderItemStr(item) {
    return `<tr>
                    <td>
                        ${item.id}
                    </td>
                    <td>
                        ${item.title}
                    </td>
                    <td>
                        ${item.description}
                    </td>
                    <td>
                        ${item.publishDate}
                    </td>
                    <td>
                        ${item.price}
                    </td>            
                    <td>
                        ${item.authors}
                    </td>       
                    <td>
                        ${item.category}
                    </td>
                    <td>
                        ${item.type}
                    </td>                   
                    <td>
                         <div class="dropdown">
                             <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                             <i class="bx bx-dots-vertical-rounded"></i>
                            </button>
                        <div class="dropdown-menu">
                        <button class="dropdown-item" onclick="showEdit(${item.id})"
                        data-bs-toggle="modal" data-bs-target="#staticBackdrop"
                        ><i class="bx bx-edit-alt me-1"></i> Edit</button
                            >
                        <button class="dropdown-item" onclick="deleteRoom(${item.id})"
                        ><i class="bx bx-trash me-1"></i> Delete</button
                        >
              </div>
            </div>
                    </td>
                </tr>`
}

window.onload = async () => {
    categories = await getCategoriesSelectOption();
    types = await getTypesSelectOption();
    await renderTable()
    onLoadSort();
    renderForm(formBody, getDataInput());
}

function getDataInput() {
    return [
        {
            label: 'Title',
            name: 'title',
            value: bookingSelected.title,
            required: true,
            pattern: "^[A-Za-z ]{6,20}",
            message: "Room name must have minimum is 6 characters and maximum is 20 characters",
        },
        {
            label: 'Type',
            name: 'type',
            value: bookingSelected.type,
            type: 'select',
            required: true,
            options: types,
            message: 'Please choose Type'
        },
        {
            label: 'Category',
            name: 'category',
            value: bookingSelected.category,
            type: 'select',
            required: true,
            options: categories,
            message: 'Please choose Category'
        },
        {
            label: 'PublishDate',
            name: 'publishDate',
            value: bookingSelected.publishDate,
        },
        {
            label: 'Price',
            name: 'price',
            value: bookingSelected.price,
            pattern: "[1-9][0-9]{1,10}",
            message: 'Price errors',
            required: true
        },
        {
            label: 'Description',
            name: 'description',
            value: bookingSelected.description,
            pattern: "^[A-Za-z ]{6,120}",
            message: "Description must have minimum is 6 characters and maximum is 20 characters",
            required: true
        },

    ];
}

async function renderTable() {
    const pageable = await getList();
    rooms = pageable.content;
    renderTBody(rooms);
    addEventEditAndDelete();
}

const findById = async (id) => {
    const response = await fetch('/api/books/' + id);
    return await response.json();
}

function showCreate() {
    $('#staticBackdropLabel').text('Create Room');
    clearForm();
    renderForm(formBody, getDataInput())
}

async function showEdit(id) {
    $('#staticBackdropLabel').text('Edit Book');
    clearForm();
    bookingSelected = await findById(id);
    console.log(bookingSelected)
    bookingSelected.authors.forEach(idAuthor => {
        for (let i = 0; i < eCheckBoxCategories.length; i++) {
            if (+idAuthor === +eCheckBoxCategories[i].value) {
                eCheckBoxCategories[i].checked = true;
            }
        }
    })
    renderForm(formBody, getDataInput());
}

function clearForm() {
    roomForm.reset();
    bookingSelected = {};
}

async function editRoom(data) {
    const res = await fetch('/api/books/' + data.id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}

async function deleteRoom(id) {
    const confirmBox = webToast.confirm("Are you sure to delete Book" + id + "?");
    confirmBox.click(async function () {
        const res = await fetch('/api/books/' + id, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(id)
        });
        if (res.ok) {
            // alert("Deleted");
            webToast.Success({
                status: 'Xóa thành công',
                message: '',
                delay: 2000,
                align: 'topright'
            });
            await getList();
        } else {
            alert("Something went wrong!")
        }
    });
}

async function createRoom(data) {
    console.log(data)
    const res = await fetch('/api/books', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}

const addEventEditAndDelete = () => {
    const eEdits = tBody.querySelectorAll('.edit');
    const eDeletes = tBody.querySelectorAll('.delete');
    for (let i = 0; i < eEdits.length; i++) {
        console.log(eEdits[i].id)
        eEdits[i].addEventListener('click', () => {
            showEdit(eEdits[i].dataset.id);
        })
    }
}


