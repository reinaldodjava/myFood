package com.myFood.order.domain.service;

import com.myFood.order.application.exception.CustomException;
import com.myFood.order.domain.model.Ingredient;
import com.myFood.order.domain.model.Snack;
import com.myFood.order.domain.model.SnackIngredient;
import com.myFood.order.domain.model.dto.CrudSnackDTO;
import com.myFood.order.domain.model.dto.SnackDTO;
import com.myFood.order.infra.persistence.repository.SnackRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

/**
 *
 * @author reinaldo.locatelli
 */
@Service
public class SnackService extends AbstractService<Snack, Long> {

    @Autowired
    private SnackRepository snackRepository;

    @Autowired
    private SnackIngredientService snackIngredientService;

    @Autowired
    private IngredientService ingredientService;

    @Override
    protected JpaRepository<Snack, Long> getRepository() {
        return snackRepository;
    }

    public List<SnackDTO> findAllSnack() {
        List<Snack> snacks = snackRepository.findAll();

        List<SnackDTO> snacksDTO = new ArrayList<>();

        snacks.stream().forEach(s -> {
            snacksDTO.add(new SnackDTO(s, snackIngredientService.findBySnackId(s.getId())));
        });

        return snacksDTO;
    }

    public List<SnackDTO> findAllEnabled() {
        List<Snack> snacks = snackRepository.findByEnabled(true);

        List<SnackDTO> snacksDTO = new ArrayList<>();

        snacks.stream().forEach(s -> {
            snacksDTO.add(new SnackDTO(s, snackIngredientService.findBySnackId(s.getId())));
        });

        return snacksDTO;

    }

    @Transactional
    public CrudSnackDTO create(CrudSnackDTO crudSnackDTO) {
        Snack snack = snackRepository.save(crudSnackDTO.getSnack());

        crudSnackDTO.getIngredients().stream().forEach(si -> {
            Optional<Ingredient> optionalIngredient = ingredientService.findById(si.getId());
            if (!optionalIngredient.isPresent()) {
                throw new CustomException("Ingrediente não encontrado", HttpStatus.BAD_REQUEST);
            }
            Ingredient ingredient = optionalIngredient.get();
            snackIngredientService.save(new SnackIngredient(snack.getId(), ingredient, si.getQuantity()));
            si.setPrice(ingredient.getPrice());
            si.setDescription(ingredient.getDescription());
        });

        crudSnackDTO.setId(snack.getId());

        return crudSnackDTO;
    }

    @Transactional
    public CrudSnackDTO update(CrudSnackDTO crudSnackDTO) {
        Snack snack = snackRepository.save(crudSnackDTO.getSnack());

        List<SnackIngredient> snackIngredients = snackIngredientService.findBySnackId(snack.getId());

        snackIngredients.stream().forEach(si -> {
            snackIngredientService.delete(si);
        });

        crudSnackDTO.getIngredients().stream().forEach(si -> {
            Optional<Ingredient> optionalIngredient = ingredientService.findById(si.getId());
            if (!optionalIngredient.isPresent()) {
                throw new CustomException("Ingrediente não encontrado", HttpStatus.BAD_REQUEST);
            }
            Ingredient ingredient = optionalIngredient.get();
            snackIngredientService.save(new SnackIngredient(snack.getId(), ingredient, si.getQuantity()));
            si.setPrice(ingredient.getPrice());
            si.setDescription(ingredient.getDescription());
        });

        return crudSnackDTO;
    }

    @Transactional
    public void deleteById(Long id) {
        List<SnackIngredient> snackIngredients = snackIngredientService.findBySnackId(id);

        snackIngredients.stream().forEach(si -> {
            snackIngredientService.delete(si);
        });

        snackRepository.deleteById(id);
    }

}
