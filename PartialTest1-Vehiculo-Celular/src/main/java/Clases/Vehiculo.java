
package Clases;

/**
 *
 * @author COPASO
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "vehiculo")
public class Vehiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idvehi")
    private int idVehi;

    @Column(name = "placa", nullable = false, length = 10)
    private String placa;

    @Column(name = "marca", nullable = false)
    private String marca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idprop", nullable = false)
    private Propietario propietario;

    @OneToMany(mappedBy = "vehiculo", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Turno> turnos = new ArrayList<>();

    // Constructores
    public Vehiculo() {}

    public Vehiculo(String placa, String marca) {
        this.placa = placa;
        this.marca = marca;
    }

    // Getters y Setters
    public int getIdVehi() { return idVehi; }
    public void setIdVehi(int idVehi) { this.idVehi = idVehi; }
    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }
    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }
    public Propietario getPropietario() { return propietario; }
    public void setPropietario(Propietario propietario) { this.propietario = propietario; }
    public List<Turno> getTurnos() { return turnos; }
    public void setTurnos(List<Turno> turnos) { this.turnos = turnos; }

    // Método para agregar turno
    public void agregarTurno(Turno turno) {
        turno.setVehiculo(this);
        this.turnos.add(turno);
    }
}