package com.example.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.bean.Album;
import com.example.bean.RespObj;
import com.example.service.AlbumService;
import com.example.service.UserService;
import com.example.unit.DataTool;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "UserServlet",urlPatterns = {"/user/*"})
//使用一类Servlet处理一类请求
public class UserServlet extends HttpServlet {
    UserService  us=new UserService();
    AlbumService as = new AlbumService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
        //System.out.println("user get");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //System.out.println("post get");
        System.out.println(req.getRequestURL());
        System.out.println(req.getRequestURI());
        req.setCharacterEncoding("utf8");
        resp.setCharacterEncoding("utf8");
        int lastIndex = req.getRequestURI().lastIndexOf("/")+1;
        String methodName = req.getRequestURI().substring(lastIndex);
        //反射
        Object result = null;
        try {
            result= UserServlet.class.getDeclaredMethod(methodName,HttpServletRequest.class,
                    HttpServletResponse.class).invoke(this,req,resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        PrintWriter out = resp.getWriter();
        String json = JSONObject.toJSONString((RespObj)result);
        out.write(json);
        out.flush();
        out.close();
    }
    protected Object getToken(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        String token =req.getParameter("token");
        String usernamepwd= DataTool.decrypt(token,DataTool.appKey);
        System.out.println("1"+token);
        try{
            String[] umpwd=usernamepwd.split(" ");
            String username = umpwd[0];
            String pwd=umpwd[1];
            int  i= us.login(username,pwd);
            if(i==1){
                return new RespObj(101,DataTool.getToken());
            }
        }catch (Exception e){
            return new RespObj(303,"wrong token");
        }
        return new RespObj(101,"wrong token");
    }
    protected Object upload(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        String key = req.getParameter("key");
        String name = req.getParameter("name");
        String album = req.getParameter("album");
        String token = req.getParameter("token");
        String[] info = DataTool.getInfo(token);
        String username= info[0];
        int i=as.addPhoto(key,name,album,username);
        if(i!=0){
            return new RespObj(101,"ok");
        }else{
            return new RespObj(303,"wrong");
        }
    }
    protected Object addAlbum(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        String  username = req.getParameter("username");
        String  albumName = req.getParameter("albumName");
        int i=as.addAlbum(username,albumName);
       if(i!=0){
           return new RespObj(101,"add album success");
       }else {
           return new RespObj(303,"add album fail");
       }
    }
    //删除照片 根据照片id
    protected Object deletePhoto(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        String  id = req.getParameter("id");
        System.out.println(id);
        int i=as.deletePhoto(Integer.parseInt(id));
        if(i!=0){
            return new RespObj(101,"delete success");
        }else{
            return new RespObj(303,"delete fail");
        }

    }
    //删除相册 根据相册id
    protected Object deleteAlbum(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
       // String  id = req.getParameter("id");
        String  username = req.getParameter("username");
        String  albumName = req.getParameter("albumName");
        int id=as.selectIdByUsernameAndAlbumname(username,albumName);
        int i=as.deleteAlbum(id);
        if(i!=0){
            return new RespObj(101,"delete success");
        }else{
            return new RespObj(303,"delete fail");
        }
    }
    //修改密码
    protected Object modifyPwd(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        String  username = req.getParameter("username");
        String  passwordOld = req.getParameter("passwordOld");
        String  passwordNew = req.getParameter("passwordNew");
        int i = us.comfirm(username,passwordOld);
        if(i!=0){
            int ret=us.modifyPwd(username,passwordNew);
            if(ret!=0){
                return new RespObj(101,"modify password success!");
            }else{
                return new RespObj(303,"modify password error!");
            }
        }else{
            return new RespObj(303,"old_passwd error!");
        }
    }
    protected Object login(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        //客户端传入账号密码
        String username = req.getParameter("username");
        String password= req.getParameter("pwd");
        int i=us.login(username,password);
        if(i==0){
            return new RespObj(302,"username or password is wrong");
        }
        //计算token
        String token = DataTool.encrypt(username+" "+password,DataTool.appKey);
        resp.addCookie(new Cookie("token",token));
        resp.addCookie(new Cookie("username",username));
        Map<String,Object> map = new HashMap<>();
        map.put("username",username);
        map.put("token",token);
        //获取所有相册列表
        List<Album> list = as.getAllAlbum(username);
        map.put("albums",list);
        return new RespObj(101,"ok",map);
    }
    protected Object register(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        System.out.println("register");
        //客户端传入账号密码
        String username = req.getParameter("username");
        String password= req.getParameter("pwd");
        if(username==null || password==null){
            return new RespObj(301,"username or password is null");
        }
        int i=us.register(username,password);
        if(i==1){
            return new RespObj(101,"ok");
        }else{
            return new RespObj(301,"username or password is wrong");
        }
    }
    protected Object refresh(HttpServletRequest req,HttpServletResponse resp)throws ServletException, IOException{
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //客户端传入账号密码
        String username = req.getParameter("username");
        Map<String,Object> map = new HashMap<>();
        //获取所有相册列表
        List<Album> list = as.getAllAlbum(username);
        map.put("albums",list);
        return new RespObj(101,"ok",map);

    }


}
