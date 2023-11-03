package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiNhaTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiPhong;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.LoaiNhaTroRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.LoaiPhongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class LoaiPhongService {
    @Autowired
    private LoaiPhongRepository loaiPhongRepository;

    Logger logger = LoggerFactory.getLogger(LoaiPhongService.class);

    @Async
    public CompletableFuture<List<LoaiPhong>> findAllLoaiPhong() {
        logger.info("get list of loaiphong by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of loaiphong {}", start);
        List<LoaiPhong> LPhong = loaiPhongRepository.findAll();
        return CompletableFuture.completedFuture(LPhong);
    }

    public LoaiPhong findloaiPhongByTenLoaiP(String tenLoaiP) {
        return loaiPhongRepository.findByTenLoaiP(tenLoaiP);
    }

    public LoaiPhong saveLoaiPhong(LoaiPhong loaiPhong) {
        return loaiPhongRepository.save(loaiPhong);
    }
}
