package com.example.day1;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

//urlPatterns:指定路径/get访问该servlet
@WebServlet(name="TestServlet",urlPatterns = {"/get"})
public class TestServlet  extends HttpServlet {

    static Map<Integer,String> map = new HashMap<Integer,String>();
    static {
        map.put(1,"aa");
        map.put(2,"bb");
        map.put(3,"cc");
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("get");
        //获取参数
//        String name = req.getParameter("name");
//        String age = req.getParameter("age");
        int id=-1;
        if(req.getParameter("id")!=null){
            id = Integer.parseInt(req.getParameter("id"));
        }



        //给客户端返回数据
        PrintWriter out = resp.getWriter();
        String s = map.get(id);
        if(s==null) s ="no such person";
        out.write(s);
//        out.write(name);
//        out.write(age);
        //前端传入参数


        //验证登入
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("post");
    }
}
