package com.kream.root.MainAndShop.domain.ProductInfo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductInfo {
    private int recent_trading_price;
    private int reg_price;
    private String product_color;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date reg_date;
}
//{'recent_trading_price': 230000, 'reg_price': 199000.0, 'product_color': ' cloud white/aluminium/chalk white ', 'reg_date': '2023-02-25'}
