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
import com.shuangyulin.dao.CourseInfo_QQ254540457DAO;
import com.shuangyulin.domain.CourseInfo_QQ254540457;
import com.shuangyulin.dao.Teacher_QQ287307421DAO;
import com.shuangyulin.domain.Teacher_QQ287307421;
import com.shuangyulin.test.TestUtil;

public class CourseInfo_QQ254540457Action extends ActionSupport {

    /*�������Ҫ��ѯ������: �γ̱��*/
    private String courseNumber;
    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }
    public String getCourseNumber() {
        return this.courseNumber;
    }

    /*�������Ҫ��ѯ������: �γ�����*/
    private String courseName;
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    public String getCourseName() {
        return this.courseName;
    }

    /*�������Ҫ��ѯ������: �Ͽ���ʦ*/
    private Teacher_QQ287307421 courseTeacher;
    public void setCourseTeacher(Teacher_QQ287307421 courseTeacher) {
        this.courseTeacher = courseTeacher;
    }
    public Teacher_QQ287307421 getCourseTeacher() {
        return this.courseTeacher;
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

    /*��ǰ��ѯ���ܼ�¼��Ŀ*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*ҵ������*/
    CourseInfo_QQ254540457DAO courseInfo_QQ254540457DAO = new CourseInfo_QQ254540457DAO();

    /*��������CourseInfo_QQ254540457����*/
    private CourseInfo_QQ254540457 courseInfo_QQ254540457;
    public void setCourseInfo_QQ254540457(CourseInfo_QQ254540457 courseInfo_QQ254540457) {
        this.courseInfo_QQ254540457 = courseInfo_QQ254540457;
    }
    public CourseInfo_QQ254540457 getCourseInfo_QQ254540457() {
        return this.courseInfo_QQ254540457;
    }

    /*��ת�����CourseInfo_QQ254540457��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�Teacher_QQ287307421��Ϣ*/
        Teacher_QQ287307421DAO teacher_QQ287307421DAO = new Teacher_QQ287307421DAO();
        List<Teacher_QQ287307421> teacher_QQ287307421List = teacher_QQ287307421DAO.QueryAllTeacher_QQ287307421Info();
        ctx.put("teacher_QQ287307421List", teacher_QQ287307421List);
        return "add_view";
    }

    /*���CourseInfo_QQ254540457��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddCourseInfo_QQ254540457() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤�γ̱���Ƿ��Ѿ�����*/
        String courseNumber = courseInfo_QQ254540457.getCourseNumber();
        CourseInfo_QQ254540457 db_courseInfo_QQ254540457 = courseInfo_QQ254540457DAO.GetCourseInfo_QQ254540457ByCourseNumber(courseNumber);
        if(null != db_courseInfo_QQ254540457) {
            ctx.put("error",  java.net.URLEncoder.encode("�ÿγ̱���Ѿ�����!"));
            return "error";
        }
        try {
            if(true) {
            Teacher_QQ287307421DAO teacher_QQ287307421DAO = new Teacher_QQ287307421DAO();
            Teacher_QQ287307421 courseTeacher = teacher_QQ287307421DAO.GetTeacher_QQ287307421ByTeacherNumber(courseInfo_QQ254540457.getCourseTeacher().getTeacherNumber());
            courseInfo_QQ254540457.setCourseTeacher(courseTeacher);
            }
            courseInfo_QQ254540457DAO.AddCourseInfo_QQ254540457(courseInfo_QQ254540457);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQ254540457��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQ254540457���ʧ��!"));
            return "error";
        }
    }

    /*��ѯCourseInfo_QQ254540457��Ϣ*/
    public String QueryCourseInfo_QQ254540457() {
        if(currentPage == 0) currentPage = 1;
        if(courseNumber == null) courseNumber = "";
        if(courseName == null) courseName = "";
        List<CourseInfo_QQ254540457> courseInfo_QQ254540457List = courseInfo_QQ254540457DAO.QueryCourseInfo_QQ254540457Info(courseNumber, courseName, courseTeacher, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        courseInfo_QQ254540457DAO.CalculateTotalPageAndRecordNumber(courseNumber, courseName, courseTeacher);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = courseInfo_QQ254540457DAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = courseInfo_QQ254540457DAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("courseInfo_QQ254540457List",  courseInfo_QQ254540457List);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("courseNumber", courseNumber);
        ctx.put("courseName", courseName);
        ctx.put("courseTeacher", courseTeacher);
        Teacher_QQ287307421DAO teacher_QQ287307421DAO = new Teacher_QQ287307421DAO();
        List<Teacher_QQ287307421> teacher_QQ287307421List = teacher_QQ287307421DAO.QueryAllTeacher_QQ287307421Info();
        ctx.put("teacher_QQ287307421List", teacher_QQ287307421List);
        return "query_view";
    }

    /*ǰ̨��ѯCourseInfo_QQ254540457��Ϣ*/
    public String FrontQueryCourseInfo_QQ254540457() {
        if(currentPage == 0) currentPage = 1;
        if(courseNumber == null) courseNumber = "";
        if(courseName == null) courseName = "";
        List<CourseInfo_QQ254540457> courseInfo_QQ254540457List = courseInfo_QQ254540457DAO.QueryCourseInfo_QQ254540457Info(courseNumber, courseName, courseTeacher, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        courseInfo_QQ254540457DAO.CalculateTotalPageAndRecordNumber(courseNumber, courseName, courseTeacher);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = courseInfo_QQ254540457DAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = courseInfo_QQ254540457DAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("courseInfo_QQ254540457List",  courseInfo_QQ254540457List);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("courseNumber", courseNumber);
        ctx.put("courseName", courseName);
        ctx.put("courseTeacher", courseTeacher);
        Teacher_QQ287307421DAO teacher_QQ287307421DAO = new Teacher_QQ287307421DAO();
        List<Teacher_QQ287307421> teacher_QQ287307421List = teacher_QQ287307421DAO.QueryAllTeacher_QQ287307421Info();
        ctx.put("teacher_QQ287307421List", teacher_QQ287307421List);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�CourseInfo_QQ254540457��Ϣ*/
    public String ModifyCourseInfo_QQ254540457Query() {
        ActionContext ctx = ActionContext.getContext();
        /*��������courseNumber��ȡCourseInfo_QQ254540457����*/
        CourseInfo_QQ254540457 courseInfo_QQ254540457 = courseInfo_QQ254540457DAO.GetCourseInfo_QQ254540457ByCourseNumber(courseNumber);

        Teacher_QQ287307421DAO teacher_QQ287307421DAO = new Teacher_QQ287307421DAO();
        List<Teacher_QQ287307421> teacher_QQ287307421List = teacher_QQ287307421DAO.QueryAllTeacher_QQ287307421Info();
        ctx.put("teacher_QQ287307421List", teacher_QQ287307421List);
        ctx.put("courseInfo_QQ254540457",  courseInfo_QQ254540457);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�CourseInfo_QQ254540457��Ϣ*/
    public String FrontShowCourseInfo_QQ254540457Query() {
        ActionContext ctx = ActionContext.getContext();
        /*��������courseNumber��ȡCourseInfo_QQ254540457����*/
        CourseInfo_QQ254540457 courseInfo_QQ254540457 = courseInfo_QQ254540457DAO.GetCourseInfo_QQ254540457ByCourseNumber(courseNumber);

        Teacher_QQ287307421DAO teacher_QQ287307421DAO = new Teacher_QQ287307421DAO();
        List<Teacher_QQ287307421> teacher_QQ287307421List = teacher_QQ287307421DAO.QueryAllTeacher_QQ287307421Info();
        ctx.put("teacher_QQ287307421List", teacher_QQ287307421List);
        ctx.put("courseInfo_QQ254540457",  courseInfo_QQ254540457);
        return "front_show_view";
    }

    /*�����޸�CourseInfo_QQ254540457��Ϣ*/
    public String ModifyCourseInfo_QQ254540457() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            Teacher_QQ287307421DAO teacher_QQ287307421DAO = new Teacher_QQ287307421DAO();
            Teacher_QQ287307421 courseTeacher = teacher_QQ287307421DAO.GetTeacher_QQ287307421ByTeacherNumber(courseInfo_QQ254540457.getCourseTeacher().getTeacherNumber());
            courseInfo_QQ254540457.setCourseTeacher(courseTeacher);
            }
            courseInfo_QQ254540457DAO.UpdateCourseInfo_QQ254540457(courseInfo_QQ254540457);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQ254540457��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQ254540457��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��CourseInfo_QQ254540457��Ϣ*/
    public String DeleteCourseInfo_QQ254540457() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            courseInfo_QQ254540457DAO.DeleteCourseInfo_QQ254540457(courseNumber);
            ctx.put("message",  java.net.URLEncoder.encode("CourseInfo_QQ254540457ɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CourseInfo_QQ254540457ɾ��ʧ��!"));
            return "error";
        }
    }

}
