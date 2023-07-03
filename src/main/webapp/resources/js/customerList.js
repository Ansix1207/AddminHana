const $pagenation = document.querySelector(".datatable-pagination-list");
const lastPage = Math.floor(customerCount / size) + 1;
let pageListNum = Math.floor((page - 1) / 10) * 10;
const startPageIdx = 1 + pageListNum;
const lastPageIdx = lastPage <= 10 + pageListNum ? lastPage : 10 + pageListNum;
let innerHTML = "";

innerHTML += `
      <li class="datatable-pagination-list-item">
        <a href="?page=${page - 10 > 0 ? page - 10 : 1}&size=${size}"data-page="1" class="datatable-pagination-list-item-link">‹</a>
      </li>`;

for (let i = startPageIdx; i <= lastPageIdx; i++) {
    innerHTML += `
          <li class="datatable-pagination-list-item ${page == i ? "datatable-active" : ""} ">
            <a href="?page=${i}&size=${size}" data-page="${i}" class="datatable-pagination-list-item-link">${i}</a>
          </li>
          `;
}
innerHTML += `
      <li class="datatable-pagination-list-item"  >
        <a href="?page=${page + 10 < lastPage ? page + 10 : lastPage}&size=${size}" data-page="2" class="datatable-pagination-list-item-link">›</a>
      </li>`;
$pagenation.innerHTML = innerHTML;

$sizeSelector = document.querySelector(".datatable-dropdown");
const sizeList = [10, 15, 20, 25, 50, 100];
let selectorInnerHTML = `
      <label>
          <select class="datatable-selector">
            ${sizeList.map((value) => (
    `<option value='${value}' ${size == value ? 'selected' : ''}>${value}</option>`
)).join("")}
          </select> entries per page
      </label>`;

$sizeSelector.innerHTML = selectorInnerHTML;
$sizeSelector.addEventListener('change', (e) => {
    const value = e.target.value;
    location.href = `customerList?page=${page}&size=${value}`;
});

$searchForm = document.querySelector(".searchForm");
$searchForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const value = e.target.searchInput.value;
    location.href = `customerList?page=${page}&size=${size}&search=${value}`;
})

const handleClickColumn = (column) => {
    const newOrdering = ordering == "desc" ? "asc" : "desc";
    location.href = `customerList?page=${page}&size=${size}&search=${search}&orderBy=${column}&ordering=${newOrdering}`;
}
const $columnHead = document.querySelector("#columnHead");
$columnHead.innerHTML = `
                <tr>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('c_id')">손님 id</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('c_name')">이름</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('c_rrn')">주민등록 번호</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('c_gender')">성별</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('c_address')">주소</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('c_mobile')">휴대폰 번호</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('c_job')">직업</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('e_id')">추천 직원 id</a></th>
                </tr>`;
