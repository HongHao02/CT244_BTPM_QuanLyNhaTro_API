package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.*;
import org.hibernate.sql.exec.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhatro/baiviet")
public class BaiVietController {
    @Autowired
    private BaiVietService baiVietService;

    @Autowired
    private FilesService filesService;

    @Autowired
    private PhongService phongService;

    @Autowired
    private ChuTroService chuTroService;
    @Autowired
    private DanhMucService danhMucService;

    @Autowired
    private PostFilesService postFilesService;

    @Autowired
    private ImageStorageService imageStorageService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiVietController.class);

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> registerNhaTro(@RequestParam(name = "tenDM") Integer tenDM,
                                                         @RequestParam(name = "maChuTro") String maChuTro,
                                                         @RequestParam(name = "tieuDe") String tieuDe,
                                                         @RequestParam(name = "ndBaiViet") String ndBaiViet,
                                                         @RequestParam(name = "file1") MultipartFile file1) {

        ChuTro existChuTro = chuTroService.findChuTroByMaChuTro(maChuTro);
        DanhMuc existDanhMuc = danhMucService.isExistDanhMuc(tenDM);

        if (existChuTro != null && existDanhMuc != null) {
            try {
                BaiViet baiViet = new BaiViet();
                Files file = new Files();
                Files savedFiles= new Files();
                PostFiles postFiles = new PostFiles();

                String urlFile = "";
                //----filecontroller
                if(!file1.isEmpty()){
                    String avtName;
                    avtName = imageStorageService.storeFile(file1);
                    if(avtName==""){
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                                new ResponseObject("fail", "Định dạng hoặc kích thước tệp không hợp lệ.", "")
                        );
                    }
                    Path[] paths = imageStorageService.loadFile(avtName).toArray(Path[]::new);
                    //Kiểm tra xem file có tồn tại không
                    if (paths.length > 0) {
                        Path filePath = paths[0];
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(BaiVietController.class,
                                "readDetailFile", filePath.getFileName().toString()).build().toUri().toString();
                        System.out.println("urlAVT " + urlPath);
                        urlFile = urlPath;
                    }
                    //---
//                String urlFile = filesService.getUrlFile(file1);
//                System.out.println("FileURL " + urlFile);
                    if (urlFile != "") {
                        file.setUrl(urlFile);
                    }
                    savedFiles = filesService.saveFiles(file);
                }

                baiViet.setDanhMuc(existDanhMuc);
                baiViet.setChuTro(existChuTro);
                baiViet.setTieuDe(tieuDe);
                baiViet.setNdBaiViet(ndBaiViet);
                baiViet.setThoiGianDang(LocalDateTime.now());

                BaiViet savedBaiViet = baiVietService.saveBaiViet(baiViet);
                if (urlFile != null) {
                    postFiles.setBaiViet(savedBaiViet);
                    postFiles.setFiles(savedFiles);
                }
                PostFiles postFilesSaved = new PostFiles();
                if (postFiles != null) {
                    postFilesSaved = postFilesService.savePostFiles(postFiles);
                }
                BaiVietDTO baiVietDTO = this.mapBaiViettoBaiVietDTO(savedBaiViet);
                baiVietDTO.setFileUrl(postFilesSaved.getFiles().getUrl());

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo bài viết mới thành công.", baiVietDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("error", "Không tạo được bài viết mới!.", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Không tìm thấy thông tin chủ trọ hoặc danh mục!.", ""));
        }


    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllBaiViet() {
        try {
            CompletableFuture<List<BaiViet>> futureBaiViets = baiVietService.findAllBaiViet();
            List<BaiViet> baiViets = futureBaiViets.get(); // Đợi kết quả trả về
            List<BaiVietDTO> baiVietDTOs = baiViets.stream()
                    .map(this::mapBaiViettoBaiVietDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách bài viết thành công.", baiVietDTOs)
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách!.", ""));
        } catch (java.util.concurrent.ExecutionException e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách!.", ""));

        }
    }

    //Ánh xạ Enity NhaTro vào NhaTroDTO cho dữ liệu trả về từ request
    public BaiVietDTO mapBaiViettoBaiVietDTO(BaiViet baiViet) {
        BaiVietDTO baiVietDTO = new BaiVietDTO();
        baiVietDTO.setIdBaiViet(baiViet.getIdBaiViet());
        baiVietDTO.setMaChuTro(baiViet.getChuTro().getId().getMaChuTro());
        baiVietDTO.setIdUser(baiViet.getChuTro().getUser().getIdUser());
        baiVietDTO.setIdDM(baiViet.getDanhMuc().getIdDM());
        baiVietDTO.setTieuDe(baiViet.getTieuDe());
        baiVietDTO.setNdBaiViet(baiViet.getNdBaiViet());
        baiVietDTO.setThoiGianDang(baiViet.getThoiGianDang());

        return baiVietDTO;
    }

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = imageStorageService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);//dung thay cho HttpStatus.OK

        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }
}
