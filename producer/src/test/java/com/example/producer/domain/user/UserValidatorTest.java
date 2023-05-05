package com.example.producer.domain.user;

import com.example.producer.domain.common.Validator;
import com.example.producer.infra.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserValidatorTest {

    @Mock
    private UserRepository userRepository;

    private Validator<User> userValidator;

    @BeforeEach
    public void init() {
        userValidator = new UserValidator(userRepository);
    }

    @Test
    public void testValidation_Ok() {

        String email = "test@naver.com";

        String password = "1234";

        User mock = User.of(email, password);

        when(userRepository.existsByEmail(anyString())).thenReturn(Boolean.FALSE);

        assertDoesNotThrow(() -> userValidator.validation(mock));

        verify(userRepository, times(1)).existsByEmail(anyString());
    }


    @Test
    public void testValidation_EmailIsEmpty() {

        String email = null;

        String password = "1234";

        User mock = User.of(email, password);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userValidator.validation(mock));

        verify(userRepository, times(0)).existsByEmail(anyString());

        assertEquals(e.getMessage(), "Email is Empty");
    }

    @Test
    public void testValidation_PasswordIsEmpty() {

        String email = "test@naver.com";

        String password = null;

        User mock = User.of(email, password);

        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> userValidator.validation(mock));

        verify(userRepository, times(0)).existsByEmail(anyString());

        assertEquals(e.getMessage(), "Password is Empty");
    }

    @Test
    public void testValidation_EmailIsNotFormat() {

        String email = "test@naver";

        String password = "1234";

        User mock = User.of(email, password);

        RuntimeException e = assertThrows(RuntimeException.class, () -> userValidator.validation(mock));

        verify(userRepository, times(0)).existsByEmail(anyString());

        assertEquals(e.getMessage(), "email format is not match");
    }

    @Test
    public void testValidation_EmailIsExists() {

        String email = "test@naver.com";

        String password = "1234";

        User mock = User.of(email, password);

        when(userRepository.existsByEmail(anyString())).thenReturn(Boolean.TRUE);

        RuntimeException e = assertThrows(RuntimeException.class, () -> userValidator.validation(mock));

        verify(userRepository, times(1)).existsByEmail(anyString());

        assertEquals(e.getMessage(), "This is a duplicate email.");
    }
}
