package com.myren.util;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.MultipartUpload;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoRequest;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;
public class OSSUtil {
     //定义外网访问的参数 --地域节点
     private static String EndPoint = "oss-cn-beijing.aliyuncs.com";
     private  static String AccessKeyID = "LTAI5tMhKpjBrgZogWSXr2v1";
     private static String AccessKeySecret = "iov5KclkRESC8hC4Z3l4ZA1mXjMTmY";
     private static String BucketName = "video-future-01"; //仓库名称
     private static String SufferUrl = "http://video-future-01.oss-cn-beijing.aliyuncs.com/"; //文件上传成功后返回的路径，类似二级域名
     private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); //格式化时间
    public static void upload(String md5,String index) throws IOException {//md5是m3u8文件的唯一名字，index是需要上传的m3u8文件的原地址。
        String endpoint = EndPoint;//oss域名
        String accessKeyId = AccessKeyID;//ossId
        String accessKeySecret = AccessKeySecret;//ossKey
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        URLConnection url = new URL(index).openConnection(); ////index为m3u8地址
        InputStream inputStream = url.getInputStream();
        ossClient.putObject("yirujun", "video/"+md5+"/"+md5+".m3u8", inputStream);//第二个参数为oss文件存放目录，第一个md5是m3u8文件地址,第二个为文件名（这样做是为了让m3u8文件与.ts文件在同一文件夹下)
        inputStream.close();

        URLConnection url1 = new URL(index).openConnection();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url1.getInputStream()));
        String  text = "";
        String tsUrl = index.split("index")[0];//获取m3u8链接的前缀部分
        while((text =bufferedReader.readLine())!= null){//读出m3u8文件,逐个上传 .ts文件
            System.out.println("上传ts文件："+text);
            if(text.indexOf(".ts") != -1){//只需要.ts文件
                try {
                    URLConnection url2 = new URL(tsUrl + text).openConnection();
                    InputStream inputStream2 = url2.getInputStream();
                    ossClient.putObject("yirujun", "video/" + md5 + "/" + text, inputStream2);//第二个参数为oss文件存放目录**
                    inputStream2.close();
                }catch (Exception E){
                    continue;
                }
            }
        }
        ossClient.shutdown();
    }

}