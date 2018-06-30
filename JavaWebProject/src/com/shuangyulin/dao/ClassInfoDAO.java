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

import com.shuangyulin.domain.SpecialFieldInfo;
import com.shuangyulin.domain.ClassInfo;

public class ClassInfoDAO {

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
    public void AddClassInfo(ClassInfo classInfo) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(classInfo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*��ѯClassInfo��Ϣ*/
    public ArrayList<ClassInfo> QueryClassInfoInfo(String classNumber,String className,SpecialFieldInfo classSpecialFieldNumber,String classBirthDate,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From ClassInfo classInfo where 1=1";
            if(!classNumber.equals("")) hql = hql + " and classInfo.classNumber like '%" + classNumber + "%'";
            if(!className.equals("")) hql = hql + " and classInfo.className like '%" + className + "%'";
            if(null != classSpecialFieldNumber && !classSpecialFieldNumber.getSpecialFieldNumber().equals("")) hql += " and classInfo.classSpecialFieldNumber.specialFieldNumber='" + classSpecialFieldNumber.getSpecialFieldNumber() + "'";
            if(!classBirthDate.equals("")) hql = hql + " and classInfo.classBirthDate like '%" + classBirthDate + "%'";
            Query q = s.createQuery(hql);
            /*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List classInfoList = q.list();
            return (ArrayList<ClassInfo>) classInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�������ܣ���ѯ���е�ClassInfo��¼*/
    public ArrayList<ClassInfo> QueryAllClassInfoInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From ClassInfo";
            Query q = s.createQuery(hql);
            List classInfoList = q.list();
            return (ArrayList<ClassInfo>) classInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    public void CalculateTotalPageAndRecordNumber(String classNumber,String className,SpecialFieldInfo classSpecialFieldNumber,String classBirthDate) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From ClassInfo classInfo where 1=1";
            if(!classNumber.equals("")) hql = hql + " and classInfo.classNumber like '%" + classNumber + "%'";
            if(!className.equals("")) hql = hql + " and classInfo.className like '%" + className + "%'";
            if(null != classSpecialFieldNumber && !classSpecialFieldNumber.getSpecialFieldNumber().equals("")) hql += " and classInfo.classSpecialFieldNumber.specialFieldNumber='" + classSpecialFieldNumber.getSpecialFieldNumber() + "'";
            if(!classBirthDate.equals("")) hql = hql + " and classInfo.classBirthDate like '%" + classBirthDate + "%'";
            Query q = s.createQuery(hql);
            List classInfoList = q.list();
            recordNumber = classInfoList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����������ȡ����*/
    public ClassInfo GetClassInfoByClassNumber(String classNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            ClassInfo classInfo = (ClassInfo)s.get(ClassInfo.class, classNumber);
            return classInfo;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����ClassInfo��Ϣ*/
    public void UpdateClassInfo(ClassInfo classInfo) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(classInfo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*ɾ��ClassInfo��Ϣ*/
    public void DeleteClassInfo (String classNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object classInfo = s.get(ClassInfo.class, classNumber);
            s.delete(classInfo);
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
