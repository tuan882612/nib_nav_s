package api_v1.reactiveuser.User.Favorite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Favorite {
    private String r_name;
    private String r_address;
}
