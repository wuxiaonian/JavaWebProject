package com.shuangyulin.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import org.apache.struts2.ServletActionContext;
import java.util.List;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shuangyulin.dao.StudentSelectCourseInfoDAO;
import com.shuangyulin.domain.StudentSelectCourseInfo;
import com.shuangyulin.dao.Student_QQ287307421DAO;
import com.shuangyulin.domain.Student_QQ287307421;
import com.shuangyulin.dao.CourseInfo_QQ254540457DAO;
import com.shuangyulin.domain.CourseInfo_QQ254540457;
import com.shuangyulin.test.TestUtil;

public class StudentSelectCourseInfoAction extends ActionSupport {

    /*�������Ҫ��ѯ������: ѧ������*/
    private Student_QQ287307421 studentNumber;
    public void setStudentNumber(Student_QQ287307421 studentNumber) {
        this.studentNumber = studentNumber;
    }
    public Student_QQ287307421 getStudentNumber() {
        return this.studentNumber;
    }

    /*�������Ҫ��ѯ������: �γ̶���*/
    private CourseInfo_QQ254540457 courseNumber;
    public void setCourseNumber(CourseInfo_QQ254540457 courseNumber) {
        this.courseNumber = courseNumber;
    }
    public CourseInfo_QQ254540457 getCourseNumber() {
        return this.courseNumber;
    }

    /*��ǰ�ڼ�ҳ*/
    private int currentPage;
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
    public int getCurrentPage() {
        return currentPage;
    }

    /*һ������ҳ*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    private int selectId;
    public void setSelectId(int selectId) {
        this.selectId = selectId;
    }
    public int getSelectId() {
        return selectId;
    }

    /*��ǰ��ѯ���ܼ�¼��Ŀ*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*ҵ������*/
    StudentSelectCourseInfoDAO studentSelectCourseInfoDAO = new StudentSelectCourseInfoDAO();

    /*��������StudentSelectCourseInfo����*/
    private StudentSelectCourseInfo studentSelectCourseInfo;
    public void setStudentSelectCourseInfo(StudentSelectCourseInfo studentSelectCourseInfo) {
        this.studentSelectCourseInfo = studentSelectCourseInfo;
    }
    public StudentSelectCourseInfo getStudentSelectCourseInfo() {
        return this.studentSelectCourseInfo;
    }

