package com.shuangyulin.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.mysql.jdbc.Statement;
import com.shuangyulin.utils.HibernateUtil;

import com.shuangyulin.domain.Teacher_QQ287307421;
import com.shuangyulin.domain.CourseInfo_QQ254540457;

public class CourseInfo_QQ254540457DAO {

    /*ÿҳ��ʾ��¼��Ŀ*/
    private final int PAGE_SIZE = 10;

    /*�����ѯ���ܵ�ҳ��*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*�����ѯ�����ܼ�¼��*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*���ͼ����Ϣ*/
    public void AddCourseInfo_QQ254540457(CourseInfo_QQ254540457 courseInfo_QQ254540457) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(courseInfo_QQ254540457);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*��ѯCourseInfo_QQ254540457��Ϣ*/
    public ArrayList<CourseInfo_QQ254540457> QueryCourseInfo_QQ254540457Info(String courseNumber,String courseName,Teacher_QQ287307421 courseTeacher,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo_QQ254540457 courseInfo_QQ254540457 where 1=1";
            if(!courseNumber.equals("")) hql = hql + " and courseInfo_QQ254540457.courseNumber like '%" + courseNumber + "%'";
            if(!courseName.equals("")) hql = hql + " and courseInfo_QQ254540457.courseName like '%" + courseName + "%'";
            if(null != courseTeacher && !courseTeacher.getTeacherNumber().equals("")) hql += " and courseInfo_QQ254540457.courseTeacher.teacherNumber='" + courseTeacher.getTeacherNumber() + "'";
            Query q = s.createQuery(hql);
            /*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List courseInfo_QQ254540457List = q.list();
            return (ArrayList<CourseInfo_QQ254540457>) courseInfo_QQ254540457List;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�������ܣ���ѯ���е�CourseInfo_QQ254540457��¼*/
    public ArrayList<CourseInfo_QQ254540457> QueryAllCourseInfo_QQ254540457Info() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo_QQ254540457";
            Query q = s.createQuery(hql);
            List courseInfo_QQ254540457List = q.list();
            return (ArrayList<CourseInfo_QQ254540457>) courseInfo_QQ254540457List;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    public void CalculateTotalPageAndRecordNumber(String courseNumber,String courseName,Teacher_QQ287307421 courseTeacher) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From CourseInfo_QQ254540457 courseInfo_QQ254540457 where 1=1";
            if(!courseNumber.equals("")) hql = hql + " and courseInfo_QQ254540457.courseNumber like '%" + courseNumber + "%'";
            if(!courseName.equals("")) hql = hql + " and courseInfo_QQ254540457.courseName like '%" + courseName + "%'";
            if(null != courseTeacher && !courseTeacher.getTeacherNumber().equals("")) hql += " and courseInfo_QQ254540457.courseTeacher.teacherNumber='" + courseTeacher.getTeacherNumber() + "'";
            Query q = s.createQuery(hql);
            List courseInfo_QQ254540457List = q.list();
            recordNumber = courseInfo_QQ254540457List.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����������ȡ����*/
    public CourseInfo_QQ254540457 GetCourseInfo_QQ254540457ByCourseNumber(String courseNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            CourseInfo_QQ254540457 courseInfo_QQ254540457 = (CourseInfo_QQ254540457)s.get(CourseInfo_QQ254540457.class, courseNumber);
            return courseInfo_QQ254540457;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����CourseInfo_QQ254540457��Ϣ*/
    public void UpdateCourseInfo_QQ254540457(CourseInfo_QQ254540457 courseInfo_QQ254540457) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(courseInfo_QQ254540457);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*ɾ��CourseInfo_QQ254540457��Ϣ*/
    public void DeleteCourseInfo_QQ254540457 (String courseNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object courseInfo_QQ254540457 = s.get(CourseInfo_QQ254540457.class, courseNumber);
            s.delete(courseInfo_QQ254540457);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

}
