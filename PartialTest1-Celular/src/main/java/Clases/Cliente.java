
package Clases;

/**
 *
 * @author MRodzDirect
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcli")
    private int idCli;

    @Column(name = "cedula", nullable = false, length = 10)
    private String cedula;

    @Column(name = "nombres", nullable = false)
    private String nombres;

    @Column(name = "apellidos", nullable = false)
    private String apellidos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Celular> celulares = new ArrayList<>();

    // Constructores
    public Cliente() {}

    public Cliente(String cedula, String nombres, String apellidos) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    // Getters y Setters
    public int getIdCli() { return idCli; }
    public void setIdCli(int idCli) { this.idCli = idCli; }
    public String getCedula() { return cedula; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public List<Celular> getCelulares() { return celulares; }
    public void setCelulares(List<Celular> celulares) { this.celulares = celulares; }

    // Método para agregar celular
    public void agregarCelular(Celular celular) {
        celular.setCliente(this);
        this.celulares.add(celular);
    }
}