package org.scaler.springsecuritytest.Model;

import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Role extends BaseModel{
    private String role;

}
