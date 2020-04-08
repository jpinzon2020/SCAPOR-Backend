package com.scapor.services.user;

import com.scapor.core.exceptions.base.ServiceException;
import com.scapor.domain.dto.UserDTO;
import com.scapor.domain.entitys.UserEntity;

import java.util.List;


public interface UserServices {

    //Get
    List<UserDTO> listUsers () throws ServiceException;

    //Get
    UserEntity findUserById (Integer id) throws ServiceException;

    //Post
    UserEntity saveUser (UserDTO user) throws ServiceException;

    //Put
    UserEntity updateUser(UserDTO user) throws ServiceException;

    //Patch
    UserEntity disableUser(Integer id, boolean status) throws ServiceException;

    //Delete
    void deleteUser(Integer id) throws ServiceException;
}
