package com.jiuqi.dna.jnjs.Servlet;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.dna.jnjs.entity.ClqkEntity;
import com.jiuqi.dna.jnjs.service.ClqkService;
import com.jiuqi.dna.jnjs.service.MdServices;
import com.jiuqi.dna.jnjs.util.PageModel;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;
/**
 * ���������
 * 1��ͨ����ȡ��Ƭ���С�000223��λ������λ���Ϊ��000223��
 * 2019��  12��31��ǰ������Ƭ(��Ƭ���°汾)��Ӧ���ʲ���Ÿ��������ñ�������
 * ��ӳ���˱���D��ȷ��������
 * C����ʾ���п�Ƭ��Ϣ��Ӧ�ġ��ʲ����ࡱ�������ݵ�stdname�С�
 * 3��D��H�зֱ��Ӧ���п�Ƭ��Ϣ���ʲ������Ϣ��
 * 4��I�к�J�зֱ���ʾ���п�Ƭ��Ϣ��Ӧ�ġ�ʹ��״�����͡�������;��
 * �������ݵ�stdname�С�
 * ȡ������
 * ��C6���ʲ����� = ����Ƭ��λΪ��000223��λ������λ���Ϊ��000223��
 * �������ж�Ӧ�ʲ�������
 * ����STDCODE > 2030000 AND STDCODE < 2030800��OR STDCODE = 2039900������Ƭ״̬�ǣ�00,02��
 * �Ҽ���������2019��12��31�գ�����31�գ�ǰ��Ƭ���°汾�����ݶ�Ӧ��Ƭ��Ϣ���ʲ��������ơ�
 * @param conn
 * @return
 * @author zhagnwei
 *
 */
public class  ClqkServlet extends DNAHttpServlet{
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
			String pageSize = request.getParameter("pageSize");
			String pageNum = request.getParameter("pageNum");
			Context conn = getContext();
			boolean flag = MdServices.isExistGAMS_ASSETCARD_TEMP();
			if(flag){
				MdServices.insertGAMS_ASSETCARD_TEMP(conn);
			}
			
			List<ClqkEntity> sublist  = ClqkService.getClqk(conn, 2019);
			int num = sublist.size();
			PageModel pm = new PageModel(sublist, Integer.valueOf(pageSize));//ÿҳ��ʾ����
	        List<ClqkEntity> list = pm.getObjects(Integer.valueOf(pageNum));//��ʾ�ڼ�ҳ
			JSONObject msg = new JSONObject();
			try {
				msg.put("msg", list);
				msg.put("pagecount", num);
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
