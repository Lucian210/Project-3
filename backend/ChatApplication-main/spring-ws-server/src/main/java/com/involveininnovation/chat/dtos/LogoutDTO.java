package com.involveininnovation.chat.dtos;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Builder
public class LogoutDTO {
    private UUID id;
}
