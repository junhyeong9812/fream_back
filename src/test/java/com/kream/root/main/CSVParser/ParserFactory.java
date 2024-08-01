package com.kream.root.main.CSVParser;

import com.kream.root.entity.Orders;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Parser;

import java.text.ParseException;
import java.util.Locale;

@Configuration
public class ParserFactory {
    @Bean
    public ReadLineContext<OrderData> ordersReadLineContext(){
        return new ReadLineContext<OrderData>( new OrderDataParser());
    }

    @Bean
    public ReadLineContext<ClickData> clickReadLineContext(){
        return new ReadLineContext<ClickData>( new ClickDataParser());
    }

    @Bean
    public ReadLineContext<StyleData> styleReadLineContext(){
        return new ReadLineContext<StyleData>( new StyleDataParser());
    }
}
