package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.NhaTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.User;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.NhaTroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class NhaTroService {
    @Autowired
    private NhaTroRepository nhaTroRepository;

    Logger logger = LoggerFactory.getLogger(NhaTroService.class);

    @Async
    public CompletableFuture<List<NhaTro>> findAllNhaTro() {
        logger.info("get list of NhaTro by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of NhaTro {}", start);
        List<NhaTro> nhaTros = nhaTroRepository.findAll();
        return CompletableFuture.completedFuture(nhaTros);
    }

    public NhaTro findNhaTroByidNhaTro(Integer idNhaTro) {
        return nhaTroRepository.findByIdNhaTro(idNhaTro);
    }

    public NhaTro saveNhaTro(NhaTro nhaTro) {
        return nhaTroRepository.save(nhaTro);
    }
}
