
package com.mercadolibre.database.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.mercadolibre.database.entity.ItemEntity;

@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource(
        locations = "classpath:application-test.properties")
@Sql(
        scripts = "classpath:data-test.sql")
class ItemRepositoryTest {
    private static final Logger logger = LoggerFactory.getLogger(ItemRepositoryTest.class);

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void testFindAllByIdIterableOfString() {
        String mla1Id = "MLA1";
        String mla2Id = "MLA2";
        String mla3Id = "MLA3";

        List<String> ids = Arrays.asList(mla1Id, mla2Id, mla3Id);

        List<ItemEntity> items = itemRepository.findAllById(ids);

        assertThat(items.size()).isEqualTo(ids.size());

        for (ItemEntity item : items) {
            assertTrue(ids.contains(item.getId()));
            assertNotNull(item.getPrice());

            logger.info("Item[{}]", item);
        }
    }
}
