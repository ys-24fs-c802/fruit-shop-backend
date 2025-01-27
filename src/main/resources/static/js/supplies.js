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

function hyphenDelete(str) {
    // 좌우 공백 제거
//    str = str.trim();

    // 하이픈 제거
    str = str.replace(/-/g, '');

    return str;
}

// 전화번호(010) 검사
function validatePhoneNumber(str) {
    str = hyphenDelete(str);
    // 전화번호 패턴 검사(01로 시작하는 10-11자리)
    return /^01[0-9]{8,9}/.test(str);
}

// 사업자 번호 검사
function validateBusinessNumber(str) {
    str = hyphenDelete(str);
    // 사업자번호 패턴 검사(10자리)
    return /[0-9]{10}/.test(str);
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
})

document.getElementById('contact1_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const contact1Error = document.getElementById('contact1Error');
    contact1Error.style.display = !validatePhoneNumber(value) ? 'block' : 'none';
})

document.getElementById('contact2_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const contact2Error = document.getElementById('contact2Error');
    contact2Error.style.display = !validatePhoneNumber(value) ? 'block' : 'none';
})

document.getElementById('business_number_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const businessNumberError = document.getElementById('businessNumberError');
    businessNumberError.style.display = !validateBusinessNumber(value) ? 'block' : 'none';
})

document.getElementById('supplyForm').addEventListener('submit', function(e) {

    e.preventDefault();

    const supply = {
        name: document.getElementById('name_id').value,
        contact1: hyphenDelete(document.getElementById('contact1_id').value),
        contact2: hyphenDelete(document.getElementById('contact2_id').value),
        businessNumber: hyphenDelete(document.getElementById('business_number_id').value),
    }

    if (! hasWhiteSpace(supply.name) &&
        ! hasSpecialChar(supply.name) &&
        ! startWithNumber(supply.name) &&
        validatePhoneNumber(supply.contact1) &&
        validatePhoneNumber(supply.contact2) &&
        validateBusinessNumber(supply.businessNumber)) {
        alert('서버로 전송한다.');
    } else {
        alert('입력값을 다시 확인해주세요.');
    }

    // fetch(요청주소, 요청내용객체)
    // 성공
    // 실패
    fetch("/supplies", {
        method: 'post',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(supply)
    }).then(response => {
        if (response.ok) {
            alert('구입처가 성공적으로 생성되었습니다.');
            document.getElementById('supplyForm').reset();
        } else {
            alert('구입처 생성에 실패했습니다.');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});