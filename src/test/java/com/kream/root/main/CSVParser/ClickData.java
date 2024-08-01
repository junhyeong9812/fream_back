package com.kream.root.main.CSVParser;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class ClickData {

    public LocalDate ubDate;

    public Long prid;

    public int clickCount;

}
