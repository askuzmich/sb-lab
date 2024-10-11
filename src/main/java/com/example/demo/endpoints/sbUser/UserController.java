package com.example.demo.endpoints.sbUser;

import com.example.demo.endpoints.sbUser.DTO.UserDto;
import com.example.demo.endpoints.sbUser.converter.DtoToUser;
import com.example.demo.endpoints.sbUser.converter.UserToDTO;
import com.example.demo.returnDataObject.CustomReturnData;
import com.example.demo.returnDataObject.CustomStatusCode;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("${api.endpoint.base-url}/users")
public class UserController {

    private final UserService userService;

    private final UserToDTO userToDTO;

    private final DtoToUser dtoToUser;

    public UserController(
            UserService userService,
            UserToDTO userToDTO,
            DtoToUser dtoToUser
    ) {
        this.userService = userService;
        this.userToDTO = userToDTO;
        this.dtoToUser = dtoToUser;
    }

    @GetMapping("/{id}")
    public CustomReturnData findUserById(@PathVariable Integer id) {
        SbUser user = this.userService.findById(id);

        UserDto userDto = this.userToDTO.convert(user);

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                userDto
        );
    }

    @GetMapping
    public CustomReturnData getAll() {
        List<SbUser> users = userService.getAll();

        List<UserDto> userDTOs = users
                .stream()
                .map((user -> {
                    return this.userToDTO.convert(user);
                }))
                .collect(Collectors.toList());

        return new CustomReturnData(
                true,
                CustomStatusCode.SUCCESS,
                "Transaction is Ok",
                userDTOs
        );
    }

    /**
     *
     * --- @param user (not DTO) because of password field ---
     *
     */
    @PostMapping
    public CustomReturnData add(@Valid @RequestBody SbUser user) {
        SbUser add = this.userService.add(user);

        UserDto newDto = this.userToDTO.convert(add);

        return new CustomReturnData(
            true,
            CustomStatusCode.SUCCESS,
            "Transaction is Ok",
            newDto
        );
    }

    /**
     *  --- !!!NOT FOR UPDATE PASSWORD!!! ---
     */
    @PutMapping("/{id}")
    public CustomReturnData update(
        @PathVariable Integer id,
        @Valid @RequestBody UserDto dto
    ) {
        SbUser converted = this.dtoToUser.convert(dto);

        SbUser updated = this.userService.update(id, converted);

        UserDto retDto = this.userToDTO.convert(updated);

        return new CustomReturnData(
            true,
            CustomStatusCode.SUCCESS,
            "Transaction is Ok",
            retDto
        );
    }

    @DeleteMapping("/{id}")
    public CustomReturnData delete(@PathVariable Integer id) {
        this.userService.delete(id);

        return new CustomReturnData(
            true,
            CustomStatusCode.SUCCESS,
            "Transaction is Ok"
        );
    }

}
