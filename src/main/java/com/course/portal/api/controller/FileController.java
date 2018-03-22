package com.course.portal.api.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class FileController {


    private String UPLOAD_DIR = "/home/Dantas/Projetos/courses-portal-v2/courses-portal-api/src/main/resources/static/files/";


    @PostMapping(path = "/upload")
    public String uploadFile(@RequestParam("files") List<MultipartFile> files) throws IOException{
        for(MultipartFile f : files){
            System.out.print("Vamos la ");
            fileUpload(f);
        }

        return "deu certo";
    }

    private void fileUpload(MultipartFile file) throws IOException{

        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR+""+file.getOriginalFilename());
        Files.write(path, bytes);

    }


    @GetMapping(path = "/download")
    public HttpEntity<byte[]> download() throws IOException {

        byte[] arquivo = Files.readAllBytes( Paths.get(UPLOAD_DIR + "img.jpg") );

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Disposition", "attachment;filename=\"img.jpg\"");

        HttpEntity<byte[]> entity = new HttpEntity<byte[]>( arquivo, httpHeaders);

        return entity;
    }


    @GetMapping(path = "/getImg")
    public void readFile(){
        //Path path = Paths.get(UPLOAD_DIR+"_arquivo_caneca.png");

        File file = new File(UPLOAD_DIR);
        File[] arquivos = file.listFiles();

        try {
            for (File arquivo : arquivos) {
                System.out.println(arquivo.getPath());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
