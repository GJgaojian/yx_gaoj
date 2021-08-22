package cn.baizhi.service;

import cn.baizhi.dao.AdminDao;
import cn.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Controller
@Transactional
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminDao ad;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Admin queryByName(String name) {
        return ad.queryByUserName(name);


    }
}
