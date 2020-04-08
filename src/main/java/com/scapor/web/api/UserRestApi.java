package com.scapor.web.api;

import com.scapor.core.exceptions.base.ServiceException;
import com.scapor.domain.dto.UserDTO;
import com.scapor.domain.entitys.UserEntity;
import com.scapor.services.user.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class UserRestApi {

    private final UserServices userServices;

    @Autowired
    public UserRestApi(UserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping("/user")
    public List<UserDTO> getAllPersons() throws ServiceException {
        return userServices.listUsers();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable("id") Integer id) throws ServiceException {
        UserEntity entity = userServices.findUserById(id);
        return new ResponseEntity<UserEntity>(entity, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping(value = "/userCreate",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDTO entity) throws ServiceException {
        UserEntity updated = userServices.saveUser(entity);
        return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
    }

    @PatchMapping("/user/{id}/procesado/{procesado}")
    public ResponseEntity<UserEntity> updateStatus(@PathVariable Boolean procesado, @PathVariable Integer id)
            throws ServiceException {
        return new ResponseEntity<>(userServices.disableUser(id, procesado), HttpStatus.OK);
    }

    @PutMapping(value = "/userUpdate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<UserEntity> update(@RequestBody UserDTO userDTO) throws ServiceException{
        UserEntity updated = userServices.updateUser(userDTO);
        return new ResponseEntity<>(updated, new HttpHeaders(),HttpStatus.OK);
    }

}
