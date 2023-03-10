package com.dnt.cloud.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dnt.cloud.common.domain.enums.EnumBase;

public class EnumBaseHandler<E extends EnumBase> extends BaseTypeHandler<EnumBase> {

    private final Map<String, E> codeLookup = new HashMap<String, E>();

    public EnumBaseHandler(Class<E> type) {
        if (type == null)
            throw new IllegalArgumentException("Type argument cannot be null");
        for (E e : type.getEnumConstants()) {
            codeLookup.put(e.getCode(), e);
        }
    }

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, EnumBase parameter,
                                    JdbcType jdbcType) throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, parameter != null ? parameter.getCode() : null);
        } else {
            ps.setObject(i, parameter != null ? parameter.getCode() : null, jdbcType.TYPE_CODE); // see r3589
        }
    }

    @Override
    public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        return codeLookup.get(s);
    }

    @Override
    public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        return codeLookup.get(s);
    }

    @Override
    public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        return codeLookup.get(s);
    }

}
