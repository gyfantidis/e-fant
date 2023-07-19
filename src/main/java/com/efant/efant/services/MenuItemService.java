package com.efant.efant.services;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.MenuItem;
import com.efant.efant.repositories.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuItemService {

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository){
        this.menuItemRepository = menuItemRepository;
    }


    public List<MenuItem> getAllMenuItems(){
        return menuItemRepository.findAll();
    }

    public MenuItem getMenuItemById(Long id) throws Exception{
        return menuItemRepository.findById(id)
                .orElseThrow(() -> new EfantException("ITEM_NOT_FOUND", "Item not exists with id: " + id, HttpStatus.NOT_FOUND));

    }

    public MenuItem createMenuItem(MenuItem menuItem) throws Exception{
        if (menuItem.getItemId() != null) {
            throw new EfantException("NEW_ITEM_ID_IS_NOT_NULL", "Item id must be null", HttpStatus.BAD_REQUEST);
        }
        menuItem = menuItemRepository.save(menuItem);
        return menuItem;
    }

    public MenuItem updateMenuItem(MenuItem menuItem) throws Exception{
        Long itemsId = menuItem.getItemId();
        MenuItem existingMenuItem = menuItemRepository.findById(itemsId)
                .orElseThrow(() -> new EfantException("ITEM_NOT_FOUND", "Item not exists with id: " + itemsId, HttpStatus.NOT_FOUND));

        existingMenuItem.setName(menuItem.getName());
        existingMenuItem.setDescription(menuItem.getDescription());
        existingMenuItem.setPrice(menuItem.getPrice());
        existingMenuItem.setImageUrl(menuItem.getImageUrl());

        existingMenuItem = menuItemRepository.save(existingMenuItem);
        return existingMenuItem;

    }



    public void deleteMenuItem(Long id) throws Exception{
        MenuItem menuItem = menuItemRepository.findById(id).orElse(null);

        if(menuItem != null){
            menuItemRepository.deleteById(id);
        }
        else{
            throw new EfantException("ITEM_NOT_FOUND", "Item not exists with id: " + id, HttpStatus.NOT_FOUND);

        }
    }


}
