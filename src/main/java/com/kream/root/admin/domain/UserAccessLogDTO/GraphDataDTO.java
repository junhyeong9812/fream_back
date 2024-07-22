package com.kream.root.admin.domain.UserAccessLogDTO;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GraphDataDTO {
    private String id;
    private List<GraphPointDTO> data;
}
