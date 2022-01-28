package org.ea.blogme.userservice.service;

import org.ea.blogme.userservice.dto.ResponseMessage;
import org.ea.blogme.userservice.dto.UserSave;
import org.ea.blogme.userservice.dto.UserUpdate;
import org.ea.blogme.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();

    Optional<User> findById(Long id);

    ResponseMessage save(UserSave userSave);

    ResponseMessage update(UserUpdate userUpdate);

    ResponseMessage delete(Long id);
}
