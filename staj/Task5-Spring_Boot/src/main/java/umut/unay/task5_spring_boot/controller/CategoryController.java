package umut.unay.task5_spring_boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umut.unay.task5_spring_boot.entity.CategoryInfo;
import umut.unay.task5_spring_boot.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController
{
    @Autowired
    private CategoryService service;

    // Get
    // Post
    @PostMapping("/add-category")
    public void addCategory(@RequestBody CategoryInfo category)
    {
        service.addCategory(category);
    }

    // Put
    // Delete
}
