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
import com.shuangyulin.dao.Student_QQ287307421DAO;
import com.shuangyulin.domain.Student_QQ287307421;
import com.shuangyulin.dao.ClassInfoDAO;
import com.shuangyulin.domain.ClassInfo;
import com.shuangyulin.test.TestUtil;

public class Student_QQ287307421Action extends ActionSupport {

/*ͼƬ�ֶ�studentPhoto��������*/
	 private File studentPhotoFile;
	 private String studentPhotoFileFileName;
	 private String studentPhotoFileContentType;
	 public File getStudentPhotoFile() {
		return studentPhotoFile;
	}
	public void setStudentPhotoFile(File studentPhotoFile) {
		this.studentPhotoFile = studentPhotoFile;
	}
	public String getStudentPhotoFileFileName() {
		return studentPhotoFileFileName;
	}
	public void setStudentPhotoFileFileName(String studentPhotoFileFileName) {
		this.studentPhotoFileFileName = studentPhotoFileFileName;
	}
	public String getStudentPhotoFileContentType() {
		return studentPhotoFileContentType;
	}
	public void setStudentPhotoFileContentType(String studentPhotoFileContentType) {
		this.studentPhotoFileContentType = studentPhotoFileContentType;
	}
    /*�������Ҫ��ѯ������: ѧ��*/
    private String studentNumber;
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }
    public String getStudentNumber() {
        return this.studentNumber;
    }

    /*�������Ҫ��ѯ������: ����*/
    private String studentName;
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    public String getStudentName() {
        return this.studentName;
    }

    /*�������Ҫ��ѯ������: ���ڰ༶*/
    private ClassInfo studentClassNumber;
    public void setStudentClassNumber(ClassInfo studentClassNumber) {
        this.studentClassNumber = studentClassNumber;
    }
    public ClassInfo getStudentClassNumber() {
        return this.studentClassNumber;
    }

    /*�������Ҫ��ѯ������: ��������*/
    private String studentBirthday;
    public void setStudentBirthday(String studentBirthday) {
        this.studentBirthday = studentBirthday;
    }
    public String getStudentBirthday() {
        return this.studentBirthday;
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
    Student_QQ287307421DAO student_QQ287307421DAO = new Student_QQ287307421DAO();

    /*��������Student_QQ287307421����*/
    private Student_QQ287307421 student_QQ287307421;
    public void setStudent_QQ287307421(Student_QQ287307421 student_QQ287307421) {
        this.student_QQ287307421 = student_QQ287307421;
    }
    public Student_QQ287307421 getStudent_QQ287307421() {
        return this.student_QQ287307421;
    }

    /*��ת�����Student_QQ287307421��ͼ*/
    public String AddView() {
        ActionContext ctx = ActionContext.getContext();
        /*��ѯ���е�ClassInfo��Ϣ*/
        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        return "add_view";
    }

    /*���Student_QQ287307421��Ϣ*/
    @SuppressWarnings("deprecation")
    public String AddStudent_QQ287307421() {
        ActionContext ctx = ActionContext.getContext();
        /*��֤ѧ���Ƿ��Ѿ�����*/
        String studentNumber = student_QQ287307421.getStudentNumber();
        Student_QQ287307421 db_student_QQ287307421 = student_QQ287307421DAO.GetStudent_QQ287307421ByStudentNumber(studentNumber);
        if(null != db_student_QQ287307421) {
            ctx.put("error",  java.net.URLEncoder.encode("��ѧ���Ѿ�����!"));
            return "error";
        }
        try {
            if(true) {
            ClassInfoDAO classInfoDAO = new ClassInfoDAO();
            ClassInfo studentClassNumber = classInfoDAO.GetClassInfoByClassNumber(student_QQ287307421.getStudentClassNumber().getClassNumber());
            student_QQ287307421.setStudentClassNumber(studentClassNumber);
            }
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*����ͼƬ�ϴ�*/
            String studentPhotoFileName = ""; 
       	 	if(studentPhotoFile != null) {
       	 		InputStream is = new FileInputStream(studentPhotoFile);
       			String fileContentType = this.getStudentPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg")  || fileContentType.equals("image/pjpeg"))
       				studentPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				studentPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("�ϴ�ͼƬ��ʽ����ȷ!"));
       				return "error";
       			}
       			File file = new File(path, studentPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
       	 	}
            if(studentPhotoFile != null)
            	student_QQ287307421.setStudentPhoto("upload/" + studentPhotoFileName);
            else
            	student_QQ287307421.setStudentPhoto("upload/NoImage.jpg");
            student_QQ287307421DAO.AddStudent_QQ287307421(student_QQ287307421);
            ctx.put("message",  java.net.URLEncoder.encode("Student_QQ287307421��ӳɹ�!"));
            return "add_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Student_QQ287307421���ʧ��!"));
            return "error";
        }
    }

    /*��ѯStudent_QQ287307421��Ϣ*/
    public String QueryStudent_QQ287307421() {
        if(currentPage == 0) currentPage = 1;
        if(studentNumber == null) studentNumber = "";
        if(studentName == null) studentName = "";
        if(studentBirthday == null) studentBirthday = "";
        List<Student_QQ287307421> student_QQ287307421List = student_QQ287307421DAO.QueryStudent_QQ287307421Info(studentNumber, studentName, studentClassNumber, studentBirthday, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        student_QQ287307421DAO.CalculateTotalPageAndRecordNumber(studentNumber, studentName, studentClassNumber, studentBirthday);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = student_QQ287307421DAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = student_QQ287307421DAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("student_QQ287307421List",  student_QQ287307421List);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("studentNumber", studentNumber);
        ctx.put("studentName", studentName);
        ctx.put("studentClassNumber", studentClassNumber);
        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        ctx.put("studentBirthday", studentBirthday);
        return "query_view";
    }

    /*ǰ̨��ѯStudent_QQ287307421��Ϣ*/
    public String FrontQueryStudent_QQ287307421() {
        if(currentPage == 0) currentPage = 1;
        if(studentNumber == null) studentNumber = "";
        if(studentName == null) studentName = "";
        if(studentBirthday == null) studentBirthday = "";
        List<Student_QQ287307421> student_QQ287307421List = student_QQ287307421DAO.QueryStudent_QQ287307421Info(studentNumber, studentName, studentClassNumber, studentBirthday, currentPage);
        /*�����ܵ�ҳ�����ܵļ�¼��*/
        student_QQ287307421DAO.CalculateTotalPageAndRecordNumber(studentNumber, studentName, studentClassNumber, studentBirthday);
        /*��ȡ���ܵ�ҳ����Ŀ*/
        totalPage = student_QQ287307421DAO.getTotalPage();
        /*��ǰ��ѯ�������ܼ�¼��*/
        recordNumber = student_QQ287307421DAO.getRecordNumber();
        ActionContext ctx = ActionContext.getContext();
        ctx.put("student_QQ287307421List",  student_QQ287307421List);
        ctx.put("totalPage", totalPage);
        ctx.put("recordNumber", recordNumber);
        ctx.put("currentPage", currentPage);
        ctx.put("studentNumber", studentNumber);
        ctx.put("studentName", studentName);
        ctx.put("studentClassNumber", studentClassNumber);
        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        ctx.put("studentBirthday", studentBirthday);
        return "front_query_view";
    }

    /*��ѯҪ�޸ĵ�Student_QQ287307421��Ϣ*/
    public String ModifyStudent_QQ287307421Query() {
        ActionContext ctx = ActionContext.getContext();
        /*��������studentNumber��ȡStudent_QQ287307421����*/
        Student_QQ287307421 student_QQ287307421 = student_QQ287307421DAO.GetStudent_QQ287307421ByStudentNumber(studentNumber);

        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        ctx.put("student_QQ287307421",  student_QQ287307421);
        return "modify_view";
    }

    /*��ѯҪ�޸ĵ�Student_QQ287307421��Ϣ*/
    public String FrontShowStudent_QQ287307421Query() {
        ActionContext ctx = ActionContext.getContext();
        /*��������studentNumber��ȡStudent_QQ287307421����*/
        Student_QQ287307421 student_QQ287307421 = student_QQ287307421DAO.GetStudent_QQ287307421ByStudentNumber(studentNumber);

        ClassInfoDAO classInfoDAO = new ClassInfoDAO();
        List<ClassInfo> classInfoList = classInfoDAO.QueryAllClassInfoInfo();
        ctx.put("classInfoList", classInfoList);
        ctx.put("student_QQ287307421",  student_QQ287307421);
        return "front_show_view";
    }

    /*�����޸�Student_QQ287307421��Ϣ*/
    public String ModifyStudent_QQ287307421() {
        ActionContext ctx = ActionContext.getContext();
        try {
            if(true) {
            ClassInfoDAO classInfoDAO = new ClassInfoDAO();
            ClassInfo studentClassNumber = classInfoDAO.GetClassInfoByClassNumber(student_QQ287307421.getStudentClassNumber().getClassNumber());
            student_QQ287307421.setStudentClassNumber(studentClassNumber);
            }
            String path = ServletActionContext.getServletContext().getRealPath("/upload"); 
            /*����ͼƬ�ϴ�*/
            String studentPhotoFileName = ""; 
       	 	if(studentPhotoFile != null) {
       	 		InputStream is = new FileInputStream(studentPhotoFile);
       			String fileContentType = this.getStudentPhotoFileContentType();
       			if(fileContentType.equals("image/jpeg") || fileContentType.equals("image/pjpeg"))
       				studentPhotoFileName = UUID.randomUUID().toString() +  ".jpg";
       			else if(fileContentType.equals("image/gif"))
       				studentPhotoFileName = UUID.randomUUID().toString() +  ".gif";
       			else {
       				ctx.put("error",  java.net.URLEncoder.encode("�ϴ�ͼƬ��ʽ����ȷ!"));
       				return "error";
       			}
       			File file = new File(path, studentPhotoFileName);
       			OutputStream os = new FileOutputStream(file);
       			byte[] b = new byte[1024];
       			int bs = 0;
       			while ((bs = is.read(b)) > 0) {
       				os.write(b, 0, bs);
       			}
       			is.close();
       			os.close();
            student_QQ287307421.setStudentPhoto("upload/" + studentPhotoFileName);
       	 	}
            student_QQ287307421DAO.UpdateStudent_QQ287307421(student_QQ287307421);
            ctx.put("message",  java.net.URLEncoder.encode("Student_QQ287307421��Ϣ���³ɹ�!"));
            return "modify_success";
        } catch (Exception e) {
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Student_QQ287307421��Ϣ����ʧ��!"));
            return "error";
       }
   }

    /*ɾ��Student_QQ287307421��Ϣ*/
    public String DeleteStudent_QQ287307421() {
        ActionContext ctx = ActionContext.getContext();
        try { 
            student_QQ287307421DAO.DeleteStudent_QQ287307421(studentNumber);
            ctx.put("message",  java.net.URLEncoder.encode("Student_QQ287307421ɾ���ɹ�!"));
            return "delete_success";
        } catch (Exception e) { 
            e.printStackTrace();
            ctx.put("error",  java.net.URLEncoder.encode("Student_QQ287307421ɾ��ʧ��!"));
            return "error";
        }
    }

}
