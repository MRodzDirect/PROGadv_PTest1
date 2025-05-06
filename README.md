# Sistema de Gestión Vehicular y Saldo de Celulares

Este repositorio contiene dos proyectos Java desarrollados con NetBeans, utilizando JPA (Java Persistence API) con Hibernate para la gestión de datos y Swing para las interfaces gráficas. A continuación, se detalla cada proyecto, incluyendo su propósito, estructura, lógica, interfaces gráficas y resultados.

---

## Proyecto 1: Sistema de Gestión Vehicular

Este proyecto permite gestionar propietarios, vehículos y turnos, con restricciones específicas para la reserva de turnos. Los objetivos principales son:

- Registrar propietarios y vehículos.
- Reservar turnos para vehículos, validando que un vehículo no tenga dos turnos el mismo día ni en el mismo andén.

### Interfaz Gráfica

Las siguientes imágenes muestran las interfaces gráficas del sistema:

- **Formulario para registrar propietario y vehículo:**
  ![Formulario de ingreso de vehículo](./assets/image-20250505134024232.png)

- **Formulario para reservar turno:**
  ![Formulario de reserva de turno](./assets/image-20250505134104217.png)

- **Mensaje de error al intentar reservar un turno para el mismo vehículo en el mismo día:**
  ![Error: Mismo día](./assets/image-20250505134148139.png)

- **Mensaje de error al intentar reservar un turno para el mismo vehículo en el mismo andén:**
  ![Error: Mismo andén](./assets/image-20250505134216436.png)

- **Confirmación de turno registrado exitosamente:**
  ![Turno registrado](./assets/image-20250505134739444.png)

### Estructura de la Base de Datos

El sistema utiliza tres tablas en una base de datos MySQL (`vehiculos_db`):

#### Tablas
- **Propietario:**
  ![Tabla Propietario](./assets/image-20250505140223329.png)

- **Vehículo:**
  ![Tabla Vehículo](./assets/image-20250505140307921.png)

- **Turno:**
  ![Tabla Turno](./assets/image-20250505140325874.png)

#### Diagrama de Clases
El diagrama de clases define las entidades y sus relaciones:
- **Propietario:** `IDPROP` (PK), `CEDULA`, `NOMBRE`, `APELLIDO`.
- **Vehículo:** `IDVEHI` (PK), `PLACA`, `MARCA`, `IDPROP` (FK a Propietario).
- **Turno:** `IDTURNO` (PK), `ANDEN`, `DIA`, `HORA`, `IDVEHI` (FK a Vehículo).

![Diagrama de Clases 1](./assets/image-20250505135216291.png)

![Diagrama de Clases 2](./assets/image-20250505140638621.png)

### Lógica del Sistema

#### Registro de Propietarios y Vehículos
- Un propietario se registra con su cédula, nombre y apellido.
- Un vehículo se asocia a un propietario existente y se registra con su placa y marca.
- Ejemplo de registro exitoso:
  ![Registro de vehículo](./assets/image-20250505134257971.png)

#### Reserva de Turnos
- **Validaciones:**
  1. **Mismo Día:** Un vehículo no puede tener dos turnos el mismo día.
     - Ejemplo de error:
       ![Error: Mismo día](./assets/image-20250505134556723.png)
       ![Mensaje de error: Mismo día](./assets/image-20250505134610579.png)
  2. **Mismo Andén:** Un vehículo no puede tener dos turnos en el mismo andén.
     - Ejemplo de error:
       ![Error: Mismo andén](./assets/image-20250505134522162.png)
       ![Mensaje de error: Mismo andén](./assets/image-20250505134530370.png)

- **Nota:** La validación del día se ejecuta primero, seguida por la validación del andén.
- **Excepción:** Diferentes vehículos (incluso del mismo propietario) pueden tener turnos en el mismo andén, día y hora.
  - Ejemplo:
    ![Vehículos diferentes, mismo andén y día](./assets/image-20250505135330278.png)
    ![Confirmación de turno para otro vehículo](./assets/image-20250505135411666.png)

### Archivos Necesarios

La estructura del proyecto está organizada en los siguientes paquetes:

- **Paquete `com.sistemavehiculos.modelo`:** Contiene las entidades JPA (`Propietario`, `Vehículo`, `Turno`).
- **Paquete `com.sistemavehiculos.servicio`:** Contiene la lógica de negocio (`VehiculoServicio`).
- **Paquete `com.sistemavehiculos.presentacion`:** Contiene las interfaces gráficas (`IngresoVehiculo`, `ReservaTurno`).

#### Archivos de Configuración
- **`pom.xml`:** Define las dependencias del proyecto, incluyendo Hibernate para JPA y el conector de MySQL.
- **`persistence.xml`:** Configura la unidad de persistencia (`VehiculosPU`), la conexión a la base de datos y la creación automática de tablas mediante Hibernate.

