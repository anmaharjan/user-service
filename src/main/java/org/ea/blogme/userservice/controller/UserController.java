package org.ea.blogme.userservice.controller;

import org.ea.blogme.userservice.dto.ResponseMessage;
import org.ea.blogme.userservice.dto.UserSave;
import org.ea.blogme.userservice.dto.UserUpdate;
import org.ea.blogme.userservice.model.User;
import org.ea.blogme.userservice.service.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService){
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id){
        Optional<User> userOpt = userService.findById(id);
        return userOpt.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> save(@Valid @RequestBody UserSave userSave){
        ResponseMessage response = userService.save(userSave);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> update(@Valid @RequestBody UserUpdate userUpdate){
        ResponseMessage response = userService.update(userUpdate);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseMessage> delete(@PathVariable Long id){
        ResponseMessage response = userService.delete(id);
        return new ResponseEntity<>(response, response.getStatus());
    }
}
