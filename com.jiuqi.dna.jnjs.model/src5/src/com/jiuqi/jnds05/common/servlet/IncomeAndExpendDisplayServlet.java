package com.jiuqi.jnds05.common.servlet;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
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
import com.jiuqi.jnds05.common.bean.IncomeAndExpendBean;
import com.jiuqi.jnds05.common.service.IncomeAndExpendDisplayService;
import com.jiuqi.jnds05.common.service.IncometoexpendService;

/**
 * 数据展示收入支出查询接口
 * @author liangfan
 */
public class IncomeAndExpendDisplayServlet extends DNAHttpServlet {
	
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
		String errorinfo = "";
		String code = "200";
		Logger logger = DNALogManager.getLogger("jnds/incomeAndExpendDisplay");
		IncomeAndExpendDisplayService service = new IncomeAndExpendDisplayService();
		try {
			List<IncomeAndExpendBean>  result =  service.getIncomeAndExpendMouth(context);
			DecimalFormat df = new DecimalFormat("0.00");
			for (int i = 0; i < result.size(); i++) {
				JSONObject successinfo_json = new JSONObject();
				successinfo_json.put("mounth", result.get(i).getMounth());
				successinfo_json.put("income", df.format(result.get(i).getIncome()));
				successinfo_json.put("expend", df.format(result.get(i).getExpend()));
				successinfo.put(successinfo_json);
			}
		} catch (Exception e) {
			errorinfo = e.getMessage();
			code = "500";
			e.printStackTrace();
		} finally {
			if ("200".equals(code)) {
				logger.logInfo(context, "成功：" + successinfo, true);
			} else {
				logger.logError(context, errorinfo, true);
			}
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
