package mappers;

import dto.UserDTO;
import security.AuthUser;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDto (AuthUser authUser);
    AuthUser toUser (UserDTO userDTO);
}
