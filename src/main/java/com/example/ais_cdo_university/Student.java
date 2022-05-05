package com.example.ais_cdo_university;

import com.example.ais_cdo_university.models.Dostigenie;
import com.example.ais_cdo_university.models.User;
import com.example.ais_cdo_university.models.Zadolgennost;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class Student {

    public Student(Long id, User user, String fio, String telephone, Integer kurs, String dopSpecialnost, Long ostatokNaSchetu, String fakultet, Iterable<Zadolgennost> zadolgensti, Iterable<Dostigenie> spisocDostigeniy, String grupNumber, String pol, String raspisanie, Integer kollichestovBalovVSystemeUspech, LocalDate dateEndEducation, LocalDate dateStartEducation, String domashniyZadanie) {
        this.id = id;
        this.user = user;
        this.fio = fio;
        this.telephone = telephone;
        this.kurs = kurs;
        this.dopSpecialnost = dopSpecialnost;
        this.ostatokNaSchetu = ostatokNaSchetu;
        this.fakultet = fakultet;
        this.zadolgensti = zadolgensti;
        this.spisocDostigeniy = spisocDostigeniy;
        this.grupNumber = grupNumber;
        this.pol = pol;
        this.raspisanie = raspisanie;
        this.kollichestovBalovVSystemeUspech = kollichestovBalovVSystemeUspech;
        this.dateEndEducation = dateEndEducation;
        this.dateStartEducation = dateStartEducation;
        this.domashniyZadanie = domashniyZadanie;
    }

    public StringBuffer spisocDostigeniyToString() {

        StringBuffer stringBuffer = new StringBuffer();
        for (Dostigenie d:
             spisocDostigeniy) {
            stringBuffer.append(d.getDostigenie()).append(", ");
        }
       return stringBuffer;
    }

    public StringBuffer zadolgenstiToString() {

        StringBuffer stringBuffer = new StringBuffer();
        for (Zadolgennost z:
                zadolgensti) {
            stringBuffer.append(z.getZadolgennostName()).append(", ");
        }
        return stringBuffer;
    }
    private Long id;

    private User user;

    private String fio;

    private String telephone;

    private Integer kurs;

    private String dopSpecialnost;

    private Long ostatokNaSchetu;

    private String fakultet;

    private Iterable<Zadolgennost> zadolgensti;

    private Iterable<Dostigenie> spisocDostigeniy;

    private String grupNumber;

    private String pol;

    private String raspisanie;

    private Integer kollichestovBalovVSystemeUspech;

    private LocalDate dateEndEducation;


    private LocalDate dateStartEducation;

    private String domashniyZadanie;
}
