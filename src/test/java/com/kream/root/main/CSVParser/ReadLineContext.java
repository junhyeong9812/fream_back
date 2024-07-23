package com.kream.root.main.CSVParser;

import lombok.extern.log4j.Log4j2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Log4j2
public class ReadLineContext<T> {
    Parser<T> parser;

    public ReadLineContext(Parser<T> parser){
        this.parser = parser;
    }

    public List<T> readByLine(String fileName) throws IOException{
        List<T> result = new ArrayList<>();
        BufferedReader reader = new BufferedReader(
            new FileReader(fileName)
        );
        Locale locale;
        String line;
        while ((line = reader.readLine()) != null) {
            try {
                result.add(parser.parse(line));
            }catch (Exception e){
                log.error("Parser 에러 발생 : " + e);
            }
        }
        reader.close();
        return result;
    }

}
