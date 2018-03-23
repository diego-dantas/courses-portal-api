package com.course.portal.api.controller;

import com.course.portal.api.controller.response.Response;
import com.course.portal.api.model.dao.entity.*;
import com.course.portal.api.model.dao.repository.EvaluationQuestionRepository;
import com.course.portal.api.model.dao.repository.GridRepository;
import com.course.portal.api.model.dao.repository.SubGridRepositoy;
import com.course.portal.api.model.dto.*;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/api")
public class SubGridController {

    @Autowired
    private SubGridRepositoy subGridRepositoy;
    @Autowired
    private GridRepository gridRepository;


    @PostMapping(value = "/createUpdateSubGrid")
    public ResponseEntity<Response<SubGridDTO>> createUpdateSubGrid(@RequestBody SubGridDTO subGridDTO){

        GridEntity gridEntity = new GridEntity();
        SubGridEntity subGridEntity = new SubGridEntity();

        Response<SubGridDTO> response = new Response<>();


        try{
            gridEntity.set_id(subGridDTO.getGrid().get_id());
            subGridEntity.setGrid(gridEntity);

            subGridEntity.set_id(subGridDTO.get_id());
            subGridEntity.setDescription(subGridDTO.getDescription());
            subGridEntity.setLabelUrl(subGridDTO.getLabelUrl());

            subGridRepositoy.save(subGridEntity);

            response.setData(subGridDTO);
            return ResponseEntity.ok(response);

        }catch (HibernateException e){
            System.out.println("Error ao salvar o sub grid " + e );
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @PostMapping(value = "/deleteSubGrid")
    public ResponseEntity<Response<SubGridDTO>> deleteSubGrid(@RequestBody SubGridDTO subGridDTO){
    	
    	Response<SubGridDTO> response = new Response<>();
        GridEntity gridEntity = new GridEntity();
        SubGridEntity subGridEntity = new SubGridEntity();
    	try {
            gridEntity.set_id(subGridDTO.getGrid().get_id());
            subGridEntity.setGrid(gridEntity);

            subGridEntity.set_id(subGridDTO.get_id());
            subGridEntity.setDescription(subGridDTO.getDescription());
            subGridEntity.setLabelUrl(subGridDTO.getLabelUrl());

    		subGridRepositoy.delete(subGridEntity);

            response.setData(subGridDTO);
            return ResponseEntity.ok(response);

        }catch (HibernateException e){
            System.out.println("Error ao salvar o sub grid " + e );
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping(value = "/getSubGrid")
    public ResponseEntity<Response<List<SubGridDTO>>> getSubGrid(){
    	
    	List<SubGridDTO> subGridDTOs = new ArrayList<>();
    	List<SubGridEntity> subGridEntities = new ArrayList<>();
    	GridEntity gridEntity = new GridEntity();
    	Response<List<SubGridDTO>> response = new Response<>();
    	
    	subGridEntities = subGridRepositoy.findAll();
    	
    	for(SubGridEntity subGridEntity : subGridEntities) {
    		
    		
    		SubGridDTO subGridDTO = new SubGridDTO();
    		GridDTO gridDTO = new GridDTO();
    		try {
    			//alimenta a grid dto
        		gridEntity = gridRepository.findOne(subGridEntity.getGrid().get_id());
        		gridDTO.set_id(gridEntity.get_id());
        		gridDTO.setDescription(gridEntity.getDescription());
        		gridDTO.setLabelUrl(gridEntity.getLabelUrl());
        		
        		//alimenta a subgrid dto
        		subGridDTO.set_id(subGridEntity.get_id());
        		subGridDTO.setDescription(subGridEntity.getDescription());
        		subGridDTO.setGrid(gridDTO);
        		subGridDTO.setLabelUrl(subGridEntity.getLabelUrl());
        		
        		subGridDTOs.add(subGridDTO);
    		}catch(Exception e ) {
    			System.out.println("Erro ao alimentar o array de subgrid " + e);
    		}
    		
    	}
    	
    	response.setData(subGridDTOs);
    	
    	return ResponseEntity.ok(response);
    }

    @RestController
    @RequestMapping(value = "/api")
    public static class EvaluationQuestionController {

        @Autowired
        private EvaluationQuestionRepository evaluationQuestionRepository;


        @PostMapping(value = "/saveEvaluationQuestion")
        public ResponseEntity<Response<List<EvaluationQuestionDTO>>> saveEvaluationQuestion(@RequestBody List<EvaluationQuestionDTO> evaluationQuestionDTOs){

            Response<List<EvaluationQuestionDTO>> response = new Response<>();

            try{

                EvaluationEntity evaluation = new EvaluationEntity();
                evaluation.set_id(evaluationQuestionDTOs.get(0).getEvaluation().get_id());

                List<EvaluationQuestionEntity> evaluationQuestionEntities =  evaluationQuestionRepository.findByEvaluation(evaluation);

                for(EvaluationQuestionEntity evaluationQuestionEntity : evaluationQuestionEntities){
                    evaluationQuestionRepository.delete(evaluationQuestionEntity.get_id());
                }


                for(EvaluationQuestionDTO evaluationQuestionDTO : evaluationQuestionDTOs){

                    QuestionEntity questionEntity = new QuestionEntity();
                    EvaluationEntity evaluationEntity = new EvaluationEntity();
                    EvaluationQuestionEntity evaluationQuestionEntity = new EvaluationQuestionEntity();

                    questionEntity.set_id(evaluationQuestionDTO.getQuestion().get_id());
                    evaluationEntity.set_id(evaluationQuestionDTO.getEvaluation().get_id());

                    evaluationQuestionEntity.setQuestion(questionEntity);
                    evaluationQuestionEntity.setEvaluation(evaluationEntity);

                    evaluationQuestionRepository.save(evaluationQuestionEntity);

                }

                response.setData(evaluationQuestionDTOs);
                return ResponseEntity.ok(response);
            }catch(HibernateException e){
                System.out.println("Erro ao salvar as questões da prova " + e);
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }


        @GetMapping(value = "/getEvaluationQuestion")
        public ResponseEntity<Response<List<EvaluationQuestionDTO>>> getEvaluationQuestion(){

            Response<List<EvaluationQuestionDTO>> response = new Response<>();
            List<EvaluationQuestionDTO> evaluationQuestionDTOs = new ArrayList<>();
            try{

                List<EvaluationQuestionEntity> evaluationQuestionEntities = evaluationQuestionRepository.findAll();
                for (EvaluationQuestionEntity evaluationQuestionEntity : evaluationQuestionEntities){

                    EvaluationDTO evaluationDTO = new EvaluationDTO();
                    QuestionDTO questionDTO = new QuestionDTO();
                    EvaluationQuestionDTO evaluationQuestionDTO = new EvaluationQuestionDTO();

                    evaluationDTO.set_id(evaluationQuestionEntity.getEvaluation().get_id());
                    questionDTO.set_id(evaluationQuestionEntity.getQuestion().get_id());

                    evaluationQuestionDTO.set_id(evaluationQuestionEntity.get_id());
                    evaluationQuestionDTO.setEvaluation(evaluationDTO);
                    evaluationQuestionDTO.setQuestion(questionDTO);

                    evaluationQuestionDTOs.add(evaluationQuestionDTO);
                }

                response.setData(evaluationQuestionDTOs);
                return ResponseEntity.ok(response);
            }catch(HibernateException e){
                System.out.println("Erro ao salvar as questões da prova " + e);
                return new ResponseEntity(HttpStatus.BAD_REQUEST);
            }
        }
    }
}
