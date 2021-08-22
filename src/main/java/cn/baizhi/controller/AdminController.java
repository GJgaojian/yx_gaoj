package cn.baizhi.controller;

import cn.baizhi.entity.Admin;
import cn.baizhi.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping("/admin")
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService as;

    @RequestMapping("/login")
    public Map<String, Object> login(@RequestBody Admin admin){
        log.debug(admin.getUsername());
        log.debug(admin.getPassword());
        HashMap<String,Object> hm = new HashMap<>();

        Admin queryAdmin = as.queryByName(admin.getUsername());

        if(queryAdmin != null){
            if(queryAdmin.getPassword().equals(admin.getPassword())){
                hm.put("status",true);
                hm.put("msg","登录成功");
            }else{
                hm.put("status",false);
                hm.put("msg","密码错误");
            }
        }else{
            hm.put("status",false);
            hm.put("msg","没有这个 用户");
        }
//
//        log.debug(queryAdmin.toString());

        return hm;

//        return false;
    }
}
