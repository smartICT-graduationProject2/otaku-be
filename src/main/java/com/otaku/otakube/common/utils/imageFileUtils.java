package com.otaku.otakube.common.utils;

public class imageFileUtils {
    public static final String FILE_EXTENSION_SEPARATOR = ".";

    public static String getFileName(String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR);
        return originalFileName.substring(0, fileExtensionIndex); //파일 이름
    }

    public static String buildFileName(String originalFileName) {
        int fileExtensionIndex = originalFileName.lastIndexOf(FILE_EXTENSION_SEPARATOR); //파일 확장자 구분선
        String fileExtension = originalFileName.substring(fileExtensionIndex); //파일 확장자
        String fileName = originalFileName.substring(0, fileExtensionIndex); //파일 이름
        String regularFileName = fileName.replaceAll("[^ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]", "_");
        String now = String.valueOf(System.currentTimeMillis()); //파일 업로드 시간

        return regularFileName + "_" + now + fileExtension;
    }
}
