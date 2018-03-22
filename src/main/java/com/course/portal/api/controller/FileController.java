package com.course.portal.api.controller;

import org.apache.commons.io.IOUtils;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping(path = "/api/upload")
public class FileController {


    private String UPLOAD_DIR = "/home/Dantas/Projetos/courses-portal-v2/courses-portal-api/src/main/resources/static/files/";


    @PostMapping(path = "/courses")
    public ResponseEntity uploadFile(@RequestParam("files") List<MultipartFile> files) throws IOException{

        String local = "courses";

        try {

            for(MultipartFile f : files){
                fileUpload(local, f);
            }

            return ResponseEntity.ok(HttpStatus.OK);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }



    //metodo para realizar o upload da imagem
    private void fileUpload(String local, MultipartFile file) throws IOException{
        byte[] bytes = file.getBytes();
        Path path = Paths.get(UPLOAD_DIR+""+local+"/"+file.getOriginalFilename());
        Files.write(path, bytes);
    }


    @GetMapping(path = "/download")
    public HttpEntity<byte[]> download() throws IOException {

        byte[] arquivo = Files.readAllBytes( Paths.get(UPLOAD_DIR + "boletoBia.pdf") );

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.add("Content-Disposition", "attachment;filename=\"boletoBia.pdf\"");

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


    @GetMapping(value="/filesTeste")
    @ResponseBody
    public ResponseEntity<byte[]> filesTeste(@RequestParam("name") String name) {

        try {

            HttpHeaders headers = new HttpHeaders();

            File file = new File(UPLOAD_DIR+name);

            InputStream is = new BufferedInputStream(new FileInputStream(file));

            String mimeType = URLConnection.guessContentTypeFromStream(is);

            headers.setContentType(MediaType.valueOf(mimeType));

            return new ResponseEntity<byte[]>(IOUtils.toByteArray(is), headers, HttpStatus.OK);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
