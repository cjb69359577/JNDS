package com.jiuqi.dna.jnjs.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.jiuqi.dna.jnjs.entity.Gdzc_wxzcEntity;
import com.jiuqi.dna.jnjs.entity.JsonMaptoObject;
import com.jiuqi.dna.jnjs.entity.SrzcgdbEntity;

public class MapToJson {
	
	
	public static Double getDoubleFormat(Double f){
		//DecimalFormat df = new DecimalFormat("#.00");
		 BigDecimal bg = new BigDecimal(f);
         double f1 = bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
		 return f1;
	}
   

	/**
	 * 将map转换为对象
	 * @param map
	 * @return
	 */
	public  static List<JsonMaptoObject> MapToJsonArray(Map<String,Double> map){
		List<JsonMaptoObject> list = new ArrayList<JsonMaptoObject>();
		for(Entry<String, Double> entry : map.entrySet()){
			JsonMaptoObject object  = new JsonMaptoObject();
			//entry.getKey()+"==="+entry.getValue()
			object.setName(entry.getKey());
			object.setValue(entry.getValue());
			list.add(object);
        }
		return list;
	}
	
	/**
	 * 将map转换为object
	 */
	public static List<SrzcgdbEntity> mapToObject(Map<String,Double> mapSr,Map<String,Double> mapZc){
		List<SrzcgdbEntity> list = new ArrayList<SrzcgdbEntity>();
		double srhj = 0.0;
		double zchj = 0.0;
		for(Entry<String, Double> entry : mapSr.entrySet()){
			srhj+=entry.getValue();
        }
		for(Entry<String, Double> entry : mapZc.entrySet()){
			 zchj+=entry.getValue();
        }
		try{
		for(int i=0;i<26;i++){
			SrzcgdbEntity entity = new SrzcgdbEntity();
			if(i==0){
				entity.setSrkm("栏次");
				entity.setSrjss(0);
				entity.setZckm("栏次");
				entity.setZcjss(0);
				list.add(entity);
			}
			if(i==1){
				entity.setSrkm("一、一般公共预算财政拨款收入");
				entity.setZchc(30+i);
				entity.setSrjss(getUnllToMap(mapSr.get("ybsr")));
				entity.setZckm("一、一般公共服务支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("201")));
				list.add(entity);
			}
			if(i==2){
				entity.setSrkm("二、上级补助收入");
				entity.setZchc(30+i);
				entity.setSrjss(getUnllToMap(mapSr.get("sjsr")));
				entity.setZckm("二、外交支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("202")));
				list.add(entity);
			}
			if(i==3){
				entity.setSrkm("三、事业收入");
				entity.setZchc(30+i);
				entity.setSrjss(getUnllToMap(mapSr.get("sysr")));
				entity.setZckm("二、外交支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("202")));
				list.add(entity);
			}
			if(i==4){
				entity.setSrkm("四、经营收入");
				entity.setZchc(30+i);
				entity.setSrjss(getUnllToMap(mapSr.get("jysr")));
				entity.setZckm("四、公共安全支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("204")));
				list.add(entity);
			}
			if(i==5){
				entity.setSrkm("五、附属单位上缴收入");
				entity.setZchc(30+i);
				entity.setSrjss(getUnllToMap(mapSr.get("fssr")));
				entity.setZckm("五、教育支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("205")));
				list.add(entity);
			}
			if(i==6){
				entity.setSrkm("六、其他收入");
				entity.setZchc(30+i);
				entity.setSrjss(getUnllToMap(mapSr.get("qtsr")));
				entity.setZckm("六、科学技术支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("206")));
				list.add(entity);
			}
			if(i==7){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("七、文化旅游体育与传媒支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("207")));
				list.add(entity);
			}
			if(i==8){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("八、社会保障和就业支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("208")));
				list.add(entity);
			}
			if(i==9){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("九、卫生健康支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("210")));
				list.add(entity);
			}
			if(i==10){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十、节能环保支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("211")));
				list.add(entity);
			}
			if(i==11){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十一、城乡社区支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("212")));
				list.add(entity);
			}
			if(i==12){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十二、农林水支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("213")));
				list.add(entity);
			}
			if(i==13){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十三、交通运输支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("214")));
				list.add(entity);
			}
			if(i==14){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十四、资源勘探信息等支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("215")));
				list.add(entity);
			}
			if(i==15){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十五、商业服务业等支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("216")));
				list.add(entity);
			}
			if(i==16){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十六、金融支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("217")));
				list.add(entity);
			}
			if(i==17){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十七、援助其他地区支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("219")));
				list.add(entity);
			}
			if(i==18){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十八、自然资源海洋气象等支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("220")));
				list.add(entity);
			}
			if(i==19){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("十九、住房保障支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get("221")));
				list.add(entity);
			}
			if(i==20){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("二十、粮油物资储备支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get(" ")));
				list.add(entity);
			}
			if(i==21){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("二十一、灾害防治及应急管理支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get(" ")));
				list.add(entity);
			}
			if(i==22){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("二十二、其他支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get(" ")));
				list.add(entity);
			}
			if(i==23){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("二十三、债务还本支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get(" ")));
				list.add(entity);
			}
			if(i==24){
				entity.setSrkm("");
				entity.setZchc(30+i);
				entity.setSrjss(0);
				entity.setZckm("二十四、债务付息支出");
				entity.setSrhc(i);
				entity.setZcjss(getUnllToMap(mapZc.get(" ")));
				list.add(entity);
			}
			if(i==25){
				entity.setSrkm("总计");
				entity.setZchc(30);
				entity.setSrjss(srhj);
				entity.setZckm("总计");
				entity.setSrhc(i);
				entity.setZcjss(zchj);
				list.add(entity);
			}
	      }
		}
		catch(Exception e){
		  e.printStackTrace();
	  }
		return list;
	}
	
	/**
	 * 将null转换为map
	 * @param d
	 * @return
	 */
	public static double getUnllToMap(Double d){
		if(null == d){
			return 0;
		}
		return d;
	}
	
	
	public static List<Gdzc_wxzcEntity> getGdzc_wxzcEntity(Map<String,Double> mapQc,Map<String,Double> mapQm) throws Exception {
		List<Gdzc_wxzcEntity> list = new ArrayList<Gdzc_wxzcEntity>();
//========================111111111111111111111===============================		
		Gdzc_wxzcEntity entity1 = new Gdzc_wxzcEntity();
		entity1.setLbname("合计");
		entity1.setHc(1);
		entity1.setQcsl(0);
		entity1.setQcjz(mapQc.get("d5"));
		entity1.setQcljzj(mapQc.get("e5"));
		entity1.setQcjingz(mapQc.get("f5"));
		
		entity1.setHjsl(0);
		entity1.setHjjz(mapQm.get("h5"));
		entity1.setHjljzj(mapQm.get("i5"));
		entity1.setHjjingz(mapQm.get("j5"));
		
		entity1.setZysl(0);
		entity1.setZyjz(mapQm.get("l5"));
		entity1.setZyljzj(0);
		entity1.setZyjingz(0);
		
		entity1.setCzcjsl(0);
		entity1.setCzcjjz(mapQm.get("p5"));
		entity1.setCzcjljzj(0);
		entity1.setCzcjjingz(0);
		
		entity1.setXzsl(0);
		entity1.setXzjz(mapQm.get("t5"));
		entity1.setXzljzj(0);
		entity1.setXzjingz(0);
		
		entity1.setDczsl(0);
		entity1.setDczjz(mapQm.get("x5"));
		entity1.setDczljzj(0);
		entity1.setDczjingz(0);
		

//===============================222222222222222222222============
     Gdzc_wxzcEntity entity2 = new Gdzc_wxzcEntity();
		entity2.setLbname("一、固定资产    ");
		entity2.setHc(2);
		entity2.setQcsl(0);
		entity2.setQcjz(mapQc.get("d6"));
		entity2.setQcljzj(mapQc.get("e6"));
		entity2.setQcjingz(mapQc.get("f6"));
		
		entity2.setHjsl(0);
		entity2.setHjjz(mapQm.get("h6"));
		entity2.setHjljzj(mapQm.get("i6"));
		entity2.setHjjingz(mapQm.get("j6"));
		
		entity2.setZysl(0);
		entity2.setZyjz(mapQm.get("l6"));
		entity2.setZyljzj(0);
		entity2.setZyjingz(0);
		
		entity2.setCzcjsl(0);
		entity2.setCzcjjz(mapQm.get("p6"));
		entity2.setCzcjljzj(0);
		entity2.setCzcjjingz(0);
		
		entity2.setXzsl(0);
		entity2.setXzjz(mapQm.get("t6"));
		entity2.setXzljzj(0);
		entity2.setXzjingz(0);
		
		entity2.setDczsl(0);
		entity2.setDczjz(mapQm.get("x6"));
		entity2.setDczljzj(0);
		entity2.setDczjingz(0);
		
		

//===========333333333333333333333333333333==================
Gdzc_wxzcEntity entity3 = new Gdzc_wxzcEntity();
		entity3.setLbname("（一）土地、房屋及构筑物：");
		entity3.setHc(3);
		entity3.setQcsl(0);
		entity3.setQcjz(mapQc.get("d7"));
		entity3.setQcljzj(mapQc.get("e7"));
		entity3.setQcjingz(mapQc.get("f7"));
		
		entity3.setHjsl(0);
		entity3.setHjjz(mapQm.get("h7"));
		entity3.setHjljzj(mapQm.get("i7"));
		entity3.setHjjingz(mapQm.get("j7"));
		
		entity3.setZysl(0);
		entity3.setZyjz(mapQm.get("l7"));
		entity3.setZyljzj(0);
		entity3.setZyjingz(0);
		
		entity3.setCzcjsl(0);
		entity3.setCzcjjz(mapQm.get("p7"));
		entity3.setCzcjljzj(0);
		entity3.setCzcjjingz(0);
		
		entity3.setXzsl(0);
		entity3.setXzjz(mapQm.get("t7"));
		entity3.setXzljzj(0);
		entity3.setXzjingz(0);
		
		entity3.setDczsl(0);
		entity3.setDczjz(mapQm.get("x7"));
		entity3.setDczljzj(0);
		entity3.setDczjingz(0);
		
		

//===================44444444444444444444444444=============================
Gdzc_wxzcEntity entity4 = new Gdzc_wxzcEntity();
		entity4.setLbname("（二）通用设备（个、台、辆等）");
		entity4.setHc(4);
		entity4.setQcsl(mapQc.get("c8"));
		entity4.setQcjz(mapQc.get("d8"));
		entity4.setQcljzj(mapQc.get("e8"));
		entity4.setQcjingz(mapQc.get("f8"));
		
		entity4.setHjsl(mapQm.get("g8"));
		entity4.setHjjz(mapQm.get("h8"));
		entity4.setHjljzj(mapQm.get("i8"));
		entity4.setHjjingz(mapQm.get("j8"));
		
		entity4.setZysl(mapQm.get("k8"));
		entity4.setZyjz(mapQm.get("l8"));
		entity4.setZyljzj(mapQm.get("m8"));
		entity4.setZyjingz(mapQm.get("n8"));
		
		entity4.setCzcjsl(mapQm.get("o8"));
		entity4.setCzcjjz(mapQm.get("p8"));
		entity4.setCzcjljzj(mapQm.get("q8"));
		entity4.setCzcjjingz(mapQm.get("r8"));
		
		entity4.setXzsl(mapQm.get("s8"));
		entity4.setXzjz(mapQm.get("t8"));
		entity4.setXzljzj(mapQm.get("u8"));
		entity4.setXzjingz(mapQm.get("v8"));
		
		entity4.setDczsl(mapQm.get("w8"));
		entity4.setDczjz(mapQm.get("x8"));
		entity4.setDczljzj(mapQm.get("v8"));
		entity4.setDczjingz(mapQm.get("z8"));
		
		
		
///=============55555555555555555================================
Gdzc_wxzcEntity entity5 = new Gdzc_wxzcEntity();
		entity5.setLbname("（三）专用设备（个、台等） ");
		entity5.setHc(5);
		entity5.setQcsl(mapQc.get("c9"));
		entity5.setQcjz(mapQc.get("d9"));
		entity5.setQcljzj(mapQc.get("e9"));
		entity5.setQcjingz(mapQc.get("f9"));
		
		entity5.setHjsl(mapQm.get("g9"));
		entity5.setHjjz(mapQm.get("h9"));
		entity5.setHjljzj(mapQm.get("i9"));
		entity5.setHjjingz(mapQm.get("j9"));
		
		entity5.setZysl(mapQm.get("k9"));
		entity5.setZyjz(mapQm.get("l9"));
		entity5.setZyljzj(mapQm.get("m9"));
		entity5.setZyjingz(mapQm.get("n9"));
		
		entity5.setCzcjsl(mapQm.get("o9"));
		entity5.setCzcjjz(mapQm.get("p9"));
		entity5.setCzcjljzj(mapQm.get("q9"));
		entity5.setCzcjjingz(mapQm.get("r9"));
		
		entity5.setXzsl(mapQm.get("s9"));
		entity5.setXzjz(mapQm.get("t9"));
		entity5.setXzljzj(mapQm.get("u9"));
		entity5.setXzjingz(mapQm.get("v9"));
		
		entity5.setDczsl(mapQm.get("w9"));
		entity5.setDczjz(mapQm.get("x9"));
		entity5.setDczljzj(mapQm.get("v9"));
		entity5.setDczjingz(mapQm.get("z9"));
		
		
//=================666666666666666666666666666=======================
Gdzc_wxzcEntity entity6 = new Gdzc_wxzcEntity();
		entity6.setLbname("（四）文物和陈列品（个、件等）");
		entity6.setHc(6);
		entity6.setQcsl(mapQc.get("c10"));
		entity6.setQcjz(mapQc.get("d10"));
		entity6.setQcljzj(mapQc.get("e10"));
		entity6.setQcjingz(mapQc.get("f10"));
		
		entity6.setHjsl(mapQm.get("g10"));
		entity6.setHjjz(mapQm.get("h10"));
		entity6.setHjljzj(mapQm.get("i10"));
		entity6.setHjjingz(mapQm.get("j10"));
		
		entity6.setZysl(mapQm.get("k10"));
		entity6.setZyjz(mapQm.get("l10"));
		entity6.setZyljzj(mapQm.get("m10"));
		entity6.setZyjingz(mapQm.get("n10"));
		
		entity6.setCzcjsl(mapQm.get("o10"));
		entity6.setCzcjjz(mapQm.get("p10"));
		entity6.setCzcjljzj(mapQm.get("q10"));
		entity6.setCzcjjingz(mapQm.get("r10"));
		
		entity6.setXzsl(mapQm.get("s10"));
		entity6.setXzjz(mapQm.get("t10"));
		entity6.setXzljzj(mapQm.get("u10"));
		entity6.setXzjingz(mapQm.get("v10"));
		
		entity6.setDczsl(mapQm.get("w10"));
		entity6.setDczjz(mapQm.get("x10"));
		entity6.setDczljzj(mapQm.get("v10"));
		entity6.setDczjingz(mapQm.get("z10"));
		

///=============777777777777777777777777777777============================
Gdzc_wxzcEntity entity7 = new Gdzc_wxzcEntity();
		entity7.setLbname("（五）图书档案（本、套等）");
		entity7.setHc(7);
		entity7.setQcsl(mapQc.get("c11"));
		entity7.setQcjz(mapQc.get("d11"));
		entity7.setQcljzj(mapQc.get("e11"));
		entity7.setQcjingz(mapQc.get("f11"));
		
		entity7.setHjsl(mapQm.get("g11"));
		entity7.setHjjz(mapQm.get("h11"));
		entity7.setHjljzj(mapQm.get("i11"));
		entity7.setHjjingz(mapQm.get("j11"));
		
		entity7.setZysl(mapQm.get("k11"));
		entity7.setZyjz(mapQm.get("l11"));
		entity7.setZyljzj(mapQm.get("m11"));
		entity7.setZyjingz(mapQm.get("n11"));
		
		entity7.setCzcjsl(mapQm.get("o11"));
		entity7.setCzcjjz(mapQm.get("p11"));
		entity7.setCzcjljzj(mapQm.get("q11"));
		entity7.setCzcjjingz(mapQm.get("r11"));
		
		entity7.setXzsl(mapQm.get("s11"));
		entity7.setXzjz(mapQm.get("t11"));
		entity7.setXzljzj(mapQm.get("u11"));
		entity7.setXzjingz(mapQm.get("v11"));
		
		entity7.setDczsl(mapQm.get("w11"));
		entity7.setDczjz(mapQm.get("x11"));
		entity7.setDczljzj(mapQm.get("v11"));
		entity7.setDczjingz(mapQm.get("z11"));
		
		
///================8888888888888888888888888888888===============
Gdzc_wxzcEntity entity8 = new Gdzc_wxzcEntity();
		entity8.setLbname("（六）家具、用具、装具及动植物（个、套等） ");
		entity8.setHc(8);
		entity8.setQcsl(mapQc.get("c12"));
		entity8.setQcjz(mapQc.get("d12"));
		entity8.setQcljzj(mapQc.get("e12"));
		entity8.setQcjingz(mapQc.get("f12"));
		
		entity8.setHjsl(mapQm.get("g12"));
		entity8.setHjjz(mapQm.get("h12"));
		entity8.setHjljzj(mapQm.get("i12"));
		entity8.setHjjingz(mapQm.get("j12"));
		
		entity8.setZysl(mapQm.get("k12"));
		entity8.setZyjz(mapQm.get("l12"));
		entity8.setZyljzj(mapQm.get("m12"));
		entity8.setZyjingz(mapQm.get("n12"));
		
		entity8.setCzcjsl(mapQm.get("o12"));
		entity8.setCzcjjz(mapQm.get("p12"));
		entity8.setCzcjljzj(mapQm.get("q12"));
		entity8.setCzcjjingz(mapQm.get("r12"));
		
		entity8.setXzsl(mapQm.get("s12"));
		entity8.setXzjz(mapQm.get("t12"));
		entity8.setXzljzj(mapQm.get("u12"));
		entity8.setXzjingz(mapQm.get("v12"));
		
		entity8.setDczsl(mapQm.get("w12"));
		entity8.setDczjz(mapQm.get("x12"));
		entity8.setDczljzj(mapQm.get("v12"));
		entity8.setDczjingz(mapQm.get("z12"));
		

//===========99999999999999999999999999999999999=========================
Gdzc_wxzcEntity entity9 = new Gdzc_wxzcEntity();
		entity9.setLbname("二、无形资产       ");
		entity9.setHc(9);
		entity9.setQcsl(0);
		entity9.setQcjz(mapQc.get("d13"));
		entity9.setQcljzj(mapQc.get("e13"));
		entity9.setQcjingz(mapQc.get("f13"));
		
		entity9.setHjsl(0);
		entity9.setHjjz(mapQm.get("h13"));
		entity9.setHjljzj(mapQm.get("i13"));
		entity9.setHjjingz(mapQm.get("j13"));
		
		entity9.setZysl(0);
		entity9.setZyjz(mapQm.get("l13"));
		entity9.setZyljzj(0);
		entity9.setZyjingz(0);
		
		entity9.setCzcjsl(0);
		entity9.setCzcjjz(mapQm.get("p13"));
		entity9.setCzcjljzj(0);
		entity9.setCzcjjingz(0);
		
		entity9.setXzsl(0);
		entity9.setXzjz(mapQm.get("t13"));
		entity9.setXzljzj(0);
		entity9.setXzjingz(0);
		
		entity9.setDczsl(0);
		entity9.setDczjz(mapQm.get("x13"));
		entity9.setDczljzj(0);
		entity9.setDczjingz(0);
		
		list.add(entity1);
		list.add(entity2);
		list.add(entity3);
		list.add(entity4);
		list.add(entity5);
		list.add(entity6);
		list.add(entity7);
		list.add(entity8);
		list.add(entity9);
		return list;
	}
}
