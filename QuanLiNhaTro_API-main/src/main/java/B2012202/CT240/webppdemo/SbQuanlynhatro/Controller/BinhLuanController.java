package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.BaiVietService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.BinhLuanService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.KhachThueService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.UniqueIDService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/api/nhatro/binhluan")
public class BinhLuanController {
    @Autowired
    private BinhLuanService binhLuanService;
    @Autowired
    private BaiVietService baiVietService;
    @Autowired
    private KhachThueService khachThueService;
    @Autowired
    private UniqueIDService uniqueIDService;

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> registerNhaTro(@RequestParam(name = "makt") String makt,
                                                         @RequestParam(name = "idBaiViet") Integer idBaiViet,
                                                         @RequestParam(name = "ndBinhLuan") String ndBinhLuan) {

        KhachThue existKhacThue = khachThueService.findKhachThueByMAKT(makt);
        BaiViet existBaiViet = baiVietService.findBaiVietByIdBaiViet(idBaiViet);

        if (existKhacThue != null && existBaiViet != null) {
            try {
                BinhLuan binhLuan = new BinhLuan();
                binhLuan.setBaiViet(existBaiViet);
                binhLuan.setKhachThue(existKhacThue);

                Integer keyIdBinhLuan = uniqueIDService.getUniqueIDInteger();
                System.out.println("idBinhluan " + keyIdBinhLuan);
                binhLuan.getId().setIdBinhLuan(keyIdBinhLuan);
                binhLuan.getId().setIdBaiViet(existBaiViet.getIdBaiViet());
                binhLuan.getId().setIdUser(existKhacThue.getUser().getIdUser());
                binhLuan.getId().setMakt(existKhacThue.getId().getMakt());

                binhLuan.setNdBinhLuan(ndBinhLuan);

                BinhLuan saved = binhLuanService.saveBinhLuan(binhLuan);
                BinhLuanDTO binhLuanDTO = this.mapBinhLuanToBinhLuanDTO(saved);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo bình luận mới thành công.", binhLuanDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("error", "Không tạo được bình luận mới!.", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Không tìm thấy thông tin về khách thuê hoặc bình luận!.", ""));
        }


    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllBinhLuan() {
        try {
            CompletableFuture<List<BinhLuan>> futureBinhLuans = binhLuanService.findAllBinhLuan();
            List<BinhLuan> binhLuans = futureBinhLuans.get(); // Đợi kết quả trả về
            List<BinhLuanDTO> binhLuanDTOs = binhLuans.stream()
                    .map(this::mapBinhLuanToBinhLuanDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách bình luận thành công.", binhLuanDTOs)
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

    //Ánh xạ Entity BinhLuan thành dữ liệu BinhLuanDTO trả về cho request
    public BinhLuanDTO mapBinhLuanToBinhLuanDTO(BinhLuan binhLuan) {
        BinhLuanDTO binhLuanDTO = new BinhLuanDTO();
        binhLuanDTO.setIdBinhLuan(binhLuan.getId().getIdBinhLuan());
        binhLuanDTO.setIdBaiViet(binhLuan.getBaiViet().getIdBaiViet());
        binhLuanDTO.setIdUser(binhLuan.getId().getIdUser());
        binhLuanDTO.setMakt(binhLuan.getId().getMakt());
        binhLuanDTO.setNdBinhLuan(binhLuan.getNdBinhLuan());
        return binhLuanDTO;
    }
}
