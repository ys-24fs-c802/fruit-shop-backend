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
    },
    submitUrl: '/admin/items',
    onSuccess: {
        message: '상품이 등록되었습니다.'
    },
    onError: {
        message: '상품 등록에 실패했습니다.'
    }
}

const formValidator = new FormValidator('itemForm', config);