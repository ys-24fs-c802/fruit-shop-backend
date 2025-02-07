document.getElementById('supplyForm').addEventListener('submit', function(e) {

    e.preventDefault();

    // TODO 유효성 검사

    const sypply = {
        id: document.getElementById('id_id').value,
        name: document.getElementById('name_id').value,
        contact1: document.getElementById('contact1_id').value,
        contact2: document.getElementById('contact2_id').value,
        businessNumber: document.getElementById('businessNumber_id').value,
    }

    // 20250207 스프링 시큐리티는 기본적으로 CSRF 보호를 활성화합니다.
    // 세션방식으로 인증을 사용하는 경우 CSRF 토큰을 반드시 포함해야 한다.
    // API 서버로 운영하는 경우 CSRF가 비활성화되며 이 경우는 포함하지 않다도 됩니다.
    // HTML 메타 태그에서 CSRF 토큰과 헤더 이름을 가져옴
    const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
    const csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

    // fetch(요청주소, 요청내용객체)
    // then 성공, 실패
    fetch("/admin/supplies/"+sypply.id+"/modify", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
            [csrfHeader]: csrfToken // CSRF 토큰 헤더 추가
        },
        body: JSON.stringify(sypply)
    }).then(response => {
        if (response.ok) {
            alert('아이템이 성공적으로 수정되었습니다.');
            document.getElementById('supplyForm').reset();
            window.location.href = '/admin/supplies'; // 수정 후 목록으로 리다이렉트
        } else {
            alert('아이템 수정에 실패했습니다.');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});