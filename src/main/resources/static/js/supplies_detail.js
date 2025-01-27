function confirmDelete(element) {

    const supplyId = element.getAttribute('data-supply-id');

    if (confirm('정말 삭제하시겠습니까?')) {
        fetch("/supplies/"+supplyId+"/remove", {
            method: 'DELETE',
            headers: {'Content-Type': 'application/json'}
        }).then(response => {
            if (response.ok) {
                alert('구입처가 삭제되었습니다.');
                window.location.href = '/supplies'; // 수정 후 목록으로 리다이렉트
            } else {
                alert('구입처 삭제에 실패했습니다.');
            }
        }).catch(error => {
            console.error('Error:', error);
            alert('오류가 발생했습니다.');
        });
    }
}