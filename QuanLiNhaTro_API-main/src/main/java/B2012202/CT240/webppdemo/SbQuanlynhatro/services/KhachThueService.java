package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.KhachThue;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.KhachThueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class KhachThueService {
    @Autowired
    private KhachThueRepository khachThueRepository;

    Logger logger = LoggerFactory.getLogger(KhachThueService.class);

    @Async
    public CompletableFuture<List<KhachThue>> findAllKhachThue() {
        logger.info("get list of KhachThue by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of KhachThue {}", start);
        List<KhachThue> dsKhachThue = khachThueRepository.findAll();
        return CompletableFuture.completedFuture(dsKhachThue);
    }

    public KhachThue findKhachThueByIDUserAndMAKT(Long idUser, String maKT) {
        return khachThueRepository.findByIDUserAndMaKT(idUser, maKT);
    }

    public KhachThue findKhachThueByMAKT(String maKT) {
        return khachThueRepository.findByMaKT(maKT);
    }

    public KhachThue saveKhachThue(KhachThue khachThue) {
        return khachThueRepository.save(khachThue);
    }
}
