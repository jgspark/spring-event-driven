package com.example.producer.service;

import com.example.producer.domain.user.User;
import com.example.producer.domain.user.UserValidator;
import com.example.producer.domain.user.UserWriter;
import com.example.producer.dto.request.UserRegisterRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    private UserService userService;

    @Mock
    private UserWriter userWriter;

    @Mock
    private UserValidator userValidator;

    @BeforeEach
    public void init() {
        userService = new UserServiceImpl(userWriter, userValidator);
    }

    /**
     * 1. 성공 케이스
     * 2. 검증이 실패가 되는 케이스
     * 3. 저장이 되지 않는 케이스
     */
    @Nested
    @DisplayName("회원 가입 메소드는")
    public class Register {

        @Test
        @DisplayName("정상적으로 동작을 하게 된다.")
        public void testRegister() {

            String email = "test@naver.com";

            String password = "!234";

            User mock = User.of(email, password);

            UserRegisterRequest userRegisterRequest = new UserRegisterRequest(email, password);

            doNothing().when(userValidator).validation(any());

            when(userWriter.writer(any())).thenReturn(mock);

            User entity = userService.register(userRegisterRequest);

            verify(userValidator, times(1)).validation(any());
            verify(userWriter, times(1)).writer(any());

            assertEquals(entity, mock);
        }

        @Test
        @DisplayName("검증이 실패를 하게 되면 예외가 발생을 하게 된다.")
        public void testRegister_ValidatorFail() {

            String errorMsg = "Fail Valid";

            String email = null;

            String password = "!234";

            UserRegisterRequest userRegisterRequest = new UserRegisterRequest(email, password);

            doThrow(new RuntimeException(errorMsg))
                    .when(userValidator)
                    .validation(any());

            RuntimeException e = assertThrows(RuntimeException.class, () -> userService.register(userRegisterRequest));

            verify(userValidator, times(1)).validation(any());

            verify(userWriter, times(0)).writer(any());

            assertEquals(e.getMessage(), errorMsg);
        }

        @Test
        @DisplayName("저장이 되지 않는 케이스의 경우 예외가 발생을 하게 된다.")
        public void testRegister_WriterFail() {

            String errorMsg = "Fail Writer";

            String email = "test@naver.com";

            String password = "!234";

            UserRegisterRequest userRegisterRequest = new UserRegisterRequest(email, password);

            doNothing().when(userValidator).validation(any());

            when(userWriter.writer(any())).thenThrow(new RuntimeException(errorMsg));

            RuntimeException e = assertThrows(RuntimeException.class, () -> userService.register(userRegisterRequest));

            verify(userValidator, times(1)).validation(any());
            verify(userWriter, times(1)).writer(any());

            assertEquals(e.getMessage(), errorMsg);
        }


    }

}
