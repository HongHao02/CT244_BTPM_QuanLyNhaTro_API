package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LoaiNhaTro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoaiNhaTroRepository extends JpaRepository<LoaiNhaTro, Integer> {

    LoaiNhaTro findByTenLoaiNT(String tenLoaiNT);
}
