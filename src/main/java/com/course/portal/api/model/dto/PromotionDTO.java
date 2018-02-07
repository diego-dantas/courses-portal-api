package com.course.portal.api.model.dto;

import java.util.Date;

public class PromotionDTO {

    private long _id;
    private String description;
    private Date dateInicial;
    private Date dateFinal;
    private double percentual;
    private String codigoCupom;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateInicial() {
        return dateInicial;
    }

    public void setDateInicial(Date dateInicial) {
        this.dateInicial = dateInicial;
    }

    public Date getDateFinal() {
        return dateFinal;
    }

    public void setDateFinal(Date dateFinal) {
        this.dateFinal = dateFinal;
    }

    public double getPercentual() {
        return percentual;
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public String getCodigoCupom() {
        return codigoCupom;
    }

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    @Override
    public String toString() {
        return "PromotionDTO{" +
                "_id=" + _id +
                ", description='" + description + '\'' +
                ", dateInicial=" + dateInicial +
                ", dateFinal=" + dateFinal +
                ", percentual=" + percentual +
                ", codigoCupom='" + codigoCupom + '\'' +
                '}';
    }
}
