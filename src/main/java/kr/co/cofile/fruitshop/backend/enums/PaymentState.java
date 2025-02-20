package kr.co.cofile.fruitshop.backend.enums;

public enum PaymentState {
    CREATED,    // 결제 생성
    APPROVED,   // 결제 승인
    FAILED,     // 결제 실패
    CANCELLED   // 결제 취소
}