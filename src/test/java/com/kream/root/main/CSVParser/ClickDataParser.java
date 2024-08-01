package com.kream.root.main.CSVParser;

import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;

@Log4j2
public class ClickDataParser implements Parser<ClickData>{

    @Override
    public ClickData parse(String str) {
        log.info("str" + str);
        String[] splitted = str.split(",");
        log.info("splitted" + splitted[0]);

        ClickData clickData = new ClickData();
        clickData.setUbDate(LocalDate.parse(splitted[0]));
        clickData.setPrid(Long.parseLong(splitted[1]));
        clickData.setClickCount(Integer.parseInt(splitted[2]));

        log.info(clickData);

        return clickData;
    }
}

