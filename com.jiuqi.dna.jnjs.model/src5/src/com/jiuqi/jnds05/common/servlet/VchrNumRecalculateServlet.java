package com.jiuqi.jnds05.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.cxf.common.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jiuqi.dna.core.log.DNALogManager;
import com.jiuqi.dna.core.log.Logger;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.dna.core.spi.application.Session;
import com.jiuqi.jnds05.common.dao.VchrNumRecalculateDao;

@SuppressWarnings({ "restriction", "serial" })
public class VchrNumRecalculateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doService(req, resp);
	}

	protected void doService(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		// 指定允许其他域名访问
		resp.setHeader("Access-Control-Allow-Origin", "*");
		Session session = AppUtil.getDefaultApp().getSystemSession();
		ContextSPI context = null;
		if (session == null) {
			session = AppUtil.getDefaultApp().newSession(null, null);
		}
		context = session.newContext(false);
		VchrNumRecalculateDao vchrNumRecalculateDao = new VchrNumRecalculateDao();
		// 获取凭证不连号的数据记录
		List<Map<String, Object>> vchrNumNoSustainData;
		JSONArray succJsonArray = new JSONArray();
		String errStr = "";
		Logger logger = DNALogManager.getLogger("jnds/vchrNumRecalculate");
		try {
			vchrNumNoSustainData = vchrNumRecalculateDao.queryVchrNumNoSustainData(context);
			// 更新凭证不连号数据
			List<Map<String, Object>> vchrNumRecalculate = vchrNumRecalculateDao.updateVchrNumRecalculate(context,
					vchrNumNoSustainData);
			if (vchrNumRecalculate != null && vchrNumRecalculate.size() > 0) {
				for (Map<String, Object> map : vchrNumRecalculate) {
					JSONObject endjo = new JSONObject();
					endjo.put("acctyear", Integer.valueOf(map.get("acctyear").toString()));
					endjo.put("unitcode", map.get("unitCode").toString());
					endjo.put("unit", map.get("unitName").toString());
					endjo.put("acctperiod", Integer.valueOf(map.get("acctperiod").toString()));
					endjo.put("runtime", map.get("runtime").toString());
					succJsonArray.put(endjo);
				}
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
			errStr = e1.toString();
		}
		JSONObject returnJo = new JSONObject();
		if (!StringUtils.isEmpty(errStr) && !"".equalsIgnoreCase(errStr)) {
			returnJo.put("code", 500);
			returnJo.put("successinfo", "");
			returnJo.put("errorinfo", errStr);
			logger.logError(context, errStr, true);
		} else {
			returnJo.put("code", 200);
			returnJo.put("successinfo", succJsonArray);
			returnJo.put("errorinfo", "");
			logger.logInfo(context, "成功：" + succJsonArray, true);
		}
		resp.setHeader("Content-type", "application/json;charset=UTF-8");
		PrintWriter writer = resp.getWriter();
		writer.print(returnJo.toString());
		writer.flush();
		writer.close();
	}
}
