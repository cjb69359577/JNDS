package com.jiuqi.jnds.cfpl6.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jiuqi.jnds.cfpl6.query.CalculationQueryParam;
import com.jiuqi.jnds.cfpl6.query.CalculationType;
import com.jiuqi.jnds.cfpl6.service.CalculationQueryService;
import com.jiuqi.jnds.cfpl6.service.ComplexQueryService;
import com.jiuqi.jnds.cfpl6.service.GLVoucherService;
import com.jiuqi.jnds.cfpl6.service.impl.FinanceAnalyseQueryServiceImpl;

@RestController
@RequestMapping("competition")
public class Cfpl6Controller {

	@Autowired
	private GLVoucherService glVoucherService;

	@Autowired
	private CalculationQueryService calculationQueryService;

	@Autowired
	private ComplexQueryService complexQueryService;

	@Autowired
	private FinanceAnalyseQueryServiceImpl financeAnalyseQueryService;

	@PostMapping("VNRecalculation")
	public Response glVoucherRecalculation() {
		// 获取分组后的凭证主表数据
		List<?> dataGroup = glVoucherService.queryGLVoucherByGroup();
		// 凭证主表 重算 及 修改
		glVoucherService.glVoucherRecalculation(dataGroup);

		Response response;
		try {
			List<?> queryLSRecalculation = glVoucherService.queryLSRecalculation();
			response = Response.success(queryLSRecalculation);
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.faild("重算失败");
		}

		return response;
	}

	@PostMapping("calculation")
	public Response calculation(@RequestBody @Nullable CalculationQueryParam requestParam) {
		requestParam = null == requestParam ? new CalculationQueryParam() : requestParam;
		CalculationType calculationType = CalculationType.getByValue(requestParam.getCalculationType());
		Response response;
		try {
			List<Object> successinfo = new ArrayList<>();
			if (calculationType == null) {
				// 提取全部数据
				Map<String, Object> result = new HashMap<>();

				// 收入支出固定表
				Map<String, Object> srzcgdb_datas = calculationQueryService.queryIncomeAndExpendDetail();
				result.put(CalculationType.SRZCGDB.getValue(), srzcgdb_datas);

				// 项目支出决算明细表
				Map<String, Object> xmzcjsmxb_result = new HashMap<>();
				List<Map<String, Object>> xmzcjsmxb_datas = calculationQueryService
						.queryFinalAccountOfExpendDetail(requestParam.getCurrentPage(), requestParam.getPageSize());
				int xmzcjsmxb_count = calculationQueryService.countFinalAccountOfExpendDetail();
				xmzcjsmxb_result.put("data", xmzcjsmxb_datas);
				xmzcjsmxb_result.put("count", xmzcjsmxb_count);
				result.put(CalculationType.XMZCJSMXB.getValue(), xmzcjsmxb_result);
				
				// 车辆情况表
				Map<String, Object> clqkb_result = new HashMap<>();
				List<Map<String,Object>> clqkb_datas = calculationQueryService.queryVehicleInformationDetail(requestParam.getCurrentPage(), requestParam.getPageSize());
				int clqkb_count = calculationQueryService.countFinalAccountOfVehicleInformationDetail();
				clqkb_result.put("data", clqkb_datas);
				clqkb_result.put("count", clqkb_count);
				result.put(CalculationType.CLQKB.getValue(), clqkb_result);
				
				// 固定和无形资产存量情况表
				Map<String, Object> gdhwxzcclqkb_datas = calculationQueryService.queryAssetsDetail();
				result.put(CalculationType.GDHWXZCCLQKB.getValue(), gdhwxzcclqkb_datas);

				successinfo.add(result);
			} else {
				// 提取指定数据
				switch (calculationType) {
				case SRZCGDB:
					Map<String, Object> srzcgdb_datas = calculationQueryService.queryIncomeAndExpendDetail();
					successinfo.add(srzcgdb_datas);
					break;
				case XMZCJSMXB:
					Map<String, Object> xmzcjsmxb_result = new HashMap<>();
					List<Map<String, Object>> xmzcjsmxb_datas = calculationQueryService
							.queryFinalAccountOfExpendDetail(requestParam.getCurrentPage(), requestParam.getPageSize());
					int xmzcjsmxb_count = calculationQueryService.countFinalAccountOfExpendDetail();
					xmzcjsmxb_result.put("data", xmzcjsmxb_datas);
					xmzcjsmxb_result.put("count", xmzcjsmxb_count);
					successinfo.add(xmzcjsmxb_result);
					break;
				case CLQKB:
					//车辆情况表
					Map<String, Object> clqkb_result = new HashMap<>();
					List<Map<String,Object>> clqkb_datas = calculationQueryService.queryVehicleInformationDetail(requestParam.getCurrentPage(), requestParam.getPageSize());
					int clqkb_count = calculationQueryService.countFinalAccountOfVehicleInformationDetail();
					clqkb_result.put("data", clqkb_datas);
					clqkb_result.put("count", clqkb_count);
					successinfo.add(clqkb_result);
					break;
				case GDHWXZCCLQKB:
					// 固定和无形资产存量情况表
					Map<String, Object> gdhwxzcclqkb_datas = calculationQueryService.queryAssetsDetail();
					successinfo.add(gdhwxzcclqkb_datas);
					break;
				default:
					break;
				}
			}
			response = Response.success(successinfo);
		} catch (Exception e) {
			e.printStackTrace();
			response = Response.faild("提取数据失败");
		}
		return response;

	}

	@GetMapping("screenDisplayData")
	public Map<String, Object> screenDisplayData() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("assetByClass", complexQueryService.queryByClass());
		result.put("assetByState", complexQueryService.queryByUsedStatus());
		result.put("monthly", financeAnalyseQueryService.queryMonthlyData());
		result.put("outlay", financeAnalyseQueryService.queryOutLayData());
		result.put("top10", financeAnalyseQueryService.queryTop10Data());
		return result;
	}

}
