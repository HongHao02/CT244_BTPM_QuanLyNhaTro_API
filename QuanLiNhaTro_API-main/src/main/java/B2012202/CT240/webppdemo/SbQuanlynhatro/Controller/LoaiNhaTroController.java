package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.LoaiNhaTroService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.UserService;
import org.hibernate.sql.exec.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/nhatro/loainhatro")
public class LoaiNhaTroController {
    @Autowired
    private LoaiNhaTroService loaiNhaTroService;
    //CODE DIFFRENCE
    private static final Logger LOGGER = LoggerFactory.getLogger(LoaiNhaTroService.class);

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllLoaiNhaTro() {
        try {
            CompletableFuture<List<LoaiNhaTro>> futureUsers = loaiNhaTroService.findAllLoaiNhaTro();
            List<LoaiNhaTro> LNTro = futureUsers.get(); // Đợi kết quả trả về
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách loại nhà trọ thành công.", LNTro)
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

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> registerLoaiNhaTro(@RequestParam("tenLoaiNT") String tenLoaiNT) {
        if (tenLoaiNT != null && loaiNhaTroService.isExistLoaiNhaTro(tenLoaiNT)) {
            try {
                LoaiNhaTro LNTro = new LoaiNhaTro(tenLoaiNT);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo loại nhà trọ thành công!", loaiNhaTroService.saveLoaiNhaTro(LNTro))
                );
            } catch (ExecutionException e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("error", "Không tạo được loại nhà trọ mới!.", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("error", "Lỗi! Loại nhà trọ không được rỗng hoặc đã tồn tại.", "")
            );
        }
    }
}
