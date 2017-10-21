/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 *
 * @author davlad
 */
@Mapper
public interface UsersMapper {

    UsersMapper INSTANCE = Mappers.getMapper( UsersMapper.class );

    @Mapping(source = "id", target = "id")
    integration.users.Users usersToUsersDto(entities.Users user);
    @Mapping(source = "id", target = "id")
    entities.Users usersDtoToUsers(integration.users.Users user);
}