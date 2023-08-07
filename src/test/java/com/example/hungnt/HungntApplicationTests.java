package com.example.hungnt;

import com.example.hungnt.Services.UserService;
import com.example.hungnt.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class HungntApplicationTests {
    @TestConfiguration
    public static class UserServiceTest{
        @Bean
        UserService userService(){
            return new UserService();
        }
    }

    @MockBean
    UserRepository userRepository;

    @Autowired
    private  UserService userService;
    @Test
    void contextLoads() {
    }

}
