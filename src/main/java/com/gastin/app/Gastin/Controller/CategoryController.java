package com.gastin.app.Gastin.Controller;

import com.gastin.app.Gastin.DTO.CategoryDTO;
import com.gastin.app.Gastin.DTO.MovementDTO;
import com.gastin.app.Gastin.Model.Category;
import com.gastin.app.Gastin.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
@CrossOrigin
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/{usuarioId}/{tipoMovimientoId}/categories")
    public ResponseEntity<CategoryDTO> saveCategory(@PathVariable(value = "usuarioId")Long usuarioId,@PathVariable(value = "tipoMovimientoId")Long tipoMovimientoId, @RequestBody CategoryDTO categoryDTO){
        return new ResponseEntity<>(categoryService.createCategory(usuarioId,tipoMovimientoId,categoryDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{usuarioId}/{tipoMovimientoId}/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(@PathVariable(value = "usuarioId")Long usuarioId,@PathVariable(value = "tipoMovimientoId")Long tipoMovimientoId){
        return new ResponseEntity<List<CategoryDTO>>(categoryService.findCategoriesByUserAndType(usuarioId,tipoMovimientoId), HttpStatus.OK);
    }
    @PutMapping("/{categoriaId}/{tipoMovimientoId}/categories")
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable(value = "tipoMovimientoId")Long tipoMovimientoId,@RequestBody CategoryDTO categoryDTO,@PathVariable(value = "categoriaId")Long categoriaId){
        return new ResponseEntity<CategoryDTO>(categoryService.updateCategory(tipoMovimientoId,categoryDTO,categoriaId), HttpStatus.OK);
    }
}
