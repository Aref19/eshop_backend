package com.example.eshop_backend.request;

import com.example.eshop_backend.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest {

    private String userName;
    private String emailId;
    private String password;
    private String role;
    private List<AddressReq> addressReqs;

    public static User UserRequestToUser(UserRequest userRequest){
       User user =   new User(
                userRequest.getUserName(),
                userRequest.getEmailId(),
                userRequest.getPassword()
        );
        user.setAddress(AddressReq.addressRequestToAddress(userRequest.getAddressReqs()));
        return user;
    }
}
