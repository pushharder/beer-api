package com.beerapi.beerapi.mappers;

import com.beerapi.beerapi.entities.User;
import com.beerapi.beerapi.models.UserDTO;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    User userDtoToUser(UserDTO userDTO);
    UserDTO userToUserDto (User user);
}
