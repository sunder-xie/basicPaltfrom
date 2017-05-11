package com.kintiger.platform.framework.ibatis;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

import com.ibatis.sqlmap.client.extensions.ParameterSetter;
import com.ibatis.sqlmap.client.extensions.ResultGetter;
import com.ibatis.sqlmap.client.extensions.TypeHandlerCallback;

public class NextDateTypeHanlderCallback implements TypeHandlerCallback {

	public Object getResult(ResultGetter arg0) throws SQLException {
		return new UnsupportedOperationException();
	}

	public void setParameter(ParameterSetter setter, Object parameter)
			throws SQLException {

		Date date = DateUtils.addDays((Date) parameter, 1);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		setter.setString(df.format(date));
	}

	public Object valueOf(String s) {
		return new UnsupportedOperationException();
	}

}
