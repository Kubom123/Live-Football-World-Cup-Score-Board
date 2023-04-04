package com.example.springboot.app;
import com.example.springboot.components.ScoreBoard;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class Config {

    @Bean
    public ScoreBoard get(){
        return new ScoreBoard();
    }
}
