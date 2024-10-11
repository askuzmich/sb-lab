package com.example.demo.endpoints.sbUser.converter;

import com.example.demo.endpoints.sbUser.DTO.UserDto;
import com.example.demo.endpoints.sbUser.SbUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DtoToUser implements Converter<UserDto, SbUser> {
    @Override
    public SbUser convert(UserDto source) {
        SbUser user = new SbUser();
//        user.setId(source.id());
        user.setName(source.name());
        user.setRoles(source.roles());
        user.setEnabled(source.enabled());

        return user;
    }
}
