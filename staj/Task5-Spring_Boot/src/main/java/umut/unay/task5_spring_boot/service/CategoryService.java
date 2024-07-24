package umut.unay.task5_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umut.unay.task5_spring_boot.entity.CategoryInfo;
import umut.unay.task5_spring_boot.repository.CategoryInfoRepository;

@Service
public class CategoryService
{
    @Autowired
    private CategoryInfoRepository repository;

    // Get
    // Post
    public void addCategory(CategoryInfo category)
    {
        repository.save(category);
    }

    // Put
    // Delete
}
