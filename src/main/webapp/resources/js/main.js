const guideList = [
    {
        title: "신규 손님 가입",
        header: "신규 손님 가입이 가능합니다.",
        description: [
            "필요한 정보를 입력합니다.",
            "이름, 주민번호, 도로명 주소, 상세주소, 휴대폰번호, 직업은 필수입력 항목입니다."
        ]
    },
    {
        title: "가입 손님 리스트",
        header: "가입된 손님의 리스트를 보여줍니다.",
        description: [
            "10 entries pes page의 숫자를 통해 1페이지당 몇 명의 손님을 표시할지 선택가능합니다.",
            "검색창을 통해 손님의 이름을 검색할 수 있습니다.",
            "페이지 선택을 통해 손님 리스트 page를 이동할수 있습니다."
        ]
    }, {
        title: "프로필 페이지",
        header: "손님에 대한 정보를 한눈에 보여줍니다.",
        description: [
            "프로필 : 손님의 요약 정보를 보여줍니다.",
            "자산 현황 : 자산 액수와 계좌 리스트 정보를 보여주며, 차트를 통해 한눈에 자산 현황을 보여줍니다.",
            "새로고침 버튼을 누르면 예금/적금/대출 차트가 현재시점 기준으로 새로고침됩니다.",
            "특이사항 : 손님에 대한 메모를 적어둘 수 있습니다. 메모 작성 후 수정 버튼을 누르면 데이터가 수정됩니다."
        ]
    }, {
        title: "신규 상품 가입",
        header: "상품 가입이 가능합니다.",
        description: [
            "이름, 비밀번호, 상품종류 등에 대한 정보를 입력 후 '상품 가입'을 클릭하면 신규 상품 가입이 가능합니다.",
        ]
    }, {
        title: "손님 자산 현황",
        header: "손님의 자산 정보와 추천 상품을 보여줍니다.",
        description: [
            "자산 현황 : 🔃(reload button)을 누르면 총 예금/적금/대출액 및 자산 차트가 표시됩니다.",
            "추천 상품 : 손님의 나이대, 성별, 직업에 기반한 추천 상품이 표시됩니다.",
            "          같은 나이대와 성별, 직업을 가진 다른 손님들이 가장 많이 가입한 상품 2개를 추천합니다."
        ]
    }, {
        title: "창구 업무",
        header: "창구업무를 누르면 입금, 출금, 계좌이체를 진행할 수 있습니다.",
        description: [
            "입금은 활성화된 계좌에만 진행할 수 있습니다.",
            "출금과 계좌이체에는 계좌와 비밀번호를 입력한 뒤 정보가 일치하면 거래를 진행할 수 있습니다.",
            "출금이 필요한 거래에는 입출금통장(보통예금) 상품만 출금이 가능합니다."
        ]
    }, {
        title: "상품 리스트",
        header: "예금/적금/대출 상품 리스트를 보여줍니다.",
        description: [
            ""
        ],
    }
]
const $adminGuideComponent = document.querySelector(".adminGuideComponent");
let innerHTML = "";
guideList.forEach((guide, idx) => {
    const {title, header, description} = guide;
    innerHTML += `
          <div class="accordion-item">
              <h2 class="accordion-header">
                  <button class="accordion-button bg-secondary text-bg-secondary ${idx == 0 ? "" : "collapsed"}" type="button" data-bs-toggle="collapse"
                          data-bs-target="#collapse${idx}"
                          aria-expanded="${idx == 0 ? "true" : "false"}" aria-controls="collapse${idx}">
                      #${idx + 1}. ${title}
                  </button>
              </h2>
              <div id="collapse${idx}" class="accordion-collapse collapse ${idx == 0 ? "show" : ""}" data-bs-parent="#accordionExample">
                <div class="accordion-body">
                  <strong>${header}</strong><br/>
                  ${description.map((sentence) => `- ${sentence} <br/>`).join("")}
                </div>
              </div>
          </div>
          `
})
$adminGuideComponent.innerHTML = innerHTML;


const $descriptionForm = document.querySelector('.descriptionForm')
const $toastSuccess = document.getElementById('toastSuccess')
const $toastFailure = document.getElementById('toastFail')

document.addEventListener("DOMContentLoaded", () => {
    console.log(alertMessage);
    if (alertMessage == null || alertMessage == "null") {
        return;
    } else {
        triggerToast($toastFailure);
    }
})

const triggerToast = ($target) => {
    const toast = new bootstrap.Toast($target);
    toast.show();
}

window.onkeydown = function (event) {
    const kcode = event.key;
    if (kcode == "refresh") {
        history.replaceState({}, null, location.pathname);
    }
}
