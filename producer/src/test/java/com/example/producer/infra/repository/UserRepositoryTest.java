package com.example.producer.infra.repository;

import com.example.producer.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class UserRepositoryTest {


    @Autowired
    private UserRepository userRepository;

    @Nested
    @DisplayName("저장 메소드는")
    public class Save {

        @Test
        @DisplayName("성공적으로 저장이 된다.")
        public void testSave_Ok() {

            String email = "test@naver.com";

            String pwd = "1234";

            User mock = User.of(email, pwd);

            User entity = userRepository.save(mock);

            assertNotNull(email);
            assertEquals(entity.getId(), mock.getId());
            assertEquals(entity.getEmail(), mock.getEmail());
            assertEquals(entity.getPassword(), mock.getPassword());
        }
    }

    @Nested
    @DisplayName("중복된 이메일 체크 메소드는")
    public class ExistsByEmail {
        private final String email = "test1@naver.com";

        private final String pwd = "1234";

        @BeforeEach
        public void init() {
            User mock = User.of(email, pwd);
            userRepository.save(mock);
            userRepository.flush();
        }

        @Test
        @DisplayName("정상적으로 찾지 못한다.")
        public void testExistsByEmail_Ok() {
            String email = "test@naver.com";
            boolean value = userRepository.existsByEmail(email);
            assertFalse(value);
        }

        @Test
        @DisplayName("중복된 이메일을 갖는다면, true 를 반환을 한다.")
        public void testExistsByEmail_Fail() {
            boolean value = userRepository.existsByEmail(email);
            assertTrue(value);
        }
    }
}
