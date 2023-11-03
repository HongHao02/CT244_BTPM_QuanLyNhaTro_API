package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChiTietDangKy;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.ChiTietDangKyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ChiTietDangKyService {
    @Autowired
    private ChiTietDangKyRepository chiTietDangKyRepository;

    Logger logger = LoggerFactory.getLogger(ChiTietDangKyService.class);

    @Async
    public CompletableFuture<List<ChiTietDangKy>> findAllChiTietDangKy() {
        logger.info("get list of ChiTietDangKy by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of ChiTietDangKy {}", start);
        List<ChiTietDangKy> dsChiTietDangKy = chiTietDangKyRepository.findAll();
        return CompletableFuture.completedFuture(dsChiTietDangKy);
    }

    public ChiTietDangKy findChiTietDangKyByAllInfo(Integer sttPhieu, Integer idNhaTro, Integer sttLau, Integer sttDay, Integer sttPhong) {
        return chiTietDangKyRepository.findChiTietDangKyByAllInfo(sttPhieu, idNhaTro, sttLau, sttDay, sttPhong);
    }

    public ChiTietDangKy saveChiTietDangKy(ChiTietDangKy chiTietDangKy) {
        return chiTietDangKyRepository.save(chiTietDangKy);
    }
}
