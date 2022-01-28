package org.ea.blogme.userservice.service.impl;

import org.ea.blogme.userservice.dto.ResponseMessage;
import org.ea.blogme.userservice.dto.UserSave;
import org.ea.blogme.userservice.dto.UserUpdate;
import org.ea.blogme.userservice.model.Names;
import org.ea.blogme.userservice.model.User;
import org.ea.blogme.userservice.repository.IRoleRepository;
import org.ea.blogme.userservice.repository.IUserRepository;
import org.ea.blogme.userservice.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    private final IUserRepository userRepository;
    private final IRoleRepository roleRepository;
    private final ModelMapper mapper;

    public UserService(IUserRepository userRepository, IRoleRepository roleRepository,
                       ModelMapper mapper){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public ResponseMessage save(UserSave userSave) {
        if(!userRepository.existsByEmail(userSave.getEmail())){
            User user = mapper.map(userSave, User.class);

            user.setNames(new Names(userSave.getFirstName(), userSave.getMiddleName(),
                    userSave.getLastName()));
            userSave.getRoles().forEach(roleId -> user.addRole(roleRepository.getById(roleId)));
            user.setRegisteredAt(new Date());
            userRepository.save(user);
            return new ResponseMessage("Saved Successfully.", HttpStatus.CREATED);
        }
        return new ResponseMessage("User with given email already exists.",
                HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseMessage update(UserUpdate userUpdate) {
        Optional<User> userOpt = findById(userUpdate.getId());
        if(userOpt.isPresent()){
            User user = userOpt.get();
            if(!userUpdate.getFirstName().isEmpty()) user.getNames().setFirstName(userUpdate.getFirstName());
            if(!userUpdate.getMiddleName().isEmpty()) user.getNames().setMiddleName(userUpdate.getMiddleName());
            if(!userUpdate.getLastName().isEmpty()) user.getNames().setLastName(userUpdate.getLastName());

            if(!userUpdate.getMobile().isEmpty()) user.setMobile(userUpdate.getMobile());
            if(!userUpdate.getEmail().isEmpty()) user.setEmail(userUpdate.getEmail());
            if(!userUpdate.getIntro().isEmpty()) user.setIntro(userUpdate.getIntro());
            if(!userUpdate.getProfile().isEmpty()) user.setProfile(userUpdate.getProfile());

            if(!userUpdate.getRoles().isEmpty()){
                userUpdate.getRoles().forEach(roleId -> user.addRole(roleRepository.getById(roleId)));
            }

            userRepository.save(user);
            return new ResponseMessage("Updated User.", HttpStatus.OK);
        }
        return new ResponseMessage("User not found.", HttpStatus.NOT_FOUND);
    }

    @Override
    public ResponseMessage delete(Long id) {
        userRepository.deleteById(id);
        return new ResponseMessage("Deleted.", HttpStatus.OK);
    }
}
