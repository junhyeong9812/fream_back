package com.kream.root.main.CSVParser;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Log4j2
public class OrderDataService {
    private final ReadLineContext<OrderData> orderDataReadLineContext;

    public OrderDataService(ReadLineContext<OrderData> orderDataReadLineContext){
        this.orderDataReadLineContext = orderDataReadLineContext;
    }

    @Transactional
    public int insertLargeVolumeData(String fileName){
        List<OrderData> orderDataList;
        try {
            orderDataList = orderDataReadLineContext.readByLine(fileName);
            log.info("파싱 완료");
            orderDataList.stream()
                    .parallel()
                    .forEach(data -> { //수정중
//                        try{
//
//                        }
                    });
        }catch (Exception e){

        }
        return 0;
    }
}
