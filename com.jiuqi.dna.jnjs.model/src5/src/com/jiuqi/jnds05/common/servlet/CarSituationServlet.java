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

import com.alibaba.fastjson.JSON;
import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.log.DNALogManager;
import com.jiuqi.dna.core.log.Logger;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.jnds05.common.bean.CarSituationIntf;
import com.jiuqi.jnds05.common.service.CarSituationService;
import com.jiuqi.jnds05.common.utils.PageModelUtil;


public class CarSituationServlet extends DNAHttpServlet {

	private static final long serialVersionUID = 1L;
	CarSituationService carservice = null;
	Map<String, List<CarSituationIntf>> cachemap = new HashMap<String, List<CarSituationIntf>>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		synchronized(this){
			doService(req, resp);	
		}
	}

	private void doService(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		carservice = CarSituationService.getInstance();
		//第N页
		String pagenumString = request.getParameter("pagenum");
		if (pagenumString == null) {
			pagenumString = "1";
		}
		int pagenum = Integer.valueOf(pagenumString);
		List<CarSituationIntf> resultlist = null;
		long start = System.currentTimeMillis();
		//下一页，第几页走缓存。其余重新查询
		if (cachemap.get("carcache") != null&&pagenum!=1) {
			resultlist = cachemap.get("carcache");
		} else {
			resultlist = carservice.GetAllCarSituations(getContext());
			cachemap.put("carcache", resultlist);
		}
		response.setCharacterEncoding("utf-8");
		List<CarSituationIntf> sublist = null;
		if (resultlist != null && resultlist.size() > 0) {
			PageModelUtil pm = new PageModelUtil(resultlist, 20);// 每页显示条数
			sublist = pm.getObjects(pagenum);// 显示第几页
		}
		PrintWriter out = null;
		String errorinfo = "";
		String code = "200";
		Logger logger = DNALogManager.getLogger("jnds/carSituation");
		try {
			response.setContentType("application/json; charset=utf-8");
			JSONObject json = new JSONObject();
			if (sublist != null && sublist.size() > 0) {
				json.put("code", code);
				json.put("successinfo", JSON.toJSON(sublist));
			}  else {
				json.put("code", "500");
				json.put("errorinfo", "");
			}
			long end = System.currentTimeMillis();
			json.put("runtime", (end - start) + "ms");
			json.put("total", resultlist.size());
			out = response.getWriter();
			out.print(json.toString());
			out.flush();
		} catch (Exception e) {
			errorinfo = e.getMessage();
			code = "500";
			e.printStackTrace();
		} finally {
			if ("200".equals(code)) {
				logger.logInfo(getContext(), "成功：" + JSON.toJSON(sublist), true);
			} else {
				logger.logError(getContext(), errorinfo, true);
			}
			if (out != null) {
				out.close();
			}
		}
	}

	private static Context getContext() {
		return AppUtil.getDefaultApp().getSystemSession().newContext(false);
	}
}
