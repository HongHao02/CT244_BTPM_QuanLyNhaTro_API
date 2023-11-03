package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.PhieuDangKy;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.PhieuDangKyDTO;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.PhieuDangKyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PhieuDangKyService {
    @Autowired
    private PhieuDangKyRepository phieuDangKyRepository;

    Logger logger = LoggerFactory.getLogger(PhieuDangKyService.class);

    @Async
    public CompletableFuture<List<PhieuDangKy>> findAllPhieuDangKy() {
        logger.info("get list of PhieuDangKy by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a PhieuDangKy of KhachThue {}", start);
        List<PhieuDangKy> dsPhieuDangKy = phieuDangKyRepository.findAll();
        return CompletableFuture.completedFuture(dsPhieuDangKy);
    }

    public PhieuDangKy findPhieuDangKyBySTTPhieu(Integer sttPhieu) {
        return phieuDangKyRepository.findBysttPhieu(sttPhieu);
    }

    public PhieuDangKy savePhieuDangKy(PhieuDangKy phieuDangKy) {
        return phieuDangKyRepository.save(phieuDangKy);
    }
}
