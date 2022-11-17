package api_v1.reactiveuser.Login.SubModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PreLogin {
    private String email;
    private String name;
    private String password;
    private String address;
}
