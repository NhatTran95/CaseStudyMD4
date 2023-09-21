const bookingForm = document.getElementById('bookingForm');
const tBody = document.getElementById("tBody");
const ePagination = document.getElementById('pagination')
const eSearch = document.getElementById('search');
const formBody = document.getElementById('formBody');

let stylistSelected = {};
let pageable = {
    page: 1,
    sort: 'id,desc',
    search: ''
}
let stylists = [];

async function getList() {
    const response = await fetch(`/api/bookings?page=${pageable.page - 1 || 0}&search=${pageable.search || ''}`);

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
                        ${item.name}
                    </td>
                    <td>
                        ${item.dayTimeBooking}
                    </td>
                    <td>
                        ${item.phoneNumber}
                    </td>
                    <td>
                       ${item.bookingDetails}
                    </td>            
                    <td>
                        ${item.totalPrice}
                    </td>       
                    <td>
                        ${item.stylist}
                    </td>
                    <td>
                        ${item.status}
                    </td>                   
                   
                </tr>`
}


window.onload = async () => {
    await renderTable()
    onLoadSort();
    // renderForm(formBody, getDataInput());
}


async function renderTable() {
    const pageable = await getList();
    rooms = pageable.content;
    renderTBody(rooms);
}

const findById = async (id) => {
    const response = await fetch('/api/books/' + id);
    return await response.json();

}
