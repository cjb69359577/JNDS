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
 * 车辆情况表
 * 1、通过获取卡片表中“000223单位”（单位编号为：000223）
 * 2019年  12月31日前车辆卡片(卡片最新版本)对应的资产编号个数决定该表行数。
 * 反映到此表中D列确定行数。
 * C列显示该行卡片信息对应的“资产分类”基础数据的stdname列。
 * 3、D到H列分别对应该行卡片信息的资产相关信息。
 * 4、I列和J列分别显示该行卡片信息对应的“使用状况”和“车辆用途”
 * 基础数据的stdname列。
 * 取数规则
 * 【C6】资产分类 = “卡片表单位为“000223单位”（单位编号为：000223）
 * 的数据中对应资产分类在
 * “（STDCODE > 2030000 AND STDCODE < 2030800）OR STDCODE = 2039900”，卡片状态是（00,02）
 * 且记账日期在2019年12月31日（包含31日）前卡片最新版本的数据对应卡片信息的资产分类名称。
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
			PageModel pm = new PageModel(sublist, Integer.valueOf(pageSize));//每页显示条数
	        List<ClqkEntity> list = pm.getObjects(Integer.valueOf(pageNum));//显示第几页
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
