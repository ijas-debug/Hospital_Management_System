package com.in.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.in.bean.*;
import com.in.dao.*;

import com.in.bean.Register;

import com.in.bean.*;
/**
 * Servlet implementation class Controller
 */
@WebServlet("/Controller")

public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public Controller() {
        super();
        
    }
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
/**
 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
 */
@SuppressWarnings("unused")

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
		String action=request.getParameter("action");
		
		Dao dobj=new Dao();
		HttpSession ses=request.getSession();
		
	//reg
		
		if(action.equals("reg"))
		{
			Register robj=new Register();
			robj.setOn(request.getParameter("on"));
			robj.setOname(request.getParameter("oname"));
			robj.setOpassword(request.getParameter("opassword"));
			robj.setOemail(request.getParameter("oemail"));
			
			String result=dobj.regUser(robj);
			ArrayList<Register> viewUser=dobj.viewUser();
			
			if(result.equals("success"))
			{
				ses.setAttribute("ulist", viewUser);
				request.getRequestDispatcher("AdminLogin.html").forward(request, response);
			}
		}
		
						
		// User
			
		if(action.equals("ureg"))
		{
			Register robj=new Register();
			robj.setUn(request.getParameter("un"));
			robj.setUname(request.getParameter("uname"));
			robj.setUpassword(request.getParameter("upassword"));
			robj.setUemail(request.getParameter("uemail"));
			
			String result=dobj.regUser1(robj);
			ArrayList<Register> viewUser1=dobj.viewUser1();
			
			if(result.equals("success"))
			{
				ses.setAttribute("ulist", viewUser1);
				request.getRequestDispatcher("login.html").forward(request, response);
				}
		}
		
		
		if(action.equals("log"))
		{
			String name=request.getParameter("oname");
		    String password=request.getParameter("opassword");

			Register robj=new Register();
			robj.setOname(name);
			robj.setOpassword(password);
			String result=dobj.loginowner(robj);
			
			
			if(result.equals("success"))
			{
			ses.setAttribute("name", name); 
			request.setAttribute("password", password);			
			request.getRequestDispatcher("Admin_Home.html").forward(request, response);
		    }
		}
		
		if(action.equals("log1"))
		{
			String uname=request.getParameter("uname");
		    String upassword=request.getParameter("upassword");

			Register robj=new Register();
			robj.setUname(uname);
			robj.setUpassword(upassword);
			
			String result=dobj.loginuser(robj);
			
			
			if(result.equals("success"))
			{
			ses.setAttribute("name", uname); 
			request.setAttribute("password", upassword);			
			request.getRequestDispatcher("User_Welcome.html").forward(request, response);
		    }
		}
		
		//submit page button
		
		if(action.equals("sub"))
		{	
			request.getRequestDispatcher("Admin_Home.jsp").forward(request, response);
		}
		
		// Admin View

				if (action.equals("viewupload")) {
					ArrayList<Register> AdminView = dobj.AdminViews();
					ses.setAttribute("ulist", AdminView);
					request.getRequestDispatcher("Admin_View.jsp").forward(request, response);
				}
		
		// Admin Request

				if (action.equals("request")) {
					ArrayList<Register> approval = dobj.AdminApproval();
					ses.setAttribute("ulist", approval);
					request.getRequestDispatcher("Admin_Request.jsp").forward(request, response);

				}
				// User search & view

				if (action.equals("search")) {
					String Email = request.getParameter("Email");
					String keyword = request.getParameter("keyword");
					ses.setAttribute("Email", Email);
					ArrayList<Register> Usersearch = dobj.search(keyword);
					ses.setAttribute("ulist", Usersearch);
					request.getRequestDispatcher("User_View.jsp").forward(request, response);
				}

				// Search page

				if (action.equals("searchpage")) {
					String Email = request.getParameter("Email");
					System.out.println(Email);
					ses.setAttribute("Email", Email);
					request.getRequestDispatcher("UserSearch.jsp").forward(request, response);

				}

				// image request

				if (action.equals("imagerequestuser")) {
					String Email = request.getParameter("Email");
					ses.setAttribute("Email", Email);
					System.out.println(Email);
					String Image = request.getParameter("Image");
					
					String result = dobj.sendRequest(Image, Email);
					if (result.equals("success")) {
						request.getRequestDispatcher("UserSearch.jsp").forward(request, response);
					}

				}

				// image status

				if (action.equals("imagestatus")) {
					String Email = request.getParameter("Email");
					System.out.println(Email);
					ArrayList<Register> status = dobj.userRequest(Email);
					ses.setAttribute("ulist", status);
					ses.setAttribute("Email", Email);
					request.getRequestDispatcher("User_Status.jsp").forward(request, response);

				}

				// Admin approval

				if (action.equals("approve")) {
					String Si_No = request.getParameter("Si_No");
					String result =dobj.approval(Si_No); 
					if(result.equals("success"))
					{
						/*String result2 = dobj.approvedelete(Si_No);
						if(result2.equals("success"))
						{
							ArrayList<Register> approval = dobj.AdminApproval();
							ses.setAttribute("ulist", approval);
							request.getRequestDispatcher("Admin_Request.jsp").forward(request, response);
						}*/
						
						ArrayList<Register> approval = dobj.AdminApproval();
						ses.setAttribute("ulist", approval);
						request.getRequestDispatcher("Admin_Request.jsp").forward(request, response);
					}
					else
					{
						
					}
				}
		
		
		
		
		
		
			
		//ownerview
		
		if(action.equals("submit1"))
		{
			ArrayList<Register> view1=dobj.view1();
			ses.setAttribute("ulist", view1);
			request.getRequestDispatcher("Viewimage.jsp").forward(request, response);
		}
		
		
	    //userview request page
		
		if(action.equals("submit"))
		{	
			ArrayList<Register> view=dobj.viewUser1();
			ArrayList<Register> v=dobj.sub1();
				ses.setAttribute("li", view);
				ses.setAttribute("li1", v);
				request.getRequestDispatcher("Searchimage.jsp").forward(request, response);
		}
		
		if (action.equals("search")) {
			String Email = request.getParameter("Email");
			String keyword = request.getParameter("keyword");
			ses.setAttribute("Email", Email);
			ArrayList<Register> Usersearch = dobj.search(keyword);
			ses.setAttribute("ulist", Usersearch);
			request.getRequestDispatcher("User_View.jsp").forward(request, response);
		}

		// User search & view

				if (action.equals("search")) {
					String Email = request.getParameter("Email");
					String keyword = request.getParameter("keyword");
					ses.setAttribute("Email", Email);
					ArrayList<Register> Usersearch = dobj.search(keyword);
					ses.setAttribute("ulist", Usersearch);
					request.getRequestDispatcher("User_View.jsp").forward(request, response);
				}

				// Search page

				if (action.equals("searchpage")) {
					String Email = request.getParameter("Email");
					System.out.println(Email);
					ses.setAttribute("Email", Email);
					request.getRequestDispatcher("UserSearch.jsp").forward(request, response);

				}

				// image request

				if (action.equals("imagerequestuser")) {
					String Email = request.getParameter("Email");
					ses.setAttribute("Email", Email);
					System.out.println(Email);
					String Image = request.getParameter("Image");
					
					String result = dobj.sendRequest(Image, Email);
					if (result.equals("success")) {
						request.getRequestDispatcher("UserSearch.jsp").forward(request, response);
					}

				}
		//download
				
			if(action.equals("do"))
			{	
				    ArrayList<Register> re1=dobj.down();
					ses.setAttribute("ul", re1);
					request.getRequestDispatcher("Download.jsp").forward(request, response);
			}
		
   }
}




