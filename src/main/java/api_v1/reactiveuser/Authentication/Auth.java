package api_v1.reactiveuser.Authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
public class Auth{
    @Id
    private String email;
    private Integer key;
}
