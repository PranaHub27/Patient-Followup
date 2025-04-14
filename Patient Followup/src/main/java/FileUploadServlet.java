
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import databasecon.Dbconnection;

@WebServlet("/FileUploadServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50)

public class FileUploadServlet extends HttpServlet {
    //private static final String SAVE_DIR="images";//this is our folder name.
	private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        //if(!fileSaveDir.exists()){
        // fileSaveDir.mkdir();
        //}
        String pid = request.getParameter("pid");
        String doid = request.getParameter("doid");
        String type = request.getParameter("type");
        String question = request.getParameter("question");
        String option1 = request.getParameter("option1");
        String option2 = request.getParameter("option2");
        String answer = request.getParameter("answer");
        String status = request.getParameter("status");
        Part part = request.getPart("file");
        String fileName = extractFileName(part);
        String savePath = "J:\\MY WEBSITES\\Gopal_Patient_Followup\\web\\images\\" + File.separator + fileName;
        File fileSaveDir = new File(savePath);
        /*if you may have more than one files with same name then you can calculate some random characters 
        and append that characters in fileName so that it will  make your each image name identical.*/
        
        part.write(savePath + File.separator);
        //out.println(id+" "+firstName+" "+lastName+" "+fileName+" "+savePath);
        /*
            You need this loop if you submitted more than one file
            for (Part part : request.getParts()) {
            String fileName = extractFileName(part);
            part.write(savePath + File.separator + fileName);
            }*/
        try {
            Connection con = Dbconnection.getConnection();
            PreparedStatement pst = con.prepareStatement("insert into cal1 values(?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1, pid);
            pst.setString(2, doid);
            pst.setString(3, type);
            pst.setString(4, question);
            pst.setString(5, option1);
            pst.setString(6, option2);
            pst.setString(7, answer);
            pst.setString(8, status);
            pst.setString(9, fileName);
            pst.setString(10, savePath);
            pst.executeUpdate();
            out.println("<center><h1>Image inserted Succesfully......</h1></center>");
            out.println("<center><a href='image_project_display.jsp'>Display</a></center>");
        } catch (Exception e) {
            out.println(e);
        }

    }

    // file name of the upload file is included in content-disposition header like this:
    //form-data; name="dataFile"; filename="PHOTO.JPG"
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
