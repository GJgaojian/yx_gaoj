package cn.baizhi;

import cn.baizhi.dao.AdminDao;
import cn.baizhi.dao.CategoryDao;
import cn.baizhi.dao.UserDao;
import cn.baizhi.dao.VideoDao;
import cn.baizhi.entity.Admin;
import cn.baizhi.entity.Category;
import cn.baizhi.entity.User;
import cn.baizhi.entity.Video;
import cn.baizhi.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class YxGaojApplicationTests {

    @Autowired
    private AdminDao adminDao;
    @Autowired
    private UserDao ud;

    @Autowired
    private CategoryDao cd;

    @Autowired
    private UserServiceImpl us;

    @Autowired
    private VideoDao vd;

    @Test
    void contextLoads() {
        List<Video> all = vd.findAll();
        for (Video video : all) {
            System.out.println(video);
        }

//        cd.insertCategory(new Category("77","lala",2,"1"));

//        List<Category> categories = cd.queryByParent("1");
//        for (Category category : categories) {
//            System.out.println(category);
//        }

//        List<Category> categories = cd.queryByLevels(1);
//        for (Category category : categories) {
//            System.out.println(category);
//        }
//        ud.updateStatus("1",1);

//        us.queryByPage(1,3);

//        List<User> users = ud.queryByPage(0, 3);
//        for (User user : users) {
//            System.out.println(user);
//        }
//        Admin admin = adminDao.queryByUserName("tom");
//        System.out.println(admin);
    }

}
