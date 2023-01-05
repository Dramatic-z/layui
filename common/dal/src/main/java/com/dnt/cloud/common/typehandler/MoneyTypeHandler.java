package com.dnt.cloud.common.typehandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import com.dnt.cloud.common.util.money.Money;

public class MoneyTypeHandler extends BaseTypeHandler<Money> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Money parameter, JdbcType jdbcType)
                                                                                                    throws SQLException {
        if (jdbcType == null) {
            ps.setString(i, parameter.getAmount().toPlainString());
        } else {
            ps.setObject(i, parameter.getAmount(), jdbcType.TYPE_CODE); // see r3589
        }
    }

    @Override
    public Money getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return new Money(rs.getString(columnName));
    }

    @Override
    public Money getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return new Money(rs.getString(columnIndex));
    }

    @Override
    public Money getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return new Money(cs.getString(columnIndex));
    }

}
