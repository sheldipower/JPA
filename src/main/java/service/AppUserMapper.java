package service;

import department.AppUser;
import dto.AppUserDto;
import org.springframework.stereotype.Component;

@Component
public class AppUserMapper {

    public AppUserDto toDto(AppUser user) {
        AppUserDto userDto = new AppUserDto();
        userDto.setId(user.getId());
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        return userDto;
    }

}