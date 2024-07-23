package com.kream.root.MyPage.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.stream.Stream;

@Service
@Log4j2
public class MyPageImgServiceImpl implements MyPageImgService{

    @Override
    public String SaveImg(String userid, MultipartFile img, String uploadDir) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss_"); //날짜 포맷 지정
            String sysFileName = format.format(new Date()); //java_util
            if (!img.isEmpty()) {
                //파일 이름 생성
                String fileName = userid + "_" + sysFileName + img.getOriginalFilename();
                String filePath = uploadDir + "/" + fileName;

                // DB에 저장할 경로 문자열
                String dbFilePath = "/upload/ProfileImg/" + fileName;

                Path path = Paths.get(filePath); // Path 객체 생성
                log.info(path);
                deleteUserImg(uploadDir, userid); //이미지가 기존에 있을 경우 삭제
                Files.write(path, img.getBytes()); // 디렉토리에 파일 저장
                return dbFilePath;
            }
            return null;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    //이미지가 기존에 있을 경우 삭제
    //해당 메소드는 이미지 삭제시에도 사용할 수 있도록 구성
    public void deleteUserImg (String uploadDir, String userid) throws IOException{
        Path path = Paths.get(uploadDir); //경로 생성

        //원하는 파일이 경로 내에 있는 지 찾기
        try(Stream<Path> files = Files.find(path, Integer.MAX_VALUE,
                (pa, basicFileAttributes) -> pa.getFileName().toString().contains(userid))){

            files.forEach(paths -> {
                try {
                    Files.delete(paths); //있을 경우 삭제하기
                    log.info("Deleted: " + paths);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        };
    }
}