![Estructura del proyecto](./assets/image-20250505135552193.png)

---

## Proyecto 2: Sistema de Saldo de Celulares

Este proyecto permite gestionar clientes, celulares y recargas, con dos opciones para realizar recargas. Los objetivos principales son:

- Registrar clientes y celulares.
- Realizar recargas con distribución manual o automática del valor entre saldo y megas.

### Interfaz Gráfica

Las siguientes imágenes muestran las interfaces gráficas del sistema:

- **Formulario para registrar cliente y celular:**
  ![Formulario de ingreso de celular](./assets/image-20250505141715935.png)

- **Formulario para realizar recarga:**
  ![Formulario de recarga](./assets/image-20250505141729152.png)

- **Confirmación de recarga exitosa:**
  ![Recarga exitosa](./assets/image-20250505141758335.png)

### Estructura de la Base de Datos

El sistema utiliza tres tablas en una base de datos MySQL (`celulares_db`):

#### Tablas
- **Cliente:** `IDCLI` (PK), `CEDULA`, `NOMBRES`, `APELLIDOS`.
- **Celular:** `IDCEL` (PK), `NUMERO`, `ESTADO`, `SALDO`, `MEGAS`, `IDCLI` (FK a Cliente).
- **Recarga:** `IDREC` (PK), `VALOR`, `SALDO`, `MEGAS`, `IDCEL` (FK a Celular).

#### Diagrama de Clases
![Diagrama de Clases](./assets/image-20250505140756400.png)

### Lógica del Sistema

#### Registro de Clientes y Celulares
- Un cliente se registra con su cédula, nombres y apellidos.
- Un celular se asocia a un cliente existente y se registra con su número y estado (0 = inactivo, 1 = activo).

#### Realizar Recargas
- **Validaciones:**
  - Solo se puede recargar un celular con estado activo (`estado = 1`).
- **Opción 1 (Manual):**
  - El usuario define cuánto del valor va al saldo, y el resto se convierte en megas (5 GB por dólar).
- **Opción 2 (Automática):**
  - El valor se distribuye automáticamente: 2/3 para saldo y 1/3 para megas (5 GB por dólar).

### Archivos Necesarios

La estructura del proyecto está organizada en los siguientes paquetes:

- **Paquete `com.sistemacelulares.modelo`:** Contiene las entidades JPA (`Cliente`, `Celular`, `Recarga`).
- **Paquete `com.sistemacelulares.servicio`:** Contiene la lógica de negocio (`CelularServicio`).
- **Paquete `com.sistemacelulares.presentacion`:** Contiene las interfaces gráficas (`IngresoCelular`, `RecargaCelular`).

#### Archivos de Configuración
- **`pom.xml`:** Igual que en el Proyecto 1, define las dependencias.
- **`persistence.xml`:** Configura la unidad de persistencia (`CelularesPU`) y la conexión a la base de datos.

![Estructura del proyecto 1](./assets/image-20250505141633546.png)

![Estructura del proyecto 2](./assets/image-20250505141641205.png)

---

## Instrucciones para Ejecutar los Proyectos

### Requisitos Previos
1. **Java JDK:** Asegúrate de tener instalado Java JDK 8 o superior.
2. **MySQL:** Instala MySQL y crea las bases de datos `vehiculos_db` y `celulares_db`.
3. **NetBeans:** Usa NetBeans como IDE para abrir y ejecutar los proyectos.
4. **Dependencias:** Las dependencias están definidas en `pom.xml`. Asegúrate de que Maven las descargue correctamente.

### Pasos para Configurar
1. **Clonar el Repositorio:**
   
   ``` bash
   git clone https://github.com/MRodzDirect/PROGadv_PTest1.git
   ```
   
   
   
2. **Abrir en NetBeans: **

   * Abre cada proyecto (SistemaVehiculos y SistemaCelulares) en NetBeans.

3. **Configurar la Base de Datos:***

- Actualiza las credenciales en persistence.xml (usuario y contraseña de MySQL).
- Hibernate creará automáticamente las tablas al ejecutar el proyecto.

4. **Ejecutar:**

- Para el Proyecto 1, ejecuta IngresoVehiculo.java para iniciar.

- Para el Proyecto 2, ejecuta IngresoCelular.java para iniciar.

  

  ### FAQ:

  **Proyecto 1: Sistema de Gestión Vehicular**

  - ¿Se pueden registrar dos vehículos de un mismo propietario en el mismo andén, día y hora?

    Sí, siempre que sean vehículos diferentes. La restricción aplica solo al mismo vehículo.

  **Proyecto 2: Sistema de Saldo de Celulares**

  - ¿Qué pasa si intento recargar un celular inactivo?

    El sistema mostrará un error indicando que el celular debe estar activo (estado = 1).

  ------

  ### Autor

  - MRodzDirect 😉