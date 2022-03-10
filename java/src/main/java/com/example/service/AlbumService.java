package com.example.service;

import com.example.Dao.AlbumDao;
import com.example.bean.Album;
import com.example.bean.Photo;
import com.example.unit.DataTool;

import java.util.List;

public class AlbumService {
    AlbumDao albumDao = new AlbumDao();
    public List<Album> getAllAlbum(String username) {
        List<Album> album = new AlbumDao().getAllAlbum(username);
        //为相册中的照片url赋值
        for (Album album1 : album) {
            for (Photo photo : album1.getPhotos()) {
                photo.setUrl(DataTool.downloadPic(photo.getKey()));
            }
        }
        return album;
    }


    public int addPhoto(String key, String name, String album, String username) {
        //根据用户名查id
        int id = albumDao.getAlbumId(username,album);

        int i = albumDao.addPhoto(key,name,id);
        return i;
    }

    public int deletePhoto(int id) {
        return albumDao.deletePhoto(id);
    }

    public int deleteAlbum(int id) {
        List<Photo> photosByAlbumId = albumDao.getPhotosByAlbumId(id);
        for (Photo photo : photosByAlbumId) {
            albumDao.deletePhoto(photo.getId());
        }
        int i=albumDao.deleteAlbum(id);
        return i;
    }

    public int addAlbum(String username, String albumName) {

        return albumDao.addAlbum(username,albumName);
    }

    public int selectIdByUsernameAndAlbumname(String username, String albumName) {
        return albumDao.selectIdByUsernameAndAlbumname(username,albumName);
    }
}
