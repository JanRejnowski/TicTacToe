package com.sda.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by RENT on 2017-07-01.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String city;

    private String street;

    private int flatNumber;

    private String postalCode;

}
