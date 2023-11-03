package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ApiResponse;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ResponseObject;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.User;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.ChuTroService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.UserService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/nhatro/chutro")
public class ChuTroController {
    @Autowired
    private UserService userService;
    @Autowired
    private ChuTroService chuTroService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllChuTro() {
        try {
            CompletableFuture<List<ChuTro>> futureChuTro = chuTroService.findAllChuTro();
            List<ChuTro> chutros = futureChuTro.get(); // Đợi kết quả trả về
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách chủ trọ thành công.", chutros)
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

    @PostMapping("/register/{id}")
    public ResponseEntity<ResponseObject> readDetailFile(@PathVariable Long id) {
        User existUser = userService.findUserByIdUser(id);
        if (existUser != null) {
            try {
                ChuTro chutro = new ChuTro();
                chutro.setUser(existUser);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo chủ trọ thành công.", chuTroService.saveChuTro(chutro))
                );
            } catch (Exception ex) {
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình thêm chủ trọ mới!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Không tìm thấy user!", "")
            );
        }

    }
}
