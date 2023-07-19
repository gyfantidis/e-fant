package com.efant.efant.services;

import com.efant.efant.exeptions.EfantException;
import com.efant.efant.model.entities.Role;
import com.efant.efant.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository){
        this.roleRepository=roleRepository;
    }



    // methods
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }


    public Role getRoleById(Long id)throws Exception{
        return roleRepository.findById(id)
                .orElseThrow(() -> new EfantException("ROLE_NOT_FOUND", "Role not exists with id: " + id, HttpStatus.NOT_FOUND));
    }
}
