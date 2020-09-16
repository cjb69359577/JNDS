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
import com.jiuqi.dna.jnjs.service.XmzcJdService;
import com.jiuqi.dna.jnjs.util.MapToJson;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;
/**
 * ��Ŀ֧��-��Ŀ֧������ǰ10
 * ����ȡ��ĿA֧�����߼�Ϊ��
 * ƾ֤����GL_VOUCHER�������Ϊ2019�꣬��λΪ��000223��λ������λ���Ϊ��000223��,
 * �ڼ�Ϊ1��12��ƾ֤������ƾ֤�ӱ�GL_VOUCHERITEM���п�Ŀ�����ԣ���Ŀ�������ݵ�STDCODE�ֶΣ�
 * ��720102����730102����740102�� ��750102�� ��790102����ͷ���ҹ����ĸ�����ϱ�GL_ASSISTCOMB��
 * ����ĿΪA�����ݣ��䡰�跽���ֶκϼ�ֵ��ȥ���������ֶκϼ�ֵ��
 * @author zhagnwei
 *
 */
public class  XmzcJdServlet extends DNAHttpServlet{
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
			long start  = System.currentTimeMillis();
			Context conn = getContext();
			Map<String,Double> mapZC = XmzcJdService.getXmzc(conn,2019);
			JSONObject msg = new JSONObject();
			List<JsonMaptoObject> list= MapToJson.MapToJsonArray(mapZC);
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
			System.out.println("����ʱ��"+ (System.currentTimeMillis()-start)+"����");
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
