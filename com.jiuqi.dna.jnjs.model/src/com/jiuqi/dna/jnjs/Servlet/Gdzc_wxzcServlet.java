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
 *�̶��������ʲ����������
 * 1���ñ��ڳ����������ݻ�ȡ��Ƭ���С�000223��λ��
 * ����λ���Ϊ��000223��2018��12��31��ǰ������Ϣ��
 * ��ĩ����ȡ��Ƭ���С�000223��λ������λ���Ϊ��000223��
 *  2019��12��31��ǰ������Ϣ��
 * 2���ڳ�������ȡ���߼�������ȡ���߼��е�λ�����̶�Ϊ����000223��λ������
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
