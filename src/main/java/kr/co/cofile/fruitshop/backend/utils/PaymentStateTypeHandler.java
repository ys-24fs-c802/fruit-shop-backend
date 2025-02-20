package kr.co.cofile.fruitshop.backend.utils;

import kr.co.cofile.fruitshop.backend.enums.PaymentState;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentStateTypeHandler extends BaseTypeHandler<PaymentState> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, PaymentState parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter.name());
    }

    @Override
    public PaymentState getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String state = rs.getString(columnName);
        return state == null ? null : PaymentState.valueOf(state);
    }

    @Override
    public PaymentState getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String state = rs.getString(columnIndex);
        return state == null ? null : PaymentState.valueOf(state);
    }

    @Override
    public PaymentState getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String state = cs.getString(columnIndex);
        return state == null ? null : PaymentState.valueOf(state);
    }
}
