package crioproject.xmeme.Model;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@Table(name = "memes")
public class Meme {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String caption;

    private String url;

    public Meme() {
    }
}
