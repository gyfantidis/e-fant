package com.efant.efant.controllers;

import com.efant.efant.model.entities.MenuItem;
import com.efant.efant.services.MenuItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {
    private MenuItemsService menuItemsService;

    @Autowired
    public MenuItemController(MenuItemsService menuItemsService) {
        this.menuItemsService = menuItemsService;
    }

    @GetMapping("/menu/items")
    public List<MenuItem> getAllMenuItems(){
        return menuItemsService.getAllMenuItems();
    }

    @GetMapping("/menu/items/{id}")
    public MenuItem getManuItemById(@PathVariable Long id)throws Exception{
        return menuItemsService.getMenuItemById(id);

    }

    @PostMapping("/menu/items")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem){
        menuItem = menuItemsService.createMenuItem(menuItem);
        return menuItem;
    }

    @PutMapping("/menu/items/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) throws Exception{
        if (!id.equals((menuItem.getItemId()))){
            throw new Exception("ID in path and ID in body are not the same");
        }

        menuItem = menuItemsService.updateMenuItem(menuItem);
        return menuItem;

    }

    @DeleteMapping("/menu/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenuItem(@PathVariable Long id) throws Exception{
        menuItemsService.deleteMenuItem(id);
    }

}


