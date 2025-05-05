
package Clases;

/**
 *
 * @author COPASO
 */

import javax.persistence.*;



@Entity
@Table(name = "recargas")
public class Recarga {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrec")
    private int idRec;

    @Column(name = "valor", nullable = false)
    private int valor;

    @Column(name = "saldo", nullable = false)
    private int saldo;

    @Column(name = "megas", nullable = false)
    private int megas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idcel", nullable = false)
    private Celular celular;

    // Constructores
    public Recarga() {}

    public Recarga(int valor, int saldo, int megas) {
        this.valor = valor;
        this.saldo = saldo;
        this.megas = megas;
    }

    // Getters y Setters
    public int getIdRec() { return idRec; }
    public void setIdRec(int idRec) { this.idRec = idRec; }
    public int getValor() { return valor; }
    public void setValor(int valor) { this.valor = valor; }
    public int getSaldo() { return saldo; }
    public void setSaldo(int saldo) { this.saldo = saldo; }
    public int getMegas() { return megas; }
    public void setMegas(int megas) { this.megas = megas; }
    public Celular getCelular() { return celular; }
    public void setCelular(Celular celular) { this.celular = celular; }
}