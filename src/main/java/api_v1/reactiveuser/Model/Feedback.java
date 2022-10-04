package api_v1.reactiveuser.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "feedback")
public class Feedback {
    @Id
    private String id = (UUID.randomUUID().toString()).substring(0,8);
    private String name;
    private String comment;
}
