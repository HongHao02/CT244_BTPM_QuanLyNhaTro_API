package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Comment;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Post;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ResponseObject;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.CommentRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.PostRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.ImageStorageService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.PostService;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/async")
public class PostController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostController.class);
    @Autowired
    private PostService postService;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ImageStorageService imageStorageService;

//    @PostMapping("/insertPost")
//    public CompletableFuture<String> createPost( @RequestBody Post post) {
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            // Dang tin va tra ve ket qua
//            CompletableFuture<Post> result = postService.createPost(post);
//            return result;
//        });
//
//        // Tr? v? CompletableFuture cho phép x? lý k?t qu? dang tin mà không c?n ch? hoàn thành
//        return completableFuture;
//    }


    @PostMapping("/insertPost")
    public ResponseEntity<ResponseObject> createPost(@RequestParam("file") MultipartFile file,
                                                     @RequestParam("title") String title,
                                                     @RequestParam("content") String content,
                                                     @RequestParam("authorCode") String authorCode) {


        // Gọi service để tạo bài viết bất đồng bộ bằng CompletableFuture
        CompletableFuture<Post> postFuture = postService.createPost(file, title, content, authorCode);
        // Trả về thông báo thành công cho người dùng
//        return ResponseEntity.ok("Đã tạo bài viết thành công");
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Create the post successfully", postFuture)
        );
    }

    //    @PostMapping("/insertComment")
//    public CompletableFuture<String> createComment(@RequestBody Comment comment) {
//        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
//            // Cho phep binh luan va tra ve ket qua
//            String result = postService.createComment(comment);
//            return result;
//        });
//
//        // Tr? v? CompletableFuture cho phép x? lý k?t qu? bình lu?n mà không c?n ch? hoàn thành
//        return completableFuture;
//    }
    @PostMapping("/insertComment")
    public ResponseEntity<String> createComment(@RequestParam("id") Long postId,
                                                @RequestParam("content") String content,
                                                @RequestParam("authorCode") String authorCode) throws NotFoundException {
        // Gọi service để tạo bình luận bất đồng bộ bằng CompletableFuture
        CompletableFuture<Comment> commentFuture = postService.createComment(postId, content, authorCode);
        // Trả về thông báo thành công cho người dùng
        return ResponseEntity.ok("Đã tạo bình luận thành công");
    }

    // API để tạo bài viết mới
    @PostMapping("/insertSync")
    public ResponseEntity<ResponseObject> createPostSync(
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("authorCode") String authorCode,
            @RequestParam("title") String title,
            @RequestParam("content") String content,
            @RequestParam("file") MultipartFile file) {
//        try{
//            return ResponseEntity.ok(new ResponseObject("ok","Add a new post successfully",postRepository.save(post)));
//        }catch (Exception e){
//            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                    new ResponseObject("false","Some error in server",e.getMessage())
//            );
//        }

        try {

            final long start = System.currentTimeMillis();
            final LocalDateTime localS = LocalDateTime.now();
            LOGGER.info("[SYNC] Time start the request insert post {}/{}", localS, start);
            //save file to a folder =>use a service
            //Khi nhan 1 file gui qua postman hoac app thi no se tra ve 1 generatedFileName, luu vao server va tao 1 ten moi
            String generatedFileName = imageStorageService.storeFile(file);
            //Lay ngay va gio hien tai
            LocalDateTime postTime = LocalDateTime.now();

            // Định dạng mã ngày giờ (neu luu tru theo String)
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//            String datetimeString = postTime.format(formatter);  // Ví dụ: 2023-04-18 09:30:45
            Thread.sleep(5000);
            final long end = System.currentTimeMillis();
            final LocalDateTime localE = LocalDateTime.now();
            LOGGER.info("[SYNC] Time end the request insert post SYNC {}/{}", localE, end);
            LOGGER.info("[SYNC] Elapsed {}", end - start);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Upload file successfully", postRepository.save(new Post(
                            categoryId, authorCode, title, content, postTime, generatedFileName
                    )))
                    //"8bea41f9a5064693ae6191058b2fe5ea.png"--> how to open it in Web brower?
            );

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("false", e.getMessage(), "")
            );
        }
    }

    // API để tạo bình luận cho bài viết ( Bình luận theo kiểu có full Thông tin của comment)
//    @PostMapping("/{postId}/comments")
//    public ResponseEntity<ResponseObject> createCommentSync(@PathVariable Long postId, @RequestBody Comment comment) throws NotFoundException {
////        Post post = postRepository.findById(postId).orElseThrow(() -> new NotFoundException("Không tìm thấy bài viết"));
////        comment.setPost(post);
////        Comment createdComment = commentRepository.save(comment);
////        return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
//
//        Optional< Post > postexists= postRepository.findById(postId);
//        return (!postexists.isPresent()) ?
//        ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//                new ResponseObject("false","Don't find any post to comment","")
//        )
//        :
//        ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("ok","The comment is sucessfully",commentRepository.save(comment))
//        );
//    }

    @PostMapping("/{postId}/comments")
    public ResponseEntity<ResponseObject> createCommentSync(@PathVariable Long postId, @RequestParam("authorCode") String authorCode,
                                                            @RequestParam("commentContent") String commentContent) {

        // Tìm kiếm post theo postId
        Optional<Post> postexists = postRepository.findById(postId);
        final long start = System.currentTimeMillis();
        LOGGER.info("[SYNC] Time start the request insert comment {}", start);
        if (postexists.isPresent()) {
            // Tạo đối tượng Comment với postId và nội dung
            Comment comment = new Comment();
            comment.setCommentContent(commentContent);
            comment.setPost(postexists.get());
            comment.setAuthorCode(authorCode);
            // Lưu bình luận vào cơ sở dữ liệu
//            Comment savedComment = commentRepository.save(comment);

            final long end = System.currentTimeMillis();
            LOGGER.info("[SYNC]Time end the request insert comment {}", end);
            LOGGER.info("[SYNC]Elapsed time to insert comment: {}", (end - start));
            Comment save = commentRepository.save(comment);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Create comment successfully ", save)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false", "Not found post with id ", postId)
            );
        }

    }
}
