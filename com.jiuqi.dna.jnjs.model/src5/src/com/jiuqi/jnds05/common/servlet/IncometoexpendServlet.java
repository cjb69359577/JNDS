package com.jiuqi.jnds05.common.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Map;

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
import com.jiuqi.jnds05.common.service.IncometoexpendService;

/**
 * 收入支出接口
 * 
 * @author sqh
 *
 */
public class IncometoexpendServlet extends DNAHttpServlet {

	private static final long serialVersionUID = -4598652610087848106L;

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
		JSONObject successinfo_json = new JSONObject();
		JSONArray successinfo = new JSONArray();
		String errorinfo = "";
		String code = "200";
		IncometoexpendService service = new IncometoexpendService();
		DecimalFormat df = new DecimalFormat("0.00");
		Logger logger = DNALogManager.getLogger("jnds/incomeToExpend");
		try {
			// 获取开始时间
			long startTime = System.currentTimeMillis(); 
			Map<String, Object> map = service.getIncometoexpendData(context);
			JSONArray json = (JSONArray) map.get("result");
			double srhj = (Double) map.get("srhj");
			double zchj = (Double) map.get("zchj");
			// 获取结束时间
			long endTime = System.currentTimeMillis(); 
			successinfo_json.put("tabletitle", "srzcgdb");
			successinfo_json.put("values", json);
			successinfo_json.put("srhj", df.format(srhj));
			successinfo_json.put("zchj", df.format(zchj));
			successinfo_json.put("runtime", (endTime - startTime) + "ms");
		} catch (Exception e) {
			code = "500";
			errorinfo = e.getMessage();
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
