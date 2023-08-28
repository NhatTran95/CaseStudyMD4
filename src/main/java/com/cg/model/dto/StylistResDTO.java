package com.cg.model.dto;

import com.cg.model.StylistStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class StylistResDTO {
    private Long id;
    private String stylistName;
    private String phone;
    private StylistStatus stylistStatus;
}
