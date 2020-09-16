package com.jiuqi.jnds05.common.service;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.jnds05.common.bean.ExcelReader;
import com.jiuqi.jnds05.common.bean.ImportEnity;
import com.jiuqi.jnds05.common.dao.ImportDao;

/**
 * ImportService
 * 
 * @author likeshuang
 * 
 */
public class ImportService {

	private ImportDao importDao = new ImportDao();

	/**
	 * parser import data
	 * 
	 * @param context
	 * @param inputStream
	 * @throws IOException
	 * @throws SQLException
	 */
	public void importData(ContextSPI context, InputStream inputStream)
			throws IOException, SQLException {
		List<ImportEnity> importDataList = ExcelReader.readExcel(inputStream);
		Map<String, Map<String, Object>> credentialMap = new HashMap<String, Map<String,Object>>();
		List<Map<String, Object>> credentialSubList = new ArrayList<Map<String,Object>>();
		Map<String, Map<String, Object>> assistcombMap = parserAssistcombMap(
				context, importDataList);
		List<Map<String, Object>> extractList = new ArrayList<Map<String,Object>>();
		for (int i = 0; i < importDataList.size(); i++) {
			ImportEnity importEnity = importDataList.get(i);
			Map<String, Object> extract = new HashMap<String, Object>();
			extractList.add(extract);
			extract.put("UNITCODE", importEnity.getUnitCode());
			extract.put("ACCTYEAR", importEnity.getYear());
			extract.put("ACCTPERIOD", importEnity.getPeriod());
			extract.put("KMCODE", importEnity.getSubjectCode());
			extract.put("KMNAME", importEnity.getSubjectName());
			extract.put("JFJE", importEnity.getDebitAmount());
			extract.put("DFJE", importEnity.getLenderAmount());

			String credentialNum = importEnity.getCredentialId();
			if (!credentialMap.containsKey(credentialNum)) {
				String orgId = importDao.getOrgId(context,
						importEnity.getUnitCode());
				Map<String, Object> credential = new HashMap<String, Object>();
				credentialMap.put(credentialNum, credential);
				String credentialId = UUID.randomUUID().toString()
						.replace("-", "");
				credential.put("RECID", credentialId);
				credential.put("VCHRNUM", importEnity.getCredentialNum());
				credential.put("ACCTYEAR", importEnity.getYear());
				credential.put("ACCTPERIOD", importEnity.getPeriod());
				credential.put("UNITID", orgId);
				credential.put("CREATEDATE", importEnity.getCredentialDate());
				Date date = new Date();
				credential.put("CREATETIME", date);
			}

			String assistcombKey = importEnity.getYear() + "_"
					+ importEnity.getProjectCode() + "_"
					+ importEnity.getFunctionTypeCode() + "_"
					+ importEnity.getEconomicTypeCode();

			Map<String, Object> credentialSub = new HashMap<String, Object>();
			String subjectId = importDao.getSubjectId(context,
					importEnity.getSubjectCode());
			credentialSub.put("RECID",
					UUID.randomUUID().toString().replace("-", ""));
			credentialSub.put("SUBJECTID", subjectId);
			credentialSub.put("VCHRID",
					credentialMap.get(credentialNum).get("RECID"));
			credentialSub.put("DEBIT", importEnity.getDebitAmount());
			credentialSub.put("CREDIT", importEnity.getLenderAmount());
			Map<String, Object> assistcombInfo = assistcombMap
					.get(assistcombKey);
			if (null != assistcombInfo) {
				credentialSub.put("ASSCOMBID", assistcombInfo.get("RECID"));
				Object jjflcode = assistcombInfo.get("JJFLCODE");
				if (null != jjflcode) {
					extract.put("JJFLCODE", jjflcode);
					extract.put("JJFLNAME", assistcombInfo.get("JJFLNAME"));
				}
				Object gnflcode = assistcombInfo.get("GNFLCODE");
				if (null != gnflcode) {
					extract.put("GNFLCODE", gnflcode);
					extract.put("GNFLNAME", assistcombInfo.get("GNFLNAME"));
				}
				Object xmcode = assistcombInfo.get("XMCODE");
				if (null != xmcode) {
					extract.put("XMCODE", xmcode);
					extract.put("XMNAME", assistcombInfo.get("XMNAME"));
				}
			}
			credentialSubList.add(credentialSub);
		}
		importDao.insertCredential(context, credentialMap);
		importDao.inserCredentialSub(context, credentialSubList);
		importDao.inserExtract(context, extractList);
	}

