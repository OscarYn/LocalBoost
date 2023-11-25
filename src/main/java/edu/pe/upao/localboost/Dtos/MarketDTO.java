package edu.pe.upao.localboost.Dtos;

import edu.pe.upao.localboost.Models.Market;


public class MarketDTO {

    private String marketName;
    private String marketInfo;
    private UserDTO userDTO;
    private ProductDTO productDTO;

    public MarketDTO(Market market) {
        this.marketName = market.getMarketName();
        this.marketInfo = market.getMarketInfo();
        this.userDTO = new UserDTO(market.getUser());
        this.productDTO = new ProductDTO(market.getProduct());
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public void setMarketInfo(String marketInfo) {
        this.marketInfo = marketInfo;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public void setProductDTO(ProductDTO productDTO) {
        this.productDTO = productDTO;
    }

    public String getMarketName() {
        return marketName;
    }

    public String getMarketInfo() {
        return marketInfo;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public ProductDTO getProductDTO() {
        return productDTO;
    }
}
