package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.ChiTietDangKyService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.KhachThueService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.PhieuDangKyService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.PhongService;
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
@RequestMapping("/api/nhatro/chitietdangky")
public class ChiTietDangKyController {
    @Autowired
    private PhieuDangKyService phieuDangKyService;

    @Autowired
    private ChiTietDangKyService chiTietDangKyService;

    @Autowired
    private PhongService phongService;

    @Autowired
    private KhachThueService khachThueService;


    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllChiTietDangKy() {
        try {
            CompletableFuture<List<ChiTietDangKy>> futureChiTietDangKy = chiTietDangKyService.findAllChiTietDangKy();
            List<ChiTietDangKy> chiTietDangKys = futureChiTietDangKy.get(); // Đợi kết quả trả về
            List<ChiTietDangKyDTO> chiTietDangKyDTOList = chiTietDangKys.stream()
                    .map(this::mapCTDKToChiTietDangKyDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách Chi tiết đăng ký thành công.", chiTietDangKyDTOList)
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách Chi tiết đăng ký!.", ""));
        } catch (java.util.concurrent.ExecutionException e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách Chi tiết đăng ký!.", ""));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addChiTietDangKy(@RequestParam(name = "sttPhieu") Integer sttPhieu,
                                                           @RequestParam(name = "idNhaTro") Integer idNhaTro,
                                                           @RequestParam(name = "sttLau") Integer sttLau,
                                                           @RequestParam(name = "sttDay") Integer sttDay,
                                                           @RequestParam(name = "sttPhong") Integer sttPhong) {
        PhieuDangKy existPhieuDangKy = phieuDangKyService.findPhieuDangKyBySTTPhieu(sttPhieu);
        PhongTro existPhongTro = phongService.findPhongByAllInfo(sttLau, idNhaTro, sttDay, sttPhong);
        ChiTietDangKy existChiTietDangKy = chiTietDangKyService.findChiTietDangKyByAllInfo(sttPhieu, idNhaTro, sttLau, sttDay, sttPhong);
        if (existPhieuDangKy != null && existPhongTro != null && existChiTietDangKy == null) {
            try {
                ChiTietDangKy chiTietDangKy = new ChiTietDangKy();
                chiTietDangKy.setPhieuDangKy(existPhieuDangKy);

                //set the fields for id
                chiTietDangKy.getId().setIdNhaTro(idNhaTro);
                chiTietDangKy.getId().setSttLau(sttLau);
                chiTietDangKy.getId().setSttDay(sttDay);
                chiTietDangKy.getId().setSttPhong(sttPhong);

                //set Phong
                chiTietDangKy.setPhongTro(existPhongTro);

                //set thoiGianDangky
                chiTietDangKy.setThoiGianDangKy(LocalDateTime.now());


                ChiTietDangKy saved = chiTietDangKyService.saveChiTietDangKy(chiTietDangKy);
                ChiTietDangKyDTO chiTietDangKyDTO = this.mapCTDKToChiTietDangKyDTO(saved);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo chi tiết đăng ký thành công.", chiTietDangKyDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình thêm chi tiết đăng ký mới!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Không có thông tin phiếu đăng ký hoặc phòng trọ!", "")
            );
        }

    }

    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateChiTietDangKy(
            @RequestParam(name = "sttPhieu") Integer sttPhieu,
            @RequestParam(name = "idNhaTro") Integer idNhaTro,
            @RequestParam(name = "sttLau") Integer sttLau,
            @RequestParam(name = "sttDay") Integer sttDay,
            @RequestParam(name = "sttPhong") Integer sttPhong,
            @RequestParam(name = "tienCoc") float tienCoc,
            @RequestParam(name = "trangThaiDuyet") String trangThaiDuyet
    ) {
        // Trước hết, bạn cần kiểm tra xem bản ghi ChiTietDangKy đã tồn tại hay chưa.
        ChiTietDangKy existChiTietDangKy = chiTietDangKyService.findChiTietDangKyByAllInfo(sttPhieu, idNhaTro, sttLau, sttDay, sttPhong);
        PhieuDangKy existPhieuDangKy = phieuDangKyService.findPhieuDangKyBySTTPhieu(sttPhieu);
        if (existChiTietDangKy != null) {
            try {
                // Cập nhật các trường trạng thái và tiền cọc.
                existChiTietDangKy.setTienCoc(tienCoc);

                if (trangThaiDuyet.equalsIgnoreCase("true")) {
                    existChiTietDangKy.setTrangThaiDuyet(true);
                } else {
                    existChiTietDangKy.setTrangThaiDuyet(false);
                }

                // Lưu bản ghi đã cập nhật vào cơ sở dữ liệu.
                ChiTietDangKy updated = chiTietDangKyService.saveChiTietDangKy(existChiTietDangKy);
                ChiTietDangKyDTO updatedDTO = this.mapCTDKToChiTietDangKyDTO(updated);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Cập nhật chi tiết đăng ký thành công.", updatedDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình cập nhật chi tiết đăng ký!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Không tìm thấy chi tiết đăng ký.", "")
            );
        }
    }

    public ChiTietDangKyDTO mapCTDKToChiTietDangKyDTO(ChiTietDangKy chiTietDangKy) {
        ChiTietDangKyDTO chiTietDangKyDTO = new ChiTietDangKyDTO();
        chiTietDangKyDTO.setSttPhieu(chiTietDangKy.getPhieuDangKy().getSttPhieu());
        chiTietDangKyDTO.setIdNhaTro(chiTietDangKy.getPhongTro().getId().getIdNhaTro());
        chiTietDangKyDTO.setSttLau(chiTietDangKy.getPhongTro().getId().getSttLau());
        chiTietDangKyDTO.setSttDay(chiTietDangKy.getPhongTro().getId().getSttDay());
        chiTietDangKyDTO.setSttPhong(chiTietDangKy.getPhongTro().getId().getSttPhong());

        chiTietDangKyDTO.setThoiGianDangKy(chiTietDangKy.getThoiGianDangKy());
        chiTietDangKyDTO.setTienCoc(chiTietDangKy.getTienCoc());
        chiTietDangKyDTO.setTrangThaiDuyet(chiTietDangKy.isTrangThaiDuyet());

        return chiTietDangKyDTO;
    }
}
