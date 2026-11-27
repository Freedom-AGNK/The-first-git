package com.tea.dao;
import com.tea.entity.TeaOrder;
import com.tea.util.DbUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeaOrderDao {
    public long insertWithConn(Connection conn,TeaOrder order)throws SQLException{
        String sql = "INSERT INTO tea_order (member_id, total_amount, status) "
                + "VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            if(order.getMemberId()==null){
                ps.setNull(1,Types.BIGINT);
            } else {
                ps.setLong(1, order.getMemberId());
            }
            ps.setBigDecimal(2, order.getTotalAmount());
            ps.setString(3, order.getStatus());
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    Long id = keys.getLong(1);
                    order.setId(id);
                    return id;
                }
            }
        }throw new SQLException("订单插入未返回主键");
    }
    public TeaOrder findById(Long id){
        String sql = "SELECT id, member_id, total_amount, status, created_at "
                + "FROM tea_order WHERE id = ?";
        try(Connection conn = DbUtil.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)){
            ps.setLong(1,id);
            try(ResultSet rs=ps.executeQuery()){
                if(rs.next()){
                    TeaOrder o=new TeaOrder();
                    o.setId(rs.getLong("id"));
                    long mid = rs.getLong("member_id");
                    o.setMemberId(rs.wasNull() ? null : mid);
                    o.setTotalAmount(rs.getBigDecimal("total_amount"));
                    o.setStatus(rs.getString("status"));
                    Timestamp ts = rs.getTimestamp("created_at");
                    if (ts != null) o.setCreatedAt(ts.toLocalDateTime());
                    return o;
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("查询订单失败",e);
        }
    }
}
