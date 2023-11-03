package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Controller.FileUploadController;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Controller.UserController;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Files;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.KhachThue;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.FilesRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class FilesService {

    @Autowired
    private FilesRepository filesRepository;

    @Autowired
    private ImageStorageService imageStorageService;

    Logger logger = LoggerFactory.getLogger(FilesService.class);

//    public List<String> getAllUrlURLFile() {
//        List<String> urls = imageStorageService.loadAll()
//                .map(path -> {
//                    System.out.println("findPath " + path);
//                    //convert fileName to url ( send request to "readDetailFile")
//                    String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                            "readDetailFile", path.getFileName().toString()).build().toUri().toString();
//                    return urlPath;
//                }).collect(Collectors.toList());
//
//        return urls;
//    }

//    public String getUrlFile(MultipartFile file) {
//        String avtName = imageStorageService.storeFile(file);
//        Path[] paths = imageStorageService.loadFile(avtName).toArray(Path[]::new);
//        //Kiểm tra xem file có tồn tại không
//        if (paths.length > 0) {
//            Path filePath = paths[0];
//            String urlPath = MvcUriComponentsBuilder.fromMethodName(FilesService.class,
//                    "readDetailFile", filePath.getFileName().toString()).build().toUri().toString();
////                    UrlResource resource = new UrlResource(filePath.toUri());
////                    System.out.println("URL " + resource);
//            System.out.println("urlAVT " + urlPath);
//            return urlPath;
//        }
//        return "";
//    }

    @Async
    public CompletableFuture<List<Files>> findAllFiles() {
        logger.info("get list of file by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of Files {}", start);
        List<Files> dsFiles = filesRepository.findAll();
        return CompletableFuture.completedFuture(dsFiles);
    }

    public Files saveFiles(Files file) {
        return filesRepository.save(file);
    }

//    public ResponseEntity<byte[]> readDetailFile(String fileName) {
//        try {
//            byte[] bytes = imageStorageService.readFileContent(fileName);
//            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);//dung thay cho HttpStatus.OK
//
//        } catch (Exception e) {
//            return ResponseEntity.noContent().build();
//        }
//    }

}
