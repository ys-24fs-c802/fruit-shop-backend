export class FormValidator {
    constructor(formId, config) {
        this.form = document.getElementById(formId);
        this.config = config;
        this.init();
    }

    init() {
        this.setupValidation();
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
}