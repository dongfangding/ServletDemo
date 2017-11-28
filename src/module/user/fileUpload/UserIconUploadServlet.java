package module.user.fileUpload;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UserIconUpload
 */
@WebServlet("/userIconUploadServlet.do")
public class UserIconUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Check that we have a file upload request
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			// Create a factory for disk-based file items
			DiskFileItemFactory factory = new DiskFileItemFactory();
			// Configure a repository (to ensure a secure temp location is used)
			String realPicPath = request.getServletContext().getRealPath("/pic");
			File repository = new File(realPicPath + "/temp");
			if (!repository.exists()) {
				repository.mkdirs();
			}
			factory.setRepository(repository);
			// Create a new file upload handler
			ServletFileUpload upload = new ServletFileUpload(factory);
			// Set overall request size constraint 10MB
			upload.setSizeMax(1024 * 1024 * 10);
			try {
				List<FileItem> items = upload.parseRequest(request);
				// Process the uploaded items
				Iterator<FileItem> iter = items.iterator();
				while (iter.hasNext()) {
					FileItem item = iter.next();
					// Process a regular form field
					if (!item.isFormField()) {
						String fileName = item.getName();
						// userIcon.jsp界面引用了当前用户头像的名称为这个，每次上传都覆盖就行
						File file = new File(realPicPath + "/userIcon.png");
						System.out.println(file.getAbsolutePath());
						item.write(file);
					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				// 返回首页
				request.getRequestDispatcher("/userIndexJspServlet.do").forward(request, response);
			}
		}
	}

}
