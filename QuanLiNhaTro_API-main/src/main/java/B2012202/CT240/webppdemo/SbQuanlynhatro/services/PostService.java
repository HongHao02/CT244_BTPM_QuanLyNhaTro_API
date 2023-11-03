package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Comment;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Post;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.CommentRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.PostRepository;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service

public class PostService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImageStorageService imageStorageService;

//    @Async
//    public CompletableFuture<Post> createPost(Post post) {
//        return CompletableFuture.supplyAsync(() -> {
//            // Luu doi tuong bai viet vao co so du lieu
//            Post savedPost = postRepository.save(post);
//            // Tra ve doi tuong bai viet da duoc luu
//            return savedPost;
//        });
//    }

    @Async
    public CompletableFuture<Post> createPost( MultipartFile file, String title, String content, String authorCode) {
        final long start = System.currentTimeMillis();
//        final LocalTime start= LocalTime.now();
        LOGGER.info("[ASYNC] Time start the request insert post {}",start);

        // Lưu trữ tệp và trả về URL đã lưu
//        String imageUrl = saveFileToStorage(file);
        String imageUrl=imageStorageService.storeFile(file);
        LOGGER.info("[ASYNC] The url of image is {}", imageUrl);

        // Tạo đối tượng Post
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setImageURL(imageUrl);
        post.setAuthorCode(authorCode);

        LocalDateTime postTime= LocalDateTime.now();
        post.setPostTime(postTime);

        // Lưu bài viết vào cơ sở dữ liệu
        Post savedPost = postRepository.save(post);

        try{
            Thread.sleep(15000);
        }catch (Exception e){
            LOGGER.error("There are some error when create a post {}",e.getMessage());
        }

        final long end = System.currentTimeMillis();
//        final LocalTime end= LocalTime.now();
        LOGGER.info("[ASYNC] Time end the request insert post {}",end);
//        LOGGER.info("[ASYNC] Elapsed time to insert post: {}", Duration.between(start,end));
        LOGGER.info("[ASYNC] Elapsed time to insert post: {}", end - start);

        return CompletableFuture.completedFuture(savedPost);
    }

//    @Async
//    // Phuong thuc them binh luan bat dong bo
//    public CompletableFuture<Comment> createComment(Comment comment) {
//        return CompletableFuture.supplyAsync(() -> {
//            // Luu doi tuong binh luan vao co so du lieu
//            Comment savedComment = commentRepository.save(comment);
//            // Tra ve doi tuong binh luan da duoc luu
//            return savedComment;
//        });
//    }

@Async
public CompletableFuture<Comment> createComment(Long postId, String content, String authorCode) throws NotFoundException {
    final long start = System.currentTimeMillis();
    LOGGER.info("[ASYNC] Time start the request insert comment {}",start);
    // Tìm kiếm bài viết theo postId
    Optional<Post> optionalPost = postRepository.findById(postId);
    if (!optionalPost.isPresent()) {
        LOGGER.error("Not found the post with id {}",postId);
        throw new NotFoundException("Không tìm thấy bài viết");
    }

    // Tạo đối tượng Comment với postId và nội dung
    Comment comment = new Comment();
    comment.setCommentContent(content);
    comment.setPost(optionalPost.get());
    comment.setAuthorCode(authorCode);

    // Lưu bình luận vào cơ sở dữ liệu
    Comment savedComment = commentRepository.save(comment);

    final long end = System.currentTimeMillis();
    LOGGER.info("[ASYNC] Time end the request insert comment {}",end);
    LOGGER.info("[ASYNC] Elapsed time to insert comment: {}", (end  - start));

    return CompletableFuture.completedFuture(savedComment);
}

    @Async
    // Lay danh sach binh luan cua mot bai viet bat dong bo
    public CompletableFuture< List<Comment> > getCommentsForPost( Long postId) {
        return CompletableFuture.supplyAsync(() -> {
            // Lay danh sach binh luan dua tren id bai viet
            List<Comment> comments = commentRepository.findByPostId(postId);
            // Tra ve danh sach binh luan
            return comments;
        });
    }

//    private String saveFileToStorage(MultipartFile file) {
//        // Logic để lưu trữ tệp vào hệ thống lưu trữ (ví dụ: AWS S3)
//        // và trả về URL của tệp đã lưu
//
//        String generatedFileName= imageStorageService.storeFile(file);
//        return generatedFileName;
//    }

//    private String saveFileToStorage(MultipartFile file) {
//        // Code để lưu trữ tệp
//        // Ví dụ: lưu trữ tệp vào AWS S3 và trả về URL của tệp
//        String fileUrl = "https://s3.amazonaws.com/bucket-name/" + file.getOriginalFilename();
//        return fileUrl;
//    }


}
