package com.example.demo.endpoints.sbUser.converter;

import com.example.demo.endpoints.sbUser.DTO.UserDto;
import com.example.demo.endpoints.sbUser.SbUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDTO implements Converter<SbUser, UserDto> {
    @Override
    public UserDto convert(SbUser source) {
        UserDto userDto = new UserDto(
                source.getId(),
                source.getName(),
                source.getRoles(),
                source.isEnabled()
        );

        return userDto;
    }
}
