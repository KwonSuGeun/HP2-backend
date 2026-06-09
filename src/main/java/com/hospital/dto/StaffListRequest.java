package com.hospital.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffListRequest {

    private String dept;
    private String staffRankCode;
    private String status;
    private String keyword;
}
