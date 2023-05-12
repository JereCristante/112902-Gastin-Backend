package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.CategoryDTO;
import com.gastin.app.Gastin.Exceptions.ResourceNotFoundException;
import com.gastin.app.Gastin.Model.Category;
import com.gastin.app.Gastin.Model.MovementType;
import com.gastin.app.Gastin.Model.User;
import com.gastin.app.Gastin.Repository.CategoryRepository;
import com.gastin.app.Gastin.Repository.MovementTypeRepository;
import com.gastin.app.Gastin.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MovementTypeRepository movementTypeRepository;
    @Override
    public CategoryDTO createCategory(Long usuario_id, Long tipo_movimiento_id, CategoryDTO categoryDTO) {
        Category category = entityMapping(categoryDTO);
        User user = userRepository.findById(usuario_id).orElseThrow(()-> new ResourceNotFoundException("Usuario","id",usuario_id));
        MovementType movementType = movementTypeRepository.findById(tipo_movimiento_id).orElseThrow(()-> new ResourceNotFoundException("TipoMovimiento","id",tipo_movimiento_id));
        category.setUser(user);
        category.setMovementType(movementType);
        Category newCategory = categoryRepository.save(category);
        return dtoMapping(newCategory);
    }

    @Override
    public CategoryDTO updateCategory(Long tipo_movimiento_id, CategoryDTO categoryDTO, Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Categoria","id",id));
        category.setDescription(categoryDTO.getDescription());
        category.setIcon(categoryDTO.getIcon());
        MovementType movementType = movementTypeRepository.findById(tipo_movimiento_id).orElseThrow(()-> new ResourceNotFoundException("TipoMovimiento","id",tipo_movimiento_id));
        category.setMovementType(movementType);
        Category newCategory = categoryRepository.save(category);
        return dtoMapping(newCategory);
    }

    @Override
    public List<CategoryDTO> findCategoriesByUserAndType(Long usuario_id, Long tipo_movimiento_id) {
        List<Category> categories = categoryRepository.findByUserId(usuario_id);
        List<Category> filteredCategories = categories.stream().filter(category -> category.getMovementType().getId().equals(tipo_movimiento_id)).collect(Collectors.toList());
        return filteredCategories.stream().map(category -> dtoMapping(category)).collect(Collectors.toList());
    }

    private CategoryDTO dtoMapping(Category category){
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(category.getId());
        categoryDTO.setDescription(category.getDescription());
        categoryDTO.setIcon(category.getIcon());
        return categoryDTO;
    }
    private Category entityMapping(CategoryDTO categoryDTO){
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setDescription(categoryDTO.getDescription());
        category.setIcon(categoryDTO.getIcon());
        return category;
    }
}
