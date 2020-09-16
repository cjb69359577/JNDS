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
 * 重算凭证号
 * 凭证主表（GL_VOUCHER）中的凭证号字段（VCHRNUM）
 * 是存储凭证的顺序号码,要求每年单位范围内此编号需在单期内连续不断号：
 * 例：A单位在2019年度第5期的凭证共有5800张，
 * 其凭证编号根据凭证日期排序分别赋值为1、2、3、4、5、6…5800。
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
						 rebuild.setRuntime(System.currentTimeMillis()-start + "毫秒");
						 rebuildMsgList.add(rebuild);
					 }
				 }
			 }
			 //long end = System.currentTimeMillis()-start;
			/* map.put("unitid", "所有单位");
			 map.put("acctyear", "2019年-2020年");
			 map.put("acctperiod","1月-12月");
			 map.put("runtime ", end+"毫秒");*/
			 
			 JSONObject msg = new JSONObject();
			 try {
				msg.put("msg", rebuildMsgList);
				msg.put("runtime", "重算凭证号已完成，共计耗时："+(System.currentTimeMillis()-startk)+"毫秒");
				response.getWriter().print(msg);
			} catch (JSONException e) {
				e.printStackTrace();
			}finally{
				response.getWriter().flush();
				response.getWriter().close();
					
			}
			//一键维护数据
			CommonService.initService(conn);
			endContext(conn);
		//	System.out.println("共耗时："+ end+"毫秒");
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
