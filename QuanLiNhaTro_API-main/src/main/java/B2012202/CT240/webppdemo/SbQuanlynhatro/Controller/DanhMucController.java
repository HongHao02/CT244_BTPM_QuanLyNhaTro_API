package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.DanhMuc;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiNhaTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ResponseObject;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.DanhMucService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.LoaiNhaTroService;
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
@RequestMapping("/api/nhatro/danhmuc")
public class DanhMucController {
    @Autowired
    private DanhMucService danhMucService;
    //CODE DIFFRENCE
    private static final Logger LOGGER = LoggerFactory.getLogger(DanhMucService.class);

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllDanhMuc() {
        try {
            CompletableFuture<List<DanhMuc>> futureDanhMuc = danhMucService.findAllDanhMuc();
            List<DanhMuc> danhMucs = futureDanhMuc.get(); // Đợi kết quả trả về
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách danh muc thành công.", danhMucs)
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
    public ResponseEntity<ResponseObject> registerDanhMuc(@RequestParam("tenDM") Integer tenDM,
                                                          @RequestParam("moTa") String moTa) {
        DanhMuc existDanhMuc = danhMucService.isExistDanhMuc(tenDM);
        if (existDanhMuc == null) {
            try {
                DanhMuc danhMuc = new DanhMuc();
                danhMuc.setTenDM(tenDM);
                danhMuc.setMoTa(moTa);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo danh muc thành công!", danhMucService.saveDanhMuc(danhMuc))
                );
            } catch (ExecutionException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("error", "Không tạo được danh mục mới!.", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Lỗi! Danh mục không được rỗng hoặc đã tồn tại.", "")
            );
        }
    }
}
