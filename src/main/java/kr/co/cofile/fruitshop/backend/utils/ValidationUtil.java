package kr.co.cofile.fruitshop.backend.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.http.ResponseEntity;
import java.util.*;

public class ValidationUtil {

    public static ResponseEntity<Map<String, List<String>>> handleValidationErrors(BindingResult bindingResult) {
        Map<String, List<String>> errorMap = new HashMap<>();

        // BindingResult에서 에러들을 순회
        bindingResult.getFieldErrors().forEach(error -> {
            // 에러가 발생한 필드명 추출
            String field = error.getField();
            // 해당 필드의 에러 메시지 추출
            String message = error.getDefaultMessage();
            // errorMap에 필드별 에러메시지 리스트 추가
            // computeIfAbsent: 해당 key가 없으면 새 ArrayList 생성
            // 있으면 기존 리스트에 메시지 추가
            // 참고: https://tinyurl.com/mrxbfpz8
            errorMap.computeIfAbsent(field, k -> new ArrayList<>()).add(message);
        });

        return ResponseEntity.badRequest().body(errorMap);
    }
}