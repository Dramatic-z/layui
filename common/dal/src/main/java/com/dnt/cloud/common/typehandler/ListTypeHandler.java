package com.dnt.cloud.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dnt.cloud.core.common.ListUtil;


public class ListTypeHandler extends BaseTypeHandler<List<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<String> parameter,
                                    JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, ListUtil.toString(parameter));
        } else {
            ps.setObject(i, ListUtil.toString(parameter), jdbcType.TYPE_CODE); // see r3589
        }
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return ListUtil.parseString(rs.getString(columnName));
    }

    @Override
    public List<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return ListUtil.parseString(rs.getString(columnIndex));
    }

    @Override
    public List<String> getNullableResult(CallableStatement cs, int columnIndex)
                                                                                throws SQLException {
        return ListUtil.parseString(cs.getString(columnIndex));
    }

}
