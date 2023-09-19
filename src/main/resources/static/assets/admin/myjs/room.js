const roomForm = document.getElementById('roomForm');
const eCheckBoxCategories = document.getElementsByName('categories');
const tBody = document.getElementById("tBody");
const eSelectType = document.getElementById('type');
const ePagination = document.getElementById('pagination')
const eSearch = document.getElementById('search');
const eModalTitle = document.getElementById("staticBackdropLabel");
const submitBtn = document.getElementById("submit-btn");
// const eOptionsType = eSelectType.querySelectorAll("option");
const formBody = document.getElementById('formBody');
const eSort = document.getElementById('sortPrice');

let roomSelected = {};
let roomDetail;
let pageable = {
    page: 1,
    sort: 'id,desc',
    search: ''
}
let categories;
let types;
let rooms = [];

roomForm.onsubmit = async (e) => {
    e.preventDefault();
    let data = getDataFromForm(roomForm);
    console.log(data)
    data = {
        ...data,
        category: {
            id: data.category
        },
        idAuthors: Array.from(eCheckBoxCategories)
            .filter(e => e.checked)
            .map(e => e.value),
        id: roomSelected.id
    }

    // let message = "Created"
    if (roomSelected.id) {
        await editBook(data);
        webToast.Success({
            status: 'Đặt lịch thành công',
            message: '',
            delay: 2000,
            align: 'topright'
        });
        // message = "Edited"
    } else {
        await createBook(data)
        webToast.Success({
            status: 'Thêm thành công',
            message: '',
            delay: 2000,
            align: 'topright'
        });
    }

    // alert(message);
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
async function getAuthorsSelectOption() {
    const res = await fetch('api/authors');
    return await res.json();
}
async function getList() {
    const response = await fetch(`/api/books?page=${pageable.page - 1 || 0}&search=${pageable.search || ''}`);

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
    const eNext = ePages[ePages.length-1]

    ePrevious.onclick = () => {
        if(pageable.page === 1){
            return;
        }
        pageable.page -= 1;
        getList();
    }
    eNext.onclick = () => {
        if(pageable.page === pageable.totalPages){
            return;
        }
        pageable.page += 1;
        getList();
    }
    for (let i = 1; i < ePages.length - 1; i++) {
        if(i === pageable.page){
            continue;
        }
        ePages[i].onclick = () => {
            pageable.page = i;
            getList();
        }
    }
}


function renderItemStr(item) {
    return `<tr>
                    <td>
                        ${item.id}
                    </td>
                    <td title="${item.description}">
                        ${item.title}
                    </td>
                    <td>
                        ${item.publishDate}
                    </td>
                    <td>
                        ${item.price}
                    </td>
                    <td>
                        ${item.type}
                    </td>
                    <td>
                        ${item.category}
                    </td>
                    <td>
                        ${item.bookAuthor}
                    </td>
                    <td>
                        <div class="d-flex">
                        <button class="dropdown-item" onclick="showEdit(${item.id})"
                        data-bs-toggle="modal" data-bs-target="#staticBackdrop"
                        ><i class="bx bx-edit-alt me-1"></i> Edit</button
                            >
                        <button class="dropdown-item" onclick="deleteBook(${item.id})"
                        ><i class="bx bx-trash me-1"></i> Delete</button
                        >
                        </div>
              </div>
            </div>
                    </td>
                </tr>`
}

window.onload = async () => {
    categories = await getCategoriesSelectOption();
    types = await getAuthorsSelectOption();
    await renderTable()

    renderForm(formBody, getDataInput());
    // onLoadSort();
}
function getDataInput() {
    return [
        {
            label: 'Title',
            name: 'title',
            value: roomSelected.title,
            required: true,
            pattern: "^[A-Za-z ]{6,20}",
            message: "Username must have minimum is 6 characters and maximum is 20 characters",
        },
        {
            label: 'Type',
            name: 'type',
            value: roomSelected.type,
            type: 'select',
            required: true,
            options: [{value: "SINGLE_VOLUME", name: "SINGLE_VOLUME"}, {value: "MULTIPLE_VOLUMES", name:"MULTIPLE_VOLUMES"}],
            message: 'Please choose Type'
        },
        {
            label: 'Category',
            name: 'category',
            value: roomSelected.categoryId,
            type: 'select',
            required: true,
            options: categories,
            message: 'Please choose Type'
        },
        {
            label: 'PublishDate',
            name: 'publishDate',
            value: roomSelected.publishDate,
            required: true,
            type: "date",
            message: 'Please choose Date'
        },
        {
            label: 'Price',
            name: 'price',
            value: roomSelected.price,
            pattern: "[1-9][0-9]{1,10}",
            message: 'Price errors',
            required: true
        },
        {
            label: 'Description',
            name: 'description',
            value: roomSelected.description,
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
}



function clearForm() {
    roomForm.reset();
    roomSelected = {};
}

function showCreate() {
    $('#staticBackdropLabel').text('Create Book');
    clearForm();
    renderForm(formBody, getDataInput())
}

async function createBook(data) {
    console.log(data)
    const res = await fetch('/api/books', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}

async function showEdit(id) {
    $('#staticBackdropLabel').text('Edit Book');
    clearForm();
    roomSelected = await findById(id);
    roomSelected.authorIds.forEach(idAuthor => {
        for (let i = 0; i < eCheckBoxCategories.length; i++) {
            if (idAuthor === +eCheckBoxCategories[i].value) {
                eCheckBoxCategories[i].checked = true;
            }
        }
    })
    renderForm(formBody, getDataInput());
}

const findById = async (id) => {
    const response = await fetch('/api/books/' + id);
    return await response.json();
}

async function editBook(data) {
    const res = await fetch('/api/books/' + data.id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    })
}

async function deleteBook(id) {
    var confirmBox = webToast.confirm("Bạn chắc chắn muốn xóa chứ??");
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
            webToast.Danger({
                status: 'Xóa lỗi rồi',
                message: '',
                delay: 2000,
                align: 'topright'
            });
        }
    });
}

const searchInput = document.querySelector('#search');
searchInput.addEventListener('search', () => {
    onSearch(event)
});

const onSearch = (e) => {
    e.preventDefault()
    pageable.search = eSearch.value;
    pageable.page = 1;
    getList();
}
