package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiNhaTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.User;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.LoaiNhaTroRepository;
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
public class LoaiNhaTroService {
    @Autowired
    private LoaiNhaTroRepository loaiNhaTroRepository;

    Logger logger = LoggerFactory.getLogger(LoaiNhaTroService.class);

    @Async
    public CompletableFuture<List<LoaiNhaTro>> findAllLoaiNhaTro() {
        logger.info("get list of loainhatro by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of loainhatro {}", start);
        List<LoaiNhaTro> LNTro = loaiNhaTroRepository.findAll();
        return CompletableFuture.completedFuture(LNTro);
    }

    public boolean isExistLoaiNhaTro(String tenLoaiNT) {
        return loaiNhaTroRepository.findByTenLoaiNT(tenLoaiNT) != null ? false : true;

    }

    public LoaiNhaTro findloaiNhaTroByTenLoaiNT(String tenLoaiNT) {
        return loaiNhaTroRepository.findByTenLoaiNT(tenLoaiNT);
    }

    public LoaiNhaTro saveLoaiNhaTro(LoaiNhaTro loaiNhaTro) {
        return loaiNhaTroRepository.save(loaiNhaTro);
    }

}
