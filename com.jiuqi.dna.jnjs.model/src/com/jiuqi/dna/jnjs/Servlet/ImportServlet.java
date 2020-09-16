package com.jiuqi.dna.jnjs.Servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.jnjs.wz.util.ContextUtil;
import com.jiuqi.dna.jnjs.wz.util.ReadUtil;
import com.jiuqi.xlib.json.JSONObject;

/**
 * @author wangzhe01
 * @date 2020��9��8��
 * 
 */
public class ImportServlet extends DNAHttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		// ��ȡ�ļ��������������ݿ�
		System.out.println(request.getContentType());
		//InputStream in = request.getInputStream();// ��ȡ������
		long start = System.currentTimeMillis();
		Workbook workbook = null;
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=utf-8");
		try {
		    //workbook = new HSSFWorkbook(in);//2003-
			//}else if("xlsx".equals(fileType)){
			List<FileItem> items = upload.parseRequest(request);
		 try{
			 InputStream inputStream = items.get(0).getInputStream();
			 workbook = new XSSFWorkbook(inputStream);  //2007+
		 }catch (Exception e) {
			 InputStream inputStream = items.get(0).getInputStream();
			 workbook = new HSSFWorkbook(inputStream);
		 }
			
		} catch (Exception e) {
			e.printStackTrace();
			feedback("�ļ��޷���Ϊxlsx����", response);
			return;
		}
		try{
			if (workbook != null) {
				Context context1 = ContextUtil.getContext();
				ReadUtil.readCenter(context1, workbook);
			}
			feedback("���ݵ���ɹ������ƺ�ʱ��"+(System.currentTimeMillis()-start), response);
		}catch(Exception e){
			e.printStackTrace();
			feedback("�ļ�����ʧ�ܣ�", response);
		}

	}

	private void feedback(String mes, HttpServletResponse response) {
		 try {
				// ���������ʽΪUTF-8
				response.setCharacterEncoding("UTF-8");
				// ������������ΪUTF-8
				response.setContentType("text/html;charset=utf-8");
				JSONObject msg = new JSONObject();
				msg.put("msg", mes);
				response.getWriter().print(msg);
				response.getWriter().flush();
				response.getWriter().close();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				
					
			}
	}
}
