package com.jiuqi.jnds05.common.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.spi.application.ContextSPI;

/**
 * import data dao
 * 
 * @author likeshuang
 * 
 */
public class ImportDao {
	/**
	 * select orgId by unitCode
	 * 
	 * @param context
	 * @param unitCode
	 * @param unitName
	 * @return
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	public String getOrgId(ContextSPI context, String unitCode)
			throws RuntimeException, SQLException {
		String orgId = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select RECID ");
		sql.append("from MD_ORG ");
		sql.append("where STDCODE = ? ");
		Connection conn = context.get(Connection.class);
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, unitCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				orgId = rs.getString(1);
			}
		} catch (Exception e) {

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}

		}
		if (null == orgId) {
			throw new RuntimeException("orgId is ull, code = " + unitCode);
		}
		return orgId;
	}

	/**
	 * select subjectId by SubjectCode
	 * 
	 * @param context
	 * @param subjectCode
	 * @param subjectName
	 * @return
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	public String getSubjectId(ContextSPI context, String subjectCode)
			throws SQLException {
		String subjectId = null;
		StringBuffer sql = new StringBuffer();
		sql.append("select RECID ");
		sql.append("from MD_ACCOUNTSUBJECT ");
		sql.append("where STDCODE = ? ");
		Connection conn = context.get(Connection.class);
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, subjectCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				subjectId = rs.getString(1);
			}
		} catch (Exception e) {

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}
		}
		return subjectId;
	}

	/**
	 * select project by projectCode
	 * 
	 * @param context
	 * @param projectCode
	 * @param projectName
	 * @return
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	public Map<String, String> getProject(ContextSPI context, String projectCode)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select RECID,STDNAME ");
		sql.append("from MD_PROJECT ");
		sql.append("where STDCODE = ? ");
		Connection conn = context.get(Connection.class);
		ResultSet rs = null;
		PreparedStatement ps = null;
		Map<String, String> project = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, projectCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				project.put("PROJETID", rs.getString(1));
				project.put("PROJETNAME", rs.getString(2));
			}
		} catch (Exception e) {

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}
		}
		return project;
	}

	/**
	 * select FunctionType by functionTypeCode
	 * 
	 * @param context
	 * @param functionTypeCode
	 * @param functionTypeName
	 * @return
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	public Map<String, String> getFunctionType(ContextSPI context,
			String functionTypeCode) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select RECID,STDNAME ");
		sql.append("from MD_EXPENDFUNCCLASS ");
		sql.append("where STDCODE = ? ");
		Connection conn = context.get(Connection.class);
		ResultSet rs = null;
		PreparedStatement ps = null;
		Map<String, String> functionType = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, functionTypeCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				functionType.put("EXPENDFUNCCLASSID", rs.getString(1));
				functionType.put("EXPENDFUNCCLASSNAME", rs.getString(2));
			}
		} catch (Exception e) {

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}

		}
		return functionType;
	}

	/**
	 * select economicTypeCode and economicTypeName by EconomicTypeCode
	 * 
	 * @param context
	 * @param economicTypeCode
	 * @param economicTypeName
	 * @return
	 * @throws RuntimeException
	 * @throws SQLException
	 */
	public Map<String, String> getEconomicType(ContextSPI context,
			String economicTypeCode) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select RECID,STDNAME ");
		sql.append("from MD_EXPENDECONCLASS ");
		sql.append("where STDCODE = ? ");
		Connection conn = context.get(Connection.class);
		ResultSet rs = null;
		PreparedStatement ps = null;
		Map<String, String> functionType = new HashMap<String, String>();
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, economicTypeCode);
			rs = ps.executeQuery();
			if (rs.next()) {
				functionType.put("EXPENDECONCLASSID", rs.getString(1));
				functionType.put("EXPENDECONCLASSNAME", rs.getString(2));
			}
		} catch (Exception e) {

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}

		}
		return functionType;
	}

	/**
	 * select AssistcombId by year projectId expendfuncclassId expendeconclassId
	 * 
	 * @param context
	 * @param year
	 * @param projectId
	 * @param expendfuncclassId
	 * @param expendeconclassId
	 * @return
	 * @throws SQLException
	 */
	public String getAssistcombId(ContextSPI context, Integer year,
			String projectId, String expendfuncclassId, String expendeconclassId)
			throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("select RECID ");
		sql.append("from GL_ASSISTCOMB ");
		sql.append("where ACCYEAR = ? ");
		if (projectId != null) {
			sql.append("and PROJECTID = '" + projectId + "' ");
		}
		if (expendfuncclassId != null) {
			sql.append("and EXPENDFUNCCLASSID = '" + expendfuncclassId + "' ");
		}
		if (expendeconclassId != null) {
			sql.append("and EXPENDECONCLASSID = '" + expendeconclassId + "' ");
		}
		Connection conn = context.get(Connection.class);
		String assistcombId = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, year);
			rs = ps.executeQuery();

			if (rs.next()) {
				assistcombId = rs.getString(1);
			}
		} catch (Exception e) {

		} finally {
			if (rs != null) {
				rs.close();
			}
			if (null != ps) {
				ps.close();
			}

		}
		return assistcombId;
	}

	/**
	 * insertAssistcomb
	 * 
	 * @param context
	 * @param year
	 * @param AssistcombId
	 * @param projectId
	 * @param expendfuncclassId
	 * @param expendeconclassId
	 * @throws SQLException
	 */
	public void insertAssistcomb(ContextSPI context, Integer year,
			String AssistcombId, String projectId, String expendfuncclassId,
			String expendeconclassId) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into GL_ASSISTCOMB(RECID,ACCYEAR,PROJECTID,EXPENDFUNCCLASSID,EXPENDECONCLASSID) ");
		sql.append("VALUES (?,?,?,?,?)");
		Connection conn = context.get(Connection.class);
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setString(1, AssistcombId);
			ps.setInt(2, year);
			ps.setString(3, projectId);
			ps.setString(4, expendfuncclassId);
			ps.setString(5, expendeconclassId);
			ps.executeUpdate();
		} catch (Exception e) {

		} finally {
			if (null != ps) {
				ps.close();
			}

		}
	}

	/**
	 * insertCredential
	 * 
	 * @param context
	 * @param credentialMap
	 * @throws SQLException
	 */
	public void insertCredential(ContextSPI context,
			Map<String, Map<String, Object>> credentialMap) throws SQLException {
		Iterator<Map.Entry<String, Map<String, Object>>> it = credentialMap
				.entrySet().iterator();
		Connection conn = context.get(Connection.class);
		PreparedStatement ps = null;
		try {
			while (it.hasNext()) {
				Map.Entry<String, Map<String, Object>> entry = it.next();
				Map<String, Object> value = entry.getValue();
				StringBuffer sql = new StringBuffer();
				sql.append("insert into GL_VOUCHER(RECID,VCHRNUM,ACCTYEAR,ACCTPERIOD,UNITID,CREATEDATE,CREATETIME) ");
				sql.append("VALUES(?,?,?,?,?,?,?)");
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, (String) value.get("RECID"));
				ps.setInt(2, (Integer) value.get("VCHRNUM"));
				ps.setInt(3, (Integer) value.get("ACCTYEAR"));
				ps.setInt(4, (Integer) value.get("ACCTPERIOD"));
				ps.setString(5, (String) value.get("UNITID"));
				java.util.Date createdate = (java.util.Date) value
						.get("CREATEDATE");
				ps.setDate(6, new java.sql.Date(createdate.getTime()));
				java.util.Date createtime = (java.util.Date) value
						.get("CREATETIME");
				ps.setDate(7, new java.sql.Date(createtime.getTime()));
				ps.executeUpdate();
			}
		} catch (SQLException e) {

		} finally {
			if (null != ps) {
				ps.close();
			}

		}
	}

	/**
	 * inserCredentialSub
	 * 
	 * @param context
	 * @param credentialSubList
	 * @throws SQLException
	 */
	public void inserCredentialSub(ContextSPI context,
			List<Map<String, Object>> credentialSubList) throws SQLException {
		Connection conn = context.get(Connection.class);
		PreparedStatement ps = null;
		try {
			for (int i = 0; i < credentialSubList.size(); i++) {
				Map<String, Object> credentialSub = credentialSubList.get(i);
				StringBuffer sql = new StringBuffer();
				sql.append("insert into GL_VOUCHERITEM(RECID,VCHRID,SUBJECTID,DEBIT,CREDIT,ASSCOMBID) ");
				sql.append("VALUES(?,?,?,?,?,?)");
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, (String) credentialSub.get("RECID"));
				ps.setString(2, (String) credentialSub.get("VCHRID"));
				Object subjectId = credentialSub.get("SUBJECTID");
				if (null == subjectId) {
					ps.setString(3, null);
				} else {
					ps.setString(3, (String) subjectId);
				}
				ps.setDouble(4, (Double) credentialSub.get("DEBIT"));
				ps.setDouble(5, (Double) credentialSub.get("CREDIT"));
				ps.setString(6, (String) credentialSub.get("ASSCOMBID"));
				ps.executeUpdate();
			}
		} catch (SQLException e) {

		} finally {
			if (null != ps) {
				ps.close();
			}

		}
	}

	/**
	 * inserExtract
	 * 
	 * @param context
	 * @param extractList
	 * @throws SQLException
	 */
	public void inserExtract(ContextSPI context,
			List<Map<String, Object>> extractList) throws SQLException {
		Connection conn = context.get(Connection.class);
		PreparedStatement ps = null;
		try {
			for (int i = 0; i < extractList.size(); i++) {
				Map<String, Object> extrac = extractList.get(i);
				StringBuffer sql = new StringBuffer();
				sql.append("insert into DATA_EXTRACT(UNITCODE,ACCTYEAR,ACCTPERIOD,KMCODE,KMNAME,JFJE,DFJE,JJFLCODE,JJFLNAME,GNFLCODE,GNFLNAME,XMCODE,XMNAME) ");
				sql.append("VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)");
				ps = conn.prepareStatement(sql.toString());
				ps.setString(1, (String) extrac.get("UNITCODE"));
				ps.setInt(2, (Integer) extrac.get("ACCTYEAR"));
				ps.setInt(3, (Integer) extrac.get("ACCTPERIOD"));
				ps.setString(4, (String) extrac.get("KMCODE"));
				ps.setString(5, (String) extrac.get("KMNAME"));
				ps.setDouble(6, (Double) extrac.get("JFJE"));
				ps.setDouble(7, (Double) extrac.get("DFJE"));
				ps.setString(8, (String) extrac.get("JJFLCODE"));
				ps.setString(9, (String) extrac.get("JJFLNAME"));
				ps.setString(10, (String) extrac.get("GNFLCODE"));
				ps.setString(11, (String) extrac.get("GNFLNAME"));
				ps.setString(12, (String) extrac.get("XMCODE"));
				ps.setString(13, (String) extrac.get("XMNAME"));
				ps.executeUpdate();
			}
		} catch (SQLException e) {

		} finally {
			if (null != ps) {
				ps.close();
			}

		}
	}
}
