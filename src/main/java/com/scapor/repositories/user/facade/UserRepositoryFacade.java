package com.scapor.repositories.user.facade;

import com.scapor.core.exceptions.base.ServiceException;
import com.scapor.domain.entitys.UserEntity;
import org.apache.catalina.User;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

public interface UserRepositoryFacade {

    //Get
    List<UserEntity> listUsers () throws ServiceException;

    //Get
    UserEntity findUserById (Integer id) throws ServiceException;

    //Post
    UserEntity saveUser (UserEntity user) throws ServiceException;

    //Put
    UserEntity updateUser(UserEntity user) throws ServiceException;

    //Patch
    UserEntity disableUser(Integer id, boolean status) throws ServiceException;

    //Delete
    void deleteUse(Integer id) throws ServiceException;

}
