package com.example.producer.infra.repository;

import com.example.producer.domain.user.User;
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
    public class Save {

        @Test
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
}
