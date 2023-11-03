package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.DayService;
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
@RequestMapping("/api/nhatro/day")
public class DayController {
    @Autowired
    private NhaTroService nhaTroService;
    @Autowired
    private LauService lauService;

    @Autowired
    private DayService dayService;

    @GetMapping("")
    public ResponseEntity<ResponseObject> getAllDay() {
        try {
            CompletableFuture<List<Day>> futureDay = dayService.findAllDay();
            List<Day> days = futureDay.get(); // Đợi kết quả trả về
            List<DayDTO> dayDTOs = days.stream()
                    .map(this::mapDayToDayDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách dãy thành công.", dayDTOs)
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
    public ResponseEntity<ResponseObject> addDay(@RequestParam(name = "idNhaTro") Integer idNhaTro,
                                                 @RequestParam(name = "sttLau") Integer sttLau,
                                                 @RequestParam(name = "sttDay") Integer sttDay,
                                                 @RequestParam(name = "tongPhongD") Integer tongPhongD) {
        NhaTro nhaTro = nhaTroService.findNhaTroByidNhaTro(idNhaTro);
        Lau existLau = lauService.findLauBySttLauAndIdNhaTro(sttLau, idNhaTro);
        Day existDay = dayService.findDayByAllInfo(sttLau, idNhaTro, sttDay);


        if (nhaTro != null && existLau != null && existDay == null && tongPhongD >= 1 && sttDay <= existLau.getTongDay()) {
            try {
                Day day = new Day(tongPhongD);

                DayID dayID = new DayID();
                dayID.setSttDay(sttDay);
                dayID.setIdNhaTro(idNhaTro);
                dayID.setSttLau(sttLau);
                day.setId(dayID);


                day.setLau(existLau);

                Day saved = dayService.saveDay(day);
                DayDTO dayDTO = this.mapDayToDayDTO(day);


                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Tạo dãy thành công.", dayDTO)
                );
            } catch (Exception ex) {
                ex.printStackTrace();
                return ResponseEntity.badRequest().body(
                        new ResponseObject("error", "Có lỗi trong quá trình thêm dãy mới!", "")
                );
            }
        } else {
            return ResponseEntity.badRequest().body(
                    new ResponseObject("error", "Không tìm thấy thông tin nhà trọ hoặc lầu,dãy không hợp lệ hoặc đã tồn tại!", "")
            );
        }

    }

    public DayDTO mapDayToDayDTO(Day day) {
        DayDTO dayDTO = new DayDTO();
        dayDTO.setIdNhaTro(day.getId().getIdNhaTro());
        dayDTO.setSttLau(day.getId().getSttLau());
        dayDTO.setSttDay(day.getId().getSttDay());
        dayDTO.setTongPhongD(day.getTongPhongD());
        return dayDTO;
    }

}
