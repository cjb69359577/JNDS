package com.jiuqi.jnds05.common.bean;

import java.io.Serializable;

public class ProjectExpenditureDetailsEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String gnfl;
	private String Z05_2EJMC;	//支出功能分类代码
	private String Z05_2EJDM;	//支出功能分类名称
	private String xm;
	private String Z05_2YJMC;	//项目代码
	private String Z05_2YJDM;	//项目名称
	private double Z05_2F1_1 = 0.00;	//合计
	
	private double Z05_2F1_2 = 0.00;	//工资福利支出小计
	private double Z05_2F1_3 = 0.00;	//基本工资
	private double Z05_2F1_4 = 0.00;	//津贴补贴
	private double Z05_2F1_5 = 0.00;	//奖金
	private double Z05_2F1_6 = 0.00;	//伙食补助
	private double Z05_2F1_7 = 0.00;	//绩效工资
	private double Z05_2F1_8 = 0.00;	//养老保险
	private double Z05_2F1_9 = 0.00;	//职业年金
	private double Z05_2F1_10 = 0.00;	//医疗保险
	private double Z05_2F1_11 = 0.00;	//公务员医疗补助
	private double Z05_2F1_12 = 0.00;	//其他社会保障
	private double Z05_2F1_13 = 0.00;	//住房公积金
	private double Z05_2F1_14 = 0.00;	//医疗费
	private double Z05_2F1_15 = 0.00;	//工资福利支出
	
	private double Z05_2F1_16 = 0.00;	//商品服务支出小计
	private double Z05_2F1_17 = 0.00;	//办公费
	private double Z05_2F1_18 = 0.00;	//印刷费
	private double Z05_2F1_19 = 0.00;	//咨询费
	private double Z05_2F1_20 = 0.00;	//手续费
	private double Z05_2F1_21 = 0.00;	//水费
	private double Z05_2F1_22 = 0.00;	//电费
	private double Z05_2F1_23 = 0.00;	//邮电费
	private double Z05_2F1_24 = 0.00;	//取暖费
	private double Z05_2F1_25 = 0.00;	//物业管理费
	private double Z05_2F1_26 = 0.00;	//差旅费
	private double Z05_2F1_27 = 0.00;	//因公出国费用
	private double Z05_2F1_28 = 0.00;	//维修（护）费
	private double Z05_2F1_29 = 0.00;	//租赁费
	private double Z05_2F1_30 = 0.00;	//会议费
	private double Z05_2F1_31 = 0.00;	//培训费
	private double Z05_2F1_32 = 0.00;	//公务接待费
	private double Z05_2F1_33 = 0.00;	//专用材料费
	private double Z05_2F1_34 = 0.00;	//被装购置费
	private double Z05_2F1_35 = 0.00;	//专用燃料费
	private double Z05_2F1_36 = 0.00;	//劳务费
	private double Z05_2F1_37 = 0.00;	//委托业务费
	private double Z05_2F1_38 = 0.00;	//工会经费
	private double Z05_2F1_39 = 0.00;	//福利费
	private double Z05_2F1_40 = 0.00;	//公务用车维护费
	private double Z05_2F1_41 = 0.00;	//其他交通费用
	private double Z05_2F1_42 = 0.00;	//税金及附加费
	private double Z05_2F1_43 = 0.00;	//其他商品服务支出
	
	private double Z05_2F1_44 = 0.00;	//对个人和家庭补助小计
	private double Z05_2F1_45 = 0.00;	//离休费
	private double Z05_2F1_46 = 0.00;	//退休给
	private double Z05_2F1_47 = 0.00;	//退职费
	private double Z05_2F1_48 = 0.00;	//抚恤金
	private double Z05_2F1_49 = 0.00;	//生活补助
	private double Z05_2F1_50 = 0.00;	//救济费
	private double Z05_2F1_51 = 0.00;	//医疗补助费
	private double Z05_2F1_52 = 0.00;	//助学金
	private double Z05_2F1_53 = 0.00;	//奖励金
	private double Z05_2F1_54 = 0.00;	//个人农业生活补贴
	private double Z05_2F1_55 = 0.00;	//其他对个人和家庭的补助
	
	private double Z05_2F1_56 = 0.00;	//债务利息及费用支出小计
	private double Z05_2F1_57 = 0.00;	//国内债务付息
	private double Z05_2F1_58 = 0.00;	//国外债务付息
	private double Z05_2F1_59 = 0.00;	//国内债务发行费
	private double Z05_2F1_60 = 0.00;	//国外债务发行费
	
	private double Z05_2F1_61 = 0.00;	//资本性支出（基本建设）小计
	private double Z05_2F1_62 = 0.00;	//房屋建筑物构建
	private double Z05_2F1_63 = 0.00;	//办公设备购置
	private double Z05_2F1_64 = 0.00;	//专用设备购置
	private double Z05_2F1_65 = 0.00;	//基础设施建设
	private double Z05_2F1_66 = 0.00;	//大型修缮
	private double Z05_2F1_67 = 0.00;	//信息设备及网络更新
	private double Z05_2F1_68 = 0.00;	//物资储备
	private double Z05_2F1_69 = 0.00;	//公务用车购置
	private double Z05_2F1_70 = 0.00;	//其他交通工具购置
	private double Z05_2F1_71 = 0.00;	//文物和陈列品购置
	private double Z05_2F1_72 = 0.00;	//无形资产购置
	private double Z05_2F1_73 = 0.00;	//其他基本建设支出
	
	private double Z05_2F1_74 = 0.00;	//资本性支出小计
	private double Z05_2F1_75 = 0.00;	//房屋建筑物购置
	private double Z05_2F1_76 = 0.00;	//办公设备购置
	private double Z05_2F1_77 = 0.00;	//专用设备购置
	private double Z05_2F1_78 = 0.00;	//基础设施建设
	private double Z05_2F1_79 = 0.00;	//大型修缮
	private double Z05_2F1_80 = 0.00;	//信息网络及软件购置更新
	private double Z05_2F1_81 = 0.00;	//物资储备
	private double Z05_2F1_82 = 0.00;	//土地补偿
	private double Z05_2F1_83 = 0.00;	//安置补助
	private double Z05_2F1_84 = 0.00;	//地上附着物和青苗补偿
	private double Z05_2F1_85 = 0.00;	//拆迁补偿
	private double Z05_2F1_86 = 0.00;	//公务用车购置
	private double Z05_2F1_87 = 0.00;	//其他交通工具购置
	private double Z05_2F1_88 = 0.00;	//文物和陈列品购置
	private double Z05_2F1_89 = 0.00;	//无形资产购置
	private double Z05_2F1_90 = 0.00;	//其他资本性支出
	
	private double Z05_2F1_91 = 0.00;	//对企业补助（基本建设）小计
	private double Z05_2F1_92 = 0.00;	//资金注入
	private double Z05_2F1_93 = 0.00;	//其他对企业补助
	
	private double Z05_2F1_94 = 0.00;	//对企业补助小计
	private double Z05_2F1_95 = 0.00;	//资金注入
	private double Z05_2F1_96 = 0.00;	//政府投资基金股权投资
	private double Z05_2F1_97 = 0.00;	//费用补贴
	private double Z05_2F1_98 = 0.00;	//利息补贴
	private double Z05_2F1_99 = 0.00;	//其他对企业补助
	
	private double Z05_2F1_100 = 0.00;	//对社会保障基金补助小计
	private double Z05_2F1_101 = 0.00;	//对社会保险基金补助
	private double Z05_2F1_102 = 0.00;	//补充全国社会保障基金
	public String getZ05_2EJMC() {
		return Z05_2EJMC;
	}
	public void setZ05_2EJMC(String z05_2ejmc) {
		Z05_2EJMC = z05_2ejmc;
	}
	public String getZ05_2EJDM() {
		return Z05_2EJDM;
	}
	public void setZ05_2EJDM(String z05_2ejdm) {
		Z05_2EJDM = z05_2ejdm;
	}
	public String getZ05_2YJMC() {
		return Z05_2YJMC;
	}
	public void setZ05_2YJMC(String z05_2yjmc) {
		Z05_2YJMC = z05_2yjmc;
	}
	public String getZ05_2YJDM() {
		return Z05_2YJDM;
	}
	public void setZ05_2YJDM(String z05_2yjdm) {
		Z05_2YJDM = z05_2yjdm;
	}
	public double getZ05_2F1_1() {
		return Z05_2F1_1;
	}
	public void setZ05_2F1_1(double z05_2f1_1) {
		Z05_2F1_1 = z05_2f1_1;
	}
	public double getZ05_2F1_2() {
		return Z05_2F1_2;
	}
	public void setZ05_2F1_2(double z05_2f1_2) {
		Z05_2F1_2 = z05_2f1_2;
	}
	public double getZ05_2F1_3() {
		return Z05_2F1_3;
	}
	public void setZ05_2F1_3(double z05_2f1_3) {
		Z05_2F1_3 = z05_2f1_3;
	}
	public double getZ05_2F1_4() {
		return Z05_2F1_4;
	}
	public void setZ05_2F1_4(double z05_2f1_4) {
		Z05_2F1_4 = z05_2f1_4;
	}
	public double getZ05_2F1_5() {
		return Z05_2F1_5;
	}
	public void setZ05_2F1_5(double z05_2f1_5) {
		Z05_2F1_5 = z05_2f1_5;
	}
	public double getZ05_2F1_6() {
		return Z05_2F1_6;
	}
	public void setZ05_2F1_6(double z05_2f1_6) {
		Z05_2F1_6 = z05_2f1_6;
	}
	public double getZ05_2F1_7() {
		return Z05_2F1_7;
	}
	public void setZ05_2F1_7(double z05_2f1_7) {
		Z05_2F1_7 = z05_2f1_7;
	}
	public double getZ05_2F1_8() {
		return Z05_2F1_8;
	}
	public void setZ05_2F1_8(double z05_2f1_8) {
		Z05_2F1_8 = z05_2f1_8;
	}
	public double getZ05_2F1_9() {
		return Z05_2F1_9;
	}
	public void setZ05_2F1_9(double z05_2f1_9) {
		Z05_2F1_9 = z05_2f1_9;
	}
	public double getZ05_2F1_10() {
		return Z05_2F1_10;
	}
	public void setZ05_2F1_10(double z05_2f1_10) {
		Z05_2F1_10 = z05_2f1_10;
	}
	public double getZ05_2F1_11() {
		return Z05_2F1_11;
	}
	public void setZ05_2F1_11(double z05_2f1_11) {
		Z05_2F1_11 = z05_2f1_11;
	}
	public double getZ05_2F1_12() {
		return Z05_2F1_12;
	}
	public void setZ05_2F1_12(double z05_2f1_12) {
		Z05_2F1_12 = z05_2f1_12;
	}
	public double getZ05_2F1_13() {
		return Z05_2F1_13;
	}
	public void setZ05_2F1_13(double z05_2f1_13) {
		Z05_2F1_13 = z05_2f1_13;
	}
	public double getZ05_2F1_14() {
		return Z05_2F1_14;
	}
	public void setZ05_2F1_14(double z05_2f1_14) {
		Z05_2F1_14 = z05_2f1_14;
	}
	public double getZ05_2F1_15() {
		return Z05_2F1_15;
	}
	public void setZ05_2F1_15(double z05_2f1_15) {
		Z05_2F1_15 = z05_2f1_15;
	}
	public double getZ05_2F1_16() {
		return Z05_2F1_16;
	}
	public void setZ05_2F1_16(double z05_2f1_16) {
		Z05_2F1_16 = z05_2f1_16;
	}
	public double getZ05_2F1_17() {
		return Z05_2F1_17;
	}
	public void setZ05_2F1_17(double z05_2f1_17) {
		Z05_2F1_17 = z05_2f1_17;
	}
	public double getZ05_2F1_18() {
		return Z05_2F1_18;
	}
	public void setZ05_2F1_18(double z05_2f1_18) {
		Z05_2F1_18 = z05_2f1_18;
	}
	public double getZ05_2F1_19() {
		return Z05_2F1_19;
	}
	public void setZ05_2F1_19(double z05_2f1_19) {
		Z05_2F1_19 = z05_2f1_19;
	}
	public double getZ05_2F1_20() {
		return Z05_2F1_20;
	}
	public void setZ05_2F1_20(double z05_2f1_20) {
		Z05_2F1_20 = z05_2f1_20;
	}
	public double getZ05_2F1_21() {
		return Z05_2F1_21;
	}
	public void setZ05_2F1_21(double z05_2f1_21) {
		Z05_2F1_21 = z05_2f1_21;
	}
	public double getZ05_2F1_22() {
		return Z05_2F1_22;
	}
	public void setZ05_2F1_22(double z05_2f1_22) {
		Z05_2F1_22 = z05_2f1_22;
	}
	public double getZ05_2F1_23() {
		return Z05_2F1_23;
	}
	public void setZ05_2F1_23(double z05_2f1_23) {
		Z05_2F1_23 = z05_2f1_23;
	}
	public double getZ05_2F1_24() {
		return Z05_2F1_24;
	}
	public void setZ05_2F1_24(double z05_2f1_24) {
		Z05_2F1_24 = z05_2f1_24;
	}
	public double getZ05_2F1_25() {
		return Z05_2F1_25;
	}
	public void setZ05_2F1_25(double z05_2f1_25) {
		Z05_2F1_25 = z05_2f1_25;
	}
	public double getZ05_2F1_26() {
		return Z05_2F1_26;
	}
	public void setZ05_2F1_26(double z05_2f1_26) {
		Z05_2F1_26 = z05_2f1_26;
	}
	public double getZ05_2F1_27() {
		return Z05_2F1_27;
	}
	public void setZ05_2F1_27(double z05_2f1_27) {
		Z05_2F1_27 = z05_2f1_27;
	}
	public double getZ05_2F1_28() {
		return Z05_2F1_28;
	}
	public void setZ05_2F1_28(double z05_2f1_28) {
		Z05_2F1_28 = z05_2f1_28;
	}
	public double getZ05_2F1_29() {
		return Z05_2F1_29;
	}
	public void setZ05_2F1_29(double z05_2f1_29) {
		Z05_2F1_29 = z05_2f1_29;
	}
	public double getZ05_2F1_30() {
		return Z05_2F1_30;
	}
	public void setZ05_2F1_30(double z05_2f1_30) {
		Z05_2F1_30 = z05_2f1_30;
	}
	public double getZ05_2F1_31() {
		return Z05_2F1_31;
	}
	public void setZ05_2F1_31(double z05_2f1_31) {
		Z05_2F1_31 = z05_2f1_31;
	}
	public double getZ05_2F1_32() {
		return Z05_2F1_32;
	}
	public void setZ05_2F1_32(double z05_2f1_32) {
		Z05_2F1_32 = z05_2f1_32;
	}
	public double getZ05_2F1_33() {
		return Z05_2F1_33;
	}
	public void setZ05_2F1_33(double z05_2f1_33) {
		Z05_2F1_33 = z05_2f1_33;
	}
	public double getZ05_2F1_34() {
		return Z05_2F1_34;
	}
	public void setZ05_2F1_34(double z05_2f1_34) {
		Z05_2F1_34 = z05_2f1_34;
	}
	public double getZ05_2F1_35() {
		return Z05_2F1_35;
	}
	public void setZ05_2F1_35(double z05_2f1_35) {
		Z05_2F1_35 = z05_2f1_35;
	}
	public double getZ05_2F1_36() {
		return Z05_2F1_36;
	}
	public void setZ05_2F1_36(double z05_2f1_36) {
		Z05_2F1_36 = z05_2f1_36;
	}
	public double getZ05_2F1_37() {
		return Z05_2F1_37;
	}
	public void setZ05_2F1_37(double z05_2f1_37) {
		Z05_2F1_37 = z05_2f1_37;
	}
	public double getZ05_2F1_38() {
		return Z05_2F1_38;
	}
	public void setZ05_2F1_38(double z05_2f1_38) {
		Z05_2F1_38 = z05_2f1_38;
	}
	public double getZ05_2F1_39() {
		return Z05_2F1_39;
	}
	public void setZ05_2F1_39(double z05_2f1_39) {
		Z05_2F1_39 = z05_2f1_39;
	}
	public double getZ05_2F1_40() {
		return Z05_2F1_40;
	}
	public void setZ05_2F1_40(double z05_2f1_40) {
		Z05_2F1_40 = z05_2f1_40;
	}
	public double getZ05_2F1_41() {
		return Z05_2F1_41;
	}
	public void setZ05_2F1_41(double z05_2f1_41) {
		Z05_2F1_41 = z05_2f1_41;
	}
	public double getZ05_2F1_42() {
		return Z05_2F1_42;
	}
	public void setZ05_2F1_42(double z05_2f1_42) {
		Z05_2F1_42 = z05_2f1_42;
	}
	public double getZ05_2F1_43() {
		return Z05_2F1_43;
	}
	public void setZ05_2F1_43(double z05_2f1_43) {
		Z05_2F1_43 = z05_2f1_43;
	}
	public double getZ05_2F1_44() {
		return Z05_2F1_44;
	}
	public void setZ05_2F1_44(double z05_2f1_44) {
		Z05_2F1_44 = z05_2f1_44;
	}
	public double getZ05_2F1_45() {
		return Z05_2F1_45;
	}
	public void setZ05_2F1_45(double z05_2f1_45) {
		Z05_2F1_45 = z05_2f1_45;
	}
	public double getZ05_2F1_46() {
		return Z05_2F1_46;
	}
	public void setZ05_2F1_46(double z05_2f1_46) {
		Z05_2F1_46 = z05_2f1_46;
	}
	public double getZ05_2F1_47() {
		return Z05_2F1_47;
	}
	public void setZ05_2F1_47(double z05_2f1_47) {
		Z05_2F1_47 = z05_2f1_47;
	}
	public double getZ05_2F1_48() {
		return Z05_2F1_48;
	}
	public void setZ05_2F1_48(double z05_2f1_48) {
		Z05_2F1_48 = z05_2f1_48;
	}
	public double getZ05_2F1_49() {
		return Z05_2F1_49;
	}
	public void setZ05_2F1_49(double z05_2f1_49) {
		Z05_2F1_49 = z05_2f1_49;
	}
	public double getZ05_2F1_50() {
		return Z05_2F1_50;
	}
	public void setZ05_2F1_50(double z05_2f1_50) {
		Z05_2F1_50 = z05_2f1_50;
	}
	public double getZ05_2F1_51() {
		return Z05_2F1_51;
	}
	public void setZ05_2F1_51(double z05_2f1_51) {
		Z05_2F1_51 = z05_2f1_51;
	}
	public double getZ05_2F1_52() {
		return Z05_2F1_52;
	}
	public void setZ05_2F1_52(double z05_2f1_52) {
		Z05_2F1_52 = z05_2f1_52;
	}
	public double getZ05_2F1_53() {
		return Z05_2F1_53;
	}
	public void setZ05_2F1_53(double z05_2f1_53) {
		Z05_2F1_53 = z05_2f1_53;
	}
	public double getZ05_2F1_54() {
		return Z05_2F1_54;
	}
	public void setZ05_2F1_54(double z05_2f1_54) {
		Z05_2F1_54 = z05_2f1_54;
	}
	public double getZ05_2F1_55() {
		return Z05_2F1_55;
	}
	public void setZ05_2F1_55(double z05_2f1_55) {
		Z05_2F1_55 = z05_2f1_55;
	}
	public double getZ05_2F1_56() {
		return Z05_2F1_56;
	}
	public void setZ05_2F1_56(double z05_2f1_56) {
		Z05_2F1_56 = z05_2f1_56;
	}
	public double getZ05_2F1_57() {
		return Z05_2F1_57;
	}
	public void setZ05_2F1_57(double z05_2f1_57) {
		Z05_2F1_57 = z05_2f1_57;
	}
	public double getZ05_2F1_58() {
		return Z05_2F1_58;
	}
	public void setZ05_2F1_58(double z05_2f1_58) {
		Z05_2F1_58 = z05_2f1_58;
	}
	public double getZ05_2F1_59() {
		return Z05_2F1_59;
	}
	public void setZ05_2F1_59(double z05_2f1_59) {
		Z05_2F1_59 = z05_2f1_59;
	}
	public double getZ05_2F1_60() {
		return Z05_2F1_60;
	}
	public void setZ05_2F1_60(double z05_2f1_60) {
		Z05_2F1_60 = z05_2f1_60;
	}
	public double getZ05_2F1_61() {
		return Z05_2F1_61;
	}
	public void setZ05_2F1_61(double z05_2f1_61) {
		Z05_2F1_61 = z05_2f1_61;
	}
	public double getZ05_2F1_62() {
		return Z05_2F1_62;
	}
	public void setZ05_2F1_62(double z05_2f1_62) {
		Z05_2F1_62 = z05_2f1_62;
	}
	public double getZ05_2F1_63() {
		return Z05_2F1_63;
	}
	public void setZ05_2F1_63(double z05_2f1_63) {
		Z05_2F1_63 = z05_2f1_63;
	}
	public double getZ05_2F1_64() {
		return Z05_2F1_64;
	}
	public void setZ05_2F1_64(double z05_2f1_64) {
		Z05_2F1_64 = z05_2f1_64;
	}
	public double getZ05_2F1_65() {
		return Z05_2F1_65;
	}
	public void setZ05_2F1_65(double z05_2f1_65) {
		Z05_2F1_65 = z05_2f1_65;
	}
	public double getZ05_2F1_66() {
		return Z05_2F1_66;
	}
	public void setZ05_2F1_66(double z05_2f1_66) {
		Z05_2F1_66 = z05_2f1_66;
	}
	public double getZ05_2F1_67() {
		return Z05_2F1_67;
	}
	public void setZ05_2F1_67(double z05_2f1_67) {
		Z05_2F1_67 = z05_2f1_67;
	}
	public double getZ05_2F1_68() {
		return Z05_2F1_68;
	}
	public void setZ05_2F1_68(double z05_2f1_68) {
		Z05_2F1_68 = z05_2f1_68;
	}
	public double getZ05_2F1_69() {
		return Z05_2F1_69;
	}
	public void setZ05_2F1_69(double z05_2f1_69) {
		Z05_2F1_69 = z05_2f1_69;
	}
	public double getZ05_2F1_70() {
		return Z05_2F1_70;
	}
	public void setZ05_2F1_70(double z05_2f1_70) {
		Z05_2F1_70 = z05_2f1_70;
	}
	public double getZ05_2F1_71() {
		return Z05_2F1_71;
	}
	public void setZ05_2F1_71(double z05_2f1_71) {
		Z05_2F1_71 = z05_2f1_71;
	}
	public double getZ05_2F1_72() {
		return Z05_2F1_72;
	}
	public void setZ05_2F1_72(double z05_2f1_72) {
		Z05_2F1_72 = z05_2f1_72;
	}
	public double getZ05_2F1_73() {
		return Z05_2F1_73;
	}
	public void setZ05_2F1_73(double z05_2f1_73) {
		Z05_2F1_73 = z05_2f1_73;
	}
	public double getZ05_2F1_74() {
		return Z05_2F1_74;
	}
	public void setZ05_2F1_74(double z05_2f1_74) {
		Z05_2F1_74 = z05_2f1_74;
	}
	public double getZ05_2F1_75() {
		return Z05_2F1_75;
	}
	public void setZ05_2F1_75(double z05_2f1_75) {
		Z05_2F1_75 = z05_2f1_75;
	}
	public double getZ05_2F1_76() {
		return Z05_2F1_76;
	}
	public void setZ05_2F1_76(double z05_2f1_76) {
		Z05_2F1_76 = z05_2f1_76;
	}
	public double getZ05_2F1_77() {
		return Z05_2F1_77;
	}
	public void setZ05_2F1_77(double z05_2f1_77) {
		Z05_2F1_77 = z05_2f1_77;
	}
	public double getZ05_2F1_78() {
		return Z05_2F1_78;
	}
	public void setZ05_2F1_78(double z05_2f1_78) {
		Z05_2F1_78 = z05_2f1_78;
	}
	public double getZ05_2F1_79() {
		return Z05_2F1_79;
	}
	public void setZ05_2F1_79(double z05_2f1_79) {
		Z05_2F1_79 = z05_2f1_79;
	}
	public double getZ05_2F1_80() {
		return Z05_2F1_80;
	}
	public void setZ05_2F1_80(double z05_2f1_80) {
		Z05_2F1_80 = z05_2f1_80;
	}
	public double getZ05_2F1_81() {
		return Z05_2F1_81;
	}
	public void setZ05_2F1_81(double z05_2f1_81) {
		Z05_2F1_81 = z05_2f1_81;
	}
	public double getZ05_2F1_82() {
		return Z05_2F1_82;
	}
	public void setZ05_2F1_82(double z05_2f1_82) {
		Z05_2F1_82 = z05_2f1_82;
	}
	public double getZ05_2F1_83() {
		return Z05_2F1_83;
	}
	public void setZ05_2F1_83(double z05_2f1_83) {
		Z05_2F1_83 = z05_2f1_83;
	}
	public double getZ05_2F1_84() {
		return Z05_2F1_84;
	}
	public void setZ05_2F1_84(double z05_2f1_84) {
		Z05_2F1_84 = z05_2f1_84;
	}
	public double getZ05_2F1_85() {
		return Z05_2F1_85;
	}
	public void setZ05_2F1_85(double z05_2f1_85) {
		Z05_2F1_85 = z05_2f1_85;
	}
	public double getZ05_2F1_86() {
		return Z05_2F1_86;
	}
	public void setZ05_2F1_86(double z05_2f1_86) {
		Z05_2F1_86 = z05_2f1_86;
	}
	public double getZ05_2F1_87() {
		return Z05_2F1_87;
	}
	public void setZ05_2F1_87(double z05_2f1_87) {
		Z05_2F1_87 = z05_2f1_87;
	}
	public double getZ05_2F1_88() {
		return Z05_2F1_88;
	}
	public void setZ05_2F1_88(double z05_2f1_88) {
		Z05_2F1_88 = z05_2f1_88;
	}
	public double getZ05_2F1_89() {
		return Z05_2F1_89;
	}
	public void setZ05_2F1_89(double z05_2f1_89) {
		Z05_2F1_89 = z05_2f1_89;
	}
	public double getZ05_2F1_90() {
		return Z05_2F1_90;
	}
	public void setZ05_2F1_90(double z05_2f1_90) {
		Z05_2F1_90 = z05_2f1_90;
	}
	public double getZ05_2F1_91() {
		return Z05_2F1_91;
	}
	public void setZ05_2F1_91(double z05_2f1_91) {
		Z05_2F1_91 = z05_2f1_91;
	}
	public double getZ05_2F1_92() {
		return Z05_2F1_92;
	}
	public void setZ05_2F1_92(double z05_2f1_92) {
		Z05_2F1_92 = z05_2f1_92;
	}
	public double getZ05_2F1_93() {
		return Z05_2F1_93;
	}
	public void setZ05_2F1_93(double z05_2f1_93) {
		Z05_2F1_93 = z05_2f1_93;
	}
	public double getZ05_2F1_94() {
		return Z05_2F1_94;
	}
	public void setZ05_2F1_94(double z05_2f1_94) {
		Z05_2F1_94 = z05_2f1_94;
	}
	public double getZ05_2F1_95() {
		return Z05_2F1_95;
	}
	public void setZ05_2F1_95(double z05_2f1_95) {
		Z05_2F1_95 = z05_2f1_95;
	}
	public double getZ05_2F1_96() {
		return Z05_2F1_96;
	}
	public void setZ05_2F1_96(double z05_2f1_96) {
		Z05_2F1_96 = z05_2f1_96;
	}
	public double getZ05_2F1_97() {
		return Z05_2F1_97;
	}
	public void setZ05_2F1_97(double z05_2f1_97) {
		Z05_2F1_97 = z05_2f1_97;
	}
	public double getZ05_2F1_98() {
		return Z05_2F1_98;
	}
	public void setZ05_2F1_98(double z05_2f1_98) {
		Z05_2F1_98 = z05_2f1_98;
	}
	public double getZ05_2F1_99() {
		return Z05_2F1_99;
	}
	public void setZ05_2F1_99(double z05_2f1_99) {
		Z05_2F1_99 = z05_2f1_99;
	}
	public double getZ05_2F1_100() {
		return Z05_2F1_100;
	}
	public void setZ05_2F1_100(double z05_2f1_100) {
		Z05_2F1_100 = z05_2f1_100;
	}
	public double getZ05_2F1_101() {
		return Z05_2F1_101;
	}
	public void setZ05_2F1_101(double z05_2f1_101) {
		Z05_2F1_101 = z05_2f1_101;
	}
	public double getZ05_2F1_102() {
		return Z05_2F1_102;
	}
	public void setZ05_2F1_102(double z05_2f1_102) {
		Z05_2F1_102 = z05_2f1_102;
	}
	public String getGnfl() {
		return gnfl;
	}
	public void setGnfl(String gnfl) {
		this.gnfl = gnfl;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
