package cn.baizhi.test;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Bucket;
import org.junit.Test;

import java.util.List;

public class AliYunOssTest {
    @Test
    public void testCreateBucket() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "https://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
//        String accessKeyId = "<yourAccessKeyId>";
        String accessKeyId = "LTAI5t8ZP4TY8F2CdYnhBu2t";
//        String accessKeySecret = "<yourAccessKeySecret>";
        String accessKeySecret = "03M7kfvHWlIrACTk1aqTzSy0eFDkED";
//        String bucketName = "<yourBucketName>";
        String bucketName = "gaoj";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 创建存储空间。
        ossClient.createBucket(bucketName);

// 关闭OSSClient。
        ossClient.shutdown();
    }

    @Test
    public void queryAllBuckets(){
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = "LTAI5t8ZP4TY8F2CdYnhBu2t";
        String accessKeySecret = "03M7kfvHWlIrACTk1aqTzSy0eFDkED";

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 列举存储空间。
        List<Bucket> buckets = ossClient.listBuckets();
        for (Bucket bucket : buckets) {
            System.out.println(" - " + bucket.getName());
        }

// 关闭OSSClient。
        ossClient.shutdown();
    }



    @Test
    public void uploadFile(){

    }
}