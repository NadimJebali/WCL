package com.example.backend.business.servicesimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.backend.business.services.UserService;
import com.example.backend.dao.entities.User;
import com.example.backend.dao.repositories.UserRepository;
import com.example.backend.exception.modelsexception.NotFound_404;
import com.example.backend.web.Models.dto.UserDto;
import com.example.backend.web.Models.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository ;

    @Autowired
    private UserMapper userMapper ;


    @Override
    public User getUserByEmail(String email) {
        
        return this.userRepository.findByEmail(email).orElseThrow(()->new NotFound_404("User Not Found"));
   
       
    }

    @Override
    public User addUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<UserDto> getAllUser() {
        return this.userRepository.findAll()
                                    .stream()
                                    .map(this.userMapper :: toDto)
                                    .toList();
    }

    @Override
    public void deleteByid(Long id) {
        User user = getUserById(id);
        this.userRepository.delete(user);
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        return this.userRepository.findByEmail(email)
                                    .map(this.userMapper :: toDto)
                                    .orElseThrow(() -> new NotFound_404("User Not Found !"));
    }

    @Override
    public User getUserById(Long id) {
        return this.userRepository.findById(id)
                                    .orElseThrow(()->new NotFound_404("User Not Found"));
    }

    



    @Override
    public UserDto updateUserDto(Long id, UserDto newUserDto) {
        User user = getUserById(id);
        user.setFirstname(newUserDto.getFirstname());
        user.setLastname(newUserDto.getLastname());
        user = this.userRepository.save(user);
        return this.userMapper.toDto(user);
    }

    @Override
    public UserDto getUserDtoById(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        return this.userMapper.toDto(user);
    }

    
    
}
