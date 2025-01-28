import { inputValidator } from "./common.js";
import { FormValidator } from "./form_validator.js";

const config = {
    fields: {
        name: {
            id: 'name_id',
            rules: [
                {
                    validate: inputValidator.hasWhiteSpace,
                    errorId: 'spaceError',
                    message: '공백을 포함할 수 없습니다.'
                },
                {
                    validate: inputValidator.hasSpecialChar,
                    errorId: 'specialCharError',
                    message: '특수문자를 포함할 수 없습니다.'
                },
                {
                    validate: inputValidator.startWithNumber,
                    errorId: 'startWithNumberError',
                    message: '숫자로 시작할 수 없습니다.'
                }
            ]
        },
    }
}

const formValidator = new FormValidator('itemForm', config);

// document.getElementById('name_id').addEventListener('input', function(e) {
//     const value = e.target.value;
//     const spaceError = document.getElementById('spaceError');
//     const specialCharError = document.getElementById('specialCharError');
//     const startWithNumberError = document.getElementById('startWithNumberError');
//
//     //console.log(value, "=>", hasWhiteSpace(value));
//
//     spaceError.style.display = inputValidator.hasWhiteSpace(value) ? 'block' : 'none';
//     specialCharError.style.display = inputValidator.hasSpecialChar(value) ? 'block' : 'none';
//     startWithNumberError.style.display = inputValidator.startWithNumber(value) ? 'block' : 'none';
// })

document.getElementById('itemForm').addEventListener('submit', function(e) {

    e.preventDefault();

    const item = {
        name: document.getElementById('name_id').value,
    }

    if (!inputValidator.hasWhiteSpace(item.name) &&
        !inputValidator.hasSpecialChar(item.name) &&
        !inputValidator.startWithNumber(item.name)) {
        // alert('서버로 전송합니다.');
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
            alert('상품이 성공적으로 등록되었습니다.');
            document.getElementById('itemForm').reset();
        } else {
            alert('상품 등록에 실패했습니다.');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});