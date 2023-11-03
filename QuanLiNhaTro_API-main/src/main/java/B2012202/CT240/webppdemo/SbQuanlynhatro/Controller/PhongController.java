package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.*;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhatro/phong")
public class PhongController {
    @Autowired
    private NhaTroService nhaTroService;
    @Autowired
    private LauService lauService;

    @Autowired
    private DayService dayService;

    @Autowired
    private PhongService phongService;
    @Autowired
    private LoaiPhongService loaiPhongService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllPhong() {
        try {
            CompletableFuture<List<PhongTro>> futurePhong = phongService.findAllPhong();
            List<PhongTro> phongs = futurePhong.get(); // Đợi kết quả trả về
            List<PhongDTO> phongDTOs = phongs.stream()
                    .map(this::mapPhongToPhongDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách Phòng thành công.", phongDTOs)
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
    public ResponseEntity<ResponseObject> addPhong(@RequestParam(name = "idNhaTro") Integer idNhaTro,
                                                   @RequestParam(name = "sttLau") Integer sttLau,
                                                   @RequestParam(name = "sttDay") Integer sttDay,
                                                   @RequestParam(name = "sttPhong") Integer sttPhong,
                                                   @RequestParam(name = "tenLoaiP") String tenLoaiP,
                                                   @RequestParam(name = "viTri") String viTri,
                                                   @RequestParam(name = "giaPhong") Float giaPhong,
                                                   @RequestParam(name = "tinhTrang") boolean tinhTrang) {
        NhaTro nhaTro = nhaTroService.findNhaTroByidNhaTro(idNhaTro);
        Lau existLau = lauService.findLauBySttLauAndIdNhaTro(sttLau, idNhaTro);
        Day existDay = dayService.findDayByAllInfo(sttLau, idNhaTro, sttDay);
        LoaiPhong existLoaiPhong = loaiPhongService.findloaiPhongByTenLoaiP(tenLoaiP);
        PhongTro existPhong = phongService.findPhongByAllInfo(sttLau, idNhaTro, sttDay, sttPhong);


        if (nhaTro != null && existLau != null && existDay != null && existLoaiPhong != null && existPhong == null
                && sttPhong >= 1 && sttPhong <= existDay.getTongPhongD()) {
            try {
                PhongTro phong = new PhongTro();

                phong.getId().setIdNhaTro(idNhaTro);
                phong.getId().setSttLau(sttLau);
                phong.getId().setSttDay(sttDay);
                phong.getId().setSttPhong(sttPhong);

                phong.setDay(existDay);
                phong.setLoaiPhong(existLoaiPhong);
                phong.setViTri(viTri);
                phong.setGiaPhong(giaPhong);
                phong.setTinhTrang(tinhTrang);

                PhongTro saved = phongService.savePhong(phong);
                PhongDTO phongDTO = this.mapPhongToPhongDTO(saved);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo phòng thành công.", phongDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình thêm phòng mới!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Thông tin nhập vào không hợp lệ!", "")
            );
        }

    }

    public PhongDTO mapPhongToPhongDTO(PhongTro phong) {
        PhongDTO phongDTO = new PhongDTO();
        phongDTO.setIdNhaTro(phong.getId().getIdNhaTro());
        phongDTO.setSttLau(phong.getId().getSttLau());
        phongDTO.setSttDay(phong.getId().getSttDay());
        phongDTO.setSttPhong(phong.getId().getSttPhong());
        phongDTO.setIdLoai(phong.getLoaiPhong().getIdLoai());
        phongDTO.setViTri(phong.getViTri());
        phongDTO.setGiaPhong(phong.getGiaPhong());
        phongDTO.setTinhTrang(phong.getTinhTrang());
        return phongDTO;
    }
}
