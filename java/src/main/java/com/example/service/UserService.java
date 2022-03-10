package com.example.service;

import com.example.Dao.AlbumDao;
import com.example.Dao.UserDao;

public class UserService {
    UserDao userDao = new UserDao();
    AlbumDao  albumDao = new AlbumDao();
    public int register(String username, String password) {
        int i=userDao.regist(username,password);
        System.out.println("i= "+i);
        if(i!=0){
            //add album
        albumDao.createAlbum(username,"默认相册");
        }
        return i;
    }

    public int login(String username, String password) {
        return userDao.login(username,password);
    }

    public int comfirm(String username, String passwordOld) {
        return userDao.login(username,passwordOld);
    }

    public int modifyPwd(String username, String passwordNew) {
       return userDao.modifyPwd(username,passwordNew);
    }
}
