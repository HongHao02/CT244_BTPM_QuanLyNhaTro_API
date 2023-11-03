package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiNhaTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiPhong;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ResponseObject;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.LoaiNhaTroService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.LoaiPhongService;
import org.hibernate.sql.exec.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/nhatro/phongtro/loaiphong")
public class LoaiPhongController {
    @Autowired
    private LoaiPhongService loaiPhongService;

    private static final Logger LOGGER = LoggerFactory.getLogger(LoaiPhongService.class);

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllLoaiPhong() {
        try {
            CompletableFuture<List<LoaiPhong>> futureLoaiPhongs = loaiPhongService.findAllLoaiPhong();
            List<LoaiPhong> LPhong = futureLoaiPhongs.get(); // Đợi kết quả trả về
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách loại phòng thành công.", LPhong)
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

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addLoaiPhong(@RequestParam("tenLoaiP") String tenLoaiP) {
        if (tenLoaiP != null && loaiPhongService.findloaiPhongByTenLoaiP(tenLoaiP) == null) {
            try {
                LoaiPhong loaiPhong = new LoaiPhong(tenLoaiP);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo loại phòng thành công!", loaiPhongService.saveLoaiPhong(loaiPhong))
                );
            } catch (ExecutionException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("error", "Không tạo được loại phòng mới!.", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Lỗi! Loại phòng không được rỗng hoặc đã tồn tại.", "")
            );
        }
    }
}
