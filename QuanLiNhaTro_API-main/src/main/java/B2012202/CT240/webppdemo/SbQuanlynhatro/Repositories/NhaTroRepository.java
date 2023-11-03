package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.NhaTro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NhaTroRepository extends JpaRepository<NhaTro, Integer> {
    NhaTro findByIdNhaTro(Integer idNhaTro);
}
