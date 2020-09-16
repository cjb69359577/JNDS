package com.jiuqi.dna.jnjs.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.dna.jnjs.entity.JsonMaptoObject;
import com.jiuqi.dna.jnjs.service.QueryAssetUsageStatisticsService;
import com.jiuqi.dna.jnjs.util.MapToJson;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;

/**
 * 资产使用状态统计
 * 具体解释为：
 *  展示《固定和无形资产存量情况表》表中第5行期末账面数【L5】、【P5】、【T5】、【X5】信息，
 *  即按照资产使用状况：
 *  “在用”、
 *  “出租借”、
 *  “闲置”、
 *  “待处置（待报废、毁损等）”展示。
 * @author Fengjs
 *
 */

public class QueryAssetUsageStatisticsServlet extends DNAHttpServlet{
	ConcurrentLinkedQueue<JSONObject> queue = new ConcurrentLinkedQueue<JSONObject>();
	final int size = 100;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 */

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doService(request, response);
	}

	private void doService(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=utf-8");
			Context conn = getContext();
			Map<String,Double> map = QueryAssetUsageStatisticsService.QueryAssetUsageStatistics(conn);
			long start = System.currentTimeMillis();
			endContext(conn);
			System.out.println("共耗时："+ (System.currentTimeMillis()-start)+"毫秒");
			List<JsonMaptoObject> list= MapToJson.MapToJsonArray(map);
			 JSONObject msg = new JSONObject();
				try {
					msg.put("msg", list);
					response.getWriter().print(msg);
				} catch (JSONException e) {
					e.printStackTrace();
				}finally{
					response.getWriter().flush();
					response.getWriter().close();
		      }
	}
	
	/**
	  * 创建上下文环境
	  * 
	  * @return
	  */
	 private static Context getContext() {
	     return AppUtil.getDefaultApp().getSystemSession().newContext(false);
	 }
	 
	 /**
	  * 销毁上下文
	  * 
	  * @param context
	  */
	 private static void endContext(Context context) {
		  if (context != null) {
		     ((ContextSPI) context).dispose();
		  }
	 }
}

