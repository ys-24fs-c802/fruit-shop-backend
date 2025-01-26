document.getElementById('itemForm').addEventListener('submit', function(e) {

    e.preventDefault();

    const item = {
        id: document.getElementById('id_id').value,
        name: document.getElementById('name_id').value,
    }

    // fetch(요청주소, 요청내용객체)
    // then 성공, 실패
    fetch("/items/"+item.id+"/modify", {
        method: 'PUT',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(item)
    }).then(response => {
        if (response.ok) {
            alert('아이템이 성공적으로 수정되었습니다.');
            document.getElementById('itemForm').reset();
            window.location.href = '/items'; // 수정 후 목록으로 리다이렉트
        } else {
            alert('아이템 수정에 실패했습니다.');
        }
    }).catch(error => {
        console.error('Error:', error);
        alert('오류가 발생했습니다.');
    });
});