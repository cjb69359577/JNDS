package com.jiuqi.dna.jnjs.Servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.dna.jnjs.entity.RebuildMsgEntity;
import com.jiuqi.dna.jnjs.service.CommonService;
import com.jiuqi.dna.jnjs.service.RebuildVoucherNumService;
import com.jiuqi.dna.jnjs.util.MD_Util;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;
/**
 * ����ƾ֤��
 * ƾ֤����GL_VOUCHER���е�ƾ֤���ֶΣ�VCHRNUM��
 * �Ǵ洢ƾ֤��˳�����,Ҫ��ÿ�굥λ��Χ�ڴ˱�����ڵ������������Ϻţ�
 * ����A��λ��2019��ȵ�5�ڵ�ƾ֤����5800�ţ�
 * ��ƾ֤��Ÿ���ƾ֤��������ֱ�ֵΪ1��2��3��4��5��6��5800��
 * @author zhagnwei
 *
 */
public class  VNRecalculation extends DNAHttpServlet{
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
			long startk = System.currentTimeMillis();
			Context conn = getContext();
			List<String> unitList = RebuildVoucherNumService.getUnitList(conn);
			List<RebuildMsgEntity> rebuildMsgList  = new ArrayList<RebuildMsgEntity>();
			
			 for(int i=0 ;i<unitList.size();i++){
			       for(int year=2019;year<=2020;year++){
					 for(int period=0;period<=12;period++){
						 RebuildMsgEntity rebuild  = new RebuildMsgEntity();
						 long start = System.currentTimeMillis();
						 RebuildVoucherNumService.batchUpdateVoucherNum(conn,unitList.get(i),year,period);
						 rebuild.setAcctperiod(period);
						 rebuild.setAcctyear(year);
						 rebuild.setUnit(MD_Util.mapORG_name.get(unitList.get(i)));
						 rebuild.setRuntime(System.currentTimeMillis()-start + "����");
						 rebuildMsgList.add(rebuild);
					 }
				 }
			 }
			 //long end = System.currentTimeMillis()-start;
			/* map.put("unitid", "���е�λ");
			 map.put("acctyear", "2019��-2020��");
			 map.put("acctperiod","1��-12��");
			 map.put("runtime ", end+"����");*/
			 
			 JSONObject msg = new JSONObject();
			 try {
				msg.put("msg", rebuildMsgList);
				msg.put("runtime", "����ƾ֤������ɣ����ƺ�ʱ��"+(System.currentTimeMillis()-startk)+"����");
				response.getWriter().print(msg);
			} catch (JSONException e) {
				e.printStackTrace();
			}finally{
				response.getWriter().flush();
				response.getWriter().close();
					
			}
			//һ��ά������
			CommonService.initService(conn);
			endContext(conn);
		//	System.out.println("����ʱ��"+ end+"����");
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
