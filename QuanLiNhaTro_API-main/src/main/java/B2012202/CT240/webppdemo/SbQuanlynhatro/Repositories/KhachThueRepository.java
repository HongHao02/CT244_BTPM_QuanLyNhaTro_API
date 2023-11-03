package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ChuTro;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.KhachThue;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.KhachThueID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface KhachThueRepository extends JpaRepository<KhachThue, KhachThueID> {
    @Query("SELECT kt FROM KhachThue kt WHERE kt.id.idUser = :idUser AND kt.id.makt = :maKT ")
    KhachThue findByIDUserAndMaKT(@Param("idUser") Long idUser, @Param("maKT") String maKT);

    @Query("SELECT kt FROM KhachThue kt WHERE kt.id.makt = :maKT ")
    KhachThue findByMaKT(@Param("maKT") String maKT);
}
