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
 * �ʲ�ʹ��״̬ͳ��
 * �������Ϊ��
 *  չʾ���̶��������ʲ�������������е�5����ĩ��������L5������P5������T5������X5����Ϣ��
 *  �������ʲ�ʹ��״����
 *  �����á���
 *  ������衱��
 *  �����á���
 *  �������ã������ϡ�����ȣ���չʾ��
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
			System.out.println("����ʱ��"+ (System.currentTimeMillis()-start)+"����");
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
	  * ���������Ļ���
	  * 
	  * @return
	  */
	 private static Context getContext() {
	     return AppUtil.getDefaultApp().getSystemSession().newContext(false);
	 }
	 
	 /**
	  * ����������
	  * 
	  * @param context
	  */
	 private static void endContext(Context context) {
		  if (context != null) {
		     ((ContextSPI) context).dispose();
		  }
	 }
}

