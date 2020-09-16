package com.jiuqi.jnds05.common.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.log.DNALogManager;
import com.jiuqi.dna.core.log.Logger;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.jnds05.common.bean.IncomeAndExpendBean;
import com.jiuqi.jnds05.common.service.IncomeAndExpendDisplayService;

/**
 * 数据展示收入支出三公经费查询接口
 * @author liangfan
 */
public class IncomeAndExpendSGDisplayServlet extends DNAHttpServlet {
	
	private static final long serialVersionUID = -7572274169418474793L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	protected void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ContextSPI context = AppUtil.getDefaultApp().getSystemSession().newContext(false);
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		JSONObject returnJson = new JSONObject();
		JSONArray successinfo = new JSONArray();
		JSONObject successinfo_json = new JSONObject();
		String errorinfo = "";
		String code = "200";
		Logger logger = DNALogManager.getLogger("jnds/incomeAndExpendSGDisplay");
		IncomeAndExpendDisplayService service = new IncomeAndExpendDisplayService();
		try {
			IncomeAndExpendBean  result =  service.getIncomeAndExpendSG(context);
			DecimalFormat df = new DecimalFormat("0.00");
				successinfo_json.put("goabroadcost", df.format(result.getGoabroadcost()));
				successinfo_json.put("officialreceptcost", df.format(result.getOfficialreceptcost()));
				successinfo_json.put("officialcarcost", df.format(result.getOfficialcarcost()));
		} catch (Exception e) {
			errorinfo = e.getMessage();
			code = "500";
			e.printStackTrace();
		} finally {
			if ("200".equals(code)) {
				logger.logInfo(context, "成功：" + successinfo_json, true);
			} else {
				logger.logError(context, errorinfo, true);
			}
			successinfo.put(successinfo_json);
			returnJson.put("successinfo", successinfo);
			returnJson.put("code", code);
			returnJson.put("errorinfo", errorinfo);
			response.getWriter().write(returnJson.toString());
			if (context != null) {
				context.resolveTrans();
				context.dispose();
			}
		}
	}
}
