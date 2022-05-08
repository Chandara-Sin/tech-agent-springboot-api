package com.scd.tech_agent.model;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
// import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tb_employees")
public class Employee {

    private UUID id;
    private String first_name;
    private String last_name;

    // EnumType.STRING is written and read from the corresponding database column
    // e.g. 1,2,3
    // @Enumerated(EnumType.STRING)
    // @Column(name = "gender", columnDefinition =
    // "ENUM('not_known','male','female','not_application')")
    private String gender;
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime hire_date;

    private Integer dept_id;
    private Integer postn_id;

    @Id
    // @GeneratedValue(generator = "id", strategy = GenerationType.AUTO)
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDateTime getHire_date() {
        return hire_date;
    }

    public void setHire_date(LocalDateTime hire_date) {
        this.hire_date = hire_date;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }

    public Integer getPostn_id() {
        return postn_id;
    }

    public void setPostn_id(Integer postn_id) {
        this.postn_id = postn_id;
    }

    @Override
    public String toString() {
        return "tb_employees{" +
                "id=" + id +
                ", first_name='" + first_name +
                ", last_name='" + last_name +
                ", email='" + email +
                ", gender=" + gender +
                ", hire_date='" + hire_date +
                ", position_id=" + postn_id +
                ", department_id=" + dept_id +
                '}';
    }
}
