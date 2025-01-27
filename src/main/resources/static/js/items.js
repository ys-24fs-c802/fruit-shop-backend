// 공백 검사
function hasWhiteSpace(str) {
    return /\s/.test(str)
}

// 특수문자 검사 [!@#$%]
// ., -, \는 정규시에 사용되는 문자이므로 이스케이프(escape) 처리를 해야 합니다.
function hasSpecialChar(str) {
    return /[!@#$%^&*():{}|<>,'~_=\.\-\\`]/.test(str);
}

// 숫자로 시작하는지 검사
function startWithNumber(str) {
    // 1abc, 9abc 시작할 때 검사는 ^를 사용합니다.
    return /^[0-9]/.test(str)
}

document.getElementById('name_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const spaceError = document.getElementById('spaceError');
    const specialCharError = document.getElementById('specialCharError');
    const startWithNumberError = document.getElementById('startWithNumberError');

    //console.log(value, "=>", hasWhiteSpace(value));

    spaceError.style.display = hasWhiteSpace(value) ? 'block' : 'none';
    specialCharError.style.display = hasSpecialChar(value) ? 'block' : 'none';
    startWithNumberError.style.display = startWithNumber(value) ? 'block' : 'none';
    // 입력시 서버 오류 메시지는 감추기
    document.getElementById('nameError').style.display = 'none';
})

document.getElementById('itemForm').addEventListener('submit', function(e) {

    e.preventDefault();

    const item = {
        name: document.getElementById('name_id').value,
    }

    if (! hasWhiteSpace(item.name) &&
        ! hasSpecialChar(item.name) &&
        ! startWithNumber(item.name)) {
        alert('서버로 전송합니다.');
    } else {
        alert('상품명을 다시 확인해주세요.');
    }

    // fetch(요청주소, 요청내용객체)
    // then 성공, 실패
    fetch("/items", {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(item)
    }).then(response => {
        if (response.ok) {
            alert('아이템이 성공적으로 등록되었습니다.');
            document.getElementById('itemForm').reset();
        } else {
            alert('아이템 등록에 실패했습니다.');
            response.json().then(errorMap => {
                Object.entries(errorMap).forEach(([field, messages]) => {
                    const errorEl = document.getElementById(`${field}Error`);
                    if (errorEl) {
                        errorEl.style.display = 'block';
                        errorEl.innerText = messages.join('\n');
                    }
                });
            });
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});