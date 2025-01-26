function confirmDelete(element) {

    const itemId = element.getAttribute('data-item-id');

    if (confirm('정말 삭제하시겠습니까?')) {
        fetch("/items/"+itemId+"/remove", {
            method: 'get',
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            if (response.ok) {
                alert('아이템이 삭제되었습니다.');
                window.location.href = '/items'; // 수정 후 목록으로 리다이렉트
            } else {
                alert('아이템 삭제에 실패했습니다.');
            }
        }).catch(error => {
            console.error('Error:', error);
            alert('오류가 발생했습니다.');
        });
    }
}