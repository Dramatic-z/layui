package com.dnt.cloud.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dnt.cloud.core.common.SetUtil;

public class SetTypeHandler extends BaseTypeHandler<Set<String>> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Set<String> parameter,
                                    JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, SetUtil.toString(parameter));
        } else {
            ps.setObject(i, SetUtil.toString(parameter), jdbcType.TYPE_CODE); // see r3589
        }
    }

    @Override
    public Set<String> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return SetUtil.parseString(rs.getString(columnName));
    }

    @Override
    public Set<String> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return SetUtil.parseString(rs.getString(columnIndex));
    }

    @Override
    public Set<String> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return SetUtil.parseString(cs.getString(columnIndex));
    }

}
