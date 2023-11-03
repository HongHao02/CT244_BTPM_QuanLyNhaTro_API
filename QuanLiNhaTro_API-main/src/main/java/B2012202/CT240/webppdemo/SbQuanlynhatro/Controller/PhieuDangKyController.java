package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.KhachThueService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.PhieuDangKyService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhatro/phieudangky")
public class PhieuDangKyController {
    @Autowired
    private PhieuDangKyService phieuDangKyService;

    @Autowired
    private KhachThueService khachThueService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllPhieuDangKy() {
        try {
            CompletableFuture<List<PhieuDangKy>> futurePhieuDangKy = phieuDangKyService.findAllPhieuDangKy();
            List<PhieuDangKy> phieuDangKys = futurePhieuDangKy.get(); // Đợi kết quả trả về
            List<PhieuDangKyDTO> phieuDangKyDTOList = phieuDangKys.stream()
                    .map(this::mapPDKtoPhieuDangKyDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách Phiếu đăng kí thành công.", phieuDangKyDTOList)
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách Phiếu đăng ký!.", ""));
        } catch (java.util.concurrent.ExecutionException e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách Phiếu đăng ký!.", ""));

        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addPhong(@RequestParam(name = "makt") String makt) {
        KhachThue existKhachThue = khachThueService.findKhachThueByMAKT(makt);

        if (existKhachThue != null) {
            try {
                PhieuDangKy phieuDangKy = new PhieuDangKy();
                phieuDangKy.setKhachThue(existKhachThue);
                phieuDangKy.setThoiGian(LocalDateTime.now());

                PhieuDangKy saved = phieuDangKyService.savePhieuDangKy(phieuDangKy);
                PhieuDangKyDTO phieuDangKyDTO = this.mapPDKtoPhieuDangKyDTO(saved);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo phiếu đăng ký thành công.", phieuDangKyDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình thêm phiếu đăng ký mới!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Không có thông tin khách thuê này!", "")
            );
        }

    }

    public PhieuDangKyDTO mapPDKtoPhieuDangKyDTO(PhieuDangKy phieuDangKy) {
        PhieuDangKyDTO phieuDangKyDTO = new PhieuDangKyDTO();
        phieuDangKyDTO.setSttPhieu(phieuDangKy.getSttPhieu());
        phieuDangKyDTO.setIdUser(phieuDangKy.getKhachThue().getUser().getIdUser());
        phieuDangKyDTO.setMakt(phieuDangKy.getKhachThue().getId().getMakt());
        phieuDangKyDTO.setThoiGian(phieuDangKy.getThoiGian());
        return phieuDangKyDTO;
    }

}
