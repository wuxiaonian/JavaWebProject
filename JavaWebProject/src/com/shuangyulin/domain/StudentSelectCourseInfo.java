package com.shuangyulin.domain;

public class StudentSelectCourseInfo {
    /*��¼���*/
    private int selectId;
    public int getSelectId() {
        return selectId;
    }
    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }

    /*ѧ������*/
    private Student_QQ287307421 studentNumber;
    public Student_QQ287307421 getStudentNumber() {
        return studentNumber;
    }
    public void setStudentNumber(Student_QQ287307421 studentNumber) {
        this.studentNumber = studentNumber;
    }

    /*�γ̶���*/
    private CourseInfo_QQ254540457 courseNumber;
    public CourseInfo_QQ254540457 getCourseNumber() {
        return courseNumber;
    }
    public void setCourseNumber(CourseInfo_QQ254540457 courseNumber) {
        this.courseNumber = courseNumber;
    }

}