package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ResponseObject;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.ImageStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/nhatro/files")
public class FileUploadController {
    //Inject store Service here---== Create Object ( only 1 time) which implement StogareService interface
    @Autowired
    private ImageStorageService stogareService;

    //This controller receive file/image from client ( capacity is set first)
    @PostMapping("/upload")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file") MultipartFile file) {
        //Phai co try catch phong trong truong chon file co su co, khong co quyen truy cap
        try {
            //save file to a folder =>use a service
            //Khi nhan 1 file gui qua postman hoac app thi no se tra ve 1 generatedFileName, luu vao server va tao 1 ten moi
            String generatedFileName = stogareService.storeFile(file);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Upload file successfully", generatedFileName)
                    //"8bea41f9a5064693ae6191058b2fe5ea.png"--> how to open it in Web brower?

            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("false", e.getMessage(), "")
            );
        }
    }

    //get image's url
    //Anh xa den controller , file name , `.+` la phan duoi file(.png)--> muc dich khong cho ben ngoai thay duong
    //dan file trong server

    @GetMapping("/{fileName:.+}")
    //Tra ve danh sach cac byte---> doc du lieu 1 file tra ve danh sach cac byte
    //goi readFile cua ImageStogareService
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = stogareService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);//dung thay cho HttpStatus.OK

        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }

    //How to load all upload files?
    //se goi ham loadAll cua ImageStogareService
    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadFiles() {
        try {
            List<String> urls = stogareService.loadAll()
                    .map(path -> {
                        System.out.println("findPath " + path);
                        //convert fileName to url ( send request to "readDetailFile")
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    }).collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("ok", "List all files upload successfully", urls));
        } catch (Exception e) {
            //Neu that bai tra ve cac String rong
            return ResponseEntity.ok(new ResponseObject("failed", "List all files upload failed", new String[]{}));
        }
    }


}
