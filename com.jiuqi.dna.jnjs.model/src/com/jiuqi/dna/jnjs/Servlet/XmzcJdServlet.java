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
 * 项目支出-项目支出进度前10
 * 例如取项目A支出数逻辑为：
 * 凭证主表（GL_VOUCHER）中年度为2019年，单位为“000223单位”（单位编号为：000223）,
 * 期间为1到12的凭证关联的凭证子表（GL_VOUCHERITEM）中科目代码以（科目基础数据的STDCODE字段）
 * “720102”或“730102”或“740102” 或“750102” 或“790102”开头并且关联的辅助组合表（GL_ASSISTCOMB）
 * 中项目为A的数据，其“借方金额”字段合计值减去“贷方金额”字段合计值。
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
