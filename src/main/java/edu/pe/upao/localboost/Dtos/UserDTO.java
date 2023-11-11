package edu.pe.upao.localboost.Dtos;


import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Models.User;

import java.util.List;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String bussinessName;


    public UserDTO(User user) {
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.bussinessName = user.getBussinessName();
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getBussinessName() {
        return bussinessName;
    }
}
