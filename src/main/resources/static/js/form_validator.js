export class FormValidator {
    constructor(formId, config) {
        this.form = document.getElementById(formId);
        this.config = config;
        this.init();
    }

    init() {
        this.setupValidation();
        this.setupSubmit();
    }

    // 유효성 검사 설정
    setupValidation() {
        Object.values(this.config.fields).forEach(field => {
            const fieldElement = document.getElementById(field.id);
            fieldElement.addEventListener('input', function (e) {
                const value = e.target.value;
                field.rules.forEach(rule => {
                    const errorElement = document.getElementById(rule.errorId);
                    if (rule.validate(value)) {
                        errorElement.style.display =  'block';
                        errorElement.innerText = rule.message;
                    } else {
                        errorElement.style.display =  'none';
                        errorElement.innerText = '';
                    }
                })
            })
        })
    }

    // 폼 전송
    setupSubmit() {
        // 화살쵸 함수를 사용하여 이벤트 핸들러 내부에서 this가 FormValidator 인스턴스를 가리키도록 설정
        this.form.addEventListener('submit', async (e) => {
            e.preventDefault();
            // 요청 객체 생성
            const formValues = {};
            let isValid = true;

            // 데이터 수집
            for (const [k, v] of Object.entries(this.config.fields)) {
                const value = document.getElementById(v.id).value;

                // 유효성 검사
                if (v.rules.some(rule => rule.validate(value))) {
                    isValid = false;
                    break;
                }

                formValues[k] = value;
            }

            // 유효성검사 실패시 요청 객체 null로 설정
            if (!isValid) {
                this.clearFormValues(formValues);
                return;
            }

            // 서버로 전송
            console.log(formValues);
            try {
                this.showLoading();

                // 서버로 전송
                const response = await fetch(this.config.submitUrl, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(formValues)
                });

                // HTTP 에러 체크
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                // 응답 데이터 처리
                // const result = await response.json();

                // 성공 처리
                this.handleSuccess(this.config.onSuccess.message);

                // 폼 초기화
                this.form.reset();
                this.clearFormValues(formValues);
            } catch (error) {
                this.handleError(this.config.onError.message)
            } finally {
                this.hideLoading();
            }
        })
    }

    // 폼 값을 초기화
    clearFormValues(formValues) {
        Object.values(formValues).forEach(key => { delete formValues[key] });
    }

    // 로딩 표시
    showLoading() {
        console.log('show loading');
    }

    // 로딩 해제
    hideLoading() {
        console.log('hide loading');
    }

    // 성공 처리
    handleSuccess(result) {
        console.log('success:', result);
    }

    // 에러 처리
    handleError(error) {
        console.error('error:', error);
    }
}