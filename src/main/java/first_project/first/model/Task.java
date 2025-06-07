package first_project.first.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String id;
    private String description;
    private String userId;
}
