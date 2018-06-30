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

import com.shuangyulin.domain.CollegeInfo;
import com.shuangyulin.domain.SpecialFieldInfo;

public class SpecialFieldInfoDAO {

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
    public void AddSpecialFieldInfo(SpecialFieldInfo specialFieldInfo) throws Exception {
        Session s = null;
        Transaction tx = null;
        try { 
            s = HibernateUtil.getSession();
            tx = s.beginTransaction(); 
            s.save(specialFieldInfo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
                tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*��ѯSpecialFieldInfo��Ϣ*/
    public ArrayList<SpecialFieldInfo> QuerySpecialFieldInfoInfo(String specialFieldNumber,String specialFieldName,CollegeInfo specialCollegeNumber,String specialBirthDate,int currentPage) { 
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From SpecialFieldInfo specialFieldInfo where 1=1";
            if(!specialFieldNumber.equals("")) hql = hql + " and specialFieldInfo.specialFieldNumber like '%" + specialFieldNumber + "%'";
            if(!specialFieldName.equals("")) hql = hql + " and specialFieldInfo.specialFieldName like '%" + specialFieldName + "%'";
            if(null != specialCollegeNumber && !specialCollegeNumber.getCollegeNumber().equals("")) hql += " and specialFieldInfo.specialCollegeNumber.collegeNumber='" + specialCollegeNumber.getCollegeNumber() + "'";
            if(!specialBirthDate.equals("")) hql = hql + " and specialFieldInfo.specialBirthDate like '%" + specialBirthDate + "%'";
            Query q = s.createQuery(hql);
            /*���㵱ǰ��ʾҳ��Ŀ�ʼ��¼*/
            int startIndex = (currentPage-1) * this.PAGE_SIZE;
            q.setFirstResult(startIndex);
            q.setMaxResults(this.PAGE_SIZE);
            List specialFieldInfoList = q.list();
            return (ArrayList<SpecialFieldInfo>) specialFieldInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�������ܣ���ѯ���е�SpecialFieldInfo��¼*/
    public ArrayList<SpecialFieldInfo> QueryAllSpecialFieldInfoInfo() {
        Session s = null; 
        try {
            s = HibernateUtil.getSession();
            String hql = "From SpecialFieldInfo";
            Query q = s.createQuery(hql);
            List specialFieldInfoList = q.list();
            return (ArrayList<SpecialFieldInfo>) specialFieldInfoList;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*�����ܵ�ҳ���ͼ�¼��*/
    public void CalculateTotalPageAndRecordNumber(String specialFieldNumber,String specialFieldName,CollegeInfo specialCollegeNumber,String specialBirthDate) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            String hql = "From SpecialFieldInfo specialFieldInfo where 1=1";
            if(!specialFieldNumber.equals("")) hql = hql + " and specialFieldInfo.specialFieldNumber like '%" + specialFieldNumber + "%'";
            if(!specialFieldName.equals("")) hql = hql + " and specialFieldInfo.specialFieldName like '%" + specialFieldName + "%'";
            if(null != specialCollegeNumber && !specialCollegeNumber.getCollegeNumber().equals("")) hql += " and specialFieldInfo.specialCollegeNumber.collegeNumber='" + specialCollegeNumber.getCollegeNumber() + "'";
            if(!specialBirthDate.equals("")) hql = hql + " and specialFieldInfo.specialBirthDate like '%" + specialBirthDate + "%'";
            Query q = s.createQuery(hql);
            List specialFieldInfoList = q.list();
            recordNumber = specialFieldInfoList.size();
            int mod = recordNumber % this.PAGE_SIZE;
            totalPage = recordNumber / this.PAGE_SIZE;
            if(mod != 0) totalPage++;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����������ȡ����*/
    public SpecialFieldInfo GetSpecialFieldInfoBySpecialFieldNumber(String specialFieldNumber) {
        Session s = null;
        try {
            s = HibernateUtil.getSession();
            SpecialFieldInfo specialFieldInfo = (SpecialFieldInfo)s.get(SpecialFieldInfo.class, specialFieldNumber);
            return specialFieldInfo;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*����SpecialFieldInfo��Ϣ*/
    public void UpdateSpecialFieldInfo(SpecialFieldInfo specialFieldInfo) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            s.update(specialFieldInfo);
            tx.commit();
        } catch (HibernateException e) {
            if(tx != null)
            	  tx.rollback();
            throw e;
        } finally {
            HibernateUtil.closeSession();
        }
    }

    /*ɾ��SpecialFieldInfo��Ϣ*/
    public void DeleteSpecialFieldInfo (String specialFieldNumber) throws Exception {
        Session s = null;
        Transaction tx = null;
        try {
            s = HibernateUtil.getSession();
            tx = s.beginTransaction();
            Object specialFieldInfo = s.get(SpecialFieldInfo.class, specialFieldNumber);
            s.delete(specialFieldInfo);
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
