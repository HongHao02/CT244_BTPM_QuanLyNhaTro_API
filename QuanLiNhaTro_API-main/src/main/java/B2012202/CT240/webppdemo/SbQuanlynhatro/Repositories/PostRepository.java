package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository< Post, Long> {
}
