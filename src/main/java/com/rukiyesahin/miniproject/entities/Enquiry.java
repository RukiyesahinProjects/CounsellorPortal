package com.rukiyesahin.miniproject.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name = "enquiry_tbl")
public class Enquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer enqId;
    private String stuName;
    private Long studentPhone;
    private String courseName;
    private String classMode;
    private String enquiryStatus;
    @CreationTimestamp
    private LocalDate createdDate;
    @UpdateTimestamp
    private LocalDate updatedDate;
    @ManyToOne
    @JoinColumn(name = "counsellorId")
    private Counsellor counsellor;

    public Integer getEnqId() {
        return enqId;
    }

    public void setEnqId(Integer enqId) {
        this.enqId = enqId;
    }

    public String getStudentName() {
        return stuName;
    }

    public void setStudentName(String studentName) {
        this.stuName = studentName;
    }

    public Long getStudentPhone() {
        return studentPhone;
    }

    public void setStudentPhone(Long studentPhone) {
        this.studentPhone = studentPhone;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getClassMode() {
        return classMode;
    }

    public void setClassMode(String classMode) {
        this.classMode = classMode;
    }

    public String getEnquiryStatus() {
        return enquiryStatus;
    }

    public void setEnquiryStatus(String enquiryStatus) {
        this.enquiryStatus = enquiryStatus;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Counsellor getCounsellor() {
        return counsellor;
    }

    public void setCounsellor(Counsellor counsellor) {
        this.counsellor = counsellor;
    }
}
