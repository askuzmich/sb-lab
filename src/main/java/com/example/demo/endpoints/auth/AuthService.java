package com.example.demo.endpoints.auth;

import com.example.demo.endpoints.sbUser.DTO.UserDto;
import com.example.demo.endpoints.sbUser.SbUser;
import com.example.demo.endpoints.sbUser.SbUserPrincipal;
import com.example.demo.endpoints.sbUser.converter.UserToDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {
    private final AuthJWTProvider authJWTProvider;

    private final UserToDTO userToDTO;

    public AuthService(AuthJWTProvider authJWTProvider, UserToDTO userToDTO) {
        this.authJWTProvider = authJWTProvider;
        this.userToDTO = userToDTO;
    }

    public Map<String, Object> getLoginInfo(Authentication authentication) {
        SbUserPrincipal sbUserPrincipal = (SbUserPrincipal)authentication.getPrincipal();

        SbUser sbUser = sbUserPrincipal.getSbUser();

        UserDto userDto = this.userToDTO.convert(sbUser);

        // JWT
        String jwt = this.authJWTProvider.createJWT(authentication);

        Map<String, Object> customResultMap = new HashMap<>();
        customResultMap.put("user", userDto);
        customResultMap.put("token", jwt);

        return customResultMap;
    }
}
