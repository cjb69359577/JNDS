package com.jiuqi.jnds05.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.log.DNALogManager;
import com.jiuqi.dna.core.log.Logger;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.Session;
import com.jiuqi.jnds05.common.bean.ProjectExpenditureTop10Entity;
import com.jiuqi.jnds05.common.service.ProjectExpenditureDetailsService;

public class ProjectExpenditureTop10Servlet extends DNAHttpServlet{
	Map<String,List<ProjectExpenditureTop10Entity>> map = new HashMap<String,List<ProjectExpenditureTop10Entity>>();
	private static final long serialVersionUID = 1L;
	
//	public Context getContext(){
//        Session loginSession = com.jiuqi.dna.core.spi.application.AppUtil.getDefaultApp().getSystemSession();
//        return loginSession.newContext(false);
//	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}
	
	private void doService(HttpServletRequest request, HttpServletResponse response) {
		Context context = getContext();
		List<ProjectExpenditureTop10Entity> result= null;
		if(null ==map.get("ProjectExpenditureTop10")){
			ProjectExpenditureDetailsService service = new ProjectExpenditureDetailsService();
			result = service.queryProjectExpenditureTop10(context);
			map.put("ProjectExpenditureTop10", result);
		}else{
			result = map.get("ProjectExpenditureTop10");
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		String code = "200";
		String errorinfo = "";
		Logger logger = DNALogManager.getLogger("jnds/projectExpenditureTop10");
		try {
			out = response.getWriter();
			response.setContentType("application/json; charset=utf-8");  
			json.put("code", code);
			json.put("errorinfo", "");
			json.put("successinfo", result);
		} catch (Exception e) {
			code = "500";
			errorinfo = e.getMessage();
			e.printStackTrace();
			json.put("code", code);
			json.put("errorinfo", e.getMessage());
			json.put("successinfo", "");
		}finally{
			if ("200".equals(code)) {
				logger.logInfo(context, "³É¹¦£º" + result, true);
			} else {
				logger.logError(context, errorinfo, true);
			}
			out.print(json.toString());
            out.flush();
			if(out != null){
				out.close();
			}
		}
	}
	
	private static Context getContext() {
		return AppUtil.getDefaultApp().getSystemSession().newContext(false);
	}
}
