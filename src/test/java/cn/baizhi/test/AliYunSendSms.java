package cn.baizhi.test;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
/*
pom.xml
<dependency>
  <groupId>com.aliyun</groupId>
  <artifactId>aliyun-java-sdk-core</artifactId>
  <version>请参见最新版本号</version>
</dependency>
*/
public class AliYunSendSms {
    public static void main(String[] args) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "LTAI5t8ZP4TY8F2CdYnhBu2t", "03M7kfvHWlIrACTk1aqTzSy0eFDkED");
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("PhoneNumbers", "13141234762");
        request.putQueryParameter("SignName", "身份验证");
        request.putQueryParameter("TemplateCode", "SMS_4020642");
//      验证码
        String yzm = "1314";
        String product="微信";
        request.putQueryParameter("TemplateParam", "{\"code\":\""+yzm+"\",\"product\":\""+product+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}