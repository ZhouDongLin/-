package com.example.unit;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mchange.v2.c3p0.DataSources;
import com.qiniu.util.Auth;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.dbutils.QueryRunner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public  class DataTool {

    public static final ComboPooledDataSource dataSource = new ComboPooledDataSource();
    static{
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/album?useSSL=false");
        dataSource.setUser("root");
        dataSource.setPassword("Sy200186");
        try {
            dataSource.setDriverClass("com.mysql.cj.jdbc.Driver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        dataSource.setInitialPoolSize(10);
        dataSource.setMaxPoolSize(50);
    }
    static String accessKey = "8TsjVAy2beBx6P5ppPvoryXANNgEPlsI44aK3ZF8";
    static String secretKey = "sRBQOnOjLwjTS79QshMUass87FpeAmcI5J5ut4HS";
    static  String bucket = "jokersss";
    static Auth auth = Auth.create(accessKey, secretKey);
    static final String baseUrl="http://r4ggxungf.hd-bkt.clouddn.com";
    public static final String appKey = "sy";
    private static final String KEY_ALGORITHM = "AES";
    private static final String DEFAULT_CIPHER_ALGORITHM = "AES";
    public static final QueryRunner runner = new QueryRunner(DataTool.dataSource);
    public static String getToken(){
        //System.out.println(upToken);
        return auth.uploadToken(bucket);
    }
    public  static String downloadPic(String key){
        String url = baseUrl+"/"+key;
        return auth.privateDownloadUrl(url,3600);
    }




    /**
     * AES 加密操作
     *
     * @param content 待加密内容
     * @param appKey  加密appKey
     * @return 返回Base64转码后的加密数据
     */
    public static String encrypt(String content, String appKey) {
        try {
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);// 创建密码器

            byte[] byteContent = content.getBytes("utf-8");

            cipher.init(Cipher.ENCRYPT_MODE, getSecretKey(appKey));// 初始化为加密模式的密码器

            byte[] result = cipher.doFinal(byteContent);// 加密

            return Base64.encodeBase64String(result);// 通过Base64转码返回
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * AES 解密操作
     *
     * @param content 待解密内容
     * @param appKey  加密appKey
     * @return
     */
    public static String decrypt(String content, String appKey) {

        try {
            // 实例化
            Cipher cipher = Cipher.getInstance(DEFAULT_CIPHER_ALGORITHM);

            // 使用密钥初始化，设置为解密模式
            cipher.init(Cipher.DECRYPT_MODE, getSecretKey(appKey));

            // 执行操作
            byte[] result = cipher.doFinal(Base64.decodeBase64(content));

            return new String(result, "utf-8");
        } catch (Exception ex) {
//			Logger.getLogger(AESUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * 生成加密秘钥
     *
     * @return
     */
    private static SecretKeySpec getSecretKey(String appKey) {
        // 返回生成指定算法密钥生成器的 KeyGenerator 对象
        KeyGenerator kg = null;

        try {
            kg = KeyGenerator.getInstance(KEY_ALGORITHM);

            // SecureRandom 实现随操作系统本身的內部状态，除非调用方在调用 getInstance 方法之后又调用了 setSeed 方法；该实现在
            // windows 上每次生成的 key 都相同，但是在 solaris 或部分 linux 系统上则不同。解决在linux操作系统中加密产生的字符串不一致问题。
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

            secureRandom.setSeed(appKey.getBytes());

            // AES 要求密钥长度为 128
            kg.init(128, secureRandom);

            // 生成一个密钥
            SecretKey secretKey = kg.generateKey();

            return new SecretKeySpec(secretKey.getEncoded(), KEY_ALGORITHM);// 转换为AES专用密钥
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }

        return null;
    }

   public static String[] getInfo(String token) {
       String info = decrypt(token, appKey);
        return info.split(" ");

   }

}
