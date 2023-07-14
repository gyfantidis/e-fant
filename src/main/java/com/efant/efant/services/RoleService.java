package com.efant.efant.services;

import com.efant.efant.model.entities.Role;
import com.efant.efant.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRep){
        roleRepository=roleRep;
    }


    // methods

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }


    public Role getRoleById(Long id)throws Exception{
        return roleRepository.findById(id)
                .orElseThrow(() -> new Exception("Role not exists with id: " + id));
    }
}
