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
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String firstName;

    private String lastName;

    private int age;

    private String phoneNumber;

    private Address address;
}
