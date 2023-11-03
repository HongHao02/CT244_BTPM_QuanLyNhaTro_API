package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Day;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Lau;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.DayRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.LauRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DayService {
    @Autowired
    private DayRepository dayRepository;

    Logger logger = LoggerFactory.getLogger(DayService.class);

    @Async
    public CompletableFuture<List<Day>> findAllDay() {
        logger.info("get list of Day by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of Day {}", start);
        List<Day> dsDay = dayRepository.findAll();
        return CompletableFuture.completedFuture(dsDay);
    }

    public Day findDayByAllInfo(Integer sttLau, Integer idNhaTro, Integer sttDay) {
        return dayRepository.findDayByAllInfo(sttLau, idNhaTro, sttDay);
    }

//    public List<Lau> findAllLauByIdNhaTro(Integer idNhaTro) {
//        return lauRepository.findAllLauByIdNhaTro(idNhaTro);
//    }

    public Day saveDay(Day day) {
        return dayRepository.save(day);
    }
}
