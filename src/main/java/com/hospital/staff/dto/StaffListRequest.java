package com.hospital.staff.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffListRequest {

    private String dept;
    private String keyword;
}
