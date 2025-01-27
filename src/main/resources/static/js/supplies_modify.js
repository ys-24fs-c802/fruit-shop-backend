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

    // fetch(요청주소, 요청내용객체)
    // then 성공, 실패
    fetch("/supplies/"+sypply.id+"/modify", {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(sypply)
    }).then(response => {
        if (response.ok) {
            alert('아이템이 성공적으로 수정되었습니다.');
            document.getElementById('supplyForm').reset();
            window.location.href = '/supplies'; // 수정 후 목록으로 리다이렉트
        } else {
            alert('아이템 수정에 실패했습니다.');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});