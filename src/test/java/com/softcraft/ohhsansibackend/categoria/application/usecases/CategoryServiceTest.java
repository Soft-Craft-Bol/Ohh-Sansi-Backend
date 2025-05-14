package com.softcraft.ohhsansibackend.categoria.application.usecases;

import com.softcraft.ohhsansibackend.categoria.domain.models.Category;
import com.softcraft.ohhsansibackend.categoria.domain.repository.implementation.CategoryDomainRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DuplicateKeyException;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class CategoryServiceTest {

    @Mock
    private CategoryDomainRepository categoryDomainRepository;

    @InjectMocks
    private CategoryService categoryService;

    public CategoryServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveCategory() {
        Category category = new Category();
        category.setNombreCategoria("Pelicano");


        when(categoryDomainRepository.save(category)).thenReturn(category);

        Map<String, Object> response = categoryService.saveCategory(category);

        assertEquals(true, response.get("success"));
        assertEquals("Categoria creada exitosamente", response.get("message"));
        verify(categoryDomainRepository, times(1)).save(category);
    }
}