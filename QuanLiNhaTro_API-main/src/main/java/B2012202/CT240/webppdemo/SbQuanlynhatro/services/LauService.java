package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Lau;
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
public class LauService {
    @Autowired
    private LauRepository lauRepository;

    Logger logger = LoggerFactory.getLogger(LauService.class);

    @Async
    public CompletableFuture<List<Lau>> findAllLau() {
        logger.info("get list of Lau by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of Lau {}", start);
        List<Lau> dsLau = lauRepository.findAll();
        return CompletableFuture.completedFuture(dsLau);
    }

    public Lau findLauBySttLauAndIdNhaTro(Integer sttLau, Integer idNhaTro) {
        return lauRepository.findBySTTLauAndIdNhaTro(sttLau, idNhaTro);
    }

    public List<Lau> findAllLauByIdNhaTro(Integer idNhaTro) {
        return lauRepository.findAllLauByIdNhaTro(idNhaTro);
    }

    public Lau saveLau(Lau lau) {
        return lauRepository.save(lau);
    }
}
