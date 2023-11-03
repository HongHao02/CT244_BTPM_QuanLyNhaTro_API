package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.User;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.ChuTroRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ChuTroService {
    @Autowired
    private ChuTroRepository chuTroRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Async
    public CompletableFuture<List<ChuTro>> findAllChuTro() {
        logger.info("get list of ChuTro by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of ChuTro {}", start);
        List<ChuTro> dschutro = chuTroRepository.findAll();
        return CompletableFuture.completedFuture(dschutro);
    }

    public ChuTro findChuTroByMaChuTro(String maChuTro) {
        return chuTroRepository.findByMaChuTro(maChuTro);
    }

    public ChuTro saveChuTro(ChuTro chuTro) {
        return chuTroRepository.save(chuTro);
    }
}
