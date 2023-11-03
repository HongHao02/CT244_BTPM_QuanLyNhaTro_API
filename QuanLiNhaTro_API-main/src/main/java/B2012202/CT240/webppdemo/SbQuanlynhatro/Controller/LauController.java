package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.LauService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.NhaTroService;
import org.hibernate.sql.exec.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/nhatro/lau")
public class LauController {
    @Autowired
    private LauService lauService;

    @Autowired
    private NhaTroService nhaTroService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllLau() {
        try {
            CompletableFuture<List<Lau>> futureLau = lauService.findAllLau();
            List<Lau> laus = futureLau.get(); // Đợi kết quả trả về
            List<LauDTO> lauDTOs = laus.stream()
                    .map(this::mapToLauDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách lầu thành công.", lauDTOs)
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách lầu!.", ""));
        } catch (java.util.concurrent.ExecutionException e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách lầu!.", ""));

        }
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseObject> addLau(@RequestParam(name = "idNhaTro") Integer idNhaTro,
                                                 @RequestParam(name = "sttLau") Integer sttLau,
                                                 @RequestParam(name = "tongDay") Integer tongDay) {
        NhaTro nhaTro = nhaTroService.findNhaTroByidNhaTro(idNhaTro);
        Lau existLau = lauService.findLauBySttLauAndIdNhaTro(sttLau, idNhaTro);

        if (nhaTro != null && existLau == null && tongDay >= 1) {
            try {
                Lau lau = new Lau(tongDay);

                LauID lauID = new LauID();
                lauID.setSttLau(sttLau);

                lau.setId(lauID);
                lau.setNhaTro(nhaTro);
//                lau.getId().setIdNhaTro(idNhaTro);
                Lau saved = lauService.saveLau(lau);
                LauDTO lauDTO = this.mapToLauDTO(saved);


                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo lầu thành công.", lauDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình thêm lầu mới!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Không tìm thấy thông tin nhà trọ hoặc lầu không hợp lệ hoặc đã tồn tại!", "")
            );
        }

    }

    public LauDTO mapToLauDTO(Lau lau) {
        LauDTO lauDTO = new LauDTO();
        lauDTO.setIdNhaTro(lau.getId().getIdNhaTro());
        lauDTO.setSttLau(lau.getId().getSttLau());
        lauDTO.setTongDay(lau.getTongDay());
        return lauDTO;
    }


}
