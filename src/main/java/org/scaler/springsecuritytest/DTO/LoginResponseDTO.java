package org.scaler.springsecuritytest.DTO;

import lombok.Data;
import org.scaler.springsecuritytest.Model.Token;

import java.util.Date;

@Data
public class LoginResponseDTO {
    private String value;
    private Date expiry;
    private Boolean is_valid;

    public  static LoginResponseDTO from(Token t){
        LoginResponseDTO l = new LoginResponseDTO();
        l.setValue(t.getValue());
        l.setExpiry(t.getExpiry());
        l.setIs_valid(true);
        return  l;
    }
}
