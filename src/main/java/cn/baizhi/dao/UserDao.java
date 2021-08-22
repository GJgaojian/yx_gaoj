package cn.baizhi.dao;

import cn.baizhi.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    List<User> queryByPage(@Param("start") int start, @Param("end") int end);

//    总条数
    Integer queryCount();

//    改变状态
    void updateStatus(@Param("id") String id,@Param("number") int number);

//    添加
    void insertUser(User user);

//    根据id删除
    void delete(String id);

//    根据id查1个
    User queryById(String id);
}
