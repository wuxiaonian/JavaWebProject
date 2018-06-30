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

public class Teacher_QQ287307421DAO {

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
    public void AddTeacher_QQ287307421(Teacher_QQ287307421 teacher_QQ287307421) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(teacher_QQ287307421);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*��ѯTeacher_QQ287307421��Ϣ*/
    public ArrayList<Teacher_QQ287307421> QueryTeacher_QQ287307421Info(String teacherNumber,String teacherName,String teacherBirthday,String teacherArriveDate,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher_QQ287307421 teacher_QQ287307421 where 1=1";
            if(!teacherNumber.equals("")) hql = hql + " and teacher_QQ287307421.teacherNumber like '%" + teacherNumber + "%'";
            if(!teacherName.equals("")) hql = hql + " and teacher_QQ287307421.teacherName like '%" + teacherName + "%'";
            if(!teacherBirthday.equals("")) hql = hql + " and teacher_QQ287307421.teacherBirthday like '%" + teacherBirthday + "%'";
            if(!teacherArriveDate.equals("")) hql = hql + " and teacher_QQ287307421.teacherArriveDate like '%" + teacherArriveDate + "%'";
            Query q = s.createQuery(hql);
            /*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List teacher_QQ287307421List = q.list();
            return (ArrayList<Teacher_QQ287307421>) teacher_QQ287307421List;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�������ܣ���ѯ���е�Teacher_QQ287307421��¼*/
    public ArrayList<Teacher_QQ287307421> QueryAllTeacher_QQ287307421Info() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher_QQ287307421";
            Query q = s.createQuery(hql);
            List teacher_QQ287307421List = q.list();
            return (ArrayList<Teacher_QQ287307421>) teacher_QQ287307421List;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    public void CalculateTotalPageAndRecordNumber(String teacherNumber,String teacherName,String teacherBirthday,String teacherArriveDate) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From Teacher_QQ287307421 teacher_QQ287307421 where 1=1";
            if(!teacherNumber.equals("")) hql = hql + " and teacher_QQ287307421.teacherNumber like '%" + teacherNumber + "%'";
            if(!teacherName.equals("")) hql = hql + " and teacher_QQ287307421.teacherName like '%" + teacherName + "%'";
            if(!teacherBirthday.equals("")) hql = hql + " and teacher_QQ287307421.teacherBirthday like '%" + teacherBirthday + "%'";
            if(!teacherArriveDate.equals("")) hql = hql + " and teacher_QQ287307421.teacherArriveDate like '%" + teacherArriveDate + "%'";
            Query q = s.createQuery(hql);
            List teacher_QQ287307421List = q.list();
            recordNumber = teacher_QQ287307421List.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����������ȡ����*/
    public Teacher_QQ287307421 GetTeacher_QQ287307421ByTeacherNumber(String teacherNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            Teacher_QQ287307421 teacher_QQ287307421 = (Teacher_QQ287307421)s.get(Teacher_QQ287307421.class, teacherNumber);
            return teacher_QQ287307421;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����Teacher_QQ287307421��Ϣ*/
    public void UpdateTeacher_QQ287307421(Teacher_QQ287307421 teacher_QQ287307421) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(teacher_QQ287307421);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*ɾ��Teacher_QQ287307421��Ϣ*/
    public void DeleteTeacher_QQ287307421 (String teacherNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object teacher_QQ287307421 = s.get(Teacher_QQ287307421.class, teacherNumber);
            s.delete(teacher_QQ287307421);
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
