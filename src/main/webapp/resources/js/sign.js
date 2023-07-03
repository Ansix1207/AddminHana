function check() {
    let name = document.querySelector("#name");
    let rrn1 = document.querySelector("#rrn1");
    let rrn2 = document.querySelector("#rrn2");
    let mobile = document.querySelector("#inputMobile");
    let address = document.querySelector("#inputAddress");

    if (name.value === "") {
        name.classList.add("is-invalid");
    } else {
        name.classList.remove("is-invalid");
    }

    if (mobile.value === "") {
        mobile.classList.add("is-invalid");
    }
    let zipCode = document.querySelector("#inputZip");
    if (zipCode.value === "") {
        zipCode.classList.add("is-invalid");
    }
    mobile.addEventListener("change", function () {
        if (mobile.value.length > 10) {
            mobile.classList.remove("is-invalid"); // 길이가 0 이상이면 "is-invalid" 클래스를 제거합니다.
        } else {
            mobile.classList.add("is-invalid");
        }
    });

    zipCode.addEventListener("change", function () {
        if (zipCode.value.length > 0) {
            zipCode.classList.remove("is-invalid"); // 길이가 0 이상이면 "is-invalid" 클래스를 제거합니다.
        } else if (zipCode.value === "") {
            zipCode.classList.add("is-invalid");
        }
    });

    if (address.value === "") {
        address.classList.add("is-invalid");
    } else {
        address.classList.remove("is-invalid");
    }

    if (name.value === "" || rrn1.value === "" || rrn2.value === "" || mobile.value === "" || address.value === "" || zipCode.value === "") {
        alert("빠진 정보를 입력해주세요.");
    } else {
        const form = document.getElementById('signForm');
        form.submit();
    }
}

function submitForm() {
    if (document.getElementById('name').value.length < 1) {
        alert("이름을 입력해야합니다.")
        return;
    }
    if (document.getElementById('rrn1').value.length < 6) {
        alert("주민 등록번호 앞자리를 모두 입력해야 합니다.")
        return;
    }
    if (document.getElementById('rrn2').value.length < 7) {
        alert("주민 등록번호 뒷자리를 모두 입력해야 합니다.")
        return;
    }
    const form = document.getElementById('signForm');

    // 필요한 input 요소 추가

    var inputRRN = document.createElement('input');
    inputRRN.setAttribute('type', 'text');
    inputRRN.setAttribute('name', 'valid_rrn');
    let rrn_p1 = document.getElementById('rrn1').value;
    let rrn_p2 = document.getElementById('rrn2').value // 예시로 고정된 값 설정
    if (!(rrn_p1.trim() == 0) && !(rrn_p2.trim() == 0)) {
        inputRRN.setAttribute("value", rrn_p1 + '-' + rrn_p2);
    }
    form.appendChild(inputRRN);

    form.submit();
    var validRrnInput = form.elements['valid_rrn']; // name이 'valid_rrn'인 요소 가져오기

    if (validRrnInput) {
        validRrnInput.parentNode.removeChild(validRrnInput); // 요소 삭제
        form.removeAttribute('valid_rrn');
    }
}


function execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function (data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if (data.userSelectedType === 'R') {
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if (data.bname !== '' && /[동|로|가]$/g.test(data.bname)) {
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if (data.buildingName !== '' && data.apartment === 'Y') {
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if (extraAddr !== '') {
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("extraAddress").value = extraAddr;

            } else {
                document.getElementById("extraAddress").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('inputZip').value = data.zonecode;
            document.getElementById("inputAddress").value = addr;
            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("inputAddress2").focus();
        }
    }).open();
}
