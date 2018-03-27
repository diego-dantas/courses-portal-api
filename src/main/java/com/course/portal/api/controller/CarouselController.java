package com.course.portal.api.controller;

import com.course.portal.api.model.dao.repository.FileRepository;
import com.google.gson.Gson;
import org.apache.catalina.connector.Response;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class CarouselController {



    @GetMapping(path = "/getImgCarousel")
    public String readFile(){
        File file = new File(FileRepository.getUPLOAD_DIR()+"/carousel");
        File[] arquivos = file.listFiles();
        List<String> list = new ArrayList<>();
        Gson gson = new Gson();
        String g = "";
        try {
            for (File arquivo : arquivos) {
                String name = arquivo.getName();
                list.add(name);
            }
            g = gson.toJson(list);

        }catch (Exception e){
            e.printStackTrace();
        }
        return g;
    }

    @GetMapping(value = "/deleteFile")
    public ResponseEntity deleteFile(@RequestParam("name") String name){
        try{
            FileRepository.deleteFile("carousel/"+name);
            return ResponseEntity.ok(HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
