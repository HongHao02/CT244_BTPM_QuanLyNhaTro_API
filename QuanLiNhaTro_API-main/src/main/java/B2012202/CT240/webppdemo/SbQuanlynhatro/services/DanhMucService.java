package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.DanhMuc;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiNhaTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.DanhMucRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.LoaiNhaTroRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class DanhMucService {
    @Autowired
    private DanhMucRepository danhMucRepository;

    Logger logger = LoggerFactory.getLogger(DanhMucService.class);

    @Async
    public CompletableFuture<List<DanhMuc>> findAllDanhMuc() {
        logger.info("get list of danhmuc by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of danhmuc {}", start);
        List<DanhMuc> dsDM = danhMucRepository.findAll();
        return CompletableFuture.completedFuture(dsDM);
    }

    public DanhMuc isExistDanhMuc(Integer tenDM) {
        return danhMucRepository.findByTenDM(tenDM);

    }

//    public LoaiNhaTro findloaiNhaTroByTenLoaiNT(String tenLoaiNT) {
//        return loaiNhaTroRepository.findByTenLoaiNT(tenLoaiNT);
//    }

    public DanhMuc saveDanhMuc(DanhMuc danhMuc) {
        return danhMucRepository.save(danhMuc);
    }
}
