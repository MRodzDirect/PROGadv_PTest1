
package Clases;

/**
 *
 * @author COPASO
 */

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;



@Entity
@Table(name = "celular")
public class Celular {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcel")
    private int idCel;

    @Column(name = "numero", nullable = false, length = 15)
    private String numero;

    @Column(name = "estado", nullable = false)
    private int estado;

    @Column(name = "saldo", nullable = false)
    private int saldo;

    @Column(name = "megas", nullable = false)
    private int megas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcli", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "celular", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Recarga> recargas = new ArrayList<>();

    // Constructores
    public Celular() {}

    public Celular(String numero, int estado) {
        this.numero = numero;
        this.estado = estado;
        this.saldo = 0;
        this.megas = 0;
    }

    // Getters y Setters
    public int getIdCel() { return idCel; }
    public void setIdCel(int idCel) { this.idCel = idCel; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
    public int getSaldo() { return saldo; }
    public void setSaldo(int saldo) { this.saldo = saldo; }
    public int getMegas() { return megas; }
    public void setMegas(int megas) { this.megas = megas; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
    public List<Recarga> getRecargas() { return recargas; }
    public void setRecargas(List<Recarga> recargas) { this.recargas = recargas; }

    // Método para agregar recarga
    public void agregarRecarga(Recarga recarga) {
        recarga.setCelular(this);
        this.recargas.add(recarga);
    }
}