package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Comment")
public class Comment {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Kiểu SEQUENCE
////    @Column(name = "id")
@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // Kiểu SEQUENCE

//You can also use "sequence"--Bo quy tac cho viec tao ra id(hay bat cu thuoc tinh nao khac) khi co
//1  doi tuong moi duoc tao ra (thay cho doan GenertationType.AUTO)
@SequenceGenerator(
        name= "comment_sequence",
        sequenceName = "comment_sequence",
        allocationSize = 1 //increment by 1
)
@GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "comment_sequence"
)
    private Long id;
    private String authorCode;

    // Quan hệ nhiều-1 với entity Bài viết (Post)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
    private String commentContent;

    public Comment(){}

    public Comment( String authorCode, String commentContent) {
        this.authorCode = authorCode;
        this.post = post;
        this.commentContent = commentContent;
    }
}
