package com.pk.userservice.userservice.model;

import jakarta.persistence.Entity;
import lombok.*;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Roles extends BaseModel{
    private String name;
}
