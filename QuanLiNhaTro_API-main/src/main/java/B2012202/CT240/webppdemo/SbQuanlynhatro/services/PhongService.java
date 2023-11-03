package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.PhongTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.PhongRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PhongService {
    @Autowired
    private PhongRepository phongRepository;

    Logger logger = LoggerFactory.getLogger(PhongService.class);

    @Async
    public CompletableFuture<List<PhongTro>> findAllPhong() {
        logger.info("get list of PhongTro by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of PhongTro {}", start);
        List<PhongTro> dsPhong = phongRepository.findAll();
        return CompletableFuture.completedFuture(dsPhong);
    }

    public PhongTro findPhongByAllInfo(Integer sttLau, Integer idNhaTro, Integer sttDay, Integer sttPhong) {
        return phongRepository.findPhongByAllInfo(sttLau, idNhaTro, sttDay, sttPhong);
    }

//    public List<Lau> findAllLauByIdNhaTro(Integer idNhaTro) {
//        return lauRepository.findAllLauByIdNhaTro(idNhaTro);
//    }

    public PhongTro savePhong(PhongTro phong) {
        return phongRepository.save(phong);
    }
}
