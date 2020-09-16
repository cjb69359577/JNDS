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
import com.jiuqi.dna.jnjs.service.SgjffxService;
import com.jiuqi.dna.jnjs.util.MapToJson;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;
/**
 * 三公经费：
 * 是指“因公出国（境）费用”、
 * “公务接待费”、“公务车购置及运行费”
 * @author Administrator
 *
 */
public class  SgjffxServlet extends DNAHttpServlet{
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
            //JSONObject msg = new JSONObject();
			//Connection conn = JdbcUtils.getPool();
			long start  = System.currentTimeMillis();
			Context conn = getContext();
			Map<String,Double> mapSG = SgjffxService.getSgjffx(conn,2019);
			List<JsonMaptoObject> list= MapToJson.MapToJsonArray(mapSG);
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
			endContext(conn);
			System.out.println("共耗时："+ (System.currentTimeMillis()-start)+"毫秒");
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
