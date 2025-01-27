import { inputValidator } from "./common.js";

document.getElementById('name_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const spaceError = document.getElementById('spaceError');
    const specialCharError = document.getElementById('specialCharError');
    const startWithNumberError = document.getElementById('startWithNumberError');

    //console.log(value, "=>", hasWhiteSpace(value));

    spaceError.style.display = inputValidator.hasWhiteSpace(value) ? 'block' : 'none';
    specialCharError.style.display = inputValidator.hasSpecialChar(value) ? 'block' : 'none';
    startWithNumberError.style.display = inputValidator.startWithNumber(value) ? 'block' : 'none';
})

document.getElementById('contact1_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const contact1Error = document.getElementById('contact1Error');
    contact1Error.style.display = !inputValidator.validateLandLineNumber(value) ? 'block' : 'none';
})

document.getElementById('contact2_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const contact2Error = document.getElementById('contact2Error');
    contact2Error.style.display = !inputValidator.validateMobileNumber(value) ? 'block' : 'none';
})

document.getElementById('business_number_id').addEventListener('input', function(e) {
    const value = e.target.value;
    const businessNumberError = document.getElementById('businessNumberError');
    businessNumberError.style.display = !inputValidator.validateBusinessNumber(value) ? 'block' : 'none';
})

document.getElementById('supplyForm').addEventListener('submit', function(e) {

    e.preventDefault();

    const supply = {
        name: document.getElementById('name_id').value,
        contact1: document.getElementById('contact1_id').value,
        contact2: document.getElementById('contact2_id').value,
        businessNumber: document.getElementById('business_number_id').value,
    }

    if (!inputValidator.hasWhiteSpace(supply.name) &&
        !inputValidator.hasSpecialChar(supply.name) &&
        !inputValidator.startWithNumber(supply.name) &&
        !inputValidator.validateLandLineNumber(supply.contact1) &&
        !inputValidator.validateMobileNumber(supply.contact2) &&
        !inputValidator.validateBusinessNumber(supply.businessNumber)) {
        // alert('서버로 전송한다.');
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