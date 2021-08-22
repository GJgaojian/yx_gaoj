package cn.baizhi.service;

import cn.baizhi.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface UserService {

    Map<String,Object> queryByPage(int page,int size);

    void updateStatus(String id,int number);

    void save(MultipartFile file, User user);

    void delete(String id);
}
