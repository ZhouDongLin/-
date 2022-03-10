package com.example.Dao;

import com.example.unit.DataTool;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import java.sql.SQLException;

public class UserDao {

    public int regist(String username,String pwd){
        String sql="insert into user values(null,?,?)";
        int i=0;
        try {
            i=DataTool.runner.update(sql,username,pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    public int login(String username, String pwd) {
        String sql="select count(*) from user where username=? and pwd=?";
        Long cnt = null;
        try {
            cnt = DataTool.runner.query(sql, new ScalarHandler<Long>(), username, pwd);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Integer.parseInt(cnt+"");
    }


    public int modifyPwd(String username, String passwordNew) {
        String sql = "UPDATE user SET pwd = ? WHERE userName = ?";
        int ret=0;
        try {
            ret=DataTool.runner.update(sql,passwordNew,username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ret;
    }
}
