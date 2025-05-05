
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
import java.util.List;

public class VehiculoServicio {
    private final EntityManagerFactory emf;

    public VehiculoServicio() {
        this.emf = Persistence.createEntityManagerFactory("VehiculosPU");
    }

    // Insertar propietario
    public void insertarPropietario(Propietario propietario) throws Exception {
        if (!validarCedula(propietario.getCedula())) {
            throw new Exception("Cédula no válida.");
        }
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(propietario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar propietario: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Insertar vehículo
    public void insertarVehiculo(Vehiculo vehiculo, int idProp) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Propietario propietario = em.find(Propietario.class, idProp);
            if (propietario == null) {
                throw new Exception("Propietario no encontrado.");
            }
            propietario.agregarVehiculo(vehiculo);
            em.merge(propietario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al insertar vehículo: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Reservar turno
    public void reservarTurno(Turno turno, int idVehi) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Vehiculo vehiculo = em.find(Vehiculo.class, idVehi);
            if (vehiculo == null) {
                throw new Exception("Vehículo no encontrado.");
            }

            // Validar que el vehículo no tenga turno el mismo día
            for (Turno t : vehiculo.getTurnos()) {
                if (t.getDia() == turno.getDia()) {
                    throw new Exception("El vehículo ya tiene un turno asignado para ese día.");
                }
            }

            // Validar que el vehículo no tenga turno en el mismo andén
            for (Turno t : vehiculo.getTurnos()) {
                if (t.getAnden() == turno.getAnden()) {
                    throw new Exception("El vehículo ya tiene un turno asignado en ese andén.");
                }
            }

            vehiculo.agregarTurno(turno);
            em.merge(vehiculo);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new Exception("Error al reservar turno: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    // Buscar propietario por cédula
    public Propietario buscarPropietarioPorCedula(String cedula) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Propietario> query = em.createQuery(
                "SELECT p FROM Propietario p WHERE p.cedula = :cedula", Propietario.class);
            query.setParameter("cedula", cedula);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Propietario no encontrado.");
        } finally {
            em.close();
        }
    }

    // Buscar vehículo por placa
    public Vehiculo buscarVehiculoPorPlaca(String placa) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Vehiculo> query = em.createQuery(
                "SELECT v FROM Vehiculo v WHERE v.placa = :placa", Vehiculo.class);
            query.setParameter("placa", placa);
            return query.getSingleResult();
        } catch (Exception e) {
            throw new Exception("Vehículo no encontrado.");
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
