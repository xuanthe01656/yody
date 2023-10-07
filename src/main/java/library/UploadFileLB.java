package library;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.FileNameUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;

public class UploadFileLB {
	public static List<String> uploadFile(String UPLOAD_DIR, HttpServletRequest request) {
		List<String> fileNames = new ArrayList<String>();
		try {
			List<Part> parts = (List<Part>) request.getParts();
			for (Part part : parts) {
				if (part.getName().equalsIgnoreCase("pic")) {
					String fileName = getFileName(part);
					fileNames.add(fileName);
					String applicationPath = request.getServletContext().getRealPath("");
					String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
					System.out.println(basePath);
					InputStream inputStream = null;
					OutputStream outputStream = null;
					try {
						File outputFilePath = new File(basePath + fileName);
						inputStream = part.getInputStream();
						outputStream = new FileOutputStream(outputFilePath);
						int read = 0;
						final byte[] bytes = new byte[1024];
						while ((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
					} catch (Exception ex) {
						fileName = null;
					} finally {
						if (outputStream != null) {
							outputStream.close();
						}
						if (inputStream != null) {
							inputStream.close();
						}
					}
				}
			}
		} catch (Exception e) {
			fileNames = null;
		}
		return fileNames;
	}
	public static List<String> uploadFileDisplay(String UPLOAD_DIR, HttpServletRequest request) {
		List<String> fileNames = new ArrayList<String>();
		try {
			List<Part> parts = (List<Part>) request.getParts();
			for (Part part : parts) {
				if (part.getName().equalsIgnoreCase("pic1")) {
					String fileName = getFileName(part);
					fileNames.add(fileName);
					String applicationPath = request.getServletContext().getRealPath("");
					String basePath = applicationPath + File.separator + UPLOAD_DIR + File.separator;
					System.out.println(basePath);
					InputStream inputStream = null;
					OutputStream outputStream = null;
					try {
						File outputFilePath = new File(basePath + fileName);
						inputStream = part.getInputStream();
						outputStream = new FileOutputStream(outputFilePath);
						int read = 0;
						final byte[] bytes = new byte[1024];
						while ((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
					} catch (Exception ex) {
						fileName = null;
					} finally {
						if (outputStream != null) {
							outputStream.close();
						}
						if (inputStream != null) {
							inputStream.close();
						}
					}
				}
			}
		} catch (Exception e) {
			fileNames = null;
		}
		return fileNames;
	}
	
	private static String getFileName(final Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	public static String renameFile(String fileName){
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyhhmmss");
		Date date = new Date();		
		return FileNameUtils.getBaseName(fileName)+"_"+sdf.format(date)+"."+FileNameUtils.getExtension(fileName);
	}

}
