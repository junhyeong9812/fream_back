package com.kream.root.main.CSVParser;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
public class OrderData {

    public String orderCode;

    public LocalDateTime orderDate;

    public int user;

    public Long prId;

    public int priceAmount;

}
