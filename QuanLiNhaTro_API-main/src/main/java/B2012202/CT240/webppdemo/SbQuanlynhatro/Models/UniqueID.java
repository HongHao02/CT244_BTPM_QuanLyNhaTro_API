package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

/**
 * <mục đích> Tạo id tự động </mục đích>
 */
@Entity
@Table(name = "UniqueID")
public class UniqueID {
    @Id
    @SequenceGenerator(
            name = "uniqueID_sequence",
            sequenceName = "uniqueID_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "uniqueID_sequence"
    )
    private Integer id;

//    private Integer currentID;

    public UniqueID() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}
