package com.kream.root.main.CSVParser;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Log4j2
public class StyleDataParser implements Parser<StyleData>{
    @Override
    public StyleData parse(String str) {
        log.info("str" + str);
        String[] splitted = str.split(",");
        log.info("splitted" + splitted[0]);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        StyleData styleData = new StyleData();
        styleData.setStyleDate((LocalDateTime.parse(splitted[0], dateTimeFormatter)));
        styleData.setPrId(Long.parseLong(splitted[1]));

        log.info(styleData);
        return styleData;
    }
}
