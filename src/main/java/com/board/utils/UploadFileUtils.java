/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-27
 Time: 오후 4:28
 desc: 파일업로드 Util class

 # 서버에 파일저장시 고려사항
 1) 동일한 파일명을 저장할 경우 : UUID를 이용하여 고유값을 생성, 원본파일명 앞에 붙여 중복문제를 해결

 2) 파일의 단일 저장 경로 : 단일 경로에 파일 저장시, 추후에 파일검색시 속도문제가 발생할 수 있기 때문에
 파일을 날짜별 폴더를 생성해 관리한다.

 3) 이미지 파일인 경우 브라우저에 출력할 파일의 크기 :

 이미지 파일의 용량이 클 경우, 서버에서 브라우저에  많은 양의 데이터를 전송하게 되므로 이미지 파일의 축소본
 , 썸네일을 생성하여 최소한의 데이터를 브라우저에 전송하도록 처리한다.


 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.utils;

import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.UUID;

public class UploadFileUtils {

    private static final Logger logger = LoggerFactory.getLogger(UploadFileUtils.class);

    public static String uploadFile(MultipartFile file, HttpServletRequest request) throws IOException {

        String originalFileName = file.getOriginalFilename();
        byte[] fileData = file.getBytes();

        String uuidFileName = getUuidFileName(originalFileName);

        String rootPath = getRootPath(originalFileName, request);
        String datePath = getDatePath(rootPath);

        File target = new File(rootPath + datePath, uuidFileName);
        FileCopyUtils.copy(fileData,target);

        if(MediaUtils.getMediaType(originalFileName)!=null){
            uuidFileName = makeThumnail(rootPath,datePath,uuidFileName);
        }

        return replaceSaveFilePath(datePath,uuidFileName);

    }

    //파일 삭제
    public static void deleteFile(String fileName, HttpServletRequest request){
        String rootPath = getRootPath(fileName,request);

        MediaType mediaType = MediaUtils.getMediaType(fileName);
        if(mediaType != null){
            String originalImg = fileName.substring(0,12) + fileName.substring(14);
            new File(rootPath + originalImg.replace('/',File.separatorChar)).delete();
        }
    }

    //파일 출력
    public static HttpHeaders getHttpHeaders(String fileName) throws UnsupportedEncodingException {
        MediaType mediaType = MediaUtils.getMediaType(fileName);
        HttpHeaders httpHeaders = new HttpHeaders();

        if(mediaType != null){
            httpHeaders.setContentType(mediaType);
            return httpHeaders;
        }

        fileName = fileName.substring(fileName.indexOf("_")+1);
        httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        httpHeaders.add("Content-Disposition","attachment;filename=\""
                +new String(fileName.getBytes("UTF-8"),"ISO-8859-1") + "\"");

        return httpHeaders;
    }

    //파일 경로 치환
    private static String replaceSaveFilePath(String datePath, String uuidFileName) {
        String savedFilePath = datePath + File.separator + uuidFileName;
        return savedFilePath.replace(File.separatorChar,'/');
    }

    // 날짜 경로 추출, 날짜 폴더 생성
    private static String getDatePath(String rootPath) {
        Calendar calendar = Calendar.getInstance();
        String yearPath = File.separator + calendar.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator + new DecimalFormat("00").format(calendar.get(Calendar.DATE));

        makeDateDir(rootPath, yearPath, monthPath, datePath);

        return datePath;
    }

    // 날짜별 폴더 생성
    private static void makeDateDir(String rootPath, String... paths) {
        // 날짜별 폴더가 이미 존재하면 메서드 종료
        if(new File(rootPath+paths[paths.length-1]).exists()){
            return;
        }
        for(String path : paths){
            File dirPath = new File(rootPath + path);
            if(!dirPath.exists())
                dirPath.mkdir();
        }
    }

    //기본 경로 추출
    public static String getRootPath(String originalFileName, HttpServletRequest request) {
        String rootPath = "/resources/upload";
        MediaType mediaType = MediaUtils.getMediaType(originalFileName);
        if(mediaType !=null)
            //이미지파일 경로
            return request.getSession().getServletContext().getRealPath(rootPath+"/images");
        //일반파일 경로
        return request.getSession().getServletContext().getRealPath(rootPath+"/files");
    }

    //파일명 중복방지 처리
    private static String getUuidFileName(String originalFileName) {
        return UUID.randomUUID().toString() + "_" + originalFileName;
    }

    //섬네일 이미지 생성
    private static String makeThumnail(String rootPath, String datePath, String uuidFileName) throws IOException {
        BufferedImage originalImg = ImageIO.read(new File(rootPath + datePath, uuidFileName));
        BufferedImage thumnailImg = Scalr.resize(originalImg, Scalr.Method.AUTOMATIC, Scalr.Mode.FIT_TO_HEIGHT, 100);

        String thumbnailImgName = "s_" + uuidFileName;
        String fullPath = rootPath + datePath + File.separator + thumbnailImgName;

        File newFile = new File(fullPath);
        String formatName = MediaUtils.getFormatName(uuidFileName);
        ImageIO.write(thumnailImg, formatName, newFile);

        return thumbnailImgName;
    }


}
