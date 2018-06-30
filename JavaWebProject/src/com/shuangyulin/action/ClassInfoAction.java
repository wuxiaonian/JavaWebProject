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
import com.shuangyulin.dao.ClassInfoDAO;
import com.shuangyulin.domain.ClassInfo;
import com.shuangyulin.dao.SpecialFieldInfoDAO;
import com.shuangyulin.domain.SpecialFieldInfo;
import com.shuangyulin.test.TestUtil;

public class ClassInfoAction extends ActionSupport {

    /*�������Ҫ��ѯ������: �༶���*/
    private String classNumber;
    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }
    public String getClassNumber() {
        return this.classNumber;
    }

    /*�������Ҫ��ѯ������: �༶����*/
    private String className;
    public void setClassName(String className) {
        this.className = className;
    }
    public String getClassName() {
        return this.className;
    }

    /*�������Ҫ��ѯ������: ����רҵ*/
    private SpecialFieldInfo classSpecialFieldNumber;
    public void setClassSpecialFieldNumber(SpecialFieldInfo classSpecialFieldNumber) {
        this.classSpecialFieldNumber = classSpecialFieldNumber;
    }
    public SpecialFieldInfo getClassSpecialFieldNumber() {
        return this.classSpecialFieldNumber;
    }

    /*�������Ҫ��ѯ������: ��������*/
    private String classBirthDate;
    public void setClassBirthDate(String classBirthDate) {
        this.classBirthDate = classBirthDate;
    }
    public String getClassBirthDate() {
        return this.classBirthDate;
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
    ClassInfoDAO classInfoDAO = new ClassInfoDAO();

    /*��������ClassInfo����*/
    private ClassInfo classInfo;
    public void setClassInfo(ClassInfo classInfo) {
        this.classInfo = classInfo;
    }
    public ClassInfo getClassInfo() {
        return this.classInfo;
    }

    /*��ת�����ClassInfo��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�SpecialFieldInfo��Ϣ*/
        SpecialFieldInfoDAO specialFieldInfoDAO = new SpecialFieldInfoDAO();
        List<SpecialFieldInfo> specialFieldInfoList = specialFieldInfoDAO.QueryAllSpecialFieldInfoInfo();
        ctx.put("specialFieldInfoList", specialFieldInfoList);
        return "add_view";
    }

    /*���ClassInfo��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddClassInfo() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤�༶����Ƿ��Ѿ�����*/
        String classNumber = classInfo.getClassNumber();
        ClassInfo db_classInfo = classInfoDAO.GetClassInfoByClassNumber(classNumber);
        if(null != db_classInfo) {
            ctx.put("error",  java.net.URLEncoder.encode("�ð༶����Ѿ�����!"));
            return "error";
        }
        try {
            if(true) {
            SpecialFieldInfoDAO specialFieldInfoDAO = new SpecialFieldInfoDAO();
            SpecialFieldInfo classSpecialFieldNumber = specialFieldInfoDAO.GetSpecialFieldInfoBySpecialFieldNumber(classInfo.getClassSpecialFieldNumber().getSpecialFieldNumber());
            classInfo.setClassSpecialFieldNumber(classSpecialFieldNumber);
            }
            classInfoDAO.AddClassInfo(classInfo);
            ctx.put("message",  java.net.URLEncoder.encode("ClassInfo��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ClassInfo���ʧ��!"));
            return "error";
        }
    }

    /*��ѯClassInfo��Ϣ*/
    public String QueryClassInfo() {
        if(currentPage == 0) currentPage = 1;
        if(classNumber == null) classNumber = "";
        if(className == null) className = "";
        if(classBirthDate == null) classBirthDate = "";
        List<ClassInfo> classInfoList = classInfoDAO.QueryClassInfoInfo(classNumber, className, classSpecialFieldNumber, classBirthDate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        classInfoDAO.CalculateTotalPageAndRecordNumber(classNumber, className, classSpecialFieldNumber, classBirthDate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = classInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = classInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("classInfoList",  classInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("classNumber", classNumber);
        ctx.put("className", className);
        ctx.put("classSpecialFieldNumber", classSpecialFieldNumber);
        SpecialFieldInfoDAO specialFieldInfoDAO = new SpecialFieldInfoDAO();
        List<SpecialFieldInfo> specialFieldInfoList = specialFieldInfoDAO.QueryAllSpecialFieldInfoInfo();
        ctx.put("specialFieldInfoList", specialFieldInfoList);
        ctx.put("classBirthDate", classBirthDate);
        return "query_view";
    }

    /*ǰ̨��ѯClassInfo��Ϣ*/
    public String FrontQueryClassInfo() {
        if(currentPage == 0) currentPage = 1;
        if(classNumber == null) classNumber = "";
        if(className == null) className = "";
        if(classBirthDate == null) classBirthDate = "";
        List<ClassInfo> classInfoList = classInfoDAO.QueryClassInfoInfo(classNumber, className, classSpecialFieldNumber, classBirthDate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        classInfoDAO.CalculateTotalPageAndRecordNumber(classNumber, className, classSpecialFieldNumber, classBirthDate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = classInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = classInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("classInfoList",  classInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("classNumber", classNumber);
        ctx.put("className", className);
        ctx.put("classSpecialFieldNumber", classSpecialFieldNumber);
        SpecialFieldInfoDAO specialFieldInfoDAO = new SpecialFieldInfoDAO();
        List<SpecialFieldInfo> specialFieldInfoList = specialFieldInfoDAO.QueryAllSpecialFieldInfoInfo();
        ctx.put("specialFieldInfoList", specialFieldInfoList);
        ctx.put("classBirthDate", classBirthDate);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�ClassInfo��Ϣ*/
    public String ModifyClassInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������classNumber��ȡClassInfo����*/
        ClassInfo classInfo = classInfoDAO.GetClassInfoByClassNumber(classNumber);

        SpecialFieldInfoDAO specialFieldInfoDAO = new SpecialFieldInfoDAO();
        List<SpecialFieldInfo> specialFieldInfoList = specialFieldInfoDAO.QueryAllSpecialFieldInfoInfo();
        ctx.put("specialFieldInfoList", specialFieldInfoList);
        ctx.put("classInfo",  classInfo);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�ClassInfo��Ϣ*/
    public String FrontShowClassInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������classNumber��ȡClassInfo����*/
        ClassInfo classInfo = classInfoDAO.GetClassInfoByClassNumber(classNumber);

        SpecialFieldInfoDAO specialFieldInfoDAO = new SpecialFieldInfoDAO();
        List<SpecialFieldInfo> specialFieldInfoList = specialFieldInfoDAO.QueryAllSpecialFieldInfoInfo();
        ctx.put("specialFieldInfoList", specialFieldInfoList);
        ctx.put("classInfo",  classInfo);
        return "front_show_view";
    }

    /*�����޸�ClassInfo��Ϣ*/
    public String ModifyClassInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            SpecialFieldInfoDAO specialFieldInfoDAO = new SpecialFieldInfoDAO();
            SpecialFieldInfo classSpecialFieldNumber = specialFieldInfoDAO.GetSpecialFieldInfoBySpecialFieldNumber(classInfo.getClassSpecialFieldNumber().getSpecialFieldNumber());
            classInfo.setClassSpecialFieldNumber(classSpecialFieldNumber);
            }
            classInfoDAO.UpdateClassInfo(classInfo);
            ctx.put("message",  java.net.URLEncoder.encode("ClassInfo��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ClassInfo��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��ClassInfo��Ϣ*/
    public String DeleteClassInfo() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            classInfoDAO.DeleteClassInfo(classNumber);
            ctx.put("message",  java.net.URLEncoder.encode("ClassInfoɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("ClassInfoɾ��ʧ��!"));
            return "error";
        }
    }

}
