package com.kream.root.MainAndShop.dto.Recommend;

import lombok.*;

@Data
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecommendDTO {

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private String date;
    private Long prId;
    private String clear_color;
    private String brand;
    private String category;
    private int price;
    private int quantity;
    private int totalPrice;
    private int click;
    private int wish;
    private int style;
    private String gender;
    private int age;
    private int paid_amount;
    private double cr;

//    public void setDate(LocalDate date) {
//        this.date = date.toString();
//    }

    public void setPrice(int price) {
        this.price = price;
        setTotalPrice();
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        setTotalPrice();
        setCr();
    }

    public void setClick(int click) {
        this.click = click;
        setCr();
    }

    public void setAge(int age){
        this.age = (age / 10) * 10;
    }

    public void setTotalPrice() {
        this.totalPrice = this.price * this.quantity;
    }

    public void setCr() {
        this.cr = this.click != 0 ? (double) this.quantity / (double) this.click : 0.0;
    }

}
