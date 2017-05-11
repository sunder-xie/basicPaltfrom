package com.kintiger.platform.report.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.jdbc.driver.OracleTypes;

import com.kintiger.platform.base.dao.impl.BaseDaoImpl;
import com.kintiger.platform.report.dao.IReportDao;
import com.kintiger.platform.report.pojo.ReportStructure;

public class ReportDaoImpl extends BaseDaoImpl implements IReportDao {
	public List<ReportStructure> getContentList(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ReportStructure getContent(long structureID) {
		return (ReportStructure) getSqlMapClientTemplate().queryForObject("report.getContent", structureID);
	}

	public List<Map<String, String>> getDataByProc(String proc,
			Map<String, String> comm) {
		// TODO Auto-generated method stub
		Connection conn = null;  
		CallableStatement stmt = null;  
		ResultSet rs = null; 
		try {
			conn = getSqlMapClient().getCurrentConnection();
			stmt = conn.prepareCall(proc);
			stmt.setDouble(1, 3000);  
            stmt.registerOutParameter(2, OracleTypes.CURSOR);  
            stmt.execute();
         // getXxx(index)中的index 需要和上面registerOutParameter的index对应  
            rs = (ResultSet) stmt.getObject(2);  
            // 获取列名及类型  
            int colunmCount = rs.getMetaData().getColumnCount();  
            String[] colNameArr = new String[colunmCount];  
            String[] colTypeArr = new String[colunmCount];  
            for (int i = 0; i < colunmCount; i++) {  
                colNameArr[i] = rs.getMetaData().getColumnName(i + 1);  
                colTypeArr[i] = rs.getMetaData().getColumnTypeName(i + 1);  
                System.out.print(colNameArr[i] + "(" + colTypeArr[i] + ")"  
                        + " | ");  
            }  
            System.out.println();  
            while (rs.next()) {  
                StringBuffer sb = new StringBuffer();  
                for (int i = 0; i < colunmCount; i++) {  
                    sb.append(rs.getString(i + 1) + " | ");  
                }  
                System.out.println(sb);  
            }  
            System.out.println("------- Test Proc Out is ResultSet end. ");  
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {  
	           if (null != rs) {  
	               try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}  
	           }  
	           if (null != stmt) {  
	               try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}  
	           }  
	           if (null != conn) {  
	               try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}  
	           }  
	       }  
		
		return null;
	}

	public List<Map<String, Object>> getDataBySql(String sql,
			Map comm) { 
		Map<String,String> search = new HashMap<String, String>();
		StringBuffer sb = new StringBuffer();
		sb.append("select * from (select row_.*, rownum rownum_ from ( ");
		sb.append(sql);
		sb.append(" ) row_ where rownum <= "+comm.get("end")+") where rownum_ > "+comm.get("start"));
		search.put("sql", sb.toString());
		return getSqlMapClientTemplate().queryForList("report.selectBySql",search);
	}

}
