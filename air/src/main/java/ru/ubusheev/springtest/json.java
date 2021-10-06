package ru.ubusheev.springtest;
import org.hibernate.Session;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class json {
    public static void main(String[] args) {
        SpringApplication.run(json.class, args);
    }

    @Bean
    public Session getSession(){
        return HibernateSession.getSessionFactory().openSession();
    }

}
