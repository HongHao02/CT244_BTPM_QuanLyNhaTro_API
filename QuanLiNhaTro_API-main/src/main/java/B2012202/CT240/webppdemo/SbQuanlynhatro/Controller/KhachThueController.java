package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.ChuTroService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.KhachThueService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.PhongService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.UserService;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhatro/khachthue")
public class KhachThueController {
    @Autowired
    private PhongService phongService;

    @Autowired
    private KhachThueService khachThueService;

    @Autowired
    private UserService userService;
    @Autowired
    private ChuTroService chuTroService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllKhachThue() {
        try {
            CompletableFuture<List<KhachThue>> futureKhachThue = khachThueService.findAllKhachThue();
            List<KhachThue> khachThues = futureKhachThue.get(); // Đợi kết quả trả về
            List<KhachThueDTO> khachThueDTOList = khachThues.stream()
                    .map(this::mapKhachThueToKhachThueDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách Khách thuê thành công.", khachThueDTOList)
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách dãy!.", ""));
        } catch (java.util.concurrent.ExecutionException e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách dãy!.", ""));

        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addKhachThue(@RequestParam(name = "idUser") Long idUser) {
        User existUser = userService.findUserByIdUser(idUser);
        ChuTro existChuTro = chuTroService.findChuTroByMaChuTro("CT" + idUser);

        if (existUser != null && existChuTro == null) {
            try {
                KhachThue khachThue = new KhachThue();
                khachThue.setUser(existUser);

                KhachThue saved = khachThueService.saveKhachThue(khachThue);
                KhachThueDTO khachThueDTO = this.mapKhachThueToKhachThueDTO(saved);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo khách thuê thành công.", khachThueDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình thêm khách mới!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Thông tin User không hợp lệ hoặc đã được đăng kí cho chủ trọ!", "")
            );
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateKhachThue(@RequestParam(name = "idUser") Long idUser,
                                                          @RequestParam(name = "idNhaTro") Integer idNhaTro,
                                                          @RequestParam(name = "sttLau") Integer sttLau,
                                                          @RequestParam(name = "sttDay") Integer sttDay,
                                                          @RequestParam(name = "sttPhong") Integer sttPhong) {
        KhachThue existkhachThue = khachThueService.findKhachThueByMAKT("KT" + idUser);
        User existUser = userService.findUserByIdUser(idUser);
        PhongTro existPhongTro = phongService.findPhongByAllInfo(sttLau, idNhaTro, sttDay, sttPhong);
        if (existkhachThue != null && existPhongTro != null) {
            try {
                KhachThue khachThue = new KhachThue();
                //setID
//                khachThue.getId().setIdUser(idUser);


                existkhachThue.setPhongTro(existPhongTro);

                KhachThue updateKhachThue = khachThueService.saveKhachThue(existkhachThue);
                KhachThueDTO khachThueDTO = this.mapKhachThueToKhachThueDTO(updateKhachThue);
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Cập nhật khách thuê thành công.", khachThueDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình cập nhật khách mới!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Thông tin User hoặc phòng đăng kí không hợp lệ!", "")
            );
        }

    }

    public KhachThueDTO mapKhachThueToKhachThueDTO(KhachThue khachThue) {
        KhachThueDTO khachThueDTO = new KhachThueDTO();
        khachThueDTO.setIdUser(khachThue.getUser().getIdUser());
        khachThueDTO.setMakt(khachThue.getId().getMakt());
        if (khachThue.getPhongTro() != null) {
            khachThueDTO.setIdNhaTro(khachThue.getPhongTro().getId().getIdNhaTro());
            khachThueDTO.setSttLau(khachThue.getPhongTro().getId().getSttLau());
            khachThueDTO.setSttDay(khachThue.getPhongTro().getId().getSttDay());
            khachThueDTO.setSttPhong(khachThue.getPhongTro().getId().getSttPhong());
        }

        return khachThueDTO;
    }
}
