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
import com.shuangyulin.dao.SpecialFieldInfoDAO;
import com.shuangyulin.domain.SpecialFieldInfo;
import com.shuangyulin.dao.CollegeInfoDAO;
import com.shuangyulin.domain.CollegeInfo;
import com.shuangyulin.test.TestUtil;

public class SpecialFieldInfoAction extends ActionSupport {

    /*�������Ҫ��ѯ������: רҵ���*/
    private String specialFieldNumber;
    public void setSpecialFieldNumber(String specialFieldNumber) {
        this.specialFieldNumber = specialFieldNumber;
    }
    public String getSpecialFieldNumber() {
        return this.specialFieldNumber;
    }

    /*�������Ҫ��ѯ������: רҵ����*/
    private String specialFieldName;
    public void setSpecialFieldName(String specialFieldName) {
        this.specialFieldName = specialFieldName;
    }
    public String getSpecialFieldName() {
        return this.specialFieldName;
    }

    /*�������Ҫ��ѯ������: ����ѧԺ*/
    private CollegeInfo specialCollegeNumber;
    public void setSpecialCollegeNumber(CollegeInfo specialCollegeNumber) {
        this.specialCollegeNumber = specialCollegeNumber;
    }
    public CollegeInfo getSpecialCollegeNumber() {
        return this.specialCollegeNumber;
    }

    /*�������Ҫ��ѯ������: ��������*/
    private String specialBirthDate;
    public void setSpecialBirthDate(String specialBirthDate) {
        this.specialBirthDate = specialBirthDate;
    }
    public String getSpecialBirthDate() {
        return this.specialBirthDate;
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
    SpecialFieldInfoDAO specialFieldInfoDAO = new SpecialFieldInfoDAO();

    /*��������SpecialFieldInfo����*/
    private SpecialFieldInfo specialFieldInfo;
    public void setSpecialFieldInfo(SpecialFieldInfo specialFieldInfo) {
        this.specialFieldInfo = specialFieldInfo;
    }
    public SpecialFieldInfo getSpecialFieldInfo() {
        return this.specialFieldInfo;
    }

    /*��ת�����SpecialFieldInfo��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�CollegeInfo��Ϣ*/
        CollegeInfoDAO collegeInfoDAO = new CollegeInfoDAO();
        List<CollegeInfo> collegeInfoList = collegeInfoDAO.QueryAllCollegeInfoInfo();
        ctx.put("collegeInfoList", collegeInfoList);
        return "add_view";
    }

