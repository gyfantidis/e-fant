package com.efant.efant.controllers;

import com.efant.efant.model.entities.Role;
import com.efant.efant.services.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    private RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService){
        this.roleService=roleService;
    }

    @GetMapping("/roles")
    public List<Role> getAllRoles(){
        return roleService.getAllRoles();
    }

    @GetMapping("/roles/{id}")
    public Role getRoleById(@PathVariable Long id) throws Exception{
        return roleService.getRoleById(id);
    }
}
