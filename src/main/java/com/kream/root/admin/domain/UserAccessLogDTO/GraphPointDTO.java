package com.kream.root.admin.domain.UserAccessLogDTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GraphPointDTO {
    private String x;
    private int y;
    public GraphPointDTO(String x, int y) {
        this.x = x;
        this.y = y;
    }
}
