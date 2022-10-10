package com.tunts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.List;

@SpringBootApplication
@Slf4j
public class TuntsChallengeApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(TuntsChallengeApplication.class, args);
        final long start = System.currentTimeMillis();
        log.info("Starting the process!");
        AverageStudent averageStudent = new AverageStudent();
        List<Student> studentList = averageStudent.validAvarage();
        averageStudent.print(studentList);
        log.info(String.format("Finished in: %s seconds.", (System.currentTimeMillis() - start) / 1000F));
    }
}
