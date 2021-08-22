package cn.baizhi.dao;

import cn.baizhi.entity.Category;

import java.util.List;

public interface CategoryDao {
    List<Category> queryByLevels(int levels);


    List<Category> queryByParent(String parentId);

    void insertCategory(Category category);

    void deleteById(String id);
}
