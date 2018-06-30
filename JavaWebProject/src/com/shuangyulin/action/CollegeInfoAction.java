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
import com.shuangyulin.dao.CollegeInfoDAO;
import com.shuangyulin.domain.CollegeInfo;
import com.shuangyulin.test.TestUtil;

public class CollegeInfoAction extends ActionSupport {

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

    private String collegeNumber;
    public void setCollegeNumber(String collegeNumber) {
        this.collegeNumber = collegeNumber;
    }
    public String getCollegeNumber() {
        return collegeNumber;
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
    CollegeInfoDAO collegeInfoDAO = new CollegeInfoDAO();

    /*��������CollegeInfo����*/
    private CollegeInfo collegeInfo;
    public void setCollegeInfo(CollegeInfo collegeInfo) {
        this.collegeInfo = collegeInfo;
    }
    public CollegeInfo getCollegeInfo() {
        return this.collegeInfo;
    }

    /*��ת�����CollegeInfo��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���CollegeInfo��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddCollegeInfo() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤ѧԺ����Ƿ��Ѿ�����*/
        String collegeNumber = collegeInfo.getCollegeNumber();
        CollegeInfo db_collegeInfo = collegeInfoDAO.GetCollegeInfoByCollegeNumber(collegeNumber);
        if(null != db_collegeInfo) {
            ctx.put("error",  java.net.URLEncoder.encode("��ѧԺ����Ѿ�����!"));
            return "error";
        }
        try {
            collegeInfoDAO.AddCollegeInfo(collegeInfo);
            ctx.put("message",  java.net.URLEncoder.encode("CollegeInfo��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CollegeInfo���ʧ��!"));
            return "error";
        }
    }

    /*��ѯCollegeInfo��Ϣ*/
    public String QueryCollegeInfo() {
        if(currentPage == 0) currentPage = 1;
        List<CollegeInfo> collegeInfoList = collegeInfoDAO.QueryCollegeInfoInfo(currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        collegeInfoDAO.CalculateTotalPageAndRecordNumber();
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = collegeInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = collegeInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("collegeInfoList",  collegeInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        return "query_view";
    }

    /*ǰ̨��ѯCollegeInfo��Ϣ*/
    public String FrontQueryCollegeInfo() {
        if(currentPage == 0) currentPage = 1;
        List<CollegeInfo> collegeInfoList = collegeInfoDAO.QueryCollegeInfoInfo(currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        collegeInfoDAO.CalculateTotalPageAndRecordNumber();
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = collegeInfoDAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = collegeInfoDAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("collegeInfoList",  collegeInfoList);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�CollegeInfo��Ϣ*/
    public String ModifyCollegeInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������collegeNumber��ȡCollegeInfo����*/
        CollegeInfo collegeInfo = collegeInfoDAO.GetCollegeInfoByCollegeNumber(collegeNumber);

        ctx.put("collegeInfo",  collegeInfo);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�CollegeInfo��Ϣ*/
    public String FrontShowCollegeInfoQuery() {
        ActionContext ctx = ActionContext.getContext();
        /*��������collegeNumber��ȡCollegeInfo����*/
        CollegeInfo collegeInfo = collegeInfoDAO.GetCollegeInfoByCollegeNumber(collegeNumber);

        ctx.put("collegeInfo",  collegeInfo);
        return "front_show_view";
    }

    /*�����޸�CollegeInfo��Ϣ*/
    public String ModifyCollegeInfo() {
        ActionContext ctx = ActionContext.getContext();
        try {
            collegeInfoDAO.UpdateCollegeInfo(collegeInfo);
            ctx.put("message",  java.net.URLEncoder.encode("CollegeInfo��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CollegeInfo��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��CollegeInfo��Ϣ*/
    public String DeleteCollegeInfo() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            collegeInfoDAO.DeleteCollegeInfo(collegeNumber);
            ctx.put("message",  java.net.URLEncoder.encode("CollegeInfoɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("CollegeInfoɾ��ʧ��!"));
            return "error";
        }
    }

}
