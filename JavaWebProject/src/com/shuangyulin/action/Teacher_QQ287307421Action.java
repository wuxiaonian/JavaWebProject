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
import com.shuangyulin.dao.Teacher_QQ287307421DAO;
import com.shuangyulin.domain.Teacher_QQ287307421;
import com.shuangyulin.test.TestUtil;

public class Teacher_QQ287307421Action extends ActionSupport {

/*ͼƬ�ֶ�teacherPhoto��������*/
	 private File teacherPhotoFile;
	 private String teacherPhotoFileFileName;
	 private String teacherPhotoFileContentType;
	 public File getTeacherPhotoFile() {
		return teacherPhotoFile;
	}
	public void setTeacherPhotoFile(File teacherPhotoFile) {
		this.teacherPhotoFile = teacherPhotoFile;
	}
	public String getTeacherPhotoFileFileName() {
		return teacherPhotoFileFileName;
	}
	public void setTeacherPhotoFileFileName(String teacherPhotoFileFileName) {
		this.teacherPhotoFileFileName = teacherPhotoFileFileName;
	}
	public String getTeacherPhotoFileContentType() {
		return teacherPhotoFileContentType;
	}
	public void setTeacherPhotoFileContentType(String teacherPhotoFileContentType) {
		this.teacherPhotoFileContentType = teacherPhotoFileContentType;
	}
    /*�������Ҫ��ѯ������: ��ʦ���*/
    private String teacherNumber;
    public void setTeacherNumber(String teacherNumber) {
        this.teacherNumber = teacherNumber;
    }
    public String getTeacherNumber() {
        return this.teacherNumber;
    }

    /*�������Ҫ��ѯ������: ��ʦ����*/
    private String teacherName;
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    public String getTeacherName() {
        return this.teacherName;
    }

    /*�������Ҫ��ѯ������: ��������*/
    private String teacherBirthday;
    public void setTeacherBirthday(String teacherBirthday) {
        this.teacherBirthday = teacherBirthday;
    }
    public String getTeacherBirthday() {
        return this.teacherBirthday;
    }

    /*�������Ҫ��ѯ������: ��ְ����*/
    private String teacherArriveDate;
    public void setTeacherArriveDate(String teacherArriveDate) {
        this.teacherArriveDate = teacherArriveDate;
    }
    public String getTeacherArriveDate() {
        return this.teacherArriveDate;
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
    Teacher_QQ287307421DAO teacher_QQ287307421DAO = new Teacher_QQ287307421DAO();

    /*��������Teacher_QQ287307421����*/
    private Teacher_QQ287307421 teacher_QQ287307421;
    public void setTeacher_QQ287307421(Teacher_QQ287307421 teacher_QQ287307421) {
        this.teacher_QQ287307421 = teacher_QQ287307421;
    }
    public Teacher_QQ287307421 getTeacher_QQ287307421() {
        return this.teacher_QQ287307421;
    }

    /*��ת�����Teacher_QQ287307421��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        return "add_view";
    }

    /*���Teacher_QQ287307421��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddTeacher_QQ287307421() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤��ʦ����Ƿ��Ѿ�����*/
        String teacherNumber = teacher_QQ287307421.getTeacherNumber();
        Teacher_QQ287307421 db_teacher_QQ287307421 = teacher_QQ287307421DAO.GetTeacher_QQ287307421ByTeacherNumber(teacherNumber);
        if(null != db_teacher_QQ287307421) {
            ctx.put("error",  java.net.URLEncoder.encode("�ý�ʦ����Ѿ�����!"));
            return "error";
        }
        try {
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*����ͼƬ�ϴ�*/
            String teacherPhotoFileName = ""; 
       	 	if(teacherPhotoFile != null) {
       	 		InputStream is = new FileInputStream(teacherPhotoFile);
       			String fileContentType = this.getTeacherPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg")  || fileContentType.equals("image/pjpeg"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("�ϴ�ͼƬ��ʽ����ȷ!"));
       				return "error";
       			}
       			File file = new File(path, teacherPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
       	 	}
            if(teacherPhotoFile != null)
            	teacher_QQ287307421.setTeacherPhoto("upload/" + teacherPhotoFileName);
            else
            	teacher_QQ287307421.setTeacherPhoto("upload/NoImage.jpg");
            teacher_QQ287307421DAO.AddTeacher_QQ287307421(teacher_QQ287307421);
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQ287307421��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQ287307421���ʧ��!"));
            return "error";
        }
    }

