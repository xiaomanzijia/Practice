package me.student.greendao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table STUDENT.
 */
public class Student {

    private Long id;
    private String name;
    private Boolean studentnum;
    private Float phone;
    private java.util.Date address;
    private java.util.Date teacher;

    public Student() {
    }

    public Student(Long id) {
        this.id = id;
    }

    public Student(Long id, String name, Boolean studentnum, Float phone, java.util.Date address, java.util.Date teacher) {
        this.id = id;
        this.name = name;
        this.studentnum = studentnum;
        this.phone = phone;
        this.address = address;
        this.teacher = teacher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getStudentnum() {
        return studentnum;
    }

    public void setStudentnum(Boolean studentnum) {
        this.studentnum = studentnum;
    }

    public Float getPhone() {
        return phone;
    }

    public void setPhone(Float phone) {
        this.phone = phone;
    }

    public java.util.Date getAddress() {
        return address;
    }

    public void setAddress(java.util.Date address) {
        this.address = address;
    }

    public java.util.Date getTeacher() {
        return teacher;
    }

    public void setTeacher(java.util.Date teacher) {
        this.teacher = teacher;
    }

}
