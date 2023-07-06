package com.example.convertJsonToCsv.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CsvVo {
    private String title;
    private String isbn;
    private String doi;

}
