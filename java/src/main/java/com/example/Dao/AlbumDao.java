package com.example.Dao;

import com.example.bean.Album;
import com.example.bean.Photo;
import com.example.unit.DataTool;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AlbumDao {
    public int  createAlbum(String username,String albumName) {
        String sql = "insert into albums values(null,?,?,?)";
        int i=0;
        try {
            Date date=new Date();   //这里的时util包下的
            SimpleDateFormat temp=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  //这是24时
            String Date=temp.format(date);
           i= DataTool.runner.update(sql,albumName,Date,username);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return i;
    }

    public List<Album> getAllAlbum(String username) {

       String sql = "select id,name,createTime from albums where username=?";
//        String  sql="select a.id id,a.name name,a.createTime createTime,p.id 'photos.id',p.key 'photos.`key`',p.name 'photos.`name`'\n" +
//                "from user u left join albums a on u.userName=a.username\n" +
//                "left join photos p on a.id=p.albumid\n" +
//                "where u.userName=?";
        List<Album> albums=null;
        try {
            //查询到所有的相册
            albums = DataTool.runner.query(sql, new BeanListHandler<Album>(Album.class), username);
            //根据相册查询相册中照片
            for (Album album : albums) {
                List<Photo> photos = getPhotos(album.getId());
                album.setPhotos(photos);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return albums;
    }

    //根据相册id获取图片
    public List<Photo> getPhotosByAlbumId(int id){
        String sql ="select id,name from photos where albumid=?";
        List<Photo> photoIdList=null;
        try {
           photoIdList = DataTool.runner.query(sql, new BeanListHandler<Photo>(Photo.class), id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
      return photoIdList;
    }


    public int addPhoto(String key, String name, int albumId) {
            String sql = "insert into photos values(null,?,?,?,?)";
        try {
            int i= DataTool.runner.update(sql,key,name,new Date(),albumId);
            return i;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }


  public  List<Photo> getPhotos(int id){
        String sql = "select *from photos where albumid=?";
      List<Photo> query=null;
      try {
         query = DataTool.runner.query(sql, new BeanListHandler<Photo>(Photo.class), id);
      } catch (SQLException throwables) {
          throwables.printStackTrace();
      }
      return query;
  }

    public int getAlbumId(String username,String albumName){
        String sql = "select id from albums where username=? and name=?";
        int id=0;
        try {
            id = DataTool.runner.query(sql, new ScalarHandler<Integer>(), username, albumName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return id;
    }

    public int deletePhoto(int id) {
        String sql = "delete from photos where id =?";
        int ret=0;

        try {
           ret= DataTool.runner.update(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        return ret;
    }

    public int deleteAlbum(int id) {
        String sql = "delete from albums where id = ?";
        int ret =0;
        try {
            ret=DataTool.runner.update(sql,id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ret;
    }

    public int addAlbum(String username, String albumName) {
        return createAlbum(username,albumName);
    }

    public int selectIdByUsernameAndAlbumname(String username, String albumName) {
        String sql = "select id from albums where username=? and name=?";
        int s=0;
        try {
            s = DataTool.runner.query(sql, new ScalarHandler<Integer>(), username, albumName);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return  s;
    }
}