    /*��ѯTeacher_QQ287307421��Ϣ*/
    public String QueryTeacher_QQ287307421() {
        if(currentPage == 0) currentPage = 1;
        if(teacherNumber == null) teacherNumber = "";
        if(teacherName == null) teacherName = "";
        if(teacherBirthday == null) teacherBirthday = "";
        if(teacherArriveDate == null) teacherArriveDate = "";
        List<Teacher_QQ287307421> teacher_QQ287307421List = teacher_QQ287307421DAO.QueryTeacher_QQ287307421Info(teacherNumber, teacherName, teacherBirthday, teacherArriveDate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        teacher_QQ287307421DAO.CalculateTotalPageAndRecordNumber(teacherNumber, teacherName, teacherBirthday, teacherArriveDate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = teacher_QQ287307421DAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = teacher_QQ287307421DAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("teacher_QQ287307421List",  teacher_QQ287307421List);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("teacherNumber", teacherNumber);
        ctx.put("teacherName", teacherName);
        ctx.put("teacherBirthday", teacherBirthday);
        ctx.put("teacherArriveDate", teacherArriveDate);
        return "query_view";
    }

    /*ǰ̨��ѯTeacher_QQ287307421��Ϣ*/
    public String FrontQueryTeacher_QQ287307421() {
        if(currentPage == 0) currentPage = 1;
        if(teacherNumber == null) teacherNumber = "";
        if(teacherName == null) teacherName = "";
        if(teacherBirthday == null) teacherBirthday = "";
        if(teacherArriveDate == null) teacherArriveDate = "";
        List<Teacher_QQ287307421> teacher_QQ287307421List = teacher_QQ287307421DAO.QueryTeacher_QQ287307421Info(teacherNumber, teacherName, teacherBirthday, teacherArriveDate, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        teacher_QQ287307421DAO.CalculateTotalPageAndRecordNumber(teacherNumber, teacherName, teacherBirthday, teacherArriveDate);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = teacher_QQ287307421DAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = teacher_QQ287307421DAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("teacher_QQ287307421List",  teacher_QQ287307421List);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("teacherNumber", teacherNumber);
        ctx.put("teacherName", teacherName);
        ctx.put("teacherBirthday", teacherBirthday);
        ctx.put("teacherArriveDate", teacherArriveDate);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�Teacher_QQ287307421��Ϣ*/
    public String ModifyTeacher_QQ287307421Query() {
        ActionContext ctx = ActionContext.getContext();
        /*��������teacherNumber��ȡTeacher_QQ287307421����*/
        Teacher_QQ287307421 teacher_QQ287307421 = teacher_QQ287307421DAO.GetTeacher_QQ287307421ByTeacherNumber(teacherNumber);

        ctx.put("teacher_QQ287307421",  teacher_QQ287307421);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Teacher_QQ287307421��Ϣ*/
    public String FrontShowTeacher_QQ287307421Query() {
        ActionContext ctx = ActionContext.getContext();
        /*��������teacherNumber��ȡTeacher_QQ287307421����*/
        Teacher_QQ287307421 teacher_QQ287307421 = teacher_QQ287307421DAO.GetTeacher_QQ287307421ByTeacherNumber(teacherNumber);

        ctx.put("teacher_QQ287307421",  teacher_QQ287307421);
        return "front_show_view";
    }

    /*�����޸�Teacher_QQ287307421��Ϣ*/
    public String ModifyTeacher_QQ287307421() {
        ActionContext ctx = ActionContext.getContext();
        try {
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*����ͼƬ�ϴ�*/
            String teacherPhotoFileName = ""; 
       	 	if(teacherPhotoFile != null) {
       	 		InputStream is = new FileInputStream(teacherPhotoFile);
       			String fileContentType = this.getTeacherPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg") || fileContentType.equals("image/pjpeg"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				teacherPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("�ϴ�ͼƬ��ʽ����ȷ!"));
       				return "error";
       			}
       			File file = new File(path, teacherPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
            teacher_QQ287307421.setTeacherPhoto("upload/" + teacherPhotoFileName);
       	 	}
            teacher_QQ287307421DAO.UpdateTeacher_QQ287307421(teacher_QQ287307421);
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQ287307421��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQ287307421��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Teacher_QQ287307421��Ϣ*/
    public String DeleteTeacher_QQ287307421() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            teacher_QQ287307421DAO.DeleteTeacher_QQ287307421(teacherNumber);
            ctx.put("message",  java.net.URLEncoder.encode("Teacher_QQ287307421ɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Teacher_QQ287307421ɾ��ʧ��!"));
            return "error";
        }
    }

}
