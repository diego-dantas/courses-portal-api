package com.course.portal.api.model.dao.repository;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileRepository {

    private static String UPLOAD_DIR = "/home/Dantas/Projetos/courses-portal-v2/courses-portal-api/src/main/resources/static/files/";

    public static String getUPLOAD_DIR() {
        return UPLOAD_DIR;
    }


    //metodo para realizar o upload da imagem
    public static void fileUpload(String local, MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        Path path = Paths.get(getUPLOAD_DIR()+""+local+"/"+file.getOriginalFilename());
        Files.write(path, bytes);
    }
}
