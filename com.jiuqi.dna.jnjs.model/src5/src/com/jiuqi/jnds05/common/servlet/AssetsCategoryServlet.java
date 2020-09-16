package com.jiuqi.jnds05.common.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.jiuqi.dna.core.http.DNAHttpServlet;
import com.jiuqi.dna.core.log.DNALogManager;
import com.jiuqi.dna.core.log.Logger;
import com.jiuqi.dna.core.spi.application.AppUtil;
import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.jnds05.common.bean.ResultShowAssetsBean;
import com.jiuqi.jnds05.common.bean.ShowAssetsBean;
import com.jiuqi.jnds05.common.service.FixedAndIntangibleAssEtsService;

public class AssetsCategoryServlet extends DNAHttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3739779282054537342L;
	
	private FixedAndIntangibleAssEtsService assetsService = FixedAndIntangibleAssEtsService.getInstance();

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
	
	private void doService(final HttpServletRequest request,
			final HttpServletResponse response) throws IOException,
			ServletException {
		
		ContextSPI context = AppUtil.getDefaultApp().getSystemSession().newContext(false);
		Logger logger = DNALogManager.getLogger("jnds/assetsCategory");
		String errorinfo = "";
		String code = "200";
		String jsonString = "";
		try {
			String orgCode = "000223";
			List<Map<String,String>> fixInstangibleAssShowInfor = assetsService.getFixInstangibleAssShowInfor(context, orgCode);
			Map<String, String> map = fixInstangibleAssShowInfor.get(0);
			List<ShowAssetsBean> showAssetsBeanList = new ArrayList<ShowAssetsBean>();
			for(int i = 2; i <= 7; i ++){
				ShowAssetsBean bean = new ShowAssetsBean();
				String zcName = getZcName(i);
				Double zcNumber =  Double.valueOf(map.get(i+""));
				BigDecimal bigDecimal = new BigDecimal(zcNumber);
				bigDecimal = bigDecimal.setScale(4, RoundingMode.HALF_UP);
				bean.setName(zcName);
				bean.setNum(bigDecimal.toString());
				showAssetsBeanList.add(bean);
			}
			ResultShowAssetsBean<ShowAssetsBean> resultShowAssetsBean = new ResultShowAssetsBean<ShowAssetsBean>();
			resultShowAssetsBean.setCode(code);
			resultShowAssetsBean.setErrorinfo("");
			resultShowAssetsBean.setSuccessinfo(showAssetsBeanList);
			JSONObject json = new JSONObject(resultShowAssetsBean);
			jsonString = json.toString();
			response.setContentType("application/json");
	 		request.setCharacterEncoding("UTF-8");
	 		response.setCharacterEncoding("UTF-8");
	 		response.getWriter().write(jsonString);    	
			response.getWriter().close();
		} catch (Exception e) {
			List<ShowAssetsBean> showAssetsBeanList = new ArrayList<ShowAssetsBean>();
			ResultShowAssetsBean<ShowAssetsBean> resultShowAssetsBean = new ResultShowAssetsBean<ShowAssetsBean>();
			resultShowAssetsBean.setCode("500");
			resultShowAssetsBean.setErrorinfo("数据组装中，请等待。");
			resultShowAssetsBean.setSuccessinfo(showAssetsBeanList);
			JSONObject json = new JSONObject(resultShowAssetsBean);
			jsonString = json.toString();
			response.setContentType("application/json");
	 		request.setCharacterEncoding("utf-8");
	 		response.setCharacterEncoding("UTF-8");
	 		response.getWriter().write(jsonString);    	
			response.getWriter().close();
			errorinfo = e.getMessage();
			code = "500";
		} finally {
			if ("200".equals(code)) {
				logger.logInfo(context, "成功：" + jsonString, true);
			} else {
				logger.logError(context, errorinfo, true);
			}
			if (context != null) {
				context.resolveTrans();
				context.dispose();
			}
		}

	}
	
	private String getZcName(int i){
		String zcName = "";
		switch(i){
			case 2: zcName = "土地、房屋及构筑物";
			break;
			case 3: zcName = "通用设备";
			break;
			case 4: zcName = "专用设备";
			break;
			case 5: zcName = "文物和陈列品";
			break;
			case 6: zcName = "图书档案";
			break;
			case 7: zcName = "家具、用具、装具及动植物";
			break;
		}
		return zcName;
	}
}
