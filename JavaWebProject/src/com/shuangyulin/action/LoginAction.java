package com.shuangyulin.action;

 

 

 

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.shuangyulin.dao.AdminDAO;
import com.shuangyulin.domain.Admin;

public class LoginAction extends ActionSupport {
 
	
	private Admin admin;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	/*ֱ����ת����½����*/
	public String view() {
		
		return "login_view";
	}
	 
	
	/* ��֤�û���¼ */
	public String CheckLogin() {
		AdminDAO adminDAO = new AdminDAO();
		ActionContext ctx = ActionContext.getContext();
		if (!adminDAO.CheckLogin(admin)) {
			ctx.put("error",  java.net.URLEncoder.encode(adminDAO.getErrMessage()));
			return "error";
		}
		ctx.getSession().put("username", admin.getUsername());
		return "main_view";

		/*
		 * ActionContext ctx = ActionContext.getContext();
		 * ctx.getApplication().put("app", "Ӧ�÷�Χ");//��ServletContext�����app
		 * ctx.getSession().put("ses", "session��Χ");//��session�����ses ctx.put("req",
		 * "request��Χ");//��request�����req ctx.put("names", Arrays.asList("����", "����",
		 * "�Ϸ�")); HttpServletRequest request = ServletActionContext.getRequest();
		 * ServletContext servletContext = ServletActionContext.getServletContext();
		 * request.setAttribute("req", "����Χ����");
		 * request.getSession().setAttribute("ses", "�Ự��Χ����");
		 * servletContext.setAttribute("app", "Ӧ�÷�Χ����"); // HttpServletResponse
		 * response = ServletActionContext.getResponse();
		 */
	}

	

}
