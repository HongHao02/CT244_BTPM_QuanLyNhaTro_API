package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.Objects;

//POJO object
@Entity
@Table(name="Car")
public class Car {
    @Id
    @SequenceGenerator(
            name= "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    private Long id;

    @Column(nullable = false,unique = true,length = 300)
    private String maXe;

    @Column(nullable=false)
    private String manufacturer;

    @Column(nullable=false)
    private String model;

    @Column(nullable=false)
    private String type;

    public Car(){};

    public Car(String maXe, String manufacturer, String model, String type) {
        this.maXe = maXe;
        this.manufacturer = manufacturer;
        this.model = model;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;
        return Objects.equals(getId(), car.getId()) && Objects.equals(getMaXe(), car.getMaXe()) && Objects.equals(getManufacturer(), car.getManufacturer()) && Objects.equals(getModel(), car.getModel()) && Objects.equals(getType(), car.getType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getMaXe(), getManufacturer(), getModel(), getType());
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", maXe='" + maXe + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                '}';
    }


}
