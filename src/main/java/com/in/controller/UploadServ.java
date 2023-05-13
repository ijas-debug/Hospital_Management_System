package com.in.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.in.bean.*;
import com.in.dao.Dao;

/**
 * Servlet implementation class UploadServ
 */
@WebServlet("/UploadServ")
public class UploadServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadServ() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);

		Register robj = new Register();
		String iname = null;
		String des = null;
		String imgname = null;
		String key = null;
		String image = null;

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		boolean isMultipartContent = ServletFileUpload.isMultipartContent(request);
		if (!isMultipartContent) {
			return;
		}

		FileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List<FileItem> fields = upload.parseRequest(request);
			Iterator<FileItem> it = fields.iterator();
			while (it.hasNext()) {
				FileItem fileItem = it.next();
				boolean isFormField = fileItem.isFormField();
				if (isFormField) {
					if (iname == null || des == null || key == null || imgname == null || image == null) {
						if (fileItem.getFieldName().equals("iname")) // field name from jsp
						{
							iname = fileItem.getString();
						}
						if (fileItem.getFieldName().equals("des")) // field name from jsp
						{
							des = fileItem.getString();
						}
						if (fileItem.getFieldName().equals("key")) // field name from jsp
						{
							key = fileItem.getString();
						}
						if (fileItem.getFieldName().equals("imgname")) // field name from jsp
						{
							imgname = fileItem.getString();
						}
					}
				} 
				else if (!isFormField)
				{
					if (fileItem.getFieldName().equals("image"))
					{
						image = fileItem.getName();
						if (fileItem.getSize() > 0) {
							fileItem.write(new File("C:\\Users\\IJAS\\Eclipseworkspace2\\Hospital_Management_Project_Kom\\src\\main\\webapp\\" + fileItem.getName()));
							
						}
					}
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			robj.setImgname(imgname);
			robj.setIname(iname);
			robj.setKey(key);
			robj.setDes(des);
			robj.setImage(image);

			HttpSession ses=request.getSession();
			Dao dobj = new Dao();
			String valid = dobj.sub(robj);
			ArrayList<Register> sub1=dobj.view1();
			
			if (valid.equals("success")) 
			{
				ses.setAttribute("ulist", sub1);
				request.getRequestDispatcher("Admin_Viewing.jsp").forward(request, response);
			}
		}
	}
}