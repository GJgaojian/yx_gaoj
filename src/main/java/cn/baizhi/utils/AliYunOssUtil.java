package cn.baizhi.utils;

import cn.baizhi.config.AliYunConfig;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class AliYunOssUtil {

    public static OSS getOssClient(){
        String endpoint = AliYunConfig.ENDPOINT;
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录RAM控制台创建RAM账号。
        String accessKeyId = AliYunConfig.ACCESSKEYID;
        String accessKeySecret = AliYunConfig.ACCESSKEYSECRET;

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        return ossClient;
    }
}
