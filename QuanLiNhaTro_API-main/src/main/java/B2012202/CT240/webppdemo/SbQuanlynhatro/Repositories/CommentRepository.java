package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Comment;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository< Comment,Long> {

    List< Comment > findByPostId(Long postID);
}
