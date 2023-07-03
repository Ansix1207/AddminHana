const $pagenation = document.querySelector(".datatable-pagination-list");
const lastPage = Math.floor(transactionCount / size) + 1;
let pageListNum = Math.floor((page - 1) / 10) * 10;
const startPageIdx = 1 + pageListNum;
const lastPageIdx = lastPage <= 10 + pageListNum ? lastPage : 10 + pageListNum;
let innerHTML = "";

innerHTML += `
      <li class="datatable-pagination-list-item">
        <a href="?t_accid=${t_accid}&page=${page - 10 > 0 ? page - 10 : 1}&size=${size}"data-page="1" class="datatable-pagination-list-item-link">‹</a>
      </li>`;

for (let i = startPageIdx; i <= lastPageIdx; i++) {
    innerHTML += `
          <li class="datatable-pagination-list-item ${page == i ? "datatable-active" : ""} ">
            <a href="?t_accid=${t_accid}&page=${i}&size=${size}" data-page="${i}" class="datatable-pagination-list-item-link">${i}</a>
          </li>
          `;
}
innerHTML += `
      <li class="datatable-pagination-list-item"  >
        <a href="?t_accid=${t_accid}&page=${page + 10 < lastPage ? page + 10 : lastPage}&size=${size}" data-page="2" class="datatable-pagination-list-item-link">›</a>
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
    location.href = `transactionList?t_accid=${t_accid}page=${page}&size=${value}`;
});

$searchForm = document.querySelector(".searchForm");
$searchForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const value = e.target.searchInput.value;
    location.href = `transactionList?t_accid=${t_accid}&page=${page}&size=${size}&search=${value}`;
})

$accountForm = document.querySelector(".accountForm");
$accountForm.addEventListener('submit', (e) => {
    e.preventDefault();
    const value = e.target.accountInput.value;
    location.href = `transactionList?t_accid=${value}&page=${page}&size=${size}&search=${search}`;
})

const handleClickColumn = (column) => {
    const newOrdering = ordering == "desc" ? "asc" : "desc";
    location.href = `transactionList?t_accid=${t_accid}&page=${page}&size=${size}&search=${search}&orderBy=${column}&ordering=${newOrdering}`;
}
const $columnHead = document.querySelector("#columnHead");
$columnHead.innerHTML = `
                <tr>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('t_id')">거래 번호</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('t_type')">입출금 여부</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('t_amount')">거래 금액</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('t_balance')">잔액</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('t_accid')">계좌 번호</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('t_counterpart_id')">거래 대상 계좌 번호</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('t_description')">비고</a></th>
                  <th data-sortable="true"><a class="datatable-sorter" onclick="handleClickColumn('t_date')">거래 날짜</a></th>
                </tr>`;
