
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import databasecon.AddDaysToDate;
import databasecon.Dbconnection;

@WebServlet("/FileUploadServlet1")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)

public class FileUploadServlet1 extends HttpServlet {
	// private static final String SAVE_DIR="images";//this is our folder name.
	private static final long serialVersionUID = 1L;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);
		String description = request.getParameter("description"); // Retrieves <input type="text" name="description">
		Part filePart = request.getPart("file"); // Retrieves <input type="file" name="file">
		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // MSIE fix.
		InputStream fileContent = filePart.getInputStream();
		// version1

		String savePath = contextPath + "\\images\\" + fileName;
		System.out.println(savePath);
		File file = new File(savePath);
		copyInputStreamToFile(fileContent, file);
		// InputStreamToFile1.copyInputStreamToFile(fileContent, savePath);

		// ... (do your job here)
	}

	private void copyInputStreamToFile(InputStream fileContent, File file) {
		try (FileOutputStream outputStream = new FileOutputStream(file, false)) {
			int read;
			byte[] bytes = new byte[8192];
			while ((read = fileContent.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void test(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// t(request, response);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		ServletContext servletContext = getServletContext();
		String contextPath = servletContext.getRealPath(File.separator);

		// if(!fileSaveDir.exists()){
		// fileSaveDir.mkdir();
		// }
		Part part = request.getPart("file");
		String fileName = extractFileName(part);
		String savePath = contextPath + "\\web\\images\\";
		File fileSaveDir = new File(savePath);
		for (Part part1 : request.getParts()) {
			part.write(savePath + File.separator + fileName);
		}
		part.write(savePath + File.separator);

		System.out.println("contextPath - " + contextPath);

		String status = "Waiting";
		String values = request.getParameter("values");
		String patId = request.getParameter("patId");
		String docId = request.getParameter("docId");
		String medicationDuration = request.getParameter("medicationDuration");
		String schedule = AddDaysToDate.addDaysToDate(Integer.parseInt(medicationDuration));
		String questionsArray[] = values.split(":");
		String query = "";

		try {
			Connection con = Dbconnection.getConnection();
			PreparedStatement ps = null;
			for (String s : questionsArray) {
				if (!s.equals(",")) {
					System.out.println("s - " + s);
					String questions[] = s.split(",");
					for (int i = 0; i < questions.length; i++) {
						query = "";
						questions[i].trim();
						if (questions[i].equals("Objective")) {
							query = "insert into cal1(pid,doid,type,question,option1,option2,medicationDuration,status,schedule,filename,path) values('"
									+ patId + "','" + docId + "','" + questions[i++] + "','" + questions[i++] + "','"
									+ questions[i++] + "','" + questions[i] + "','" + medicationDuration + "','"
									+ status + "','" + schedule + "','" + fileName + "','" + savePath + "')";
							System.out.println("query - " + query);
							ps = con.prepareStatement(query);
							ps.executeUpdate();
						} else if (questions[i].equals("Descriptive")) {
							query = "insert into cal1(pid,doid,type,question,medicationDuration,status,schedule,filename,path) values('"
									+ patId + "','" + docId + "','" + questions[i++] + "','" + questions[i] + "','"
									+ medicationDuration + "','" + status + "','" + schedule + "','" + fileName + "','"
									+ savePath + "')";
							System.out.println("query - " + query);
							ps = con.prepareStatement(query);
							ps.executeUpdate();
						}
						System.out.println(questions[i]);
					}
				}
			}
			// out.print("Sorry username or password error");
			// response.sendRedirect("Gopal_QQ.jsp?m1=success");

		} catch (Exception e) {
			e.printStackTrace();
		}

		/*
		 * if you may have more than one files with same name then you can calculate
		 * some random characters and append that characters in fileName so that it will
		 * make your each image name identical.
		 */

		// part.write(savePath + File.separator);

		// out.println(id+" "+firstName+" "+lastName+" "+fileName+" "+savePath);
		/*
		 * You need this loop if you submitted more than one file for (Part part :
		 * request.getParts()) { String fileName = extractFileName(part);
		 * part.write(savePath + File.separator + fileName); }
		 */
		/*
		 * try { Connection con =
		 * Dbconnection.getConnection; PreparedStatement pst =
		 * con.prepareStatement("insert into cal1 values(?,?,?,?,?,?,?,?,?,?)");
		 * 
		 * pst.setString(8, status); pst.setString(9, fileName); pst.setString(10,
		 * savePath); pst.executeUpdate();
		 * response.sendRedirect("addqq.jsp?m1=success"); } catch (Exception e) {
		 * out.println(e); }
		 */
		out.println("<center><h1>Image inserted Succesfully......</h1></center>");

	}
	/*
	 * private String getFileName(Part part) { for (String content :
	 * part.getHeader("content-disposition").split(";")) { if
	 * (content.trim().startsWith("filename")) return
	 * content.substring(content.indexOf("=") + 2, content.length() - 1); } return
	 * Constants.DEFAULT_FILENAME; }
	 */

	/*
	 * private void t(HttpServletRequest request, HttpServletResponse response) {
	 * String status = "Waiting"; String values = request.getParameter("values");
	 * String patId = request.getParameter("patId"); String docId =
	 * request.getParameter("docId"); String medicationDuration =
	 * request.getParameter("medicationDuration"); String questionsArray[] =
	 * values.split(":"); String query = ""; try { Connection con =
	 * Dbconnection.getConnection(); PreparedStatement ps = null; for (String s :
	 * questionsArray) { if (!s.equals(",")) { System.out.println("s - " + s);
	 * String questions[] = s.split(","); for (int i = 0; i < questions.length; i++)
	 * { query = ""; questions[i].trim(); if (questions[i].equals("Objective")) {
	 * query =
	 * "insert into cal1(pid,doid,type,question,option1,option2,medicationDuration) values('"
	 * + patId + "','" + docId + "','" + questions[i++] + "','" + questions[i++] +
	 * "','" + questions[i++] + "','" + questions[i] + "','" + medicationDuration +
	 * "')"; System.out.println("query - " + query); ps =
	 * con.prepareStatement(query); ps.executeUpdate(); } else if
	 * (questions[i].equals("Descriptive")) { query =
	 * "insert into cal1(pid,doid,type,question,medicationDuration) values('" +
	 * patId + "','" + docId + "','" + questions[i++] + "','" + questions[i] + "','"
	 * + medicationDuration + "')"; System.out.println("query - " + query); ps =
	 * con.prepareStatement(query); ps.executeUpdate(); }
	 * System.out.println(questions[i]); } } } //
	 * out.print("Sorry username or password error"); //
	 * response.sendRedirect("admin.jsp?m2=Failed");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); } }
	 */

	// file name of the upload file is included in content-disposition header like
	// this:
	// form-data; name="dataFile"; filename="PHOTO.JPG"
	private String extractFileName(Part part) {
		String contentDisp = part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}
}
