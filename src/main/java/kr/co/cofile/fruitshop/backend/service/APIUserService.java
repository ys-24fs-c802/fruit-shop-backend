package kr.co.cofile.fruitshop.backend.service;

import kr.co.cofile.fruitshop.backend.dto.UserDTO;
import kr.co.cofile.fruitshop.backend.mapper.APIUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class APIUserService {
    private final APIUserMapper apiUserMapper;
    private final PasswordEncoder passwordEncoder;

    public void signup(UserDTO userDTO) {
        String encodedPw = passwordEncoder.encode(userDTO.getPassword());
        userDTO.setPassword(encodedPw);
        userDTO.setEnabled(userDTO.isEnabled());

        // 사용자 등록
        apiUserMapper.save(userDTO);
        // 권한 등록
        apiUserMapper.insertUserRole(userDTO.getId(), 1);
    }
}
