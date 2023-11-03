package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.*;
import org.hibernate.sql.exec.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhatro")
public class NhaTroController {
    @Autowired
    private NhaTroService nhaTroService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChuTroService chuTroService;
    @Autowired
    private LoaiNhaTroService loaiNhaTroService;

    @Autowired
    private LauService lauService;
    private static final Logger LOGGER = LoggerFactory.getLogger(NhaTroService.class);

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> registerNhaTro(@RequestParam(name = "tenLoaiNT") String tenLoaiNT,
                                                         @RequestParam(name = "maChuTro") String maChuTro,
                                                         @RequestParam(name = "tenNT") String tenNT,
                                                         @RequestParam(name = "diaChi") String diaChi,
                                                         @RequestParam(name = "soPhong") Integer soPhong,
                                                         @RequestParam(name = "tongPhong") Integer tongPhong) {

        ChuTro chuTro = chuTroService.findChuTroByMaChuTro(maChuTro);
        if (chuTro != null) {
            LOGGER.info("CHUTRO " + chuTro.getId().getMaChuTro());
        } else {
            LOGGER.info("CHUTRO is null");
        }
        Boolean isLoaiNhaTro = loaiNhaTroService.isExistLoaiNhaTro(tenLoaiNT);
        LoaiNhaTro loaiNhaTro = loaiNhaTroService.findloaiNhaTroByTenLoaiNT(tenLoaiNT);
        LOGGER.info("Loai nha tro " + tenLoaiNT + " MaChuTro " + maChuTro);
        //Kiểm tra nếu loại nhà trọ hợp lệ nghĩa là đã có trong DB
        if (chuTro != null && loaiNhaTro != null) {
            try {
                NhaTro nhaTro = new NhaTro();
                nhaTro.setChuTro(chuTro);
                nhaTro.setLoaiNhaTro(loaiNhaTro);
                nhaTro.setTenNT(tenNT);
                nhaTro.setDiaChi(diaChi);
                nhaTro.setSoPhong(soPhong);
                nhaTro.setTongPhong(tongPhong);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo nhà trọ mới thành công.", nhaTroService.saveNhaTro(nhaTro))
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("error", "Không tạo được nhà trọ mới!.", ""));
            }
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Không tìm thấy thông tin chủ trọ hoặc loại nhà trọ!.", ""));
        }


    }

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllNhaTro() {
        try {
            CompletableFuture<List<NhaTro>> futureNhaTros = nhaTroService.findAllNhaTro();
            List<NhaTro> nhatros = futureNhaTros.get(); // Đợi kết quả trả về
            List<NhaTroDTO> nhatroDTOs = nhatros.stream()
                    .map(this::mapNhaTroToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách nhà trọ thành công.", nhatroDTOs)
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
    public NhaTroDTO mapNhaTroToDTO(NhaTro nhaTro) {
        NhaTroDTO dto = new NhaTroDTO();
        dto.setIdNhaTro(nhaTro.getIdNhaTro());
        dto.setChuTro(nhaTro.getChuTro());
        dto.setLoaiNhaTro(nhaTro.getLoaiNhaTro());
        dto.setTenNT(nhaTro.getTenNT());
        dto.setDiaChi(nhaTro.getDiaChi());
        dto.setSoPhong(nhaTro.getSoPhong());
        dto.setTongPhong(nhaTro.getTongPhong());
        return dto;
    }
}
