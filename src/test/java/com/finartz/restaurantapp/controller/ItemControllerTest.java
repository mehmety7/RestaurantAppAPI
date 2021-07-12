package com.finartz.restaurantapp.controller;

import com.finartz.restaurantapp.model.Item;
import com.finartz.restaurantapp.repository.ItemRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class ItemControllerTest {

    @Mock
    private ItemRepository itemRepository;

    @InjectMocks
    private ItemController itemController;


    @Test
    public void testAddItem()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        when(itemRepository.save(any(Item.class))).thenReturn(true);

        Item item = new Item(1L, "Hamburger", "piece");
        ResponseEntity<Item> responseEntity = itemController.create(item);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }


    @Test
    public void testFindAll()
    {
        // given
        Item item1 = new Item(1L, "Hamburger", "piece");
        Item item2 = new Item(2L, "Cheeseburger", "piece");
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);

        when(itemRepository.findAll()).thenReturn(items);

        // when
        ResponseEntity<List<Item>> result = itemController.getAll();

        // then
        assertThat(result.getBody().size()).isEqualTo(2);

        assertThat(result.getBody().get(0).getName())
                .isEqualTo(item1.getName());

        assertThat(result.getBody().get(1).getName())
                .isEqualTo(item2.getName());
    }

}
