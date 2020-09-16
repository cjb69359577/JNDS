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
import com.jiuqi.dna.jnjs.entity.Gdzc_wxzcEntity;
import com.jiuqi.dna.jnjs.service.Gdzc_wxzcService;
import com.jiuqi.dna.jnjs.util.MapToJson;
import com.jiuqi.xlib.json.JSONObject;
/**
 *固定和无形资产存量情况表
 * 1、该表期初账面数根据获取卡片表中“000223单位”
 * （单位编号为：000223）2018年12月31日前存量信息；
 * 期末数获取卡片表中“000223单位”（单位编号为：000223）
 *  2019年12月31日前存量信息。
 * 2、期初账面数取数逻辑解析（取数逻辑中单位条件固定为：“000223单位”）：
 * @param conn
 * @return
 * @author zhagnwei
 *
 */
public class  Gdzc_wxzcServlet extends DNAHttpServlet{
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
			Map<String,Double> mapQc  = Gdzc_wxzcService.getGdzc_wxzc_qc(conn, 2019);
			Map<String,Double> mapQm  = Gdzc_wxzcService.getGdzc_wxzc_qm(conn, 2019);
			try {
			List<Gdzc_wxzcEntity> list = MapToJson.getGdzc_wxzcEntity(mapQc,mapQm);
			JSONObject msg = new JSONObject();
				msg.put("msg", list);
				response.getWriter().print(msg);
			} catch (Exception e) {
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
