package api_v1.reactiveuser.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String id = (UUID.randomUUID().toString()).substring(0,8);
    private String name;
    private String email;
    private String password;
    private String address;
    private List<Order> order;
    private List<Favorite> favorite;
}
