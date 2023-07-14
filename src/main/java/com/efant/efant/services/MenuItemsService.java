package com.efant.efant.services;

import com.efant.efant.model.entities.MenuItem;
import com.efant.efant.repositories.MenuItemsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MenuItemsService {

    private MenuItemsRepository menuItemsRepository;

    @Autowired
    public MenuItemsService(MenuItemsRepository menuItemsRepository){
        this.menuItemsRepository=menuItemsRepository;
    }


    public List<MenuItem> getAllMenuItems(){
        return menuItemsRepository.findAll();
    }

    public MenuItem getMenuItemById(Long id) throws Exception{
        return menuItemsRepository.findById(id)
                .orElseThrow(() -> new Exception("Item not exists with id: " + id));

    }

    public MenuItem createMenuItem(MenuItem menuItem){
        menuItem = menuItemsRepository.save(menuItem);
        return menuItem;
    }

    public MenuItem updateMenuItem(MenuItem menuItem) throws Exception{
        Long itemsId = menuItem.getItemId();
        MenuItem existingMenuItem = menuItemsRepository.findById(itemsId)
                .orElseThrow(() -> new Exception("Item not exists with id: " + itemsId));

        existingMenuItem.setName(menuItem.getName());
        existingMenuItem.setDescription(menuItem.getDescription());
        existingMenuItem.setPrice(menuItem.getPrice());
        existingMenuItem.setImageUrl(menuItem.getImageUrl());

        existingMenuItem = menuItemsRepository.save(existingMenuItem);
        return existingMenuItem;

    }



    public void deleteMenuItem(Long id) throws Exception{
        MenuItem menuItem = menuItemsRepository.findById(id).orElse(null);

        if(menuItem != null){
            menuItemsRepository.deleteById(id);
        }
        else{
            throw new Exception("Item not exists with id" +id);

        }
    }


}
