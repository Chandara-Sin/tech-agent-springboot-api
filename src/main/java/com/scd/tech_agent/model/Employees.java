package com.scd.tech_agent.model;

// import org.hibernate.annotations.Type;
// import org.hibernate.annotations.TypeDef;

import javax.persistence.*;

@Entity
@Table(name = "tb_employees")
public class Employees {

    private Integer emp_id;
    private String first_name;
    private String last_name;

    // EnumType.STRING is written and read from the corresponding database column
    // e.g. 1,2,3
    // @Enumerated(EnumType.STRING)
    // @Column(name = "gender", columnDefinition =
    // "ENUM('not_known','male','female','not_application')")
    private String gender;
    private String hire_date;
    private Integer salr_id;
    private Integer dept_id;
    private Integer postn_id;

    @Id
    @GeneratedValue(generator = "emp_id", strategy = GenerationType.AUTO)
    @Column(name = "emp_id")
    public Integer getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(Integer emp_id) {
        this.emp_id = emp_id;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getHire_date() {
        return hire_date;
    }

    public void setHire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public Integer getSalr_id() {
        return salr_id;
    }

    public void setSalr_id(Integer salr_id) {
        this.salr_id = salr_id;
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
                "id=" + emp_id +
                ", first_name='" + first_name +
                ", last_name='" + last_name +
                ", gender=" + gender +
                ", hire_date='" + hire_date +
                ", salary_id=" + salr_id +
                ", position_id=" + postn_id +
                ", department_id=" + dept_id +
                '}';
    }

}
