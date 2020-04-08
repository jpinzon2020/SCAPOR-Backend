package com.scapor.services.user.impl;

import com.scapor.core.exceptions.base.ServiceException;
import com.scapor.domain.dto.UserDTO;
import com.scapor.domain.entitys.UserEntity;
import com.scapor.services.user.UserServices;
import com.scapor.repositories.user.facade.UserRepositoryFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(propagation = Propagation.NOT_SUPPORTED)

public class UserServicesImpl implements UserServices {

    private final UserRepositoryFacade userRepositoryFacade;

    public UserServicesImpl(UserRepositoryFacade userRepositoryFacade) {
        this.userRepositoryFacade = userRepositoryFacade;
    }

    @Override
    public List<UserDTO> listUsers() throws ServiceException {

        List<UserEntity> users = userRepositoryFacade.listUsers();
        return users.stream().map(this::mapperDTOEntity).collect(Collectors.toList());

    }

    @Override
    public UserEntity findUserById(Integer id) throws ServiceException {
        return userRepositoryFacade.findUserById(id);
    }

    @Override
    public UserEntity saveUser(UserDTO user) throws ServiceException {
        return userRepositoryFacade.saveUser(mapperEntityDTO(user));
    }

    @Override
    public UserEntity updateUser(UserDTO user) throws ServiceException {
        return userRepositoryFacade.updateUser(mapperEntityDTO(user));
    }

    @Override
    public UserEntity disableUser(Integer id, boolean status) throws ServiceException {
        return userRepositoryFacade.disableUser(id,status);
    }

    @Override
    public void deleteUser(Integer id) throws ServiceException {
         userRepositoryFacade.deleteUse(id);
    }

    //Convetir entre entidad y DTO
    private UserEntity mapperEntityDTO(UserDTO userDTO){
        return UserEntity.builder().
                id(userDTO.getId()).
                name(userDTO.getName()).
                surname(userDTO.getSurname()).
                process(userDTO.getProcess()).build();
    }

    //Convertir de DTO a entidad
    private UserDTO mapperDTOEntity(UserEntity userEntity){

        return  UserDTO.builder().
                id(userEntity.getId()).
                name(userEntity.getName()).
                surname(userEntity.getSurname()).
                process(userEntity.getProcess()).build();
    }
}
