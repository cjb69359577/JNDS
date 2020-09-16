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
import com.jiuqi.jnds05.common.service.ProjectExpenditureDetailsService;

public class ProjectExpenditureDetailsServlet extends DNAHttpServlet {
	private Map<String, List<Map<String, String>>> map = new HashMap<String, List<Map<String, String>>>();
	private int total = 0;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// public Context getContext(){
	// Session loginSession =
	// com.jiuqi.dna.core.spi.application.AppUtil.getDefaultApp().getSystemSession();
	// return loginSession.newContext(false);
	// }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	private void doService(HttpServletRequest request, HttpServletResponse response) {
		Context context = getContext();
		int page = (request.getParameter("pagenum") == null || "".equals(request.getParameter("pagenum"))) ? 0
				: Integer.valueOf(request.getParameter("pagenum"));
		if (null == map.get("ProjectExpenditureDetails" + page)) {
			ProjectExpenditureDetailsService service = new ProjectExpenditureDetailsService();
			Map<String, List<Map<String, String>>> result = service.queryProjectExpenditureDetails(context);
			List<Map<String, String>> list = result.get("resultList");
			total = list.size();
			int totalPage = list.size() % 50 > 0 ? list.size() / 50 + 1 : list.size() / 50;
			for (int i = 0; i < totalPage; i++) {
				map.put("ProjectExpenditureDetails" + page, list.subList(50 * page, page == totalPage - 1 ? list.size() : (page + 1) * 50));
			}
			map.put("thead", result.get("baseResult"));
		}
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		Logger logger = DNALogManager.getLogger("jnds/queryProjectExpenditureDetails");
		String errorinfo = "";
		String code = "200";
		try {
			out = response.getWriter();
			response.setContentType("application/json; charset=utf-8");
			json.put("code", code);
			json.put("errorinfo", "");
			json.put("successinfo", map.get("ProjectExpenditureDetails" + page));
			json.put("thead", map.get("thead"));
			json.put("total", total);
		} catch (Exception e) {
			errorinfo = e.getMessage();
			code = "500";
			e.printStackTrace();
			json.put("code", "500");
			json.put("errorinfo", e.getMessage());
			json.put("successinfo", "");
		} finally {
			if ("200".equals(code)) {
				logger.logInfo(context, "³É¹¦£º" + map.get("ProjectExpenditureDetails" + page), true);
			} else {
				logger.logError(context, errorinfo, true);
			}
			out.print(json.toString());
			out.flush();
			if (out != null) {
				out.close();
			}
		}
	}

	private static Context getContext() {
		return AppUtil.getDefaultApp().getSystemSession().newContext(false);
	}
}
