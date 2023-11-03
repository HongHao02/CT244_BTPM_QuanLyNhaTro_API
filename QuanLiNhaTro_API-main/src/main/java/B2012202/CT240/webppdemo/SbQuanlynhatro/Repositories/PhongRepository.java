package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.PhongTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.PhongTroID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PhongRepository extends JpaRepository<PhongTro, PhongTroID> {
    @Query("SELECT p FROM PhongTro p WHERE p.id.sttLau = :sttLau AND p.id.idNhaTro = :idNhaTro AND p.id.sttDay = :sttDay AND p.id.sttPhong = :sttPhong")
    PhongTro findPhongByAllInfo(@Param("sttLau") Integer sttLau, @Param("idNhaTro") Integer idNhaTro, @Param("sttDay") Integer sttDay, @Param("sttPhong") Integer sttPhong);
}
