package com.gastin.app.Gastin.Service;

import com.gastin.app.Gastin.DTO.AccountDTO;
import com.gastin.app.Gastin.DTO.CategoryDTO;
import com.gastin.app.Gastin.Model.Category;

import java.util.List;

public interface CategoryService {
    public CategoryDTO createCategory(Long usuario_id,Long tipo_movimiento_id,CategoryDTO categoryDTO);
    public CategoryDTO updateCategory(Long tipo_movimiento_id,CategoryDTO categoryDTO, Long id);
    public List<CategoryDTO> findCategoriesByUserAndType(Long usuario_id,Long tipo_movimiento_id);
}
