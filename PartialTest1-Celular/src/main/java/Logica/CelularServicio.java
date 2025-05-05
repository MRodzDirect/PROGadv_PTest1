
package Logica;

/**
 *
 * @author COPASO
 */


import Clases.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CelularServicio {
    private static final int MEGAS_POR_DOLAR = 5;
    private final EntityManagerFactory emf;

    public CelularServicio() {
        this.emf = Persistence.createEntityManagerFactory("CelularesPU");
    }

    // Insertar cliente
    public void insertarCliente(Cliente cliente) throws Exception {
        if (!validarCedula(cliente.getCedula())) {
            throw new Exception("Cédula no válida.");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar cliente: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Insertar celular
    public void insertarCelular(Celular celular, int idCli) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Cliente cliente = em.find(Cliente.class, idCli);
            if (cliente == null) {
                throw new Exception("Cliente no encontrado.");
            }
            cliente.agregarCelular(celular);
            em.merge(cliente);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar celular: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Realizar recarga (Opción 1: Distribución manual)
    public void realizarRecargaManual(int idCel, int valor, int saldo) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Celular celular = em.find(Celular.class, idCel);
            if (celular == null) {
                throw new Exception("Celular no encontrado.");
            }
            if (celular.getEstado() != 1) {
                throw new Exception("El celular no está activo (estado != 1).");
            }

            int dolaresParaMegas = valor - saldo;
            if (dolaresParaMegas < 0) {
                throw new Exception("El saldo asignado no puede ser mayor que el valor total.");
            }
            int megas = dolaresParaMegas * MEGAS_POR_DOLAR;

            Recarga recarga = new Recarga(valor, saldo, megas);
            celular.agregarRecarga(recarga);
            celular.setSaldo(celular.getSaldo() + saldo);
            celular.setMegas(celular.getMegas() + megas);
            em.merge(celular);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al realizar recarga: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Realizar recarga (Opción 2: Distribución automática 2/3 saldo, 1/3 megas)
    public void realizarRecargaAutomatica(int idCel, int valor) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Celular celular = em.find(Celular.class, idCel);
            if (celular == null) {
                throw new Exception("Celular no encontrado.");
            }
            if (celular.getEstado() != 1) {
                throw new Exception("El celular no está activo (estado != 1).");
            }

            int saldo = (valor * 2) / 3;
            int dolaresParaMegas = valor - saldo;
            int megas = dolaresParaMegas * MEGAS_POR_DOLAR;

            Recarga recarga = new Recarga(valor, saldo, megas);
            celular.agregarRecarga(recarga);
            celular.setSaldo(celular.getSaldo() + saldo);
            celular.setMegas(celular.getMegas() + megas);
            em.merge(celular);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al realizar recarga: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Buscar cliente por cédula
    public Cliente buscarClientePorCedula(String cedula) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Cliente> query = em.createQuery(
                "SELECT c FROM Cliente c WHERE c.cedula = :cedula", Cliente.class);
            query.setParameter("cedula", cedula);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Cliente no encontrado.");
        } finally {
            em.close();
        }
    }

    // Buscar celular por número
    public Celular buscarCelularPorNumero(String numero) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Celular> query = em.createQuery(
                "SELECT c FROM Celular c WHERE c.numero = :numero", Celular.class);
            query.setParameter("numero", numero);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Celular no encontrado.");
        } finally {
            em.close();
        }
    }

    // Validar cédula (10 dígitos)
    private boolean validarCedula(String cedula) {
        return cedula != null && cedula.matches("\\d{10}");
    }

    // Cerrar EntityManagerFactory
    public void cerrar() {
        if (emf.isOpen()) {
            emf.close();
        }
    }
}