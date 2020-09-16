package com.jiuqi.jnds05.common.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.log.DNALogManager;
import com.jiuqi.dna.core.log.Logger;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.dna.core.spi.application.Session;
import com.jiuqi.jnds05.common.bean.SysConstant;
import com.jiuqi.jnds05.common.service.ImportService;

/**
 * import controller
 * 
 * @author likeshuang
 * 
 */
@MultipartConfig
public class ImportServlet extends DNAHttpServlet {

	private static final long serialVersionUID = 7689954890023652645L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doService(req, resp);
	}

	protected void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Session session = AppUtil.getDefaultApp().getSystemSession();
		ContextSPI context = null;
		if (session == null) {
			session = AppUtil.getDefaultApp().newSession(null, null);
		}
		context = session.newContext(false);
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		request.setCharacterEncoding(SysConstant.UTF8);
		response.setContentType(SysConstant.JSON_CONTENT_TYPE);
		JSONObject result = new JSONObject();
		Integer code = SysConstant.SUCCESS;
		String message = SysConstant.IMPORT_SUCCESS;
		Logger logger = DNALogManager.getLogger("jnds/import");
		try {
			long startTime = System.currentTimeMillis();
			List<FileItem> items = upload.parseRequest(request);
			InputStream inputStream = items.get(0).getInputStream();
			new ImportService().importData(context, inputStream);
			long endTime = System.currentTimeMillis();
			result.put(SysConstant.RUNTIME, (endTime - startTime) + "ms");
		} catch (Exception e) {
			message = SysConstant.IMPORT_FAIL + e.getMessage();
			code = SysConstant.FAIL;
		} finally {
			if (code == 200) {
				logger.logInfo(context, "³É¹¦£º" + message, true);
			} else {
				logger.logError(context, message, true);
			}
			result.put(SysConstant.RESPONSE_STATUS, code);
			result.put(SysConstant.RESPONSE_MESSAGE, message);
			response.getWriter().write(result.toString());
			if (context != null) {
				context.resolveTrans();
				context.dispose();
			}
		}

	}
}