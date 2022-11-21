package api_v1.reactiveuser.Authentication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "auth")
public class Auth implements Serializable {
    @Id
    private String email;
    private Integer key;
    private boolean found;
    @Indexed(
        name = "expire_1",
        expireAfterSeconds = 180)
    private Date expire;
}
