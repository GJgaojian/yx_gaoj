package cn.baizhi.service;

import cn.baizhi.entity.Category;

import java.util.List;


public interface CategoryService {


    List<Category> queryByLevels(int levels);


    List<Category> queryByParent(String parentId);

    void insertCategory(Category category);

    void deleteById(String id);
}
