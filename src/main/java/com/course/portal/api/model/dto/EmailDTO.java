package com.course.portal.api.model.dto;

import java.util.List;

public class EmailDTO {

    private StudentDTO student;
    private String assunto;
    private String textoSimples;
    private String textoHtml;


    public EmailDTO(){ }

    public StudentDTO getStudent() {
        return student;
    }

    public void setStudent(StudentDTO student) {
        this.student = student;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public String getTextoSimples() {
        return textoSimples;
    }

    public void setTextoSimples(String textoSimples) {
        this.textoSimples = textoSimples;
    }

    public String getTextoHtml() {
        return textoHtml;
    }

    public void setTextoHtml(String textoHtml) {
        this.textoHtml = textoHtml;
    }
}
