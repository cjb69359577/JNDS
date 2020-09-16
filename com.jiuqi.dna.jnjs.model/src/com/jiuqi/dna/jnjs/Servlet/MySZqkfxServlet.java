package com.jiuqi.dna.jnjs.Servlet;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.dna.jnjs.service.MySZqkfxService;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;
/**
 * 收支分析-每月收支情况分析
 * 具体解释为：展示指定单位：“000223单位”
 * （单位编号为：000223）
 * 2019年度1至12月份的收入及支出情况。
 * @author Administrator
 *
 */
public class  MySZqkfxServlet extends DNAHttpServlet{
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
			Map<String,Double> mapSR = MySZqkfxService.getSZqkfxBySR(conn,2019);
			Map<String,Double> mapZC = MySZqkfxService.getSZqkfxByZC(conn,2019);
			double[] doubleSr = new double[12];
			double[] doubleZc = new double[12];
			for(int i=0;i<12;i++){
				doubleSr[i]=mapSR.get((i+1)+"");
				doubleZc[i]=mapZC.get((i+1)+"");
			}
			 JSONObject msg = new JSONObject();
			 try {
				msg.put("mapSr", doubleSr);
				msg.put("mapZc", doubleZc);
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
