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
import com.jiuqi.dna.jnjs.entity.SrzcgdbEntity;
import com.jiuqi.dna.jnjs.service.SrZcgdbService;
import com.jiuqi.dna.jnjs.util.MapToJson;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;
/**
 * ����֧���̶���
 * A-C����Ҫ����ȡ2019��ȡ�000223��λ����
 * ��λ���Ϊ��000223��
 * 1��12��ָ����Ŀ��ص�ƾ֤���ݵĽ����Ϣ��
 * �Ӷ����ֳ�����λ����ȵ��������
 * @author zhangwei
 *
 */
public class  SrZcgdbServlet extends DNAHttpServlet{
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
			//long start  = System.currentTimeMillis();
			Context conn = getContext();
			 Map<String,Double> mapSr = SrZcgdbService.getSrService(conn, 2019);
			 Map<String,Double> mapZc =  SrZcgdbService.getZcService(conn, 2019);
			 List<SrzcgdbEntity> map = MapToJson.mapToObject(mapSr,mapZc);
			 JSONObject msg = new JSONObject();
			try {
				msg.put("msg", map);
				response.getWriter().print(msg);
			} catch (JSONException e) {
				e.printStackTrace();
			}finally{
				response.getWriter().flush();
				response.getWriter().close();
					
			}
			endContext(conn);
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
