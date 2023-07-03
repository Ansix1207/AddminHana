function checkPwd() {
    var form = document.getElementById('transferForm');
    var isCheck = document.createElement('input');
    isCheck.setAttribute('type', 'text');
    isCheck.setAttribute('name', 'isCheck');
    let acc_id = document.getElementById('acc_id').value;
    document.getElementById('acc_id').value = acc_id.replace(/ /g, "");
    // let t_amount = document.getElementById('t_amount').value;
    // document.getElemenrtById('t_amount').value = t_amount.replace(/ /g, "");

    if (document.getElementById('acc_password').value.trim() <= 3) {
        alert("비밀번호 4자리를 모두 입력해주세요.")
        return;
    }
    if (document.getElementById('acc_id').value.trim() <= 0) {
        alert("내 계좌번호를 입력해야 합니다.")
        return;
    }
    isCheck.setAttribute('value', 'check');
    form.appendChild(isCheck);
    form.submit();
    var isCheckInput = form.elements['isCheck']; // name이 'valid_rrn'인 요소 가져오기
    if (isCheckInput) {
        isCheckInput.parentNode.removeChild(isCheckInput); // 요소 삭제
        form.removeAttribute('isCheck');
    }
}
