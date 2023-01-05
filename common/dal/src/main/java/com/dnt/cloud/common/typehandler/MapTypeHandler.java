package com.dnt.cloud.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dnt.cloud.core.common.MapUtil;

public class MapTypeHandler extends BaseTypeHandler<Map<String, String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, String> parameter,
                                    JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, MapUtil.toString(parameter));
        } else {
            ps.setObject(i, MapUtil.toString(parameter), jdbcType.TYPE_CODE); // see r3589
        }
    }

    @Override
    public Map<String, String> getNullableResult(ResultSet rs, String columnName)
                                                                                 throws SQLException {
        return MapUtil.parseString(rs.getString(columnName));
    }

    @Override
    public Map<String, String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return MapUtil.parseString(rs.getString(columnIndex));
    }

    @Override
    public Map<String, String> getNullableResult(CallableStatement cs, int columnIndex)
                                                                                       throws SQLException {
        return MapUtil.parseString(cs.getString(columnIndex));
    }

}
