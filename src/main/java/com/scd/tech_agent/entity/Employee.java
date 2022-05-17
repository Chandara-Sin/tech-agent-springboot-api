package com.scd.tech_agent.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
// import org.hibernate.annotations.TypeDef;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@ToString
@Getter
@Setter
@Entity
@Table(name = "tb_employees")
public class Employee {

    @Id
    @GeneratedValue(generator = "id")
    @GenericGenerator(name = "id", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "VARCHAR(36)")
    @Type(type = "uuid-char")
    private UUID id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    // EnumType.STRING is written and read from the corresponding database column
    // e.g. 1,2,3
    // @Enumerated(EnumType.STRING)
    // @Column(name = "gender", columnDefinition =
    // "ENUM('not_known','male','female','not_application')")
    private String gender;
    private String email;

    @Column(name = "hire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime hireDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "dept_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    private Department department;

    @Column(name = "dept_id")
    private Integer deptId;

    @Column(name = "postn_id")
    private Integer postnId;
}