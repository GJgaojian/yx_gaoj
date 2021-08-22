package cn.baizhi.controller;

import cn.baizhi.entity.Category;
import cn.baizhi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService cs;

    @RequestMapping("/queryByLevels")
    public List<Category> queryByLevels(int levels){
        return cs.queryByLevels(levels);
    }

    @RequestMapping("/queryByParent")
    public List<Category> queryByParent(String parentId){
        return cs.queryByParent(parentId);
    }

    @RequestMapping("/insertCategory")
    public void insertCategory(@RequestBody Category category){
        System.out.println(category);
        cs.insertCategory(category);
    }

    @RequestMapping("/deleteCategory")
    public void deleteCategory(String id){
        System.out.println(id);
        cs.deleteById(id);
    }
}
