package edu.pe.upao.localboost.Dtos;

import edu.pe.upao.localboost.Models.Product;
import edu.pe.upao.localboost.Models.Review;
import edu.pe.upao.localboost.Models.User;

public class ReviewDTO {
    private String text;
    private UserDTO userDTO;
    private MarketDTO marketDTO;

    public ReviewDTO(Review review) {
        this.text = review.getText();
        this.userDTO = new UserDTO(review.getUser());
        this.marketDTO = new MarketDTO(review.getMarket());
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }


    public String getText() {
        return text;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public MarketDTO getMarketDTO() {
        return marketDTO;
    }

    public void setMarketDTO(MarketDTO marketDTO) {
        this.marketDTO = marketDTO;
    }
}
