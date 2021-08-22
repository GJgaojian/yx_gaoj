package cn.baizhi.service;

import cn.baizhi.dao.UserDao;
import cn.baizhi.entity.User;
import cn.baizhi.utils.AliYunOssUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao ud;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Map<String, Object> queryByPage(int page, int size) {

        HashMap<String,Object> hm = new HashMap<>();

        List<User> users = ud.queryByPage((page - 1) * size, size);
        for (User user : users) {
            System.out.println(user);
        }

        Integer pageCount = ud.queryCount();
        System.out.println("总条数:  "+pageCount);

        int pagenum = pageCount%size==0?pageCount/size:pageCount/size+1;


        hm.put("data",users);
        hm.put("page",page);
        hm.put("pagenum",pagenum);

        return hm;
    }

    @Override
    public void updateStatus(String id, int number) {
        ud.updateStatus(id,number);
    }


    @Override
    public void save(MultipartFile photo, User user) {
        OSS ossClient = AliYunOssUtil.getOssClient();
        // 填写Byte数组。
        byte[] content = null;
        try {
            content = photo.getBytes();
        }catch (Exception e){
            e.printStackTrace();
        }
// 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
        ossClient.putObject("gao-jian", photo.getOriginalFilename(), new ByteArrayInputStream(content));
// 关闭OSSClient。
        ossClient.shutdown();

        user.setId(UUID.randomUUID().toString());
        user.setCreate_date(new Date());
        user.setHead_img("https://gao-jian.oss-cn-beijing.aliyuncs.com/"+photo.getOriginalFilename());
        user.setStatus(0);
        System.out.println(user);
        System.out.println("save 业务执行了2");
        ud.insertUser(user);
    }


    @Override
    public void delete(String id) {
//        根据id查1个  得到图片路径
        User user = ud.queryById(id);
//        删除图片
        String head_img = user.getHead_img();

        OSS ossClient = AliYunOssUtil.getOssClient();

        // 填写Bucket名称。
        String bucketName = "gao-jian";
// 填写文件完整路径。文件完整路径中不能包含Bucket名称。
        String objectName = head_img;

// 删除文件或目录。如果要删除目录，目录必须为空。
        ossClient.deleteObject(bucketName, objectName);
// 关闭OSSClient。
        ossClient.shutdown();
//        删除用户信息
         ud.delete(id);
    }
}
