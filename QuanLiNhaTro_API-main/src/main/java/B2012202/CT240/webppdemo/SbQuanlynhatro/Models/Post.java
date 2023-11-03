package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
//Auto generate Getter, Setter, Constructor, HasCode, Equals
@Entity
@Table(name = "Post")

public class Post {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Kiểu SEQUENCE

    //You can also use "sequence"--Bo quy tac cho viec tao ra id(hay bat cu thuoc tinh nao khac) khi co
    //1  doi tuong moi duoc tao ra (thay cho doan GenertationType.AUTO)
    @SequenceGenerator(
            name= "post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "post_sequence"
    )
    @Column(name = "id")
    private Long id;
    private Long categoryId;
    private String authorCode;
    private String title;
    private String content;
    private LocalDateTime postTime;
    private String imageURL;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

    //getter and setter auto by lombox

    public Post(){}
    public Post( Long categoryId, String authorCode, String title, String content, LocalDateTime postTime, String imageURL) {
        this.categoryId = categoryId;
        this.authorCode = authorCode;
        this.title = title;
        this.content = content;
        this.postTime = postTime;
        this.imageURL = imageURL;
        this.comments = comments;
    }
}