    /*��ת�����StudentSelectCourseInfo��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�Student_QQ287307421��Ϣ*/
        Student_QQ287307421DAO student_QQ287307421DAO = new Student_QQ287307421DAO();
        List<Student_QQ287307421> student_QQ287307421List = student_QQ287307421DAO.QueryAllStudent_QQ287307421Info();
        ctx.put("student_QQ287307421List", student_QQ287307421List);
        /*��ѯ���е�CourseInfo_QQ254540457��Ϣ*/
        CourseInfo_QQ254540457DAO courseInfo_QQ254540457DAO = new CourseInfo_QQ254540457DAO();
        List<CourseInfo_QQ254540457> courseInfo_QQ254540457List = courseInfo_QQ254540457DAO.QueryAllCourseInfo_QQ254540457Info();
        ctx.put("courseInfo_QQ254540457List", courseInfo_QQ254540457List);
        return "add_view";
    }

    /*���StudentSelectCourseInfo��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddStudentSelectCourseInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Student_QQ287307421DAO student_QQ287307421DAO = new Student_QQ287307421DAO();
            Student_QQ287307421 studentNumber = student_QQ287307421DAO.GetStudent_QQ287307421ByStudentNumber(studentSelectCourseInfo.getStudentNumber().getStudentNumber());
            studentSelectCourseInfo.setStudentNumber(studentNumber);
            }
            if(true) {
            CourseInfo_QQ254540457DAO courseInfo_QQ254540457DAO = new CourseInfo_QQ254540457DAO();
            CourseInfo_QQ254540457 courseNumber = courseInfo_QQ254540457DAO.GetCourseInfo_QQ254540457ByCourseNumber(studentSelectCourseInfo.getCourseNumber().getCourseNumber());
            studentSelectCourseInfo.setCourseNumber(courseNumber);
            }
            studentSelectCourseInfoDAO.AddStudentSelectCourseInfo(studentSelectCourseInfo);
            ctx.put("message",  java.net.URLEncoder.encode("StudentSelectCourseInfo��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("StudentSelectCourseInfo���ʧ��!"));
            return "error";
        }
    }

    /*��ѯStudentSelectCourseInfo��Ϣ*/
    public String QueryStudentSelectCourseInfo() {
        if(currentPage == 0) currentPage = 1;
        List<StudentSelectCourseInfo> studentSelectCourseInfoList = studentSelectCourseInfoDAO.QueryStudentSelectCourseInfoInfo(studentNumber, courseNumber, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        studentSelectCourseInfoDAO.CalculateTotalPageAndRecordNumber(studentNumber, courseNumber);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = studentSelectCourseInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = studentSelectCourseInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("studentSelectCourseInfoList",  studentSelectCourseInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("studentNumber", studentNumber);
        Student_QQ287307421DAO student_QQ287307421DAO = new Student_QQ287307421DAO();
        List<Student_QQ287307421> student_QQ287307421List = student_QQ287307421DAO.QueryAllStudent_QQ287307421Info();
        ctx.put("student_QQ287307421List", student_QQ287307421List);
        ctx.put("courseNumber", courseNumber);
        CourseInfo_QQ254540457DAO courseInfo_QQ254540457DAO = new CourseInfo_QQ254540457DAO();
        List<CourseInfo_QQ254540457> courseInfo_QQ254540457List = courseInfo_QQ254540457DAO.QueryAllCourseInfo_QQ254540457Info();
        ctx.put("courseInfo_QQ254540457List", courseInfo_QQ254540457List);
        return "query_view";
    }

    /*ǰ̨��ѯStudentSelectCourseInfo��Ϣ*/
    public String FrontQueryStudentSelectCourseInfo() {
        if(currentPage == 0) currentPage = 1;
        List<StudentSelectCourseInfo> studentSelectCourseInfoList = studentSelectCourseInfoDAO.QueryStudentSelectCourseInfoInfo(studentNumber, courseNumber, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        studentSelectCourseInfoDAO.CalculateTotalPageAndRecordNumber(studentNumber, courseNumber);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = studentSelectCourseInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = studentSelectCourseInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("studentSelectCourseInfoList",  studentSelectCourseInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("studentNumber", studentNumber);
        Student_QQ287307421DAO student_QQ287307421DAO = new Student_QQ287307421DAO();
        List<Student_QQ287307421> student_QQ287307421List = student_QQ287307421DAO.QueryAllStudent_QQ287307421Info();
        ctx.put("student_QQ287307421List", student_QQ287307421List);
        ctx.put("courseNumber", courseNumber);
        CourseInfo_QQ254540457DAO courseInfo_QQ254540457DAO = new CourseInfo_QQ254540457DAO();
        List<CourseInfo_QQ254540457> courseInfo_QQ254540457List = courseInfo_QQ254540457DAO.QueryAllCourseInfo_QQ254540457Info();
        ctx.put("courseInfo_QQ254540457List", courseInfo_QQ254540457List);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�StudentSelectCourseInfo��Ϣ*/
    public String ModifyStudentSelectCourseInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������selectId��ȡStudentSelectCourseInfo����*/
        StudentSelectCourseInfo studentSelectCourseInfo = studentSelectCourseInfoDAO.GetStudentSelectCourseInfoBySelectId(selectId);

        Student_QQ287307421DAO student_QQ287307421DAO = new Student_QQ287307421DAO();
        List<Student_QQ287307421> student_QQ287307421List = student_QQ287307421DAO.QueryAllStudent_QQ287307421Info();
        ctx.put("student_QQ287307421List", student_QQ287307421List);
        CourseInfo_QQ254540457DAO courseInfo_QQ254540457DAO = new CourseInfo_QQ254540457DAO();
        List<CourseInfo_QQ254540457> courseInfo_QQ254540457List = courseInfo_QQ254540457DAO.QueryAllCourseInfo_QQ254540457Info();
        ctx.put("courseInfo_QQ254540457List", courseInfo_QQ254540457List);
        ctx.put("studentSelectCourseInfo",  studentSelectCourseInfo);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�StudentSelectCourseInfo��Ϣ*/
    public String FrontShowStudentSelectCourseInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������selectId��ȡStudentSelectCourseInfo����*/
        StudentSelectCourseInfo studentSelectCourseInfo = studentSelectCourseInfoDAO.GetStudentSelectCourseInfoBySelectId(selectId);

        Student_QQ287307421DAO student_QQ287307421DAO = new Student_QQ287307421DAO();
        List<Student_QQ287307421> student_QQ287307421List = student_QQ287307421DAO.QueryAllStudent_QQ287307421Info();
        ctx.put("student_QQ287307421List", student_QQ287307421List);
        CourseInfo_QQ254540457DAO courseInfo_QQ254540457DAO = new CourseInfo_QQ254540457DAO();
        List<CourseInfo_QQ254540457> courseInfo_QQ254540457List = courseInfo_QQ254540457DAO.QueryAllCourseInfo_QQ254540457Info();
        ctx.put("courseInfo_QQ254540457List", courseInfo_QQ254540457List);
        ctx.put("studentSelectCourseInfo",  studentSelectCourseInfo);
        return "front_show_view";
    }

    /*�����޸�StudentSelectCourseInfo��Ϣ*/
    public String ModifyStudentSelectCourseInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Student_QQ287307421DAO student_QQ287307421DAO = new Student_QQ287307421DAO();
            Student_QQ287307421 studentNumber = student_QQ287307421DAO.GetStudent_QQ287307421ByStudentNumber(studentSelectCourseInfo.getStudentNumber().getStudentNumber());
            studentSelectCourseInfo.setStudentNumber(studentNumber);
            }
            if(true) {
            CourseInfo_QQ254540457DAO courseInfo_QQ254540457DAO = new CourseInfo_QQ254540457DAO();
            CourseInfo_QQ254540457 courseNumber = courseInfo_QQ254540457DAO.GetCourseInfo_QQ254540457ByCourseNumber(studentSelectCourseInfo.getCourseNumber().getCourseNumber());
            studentSelectCourseInfo.setCourseNumber(courseNumber);
            }
            studentSelectCourseInfoDAO.UpdateStudentSelectCourseInfo(studentSelectCourseInfo);
            ctx.put("message",  java.net.URLEncoder.encode("StudentSelectCourseInfo��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("StudentSelectCourseInfo��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��StudentSelectCourseInfo��Ϣ*/
    public String DeleteStudentSelectCourseInfo() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            studentSelectCourseInfoDAO.DeleteStudentSelectCourseInfo(selectId);
            ctx.put("message",  java.net.URLEncoder.encode("StudentSelectCourseInfoɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("StudentSelectCourseInfoɾ��ʧ��!"));
            return "error";
        }
    }

}