    /*���SpecialFieldInfo��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddSpecialFieldInfo() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤רҵ����Ƿ��Ѿ�����*/
        String specialFieldNumber = specialFieldInfo.getSpecialFieldNumber();
        SpecialFieldInfo db_specialFieldInfo = specialFieldInfoDAO.GetSpecialFieldInfoBySpecialFieldNumber(specialFieldNumber);
        if(null != db_specialFieldInfo) {
            ctx.put("error",  java.net.URLEncoder.encode("��רҵ����Ѿ�����!"));
            return "error";
        }
        try {
            if(true) {
            CollegeInfoDAO collegeInfoDAO = new CollegeInfoDAO();
            CollegeInfo specialCollegeNumber = collegeInfoDAO.GetCollegeInfoByCollegeNumber(specialFieldInfo.getSpecialCollegeNumber().getCollegeNumber());
            specialFieldInfo.setSpecialCollegeNumber(specialCollegeNumber);
            }
            specialFieldInfoDAO.AddSpecialFieldInfo(specialFieldInfo);
            ctx.put("message",  java.net.URLEncoder.encode("SpecialFieldInfo��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("SpecialFieldInfo���ʧ��!"));
            return "error";
        }
    }

    /*��ѯSpecialFieldInfo��Ϣ*/
    public String QuerySpecialFieldInfo() {
        if(currentPage == 0) currentPage = 1;
        if(specialFieldNumber == null) specialFieldNumber = "";
        if(specialFieldName == null) specialFieldName = "";
        if(specialBirthDate == null) specialBirthDate = "";
        List<SpecialFieldInfo> specialFieldInfoList = specialFieldInfoDAO.QuerySpecialFieldInfoInfo(specialFieldNumber, specialFieldName, specialCollegeNumber, specialBirthDate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        specialFieldInfoDAO.CalculateTotalPageAndRecordNumber(specialFieldNumber, specialFieldName, specialCollegeNumber, specialBirthDate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = specialFieldInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = specialFieldInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("specialFieldInfoList",  specialFieldInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("specialFieldNumber", specialFieldNumber);
        ctx.put("specialFieldName", specialFieldName);
        ctx.put("specialCollegeNumber", specialCollegeNumber);
        CollegeInfoDAO collegeInfoDAO = new CollegeInfoDAO();
        List<CollegeInfo> collegeInfoList = collegeInfoDAO.QueryAllCollegeInfoInfo();
        ctx.put("collegeInfoList", collegeInfoList);
        ctx.put("specialBirthDate", specialBirthDate);
        return "query_view";
    }

    /*ǰ̨��ѯSpecialFieldInfo��Ϣ*/
    public String FrontQuerySpecialFieldInfo() {
        if(currentPage == 0) currentPage = 1;
        if(specialFieldNumber == null) specialFieldNumber = "";
        if(specialFieldName == null) specialFieldName = "";
        if(specialBirthDate == null) specialBirthDate = "";
        List<SpecialFieldInfo> specialFieldInfoList = specialFieldInfoDAO.QuerySpecialFieldInfoInfo(specialFieldNumber, specialFieldName, specialCollegeNumber, specialBirthDate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        specialFieldInfoDAO.CalculateTotalPageAndRecordNumber(specialFieldNumber, specialFieldName, specialCollegeNumber, specialBirthDate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = specialFieldInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = specialFieldInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("specialFieldInfoList",  specialFieldInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("specialFieldNumber", specialFieldNumber);
        ctx.put("specialFieldName", specialFieldName);
        ctx.put("specialCollegeNumber", specialCollegeNumber);
        CollegeInfoDAO collegeInfoDAO = new CollegeInfoDAO();
        List<CollegeInfo> collegeInfoList = collegeInfoDAO.QueryAllCollegeInfoInfo();
        ctx.put("collegeInfoList", collegeInfoList);
        ctx.put("specialBirthDate", specialBirthDate);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�SpecialFieldInfo��Ϣ*/
    public String ModifySpecialFieldInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������specialFieldNumber��ȡSpecialFieldInfo����*/
        SpecialFieldInfo specialFieldInfo = specialFieldInfoDAO.GetSpecialFieldInfoBySpecialFieldNumber(specialFieldNumber);

        CollegeInfoDAO collegeInfoDAO = new CollegeInfoDAO();
        List<CollegeInfo> collegeInfoList = collegeInfoDAO.QueryAllCollegeInfoInfo();
        ctx.put("collegeInfoList", collegeInfoList);
        ctx.put("specialFieldInfo",  specialFieldInfo);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�SpecialFieldInfo��Ϣ*/
    public String FrontShowSpecialFieldInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������specialFieldNumber��ȡSpecialFieldInfo����*/
        SpecialFieldInfo specialFieldInfo = specialFieldInfoDAO.GetSpecialFieldInfoBySpecialFieldNumber(specialFieldNumber);

        CollegeInfoDAO collegeInfoDAO = new CollegeInfoDAO();
        List<CollegeInfo> collegeInfoList = collegeInfoDAO.QueryAllCollegeInfoInfo();
        ctx.put("collegeInfoList", collegeInfoList);
        ctx.put("specialFieldInfo",  specialFieldInfo);
        return "front_show_view";
    }

    /*�����޸�SpecialFieldInfo��Ϣ*/
    public String ModifySpecialFieldInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            CollegeInfoDAO collegeInfoDAO = new CollegeInfoDAO();
            CollegeInfo specialCollegeNumber = collegeInfoDAO.GetCollegeInfoByCollegeNumber(specialFieldInfo.getSpecialCollegeNumber().getCollegeNumber());
            specialFieldInfo.setSpecialCollegeNumber(specialCollegeNumber);
            }
            specialFieldInfoDAO.UpdateSpecialFieldInfo(specialFieldInfo);
            ctx.put("message",  java.net.URLEncoder.encode("SpecialFieldInfo��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("SpecialFieldInfo��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��SpecialFieldInfo��Ϣ*/
    public String DeleteSpecialFieldInfo() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            specialFieldInfoDAO.DeleteSpecialFieldInfo(specialFieldNumber);
            ctx.put("message",  java.net.URLEncoder.encode("SpecialFieldInfoɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("SpecialFieldInfoɾ��ʧ��!"));
            return "error";
        }
    }

}
