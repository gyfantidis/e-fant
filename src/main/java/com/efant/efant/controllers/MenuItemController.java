package com.efant.efant.controllers;

import com.efant.efant.model.entities.MenuItem;
import com.efant.efant.services.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MenuItemController {
    private MenuItemService menuItemService;

    @Autowired
    public MenuItemController(MenuItemService menuItemService) {
        this.menuItemService = menuItemService;
    }

    @GetMapping("/menu/items")
    public List<MenuItem> getAllMenuItems(){
        return menuItemService.getAllMenuItems();
    }

    @GetMapping("/menu/items/{id}")
    public MenuItem getManuItemById(@PathVariable Long id)throws Exception{
        return menuItemService.getMenuItemById(id);

    }

    @PostMapping("/menu/items")
    @ResponseStatus(value = HttpStatus.CREATED)
    public MenuItem createMenuItem(@RequestBody MenuItem menuItem){
        menuItem = menuItemService.createMenuItem(menuItem);
        return menuItem;
    }

    @PutMapping("/menu/items/{id}")
    public MenuItem updateMenuItem(@PathVariable Long id, @RequestBody MenuItem menuItem) throws Exception{
        if (!id.equals((menuItem.getItemId()))){
            throw new Exception("ID in path and ID in body are not the same");
        }

        menuItem = menuItemService.updateMenuItem(menuItem);
        return menuItem;

    }

    @DeleteMapping("/menu/items/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMenuItem(@PathVariable Long id) throws Exception{
        menuItemService.deleteMenuItem(id);
    }

}


