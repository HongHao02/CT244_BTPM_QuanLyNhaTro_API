package B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.product;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

//JpaRepository<Enity type,type of pkey>
//Chua cac xu ly lien quan den Entity product
public interface ProductRepository extends JpaRepository<product, Long> {
    List<product> findByproductName (String productName);
}
