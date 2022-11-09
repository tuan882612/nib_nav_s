package api_v1.reactiveuser.User;

import api_v1.reactiveuser.User.Favorite.Favorite;
import api_v1.reactiveuser.User.Order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
public class User {
    @Id
    private String email;
    private String name;
    private String password;
    private String address;
    private List<Order> order;
    private List<Favorite> favorite;
}
