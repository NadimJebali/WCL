package com.example.backend.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.backend.business.services.UserService;
import com.example.backend.dao.entities.User;
import com.example.backend.web.Models.dto.UserDto;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user")
@PreAuthorize("hasRole('ADMIN') or hasRole('TEACHER') or hasRole('STUDENT')")
@Tag(name = "UserController" , description = "For Admin")
public class UserContoroller {

    @Autowired
    private UserService userService ;
    
    @Operation(summary = "Recover all User")
    @PreAuthorize("hasAuthority('admin:read')")
    @GetMapping("/alluser")
    public ResponseEntity<List<UserDto>> alluser() {
        return new ResponseEntity<>(this.userService.getAllUser() , HttpStatus.OK);
    }

    @Operation(summary = "Recover User By Email")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/findbyemail")
    public ResponseEntity<UserDto> getuserByemail(@RequestParam String email) {
        return new ResponseEntity<>(this.userService.getUserDtoByEmail(email) ,HttpStatus.OK);
    }

    @Operation(summary = "Recover User By Id")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @GetMapping("/find/{id}")
    public ResponseEntity<UserDto> getuserById(@PathVariable("id") Long id) {
        return new ResponseEntity<>(this.userService.getUserDtoById(id) ,HttpStatus.OK);
    }

    @Operation(summary = "Delete User By Id")
    @PreAuthorize("hasAuthority('admin:delete')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("id") Long id)
    {
        this.userService.deleteByid(id) ;
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @Operation(summary = "Update User Lastname and firstname by Id")
    @PreAuthorize("hasAuthority('admin:read') or hasAuthority('teacher:read') or hasAuthority('student:read')")
    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> updateUserbylastfirst(@PathVariable Long id, @RequestBody UserDto userDto) {
        return new ResponseEntity<>(this.userService.updateUserDto(id, userDto)  , HttpStatus.OK);
    }
    
}
