
package Clases;

/**
 *
 * @author COPASO
 */

import javax.persistence.*;

@Entity
@Table(name = "turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idturno")
    private int idTurno;

    @Column(name = "anden", nullable = false)
    private int anden;

    @Column(name = "dia", nullable = false)
    private int dia;

    @Column(name = "hora", nullable = false)
    private int hora;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idvehi", nullable = false)
    private Vehiculo vehiculo;

    // Constructores
    public Turno() {}

    public Turno(int anden, int dia, int hora) {
        this.anden = anden;
        this.dia = dia;
        this.hora = hora;
    }

    // Getters y Setters
    public int getIdTurno() { return idTurno; }
    public void setIdTurno(int idTurno) { this.idTurno = idTurno; }
    public int getAnden() { return anden; }
    public void setAnden(int anden) { this.anden = anden; }
    public int getDia() { return dia; }
    public void setDia(int dia) { this.dia = dia; }
    public int getHora() { return hora; }
    public void setHora(int hora) { this.hora = hora; }
    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }
}