package cn.baizhi.controller;

import cn.baizhi.entity.User;
import cn.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService us;

    @RequestMapping("/queryByPage")
    public Map<String,Object> queryByPage(int page){
        int size=6;
        return us.queryByPage(page,size);
    }


    @RequestMapping("/updateStatus")
    public void updateStatus(String id,int number){
        System.out.println(id);
        System.out.println(number);
        us.updateStatus(id,number);
    }


    @RequestMapping("/add")
    public void add(MultipartFile photo, String username, String phone, String brief) throws IOException {
        System.out.println("add方法执行了");
        User user = new User(null, username, phone, null, brief, null, null, null, null);
        us.save(photo,user);
    }

    @RequestMapping("/delete")
    public void delete(String id){
        us.delete(id);
    }

    @RequestMapping("/a")
    public List<String> a(){
        return Arrays.asList("1月","2月","3月","4月","5月","6月");
    }
}
