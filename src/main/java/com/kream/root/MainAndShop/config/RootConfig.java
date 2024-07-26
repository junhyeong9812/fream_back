package com.kream.root.MainAndShop.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RootConfig {
    @Bean
    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setFieldMatchingEnabled(true) //필드값으로 매칭이 가능한지
                .setFieldAccessLevel(org.modelmapper.config.
                        Configuration.AccessLevel.PRIVATE) //접근 레벨(public, protected, private)
                .setMatchingStrategy(MatchingStrategies.LOOSE); //소스 토큰과 대상 토큰의 일치 방법

        return modelMapper;
    }
}
