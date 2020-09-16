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
import com.jiuqi.dna.jnjs.entity.ClqkEntity;
import com.jiuqi.dna.jnjs.entity.XmzcEntry;
import com.jiuqi.dna.jnjs.service.ClqkService;
import com.jiuqi.dna.jnjs.service.Gdzc_wxzcService;
import com.jiuqi.dna.jnjs.service.SgjffxService;
import com.jiuqi.dna.jnjs.service.SrZcgdbService;
import com.jiuqi.dna.jnjs.service.XmzcService;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;
/**
 * 报表一键取数
 * @author Administrator
 *
 */
public class  Calculation extends DNAHttpServlet{
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
			long start  = System.currentTimeMillis();
			Context conn = getContext();
			 Map<String,Double> mapSr = SrZcgdbService.getSrService(conn, 2019);
			 Map<String,Double> mapZc =  SrZcgdbService.getZcService(conn, 2019);
			 List<ClqkEntity> sublist  = ClqkService.getClqk(conn, 2019);
			 Map<String, XmzcEntry> mapZC = XmzcService.getXmzc(conn, 2019);
			 Map<String,Double> mapQc  = Gdzc_wxzcService.getGdzc_wxzc_qc(conn, 2019);
			Map<String,Double> mapQm  = Gdzc_wxzcService.getGdzc_wxzc_qm(conn, 2019);
			Map<String,Double> mapSG = SgjffxService.getSgjffx(conn,2019);
			
			
			 JSONObject msg = new JSONObject();
				try {
					msg.put("code", 200);
					msg.put("successinfo", true);
					msg.put("runtime ", "共耗时："+ (System.currentTimeMillis()-start)+"毫秒");
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
