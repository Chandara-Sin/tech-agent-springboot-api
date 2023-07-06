package com.scd.tech_agent.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "tb_employees")
public class Employee {

    @Id
    @GeneratedValue(generator = "id", strategy = GenerationType.IDENTITY)
    private Integer id;

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

    @Column(name = "hire_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
    private LocalDateTime hireDate;

    @OneToOne(mappedBy = "employee")
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "dept_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    private Department department;

    @Column(name = "dept_id")
    private Integer deptId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "postn_id", nullable = false, updatable = false, insertable = false)
    @JsonIgnore
    private Position position;

    @Column(name = "postn_id")
    private Integer postnId;

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "firstName = " + firstName + ", " +
                "lastName = " + lastName + ", " +
                "gender = " + gender + ", " +
                "hireDate = " + hireDate + ", " +
                "deptId = " + deptId + ", " +
                "postnId = " + postnId + ")";
    }
}
