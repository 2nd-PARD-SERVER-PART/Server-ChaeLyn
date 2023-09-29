package com.pard.server.hw2nd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
        private Integer id;
        private String name;
        private Integer price;
        private Integer count;
}
