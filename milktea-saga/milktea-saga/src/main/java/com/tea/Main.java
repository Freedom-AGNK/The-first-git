package com.tea;
import com.tea.util.DbUtil;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        try(Connection conn= DbUtil.getConnection()){
            System.out.println("✓ 连接成功：" + conn.getCatalog());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}