	/**
	 * parser Assistcomb
	 * 
	 * @param context
	 * @param importDataList
	 * @return
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	private Map<String, Map<String, Object>> parserAssistcombMap(
			ContextSPI context, List<ImportEnity> importDataList)
			throws RuntimeException, SQLException {
		Map<String, Map<String, Object>> assistcombMap = new HashMap<String, Map<String,Object>>();
		for (int i = 0; i < importDataList.size(); i++) {
			ImportEnity importEnity = importDataList.get(i);
			Map<String, Object> assistcomb = new HashMap<String, Object>();

			Map<String, String> project = new HashMap<String, String>();
			String projectCode = importEnity.getProjectCode();
			if (null != projectCode && projectCode.length() > 0) {
				project = importDao.getProject(context, projectCode);
				if (project.size() > 0) {
					assistcomb.put("PROJETID", project.get("PROJETID"));
					assistcomb.put("PROJETNAME", project.get("PROJETNAME"));
				} else {
					assistcomb.put("PROJETID", null);
					assistcomb.put("PROJETNAME", null);
				}
			}

			Map<String, String> functionType = new HashMap<String, String>();
			String functionTypeCode = importEnity.getFunctionTypeCode();
			if (null != functionTypeCode && functionTypeCode.length() > 0) {
				functionType = importDao.getFunctionType(context,
						functionTypeCode);
				if (functionType.size() > 0) {
					assistcomb.put("EXPENDFUNCCLASSID",
							functionType.get("EXPENDFUNCCLASSID"));
					assistcomb.put("EXPENDFUNCCLASSNAME",
							functionType.get("EXPENDFUNCCLASSNAME"));
				} else {
					assistcomb.put("EXPENDFUNCCLASSID", null);
					assistcomb.put("EXPENDFUNCCLASSNAME", null);
				}

			}

			Map<String, String> economicType = new HashMap<String, String>();
			String economicTypeCode = importEnity.getEconomicTypeCode();
			if (null != economicTypeCode && economicTypeCode.length() > 0) {
				economicType = importDao.getEconomicType(context,
						economicTypeCode);
				if (economicType.size() > 0) {
					assistcomb.put("EXPENDECONCLASSID",
							economicType.get("EXPENDECONCLASSID"));
					assistcomb.put("EXPENDECONCLASSNAME",
							economicType.get("EXPENDECONCLASSNAME"));
				} else {
					assistcomb.put("EXPENDECONCLASSID", null);
					assistcomb.put("EXPENDECONCLASSNAME", null);
				}

			}

			String assistcombKey = importEnity.getYear() + "_" + projectCode
					+ "_" + functionTypeCode + "_" + economicTypeCode;
			if (assistcomb.size() > 0
					&& !assistcombMap.containsKey(assistcombKey)) {
				Object projetIdObj = assistcomb.get("PROJETID");
				Object expendfuncclassIdObj = assistcomb
						.get("EXPENDFUNCCLASSID");
				Object expendeconclassIdObj = assistcomb
						.get("EXPENDECONCLASSID");
				String projetId = null == projetIdObj ? "" : String
						.valueOf(projetIdObj);
				String expendfuncclassId = null == expendfuncclassIdObj ? ""
						: String.valueOf(expendfuncclassIdObj);
				String expendeconclassId = null == expendeconclassIdObj ? ""
						: String.valueOf(expendeconclassIdObj);
				String assistcombId = importDao.getAssistcombId(context,
						importEnity.getYear(), projetId, expendfuncclassId,
						expendeconclassId);
				if (null == assistcombId) {
					assistcombId = UUID.randomUUID().toString()
							.replace("-", "");
					importDao.insertAssistcomb(context, importEnity.getYear(),
							assistcombId, projetId, expendfuncclassId,
							expendeconclassId);
				}
				assistcomb.put("RECID", assistcombId);
				assistcombMap.put(assistcombKey, assistcomb);
			}
		}
		return assistcombMap;
	}
}
