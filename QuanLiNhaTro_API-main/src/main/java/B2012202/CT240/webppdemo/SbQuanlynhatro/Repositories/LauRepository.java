package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Lau;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.LauID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface LauRepository extends JpaRepository<Lau, LauID> {
    @Query("SELECT l FROM Lau l WHERE l.id.sttLau = :sttLau AND l.id.idNhaTro = :idNhaTro")
    Lau findBySTTLauAndIdNhaTro(@Param("sttLau") Integer sttLau, @Param("idNhaTro") Integer idNhaTro);

    @Query("SELECT l FROM Lau l WHERE l.id.idNhaTro = :idNhaTro")
    List<Lau> findAllLauByIdNhaTro(@Param("idNhaTro") Integer idNhaTro);
}
