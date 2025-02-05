package kr.co.cofile.fruitshop.backend.service;

import kr.co.cofile.fruitshop.backend.dto.UserDTO;
import kr.co.cofile.fruitshop.backend.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserMapper userMapper;

    public void signup(UserDTO userDTO) {
        userDTO.setEnabled(true);

        userMapper.save(userDTO);
    }

}
