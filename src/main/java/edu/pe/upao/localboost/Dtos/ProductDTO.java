package edu.pe.upao.localboost.Dtos;

import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Models.User;

public class ProductDTO {
    private String name;
    private String description;
    private int price;
    private String category;
    private UserDTO userDTO;

    public ProductDTO(Product product) {
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.category = product.getCategory();

        if(product.getUser() != null){
            this.userDTO = new UserDTO(product.getUser());
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
