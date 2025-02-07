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
        contact1: {
            id: 'contact1_id',
            rules: [
                {
                    validate: inputValidator.validateLandLineNumber,
                    errorId: 'contact1Error',
                    message: '일반전화번호의 입력 형식을 확인해주세요.'
                }
            ]
        },
        contact2: {
            id: 'contact2_id',
            rules: [
                {
                    validate: inputValidator.validateMobileNumber,
                    errorId: 'contact2Error',
                    message: '휴대전화의 입력 형식을 확인해주세요.'
                }
            ]
        },
        businessNumber: {
            id: 'business_number_id',
            rules: [
                {
                    validate: inputValidator.validateBusinessNumber,
                    errorId: 'businessNumberError',
                    message: '사업자번호의 입력 형식을 확인해주세요.'
                }
            ]
        }
    },
    submitUrl: '/admin/supplies',
    onSuccess: {
        message: '구입처가 등록되었습니다.'
    },
    onError: {
        message: '구입처 등록에 실패했습니다.'
    }
}

const formValidator = new FormValidator('supplyForm', config);