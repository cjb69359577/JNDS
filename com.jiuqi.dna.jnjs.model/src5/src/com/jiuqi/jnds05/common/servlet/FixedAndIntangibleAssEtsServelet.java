package com.jiuqi.jnds05.common.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import com.jiuqi.jnds05.common.service.FixedAndIntangibleAssEtsService;

/**
 * 
 * @author wangning02 固定和无形资产 存量情况表
 */
public class FixedAndIntangibleAssEtsServelet extends DNAHttpServlet {

	private static final long serialVersionUID = -6242464107900538777L;

	// 固定和无形资产service
	private FixedAndIntangibleAssEtsService assetsService = FixedAndIntangibleAssEtsService.getInstance();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doService(request, response);
	}

	private void doService(final HttpServletRequest request, final HttpServletResponse response)
			throws IOException, ServletException {
		Context ctx = getContext();

		String orgCode = "000223";
		long start = System.currentTimeMillis();
		List<Map<String, String>> list = assetsService.getFixAndIntangibleAssEtsList(ctx, orgCode);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		String code = "200";
		String errorinfo = "";
		Logger logger = DNALogManager.getLogger("jnds/fixedAndIntangibleAssEts");
		try {
			out = response.getWriter();
			response.setContentType("application/json; charset=utf-8");
			json.put("code", code);
			json.put("errorinfo", "");
			json.put("successinfo", JSON.toJSON(list));
			long end = System.currentTimeMillis();
			json.put("runtime", (end - start) + " ms");
			out.print(json.toString());
			out.flush();
//			System.out.println("渲染+查询总耗时：" + (System.currentTimeMillis() - start) + " ms");
		} catch (Exception e) {
			errorinfo = e.getMessage();
			code = "500";
			e.printStackTrace();
			json.put("runtime", "999");
			json.put("code", code);
			json.put("errorinfo", e.getMessage());
			json.put("successinfo", "");
			out.print(json.toString());
		} finally {
			if ("200".equals(code)) {
				logger.logInfo(ctx, "成功：" + JSON.toJSON(list), true);
			} else {
				logger.logError(ctx, errorinfo, true);
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
