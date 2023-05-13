package com.in.dao;

import java.sql.*;
import java.util.*;
import com.in.bean.Register;

public class Dao {
	Connection con = null;
	PreparedStatement ps = null;
	Statement st = null;
	ResultSet rs = null;
	String query = null;
	Databaseconnector dobj = new Databaseconnector();

	public String regUser(Register robj) {
		try {
			con = dobj.Dbconnect();
			query = "insert into owner(Name,Username,Password,Email) values(?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, robj.getOn());
			ps.setString(2, robj.getOname());
			ps.setString(3, robj.getOpassword());
			ps.setString(4, robj.getOemail());
			int i = ps.executeUpdate();

			if (i != 0) {
				return "success";

			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
		return " ";

	}

	public String regUser1(Register robj) {
		try {
			con = dobj.Dbconnect();
			query = "insert into user(Name,Username,Password,Email) values(?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, robj.getUn());
			ps.setString(2, robj.getUname());
			ps.setString(3, robj.getUpassword());
			ps.setString(4, robj.getUemail());

			int i = ps.executeUpdate();

			if (i != 0) {
				return "success";

			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
		return " ";

	}

	public ArrayList<Register> viewUser() {
		ArrayList<Register> viewL = new ArrayList<Register>();
		try {
			query = "select * from owner";
			con = dobj.Dbconnect();
			st = con.createStatement();
			rs = st.executeQuery(query);

			while (rs.next()) {
				Register robj = new Register();
				robj.setOn(rs.getString(1));
				robj.setOname(rs.getString(2));
				robj.setOpassword(rs.getString(3));
				robj.setOemail(rs.getString(4));
				robj.setId(rs.getString(5));
				viewL.add(robj);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return viewL;
	}

	public ArrayList<Register> viewUser1() {
		ArrayList<Register> viewL = new ArrayList<Register>();
		try {
			query = "select * from user";
			con = dobj.Dbconnect();
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				Register robj = new Register();
				robj.setUn(rs.getString("Name"));
				robj.setUname(rs.getString(2));
				robj.setUpassword(rs.getString(3));
				robj.setUemail(rs.getString(4));
				robj.setUid(rs.getString(5));
				System.out.println(robj.getUn() + "555555");
				viewL.add(robj);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return viewL;
	}

	public String loginowner(Register robj) {
		String user = robj.getOname();
		String pass = robj.getOpassword();
		try {
			String query = "select * from owner where Username='" + user + "' and Password='" + pass + "'";
			con = dobj.Dbconnect();
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				String s = rs.getString("Username");
				System.out.println(s);
				String s1 = rs.getString("Password");
				System.out.println(s1);

				robj.setOname(rs.getString(1));
				robj.setOpassword(rs.getString(2));
				if ((user.equals(s)) && (pass.equals(s1))) {
					return "success";
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "invalid";
	}

	public String loginuser(Register robj) {
		String user1 = robj.getUname();
		String pass1 = robj.getUpassword();
		try {
			String query = "select * from user where Username='" + user1 + "' and Password='" + pass1 + "'";
			con = dobj.Dbconnect();
			st = con.createStatement();
			rs = st.executeQuery(query);
			while (rs.next()) {
				String a = rs.getString("username");
				String b = rs.getString("password");

				robj.setUname(rs.getString(1));
				robj.setUpassword(rs.getString(2));

				if ((user1.equals(a)) && (pass1.equals(b))) {
					return "success";
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return "invalid";
	}

//submit table insertion

	public String sub(Register robj) {
		try {
			con = dobj.Dbconnect();
			query = "insert into sub(name,keyword,description,ownername,image) values(?,?,?,?,?)";
			ps = con.prepareStatement(query);
			ps.setString(1, robj.getImgname());
			ps.setString(2, robj.getKey());
			ps.setString(3, robj.getDes());
			ps.setString(4, robj.getIname());
			ps.setString(5, robj.getImage());

			int i = ps.executeUpdate();

			if (i != 0) {
				return "success";

			}
		}

		catch (Exception e) {
			System.out.println(e);
		}
		return " ";
	}
	
	

	// Admin View

	public ArrayList<Register> AdminViews() {
		{
			ArrayList<Register> viewS = new ArrayList<Register>();
			try {

				String query = "select * from sub";
				con = dobj.Dbconnect();
				st = con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					Register robj = new Register();
					
					robj.setIname(rs.getString(1));
					robj.setKey(rs.getString(2));
					
					robj.setDes(rs.getString(3));
					;
					robj.setImage("image");
					viewS.add(robj);
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return viewS;
		}
	}
	
	
	
	
	//Admin request

		public ArrayList<Register> AdminApproval() {
			{
				ArrayList<Register> approval = new ArrayList<Register>();
				try {

					String query = "select * from userrequest";
					con = dobj.Dbconnect();
					st = con.createStatement();
					rs = st.executeQuery(query);
					while (rs.next()) {
						Register robj = new Register();
						robj.setSi_No(Integer.toString(rs.getInt(1)));
						robj.setImageName(rs.getString(2));
						robj.setImage("image");
						robj.setEmail(rs.getString(3));
						robj.setAction(rs.getString(4));
						approval.add(robj);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				return approval;
			}
		}
		

	
	// User search & view

		public ArrayList<Register> search(String Keyword) {
			ArrayList<Register> list = new ArrayList<Register>();

			try {

				String query = "select * from sub where Keyword like '%" + Keyword + "%'";
				st = con.createStatement();
				rs = st.executeQuery(query);
				while (rs.next()) {
					Register robj = new Register();
					robj.setFid(rs.getString("id")); 
					robj.setDes(rs.getString("description"));
					robj.setImgname(rs.getString("name"));
					robj.setAction(rs.getString(""));
					
					 
					 
					list.add(robj);
				}

			} catch (Exception e) {
				System.out.println(e);
			}
			return list;
		}
		
		// user request

		public ArrayList<Register> userRequest(String Email) {
			{
				ArrayList<Register> approval = new ArrayList<Register>();
				try {

					String query = "select * from userrequest where Email='" + Email + "'";
					con = dobj.Dbconnect();
					st = con.createStatement();
					rs = st.executeQuery(query);
					while (rs.next()) {
						Register robj = new Register();
						robj.setSi_No(Integer.toString(rs.getInt(1)));
						robj.setImageName(rs.getString(2));
						robj.setImage("image");
						robj.setEmail(rs.getString(3));
						robj.setAction(rs.getString(4));
						System.out.println(robj);
						approval.add(robj);
					}
				} catch (Exception e) {
					System.out.println(e);
				}
				return approval;
			}
		}

		// request

		public String sendRequest(String image, String email) {
			try {
				String query = "insert into  userrequest (Imagename,Email) values('" + image + "','" + email + "')";
				ps = con.prepareStatement(query);

				int i = ps.executeUpdate();

				if (i != 0) {
					return "success";

				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return "";
		}

		

		public String approval(String si_No) {
			try {
				con = dobj.Dbconnect();
				String query = "update userrequest set Status='Approved' where Si_No='" + si_No + "'";
				ps = con.prepareStatement(query);
				int i = ps.executeUpdate();

				if (i != 0) {
					String q1 = "insert into download(select* from userrequest where Si_No='" + si_No + "')";
					PreparedStatement ps1 = con.prepareStatement(q1);
					if (ps1.executeUpdate() != 0) {
						return "success";
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			return "";
		}
		

		// user download

				public ArrayList<Register> download(String Email) {
					ArrayList<Register> down = new ArrayList<Register>();
					try {
						String query = "select * from download where Email='"+Email+"'";
						con = dobj.Dbconnect();
						st = con.createStatement();
						rs = st.executeQuery(query);

						while (rs.next()) {
							Register robj = new Register();
							robj.setSi_No(Integer.toString(rs.getInt(1)));
							robj.setImageName(rs.getString(2));
							//robj.setImage(rs.getString("Image"));
							down.add(robj);
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					return down;
				}
				
				
				
				
				
				//used this for request table + view image

				public ArrayList<Register> sub1() {
					ArrayList<Register> viewL = new ArrayList<Register>();
					try {
						query = "select * from sub";
						con = dobj.Dbconnect();
						st = con.createStatement();
						rs = st.executeQuery(query);

						while (rs.next()) {
							Register robj1 = new Register();

							robj1.setImgname(rs.getString(1));
							robj1.setKey(rs.getString(2));
							robj1.setDes(rs.getString(3));
							robj1.setIname(rs.getString(4));
							robj1.setImage(rs.getString(5));
							System.out.println("555555");
							viewL.add(robj1);
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					return viewL;
				}

			//view owner

				public ArrayList<Register> view1() {
					{
						ArrayList<Register> viewS = new ArrayList<Register>();
						try {
							System.out.println("here");
							String query = "select * from sub";
							con = dobj.Dbconnect();
							st = con.createStatement();
							rs = st.executeQuery(query);
							while (rs.next()) {
								Register robj = new Register();

								robj.setKey(rs.getString("keyword"));
								robj.setImgname(rs.getString("name"));
								robj.setDes(rs.getString("description"));
								robj.setImgname(rs.getString("image"));
								viewS.add(robj);
							}
						} catch (Exception e) {
							System.out.println(e);
						}
						return viewS;
					}
				}

			//User Searchpage
				/*
				 * public ArrayList<Register> view(){ { ArrayList<Register> viewS=new
				 * ArrayList<Register>(); try { System.out.println("here"); String query =
				 * "select * from sub"; con = dobj.Dbconnect(); st = con.createStatement(); rs =
				 * st.executeQuery(query); while (rs.next()) { Register robj=new Register();
				 * robj.setFid(rs.getString("id")); robj.setImgname(rs.getString("name"));
				 * robj.setDes(rs.getString("description")); viewS.add(robj); } } catch
				 * (Exception e) { System.out.println(e); } return viewS; } }
				 */

			//request

				public String req(Register robj) {
					try {
						System.out.println("here");
						String query = "insert into request(username,id,name,description,image,status) values(?,?,?,?,?,?)";
						con = dobj.Dbconnect();
						ps = con.prepareStatement(query);
						ps.setString(1, robj.getUn());
						ps.setString(2, robj.getFid());
						ps.setString(3, robj.getImgname());
						ps.setString(4, robj.getDes());
						ps.setString(5, robj.getImage());
						ps.setString(6, "0");

						int i = ps.executeUpdate();

						if (i != 0) {
							return "success";
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					return "";

				}

			//request view

				public ArrayList<Register> req1() {
					{
						ArrayList<Register> viewS = new ArrayList<Register>();
						try {
							System.out.println("here");
							String query = "select * from request";
							con = dobj.Dbconnect();
							st = con.createStatement();
							rs = st.executeQuery(query);

							while (rs.next()) {
								Register robj = new Register();
								robj.setUn(rs.getString("username"));
								robj.setFid(rs.getString("id"));
								robj.setImgname(rs.getString("image"));
								robj.setDes(rs.getString("description"));
								robj.setImage(rs.getString("image"));
								viewS.add(robj);
							}
						} catch (Exception e) {
							System.out.println(e);
						}
						return viewS;
					}
				}

			//approval

				public String approval(Register robj) {
					try {

						String id = robj.getFid();
						con = dobj.Dbconnect();

						System.out.println("here");
						String query = "update request set status = '1' where id = '" + id + "'";
						ps = con.prepareStatement(query);
						// ps.setString(1, robj.getStatus());
						int i = ps.executeUpdate();
						System.out.println("here");
						if (i != 0) {
							return "success";

						}
					}

					catch (Exception e) {
						System.out.println(e);
					}
					return " ";
				}

			//reject

				public String appro(Register robj) {
					try {

						String id = robj.getFid();
						con = dobj.Dbconnect();

						System.out.println("here");
						String query = "update request set status = '0' where id = '" + id + "'";
						ps = con.prepareStatement(query);
						// ps.setString(1, robj.getStatus());
						int i = ps.executeUpdate();
						System.out.println("here");
						if (i != 0) {
							return "success";

						}
					}

					catch (Exception e) {
						System.out.println(e);
					}
					return " ";
				}

			//download

				public ArrayList<Register> down() {
					ArrayList<Register> viewL = new ArrayList<Register>();
					try {
						query = "select id,name,description,image from request where status='1'";
						con = dobj.Dbconnect();
						st = con.createStatement();
						rs = st.executeQuery(query);

						while (rs.next()) {
							Register robj = new Register();
							robj.setFid(rs.getString("id"));
							robj.setImgname(rs.getString("name"));
							robj.setDes(rs.getString("description"));
							robj.setImgname(rs.getString("image"));
							viewL.add(robj);
						}
					} catch (Exception e) {
						System.out.println(e);
					}
					return viewL;
				}
}
