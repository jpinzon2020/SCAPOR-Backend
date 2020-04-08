package com.scapor.repositories.user.facade;

import com.scapor.core.exceptions.base.ServiceException;
import com.scapor.core.exceptions.enums.LogRefServices;
import com.scapor.core.exceptions.persistence.DataNotFoundPersistenceException;
import com.scapor.core.exceptions.services.DataNotFoundServiceException;
import com.scapor.domain.entitys.UserEntity;
import com.scapor.repositories.user.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.PersistenceException;
import java.util.List;
import java.util.Optional;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)//Rolback

public class UserRepositoryFacadeImpl implements UserRepositoryFacade {

    private final UserRepository userRepository;

    //Inyectar dependecia
    public UserRepositoryFacadeImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserEntity> listUsers() throws ServiceException {

        try {
            return Optional.of(userRepository.findAll())
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encontraron registros"));
        } catch (IllegalArgumentException | PersistenceException ex) {
            //Error 400
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de datos", ex);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error interno", e);
        }

    }

    @Override
    public UserEntity findUserById(Integer id) throws ServiceException {

        try {
            return Optional.of(userRepository.findUserEntityById(id))
                    .orElseThrow(() -> new DataNotFoundServiceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "No se encuentra el regisro"));

        } catch (IllegalArgumentException | PersistenceException ex) {
            //Error 400
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_NO_ENCONTRADO, "Error en la busqueda de datos", ex);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "Error interno", e);
        }
    }

    @Override
    public UserEntity saveUser(UserEntity user) throws ServiceException {

        try {
            return userRepository.save(user);
        } catch (IllegalArgumentException | PersistenceException ex) {
            //Error 400
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_CORRUPTO, "Error en la integridad de los datos", ex);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_PERSISTENCIA, "Error interno", e);
        }

    }

    @Override
    public UserEntity updateUser(UserEntity user) throws ServiceException {
        try {
            return userRepository.save(
                    mapperUserUpdate(user)
            );
        } catch (IllegalArgumentException | PersistenceException ex) {
            //Error 400
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_CORRUPTO, "Error en la integridad de los datos", ex);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_PERSISTENCIA, "Error interno", e);
        }
    }


    @Override
    public UserEntity disableUser(Integer id, boolean status) throws ServiceException {

        try {
            return userRepository.save(mapperUserUpdateStatus(id, status));

        } catch (IllegalArgumentException | PersistenceException ex) {
            //Error 400
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_CORRUPTO, "Error en la integridad de los datos", ex);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_PERSISTENCIA, "Error interno", e);
        }
    }


    @Override
    public void deleteUse(Integer id) throws ServiceException {

        try {
            Optional<UserEntity> user = userRepository.findById(id);
            if (user.isPresent()) {
                userRepository.deleteById(id);
            } else {
                throw new ServiceException(LogRefServices.ERROR_GENERAL_SERVICIO, "El id del usuario no existe");
            }
        } catch (IllegalArgumentException | PersistenceException ex) {
            //Error 400
            throw new DataNotFoundPersistenceException(LogRefServices.ERROR_DATO_CORRUPTO, "Error en la integridad de los datos", ex);
        } catch (Exception e) {
            throw new ServiceException(LogRefServices.ERROR_PERSISTENCIA, "Error interno", e);
        }
    }

    private UserEntity mapperUserUpdateStatus(Integer id, boolean status) throws ServiceException {
        UserEntity userActual;
        userActual = findUserById(id);
        userActual.setProcess(status);
        return userActual;
    }

    private UserEntity mapperUserUpdate(UserEntity user) throws ServiceException {
        UserEntity userActual;
        userActual = findUserById(user.getId());
        userActual.setName(user.getName());
        userActual.setSurname(user.getSurname());
        userActual.setProcess(user.getProcess());
        return userActual;
    }
}
