
package com.mercadolibre.database.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mercadolibre.database.entity.ItemEntity;

public interface ItemRepository extends CrudRepository<ItemEntity, String> {
    @Override
    public List<ItemEntity> findAllById(Iterable<String> ids);
}
