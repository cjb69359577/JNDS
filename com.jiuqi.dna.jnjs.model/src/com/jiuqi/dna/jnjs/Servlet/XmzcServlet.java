package com.jiuqi.dna.jnjs.Servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentLinkedQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.dna.jnjs.entity.XmzcEntry;
import com.jiuqi.dna.jnjs.service.XmzcService;
import com.jiuqi.dna.jnjs.util.PageModel_Xm;
import com.jiuqi.xlib.json.JSONException;
import com.jiuqi.xlib.json.JSONObject;
/**
 * 每月收支情况分析
 * @author zhagnwei
 *
 */
public class  XmzcServlet extends DNAHttpServlet{
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
		// JSONObject msg = new JSONObject();
		// Connection conn = JdbcUtils.getPool();
		String pageSize = request.getParameter("pageSize");
		String pageNum = request.getParameter("pageNum");
		long start = System.currentTimeMillis();
		Context conn = getContext();
		Map<String, XmzcEntry> mapZC = XmzcService.getXmzc(conn, 2019);
		endContext(conn);
		List<XmzcEntry> values = new ArrayList<XmzcEntry>();
		for(Entry<String, XmzcEntry> entry : mapZC.entrySet()){
			values.add(entry.getValue());
        }
		
		//Collection<XmzcEntry> values = mapZC.values();
		for (XmzcEntry xmzcEntry : values) {
			xmzcEntry.setGzflzcxj(new BigDecimal(xmzcEntry.getJe30101()==null?0:xmzcEntry.getJe30101()).add(
							new BigDecimal(xmzcEntry.getJe30102()==null?0:xmzcEntry.getJe30102())).add(
							new BigDecimal(xmzcEntry.getJe30103()==null?0:xmzcEntry.getJe30103())).add(
							new BigDecimal(xmzcEntry.getJe30106()==null?0:xmzcEntry.getJe30106())).add(
							new BigDecimal(xmzcEntry.getJe30107()==null?0:xmzcEntry.getJe30107())).add(
							new BigDecimal(xmzcEntry.getJe30108()==null?0:xmzcEntry.getJe30108())).add(
							new BigDecimal(xmzcEntry.getJe30109()==null?0:xmzcEntry.getJe30109())).add(
							new BigDecimal(xmzcEntry.getJe30110()==null?0:xmzcEntry.getJe30110())).add(
							new BigDecimal(xmzcEntry.getJe30111()==null?0:xmzcEntry.getJe30111())).add(
							new BigDecimal(xmzcEntry.getJe30112()==null?0:xmzcEntry.getJe30112())).add(
							new BigDecimal(xmzcEntry.getJe30113()==null?0:xmzcEntry.getJe30113())).add(
							new BigDecimal(xmzcEntry.getJe30114()==null?0:xmzcEntry.getJe30114())).add(
							new BigDecimal(xmzcEntry.getJe30199()==null?0:xmzcEntry.getJe30199())).doubleValue());
			xmzcEntry.setSphfwzcxj(new BigDecimal(xmzcEntry.getJe30201()==null?0:xmzcEntry.getJe30201()).add(
							new BigDecimal(xmzcEntry.getJe30202()==null?0:xmzcEntry.getJe30202())).add(
							new BigDecimal(xmzcEntry.getJe30203()==null?0:xmzcEntry.getJe30203())).add(
							new BigDecimal(xmzcEntry.getJe30204()==null?0:xmzcEntry.getJe30204())).add(
							new BigDecimal(xmzcEntry.getJe30205()==null?0:xmzcEntry.getJe30205())).add(
							new BigDecimal(xmzcEntry.getJe30206()==null?0:xmzcEntry.getJe30206())).add(
							new BigDecimal(xmzcEntry.getJe30207()==null?0:xmzcEntry.getJe30207())).add(
							new BigDecimal(xmzcEntry.getJe30208()==null?0:xmzcEntry.getJe30208())).add(
							new BigDecimal(xmzcEntry.getJe30209()==null?0:xmzcEntry.getJe30209())).add(
							new BigDecimal(xmzcEntry.getJe30211()==null?0:xmzcEntry.getJe30211())).add(
							new BigDecimal(xmzcEntry.getJe30212()==null?0:xmzcEntry.getJe30212())).add(
							new BigDecimal(xmzcEntry.getJe30213()==null?0:xmzcEntry.getJe30213())).add(
							new BigDecimal(xmzcEntry.getJe30214()==null?0:xmzcEntry.getJe30214())).add(
							new BigDecimal(xmzcEntry.getJe30215()==null?0:xmzcEntry.getJe30215())).add(
							new BigDecimal(xmzcEntry.getJe30216()==null?0:xmzcEntry.getJe30216())).add(
							new BigDecimal(xmzcEntry.getJe30217()==null?0:xmzcEntry.getJe30217())).add(
							new BigDecimal(xmzcEntry.getJe30218()==null?0:xmzcEntry.getJe30218())).add(
							new BigDecimal(xmzcEntry.getJe30224()==null?0:xmzcEntry.getJe30224())).add(
							new BigDecimal(xmzcEntry.getJe30225()==null?0:xmzcEntry.getJe30225())).add(
							new BigDecimal(xmzcEntry.getJe30226()==null?0:xmzcEntry.getJe30226())).add(
							new BigDecimal(xmzcEntry.getJe30227()==null?0:xmzcEntry.getJe30227())).add(
							new BigDecimal(xmzcEntry.getJe30228()==null?0:xmzcEntry.getJe30228())).add(
							new BigDecimal(xmzcEntry.getJe30229()==null?0:xmzcEntry.getJe30229())).add(
							new BigDecimal(xmzcEntry.getJe30231()==null?0:xmzcEntry.getJe30231())).add(
							new BigDecimal(xmzcEntry.getJe30239()==null?0:xmzcEntry.getJe30239())).add(
							new BigDecimal(xmzcEntry.getJe30240()==null?0:xmzcEntry.getJe30240())).add(
							new BigDecimal(xmzcEntry.getJe30299()==null?0:xmzcEntry.getJe30299())).doubleValue());
			xmzcEntry.setDgrhjtdbzxj(new BigDecimal(xmzcEntry.getJe30301()==null?0:xmzcEntry.getJe30301()).add(
							new BigDecimal(xmzcEntry.getJe30302()==null?0:xmzcEntry.getJe30302())).add(
							new BigDecimal(xmzcEntry.getJe30303()==null?0:xmzcEntry.getJe30303())).add(
							new BigDecimal(xmzcEntry.getJe30304()==null?0:xmzcEntry.getJe30304())).add(
							new BigDecimal(xmzcEntry.getJe30305()==null?0:xmzcEntry.getJe30305())).add(
							new BigDecimal(xmzcEntry.getJe30306()==null?0:xmzcEntry.getJe30306())).add(
							new BigDecimal(xmzcEntry.getJe30307()==null?0:xmzcEntry.getJe30307())).add(
							new BigDecimal(xmzcEntry.getJe30308()==null?0:xmzcEntry.getJe30308())).add(
							new BigDecimal(xmzcEntry.getJe30309()==null?0:xmzcEntry.getJe30309())).add(
							new BigDecimal(xmzcEntry.getJe30310()==null?0:xmzcEntry.getJe30310())).add(
							new BigDecimal(xmzcEntry.getJe30399()==null?0:xmzcEntry.getJe30399())).doubleValue());
			
			xmzcEntry.setZwlxjfyzcxj(new BigDecimal(xmzcEntry.getJe30701()==null?0:xmzcEntry.getJe30701()).add(
							new BigDecimal(xmzcEntry.getJe30702()==null?0:xmzcEntry.getJe30702())).add(
							new BigDecimal(xmzcEntry.getJe30703()==null?0:xmzcEntry.getJe30703())).add(
							new BigDecimal(xmzcEntry.getJe30704()==null?0:xmzcEntry.getJe30704())).doubleValue());
			
			xmzcEntry.setZbxzcjbjsxj(new BigDecimal(xmzcEntry.getJe30901()==null?0:xmzcEntry.getJe30901()).add(
							new BigDecimal(xmzcEntry.getJe30902()==null?0:xmzcEntry.getJe30902())).add(
							new BigDecimal(xmzcEntry.getJe30903()==null?0:xmzcEntry.getJe30903())).add(
							new BigDecimal(xmzcEntry.getJe30905()==null?0:xmzcEntry.getJe30905())).add(
							new BigDecimal(xmzcEntry.getJe30906()==null?0:xmzcEntry.getJe30906())).add(
							new BigDecimal(xmzcEntry.getJe30907()==null?0:xmzcEntry.getJe30907())).add(
							new BigDecimal(xmzcEntry.getJe30908()==null?0:xmzcEntry.getJe30908())).add(
							new BigDecimal(xmzcEntry.getJe30913()==null?0:xmzcEntry.getJe30913())).add(
							new BigDecimal(xmzcEntry.getJe30919()==null?0:xmzcEntry.getJe30919())).add(
							new BigDecimal(xmzcEntry.getJe30921()==null?0:xmzcEntry.getJe30921())).add(
							new BigDecimal(xmzcEntry.getJe30922()==null?0:xmzcEntry.getJe30922())).add(
							new BigDecimal(xmzcEntry.getJe30999()==null?0:xmzcEntry.getJe30999())).doubleValue());
			
			xmzcEntry.setZbxzcxj(new BigDecimal(xmzcEntry.getJe31001()==null?0:xmzcEntry.getJe31001()).add(
							new BigDecimal(xmzcEntry.getJe31002()==null?0:xmzcEntry.getJe31002())).add(
							new BigDecimal(xmzcEntry.getJe31003()==null?0:xmzcEntry.getJe31003())).add(
							new BigDecimal(xmzcEntry.getJe31005()==null?0:xmzcEntry.getJe31005())).add(
							new BigDecimal(xmzcEntry.getJe31006()==null?0:xmzcEntry.getJe31006())).add(
							new BigDecimal(xmzcEntry.getJe31007()==null?0:xmzcEntry.getJe31007())).add(
							new BigDecimal(xmzcEntry.getJe31008()==null?0:xmzcEntry.getJe31008())).add(
							new BigDecimal(xmzcEntry.getJe31009()==null?0:xmzcEntry.getJe31009())).add(
							new BigDecimal(xmzcEntry.getJe31010()==null?0:xmzcEntry.getJe31010())).add(
							new BigDecimal(xmzcEntry.getJe31011()==null?0:xmzcEntry.getJe31011())).add(
							new BigDecimal(xmzcEntry.getJe31012()==null?0:xmzcEntry.getJe31012())).add(
							new BigDecimal(xmzcEntry.getJe31013()==null?0:xmzcEntry.getJe31013())).add(
							new BigDecimal(xmzcEntry.getJe31019()==null?0:xmzcEntry.getJe31019())).add(
							new BigDecimal(xmzcEntry.getJe31021()==null?0:xmzcEntry.getJe31021())).add(
							new BigDecimal(xmzcEntry.getJe31022()==null?0:xmzcEntry.getJe31022())).add(
							new BigDecimal(xmzcEntry.getJe31099()==null?0:xmzcEntry.getJe31099())).doubleValue());
			xmzcEntry.setDqybzjbxsxj(new BigDecimal(xmzcEntry.getJe31101()==null?0:xmzcEntry.getJe31101()).add(
							new BigDecimal(xmzcEntry.getJe31199()==null?0:xmzcEntry.getJe31199())).doubleValue());
			
			xmzcEntry.setDqybzxj(new BigDecimal(xmzcEntry.getJe31201()==null?0:xmzcEntry.getJe31201()).add(
							new BigDecimal(xmzcEntry.getJe31203()==null?0:xmzcEntry.getJe31203())).add(
							new BigDecimal(xmzcEntry.getJe31204()==null?0:xmzcEntry.getJe31204())).add(
							new BigDecimal(xmzcEntry.getJe31205()==null?0:xmzcEntry.getJe31205())).add(
							new BigDecimal(xmzcEntry.getJe31299()==null?0:xmzcEntry.getJe31299())).doubleValue());
			
			xmzcEntry.setDshbzjjbzxj(new BigDecimal(xmzcEntry.getJe31302()==null?0:xmzcEntry.getJe31302()).add(
							new BigDecimal(xmzcEntry.getJe31303()==null?0:xmzcEntry.getJe31303())).doubleValue());
			xmzcEntry.setHj(new BigDecimal(xmzcEntry.getGzflzcxj())
								.add(new BigDecimal(xmzcEntry.getSphfwzcxj()))
								.add(new BigDecimal(xmzcEntry.getDgrhjtdbzxj()))
								.add(new BigDecimal(xmzcEntry.getZwlxjfyzcxj()))
								.add(new BigDecimal(xmzcEntry.getZbxzcjbjsxj()))
								.add(new BigDecimal(xmzcEntry.getZbxzcxj()))
								.add(new BigDecimal(xmzcEntry.getDqybzjbxsxj()))	
								.add(new BigDecimal(xmzcEntry.getDqybzxj()))	
								.add(new BigDecimal(xmzcEntry.getDshbzjjbzxj())).doubleValue()	
										);
		}
		int num = values.size();
		PageModel_Xm pm = new PageModel_Xm(values, Integer.valueOf(pageSize));//每页显示条数
		List<XmzcEntry> list = pm.getObjects(Integer.valueOf(pageNum));//显示第几页
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
		/*JSONArray obj = new JSONArray(values);
		try {
//			System.out.println(obj.toString(4));
			response.getWriter().print(obj.toString(4));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		System.out.println(mapZC.size());*/
		System.out.println("共耗时：" + (System.currentTimeMillis() - start) + "毫秒");
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
