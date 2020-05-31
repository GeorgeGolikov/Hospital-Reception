package project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import project.entity.Diagnosis;
import project.entity.User;
import project.repository.DiagnosisRepository;
import project.repository.UserRepository;

import java.util.Collections;

//@Component
//public class TestDataInit implements CommandLineRunner
//{
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    DiagnosisRepository diagnosisRepository;
//
//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Override
//    public void run(String... args) throws Exception
//    {
//        diagnosisRepository.save(new Diagnosis(1, "cold123123", null));
//        diagnosisRepository.save(new Diagnosis(2, "cold123456", null));
//
//        userRepository.save(new User("user", passwordEncoder.encode("pwd"),
//                                        Collections.singletonList("ROLE_USER")));
//        userRepository.save(new User("admin", passwordEncoder.encode("apwd"),
//                                        Collections.singletonList("ROLE_ADMIN")));
//    }
//}
