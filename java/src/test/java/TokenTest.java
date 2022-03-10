import com.example.Dao.AlbumDao;
import com.example.Dao.UserDao;
import com.example.bean.Photo;
import com.example.service.AlbumService;
import com.example.service.UserService;
import com.example.unit.DataTool;
import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;

import java.util.List;

public class TokenTest {

    @Test
    public void testToken(){
        String accessKey = "8TsjVAy2beBx6P5ppPvoryXANNgEPlsI44aK3ZF8";
        String secretKey = "sRBQOnOjLwjTS79QshMUass87FpeAmcI5J5ut4HS";
        String bucket = "jokersss";
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);
        System.out.println(upToken);
    }
    @Test
    public void testInsert(){
        System.out.println(new UserService().register("123", "123"));
    }
    @Test
    public void testLogin(){
        System.out.println(new UserDao().login("123","123"));
    }
    @Test
    public void testAlbum(){

        System.out.println(new AlbumService().getAllAlbum("123"));
    }
    @Test
    public void testAESEncode(){
        String token = DataTool.encrypt("123abc",DataTool.appKey);
        System.out.println(token);
    }
    @Test
    public void testAESDecode(){
        System.out.println(DataTool.decrypt("JEO+IQ7isM8BRX3uAdZJmg==", DataTool.appKey));
    }


    @Test
    public void testDe(){
        System.out.println(new AlbumService().deletePhoto(19));
    }
    @Test
    public void testPhotoid(){
        List<Photo> photosByAlbumId = new AlbumDao().getPhotosByAlbumId(7);
        for (Photo photo : photosByAlbumId) {
            System.out.println(photo.getId());
            System.out.println(photo.getName());
        }
    }
    @Test
    public void testDAlbum(){
        new AlbumService().deleteAlbum(10);
        new AlbumService().deleteAlbum(11);
        int i = new AlbumService().deleteAlbum(12);
        System.out.println(i);

    }
    @Test
    public void testModifyPwd(){
        System.out.println(new UserService().modifyPwd("123", "13224235"));

    }
    @Test
    public void testAddAlbum(){
        System.out.println(new AlbumService().addAlbum("1234", "test"));

    }
    @Test
    public void testSelectId(){
        System.out.println(new AlbumService().deleteAlbum(new AlbumService().selectIdByUsernameAndAlbumname("1", "test1")) );

    }
}
