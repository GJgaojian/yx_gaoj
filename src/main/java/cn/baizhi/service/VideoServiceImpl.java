package cn.baizhi.service;

import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.Video;
import cn.baizhi.utils.AliYunOssUtil;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoDao vd;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Video> findAll() {
        return vd.findAll();
    }

    @Override
    public void add(MultipartFile file, Video video) {
        OSS ossClient = AliYunOssUtil.getOssClient();
        String bucketName = "gao-jian";
        String objectName="video/video";
        byte[] content = null;
        try {
            content = file.getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject(bucketName, objectName+"/"+file.getOriginalFilename(), new ByteArrayInputStream(content));
        System.out.println("上传完成");
//        // 使用精确时间模式截取视频1s处的内容，输出为JPG格式的图片，宽度为800，高度为600。
        String style = "video/snapshot,t_1000,f_jpg,w_800,h_600";
        // 指定过期时间为10分钟。
        Date expiration = new Date(new Date().getTime() + 1000 * 60 * 10 );
        GeneratePresignedUrlRequest req = new GeneratePresignedUrlRequest(bucketName, objectName+"/"+file.getOriginalFilename(), HttpMethod.GET);
        req.setExpiration(expiration);
        req.setProcess(style);
        URL signedUrl = ossClient.generatePresignedUrl(req);
        System.out.println("封面路径:  "+signedUrl);


        InputStream inputStream = null;
        try {
            // 填写网络流地址。
            inputStream = new URL(signedUrl.toString()).openStream();
        }catch (Exception e){
            e.printStackTrace();
        }
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        String[] split = file.getOriginalFilename().split("\\.");
        ossClient.putObject(bucketName, "video/img/"+split[0]+".jpg", inputStream);

        ossClient.shutdown();

        video.setId(UUID.randomUUID().toString());
        String path = "https://gao-jian.oss-cn-beijing.aliyuncs.com/video/";
        video.setCoverPath(path+"img/"+split[0]+".jpg");
        video.setVideoPath(path+"video/"+file.getOriginalFilename());
        video.setCreateDate(new Date());
        vd.add(video);
    }
}
