package com.hospital.staff.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StaffListRequest {

    private String staffId;
    private String dept;
    private String staffRankCode;
    private String keyword;
}
