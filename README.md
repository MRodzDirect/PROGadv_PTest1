# Projecto 1 - Sistema de Gestion Vehicular



## Interfaz grafica:

![image-20250505134024232](./assets/image-20250505134024232.png)



![image-20250505134104217](./assets/image-20250505134104217.png)



![image-20250505134148139](./assets/image-20250505134148139.png)

![image-20250505134216436](./assets/image-20250505134216436.png)

## Tablas Necesarias:

* Tres

![image-20250505135216291](./assets/image-20250505135216291.png)

![image-20250505140638621](./assets/image-20250505140638621.png)

## Logica:



![image-20250505134257971](./assets/image-20250505134257971.png)

![image-20250505134316905](./assets/image-20250505134316905.png)

![image-20250505134347763](./assets/image-20250505134347763.png)

Si quiero registrar en el mismo anden:



![image-20250505134522162](./assets/image-20250505134522162.png)

![image-20250505134530370](./assets/image-20250505134530370.png)

O en el mismo dia:

![image-20250505134556723](./assets/image-20250505134556723.png)

![image-20250505134610579](./assets/image-20250505134610579.png)



Como mostre previamente no sucede nada cuando se registra OTRO vehiculo en el mismo anden o el mismo dia

**Nota :** En este sistema la primera sentencia de verificacion en ejecutarse es el dia, luego se verifica el anden.

![image-20250505134739444](./assets/image-20250505134739444.png) 

## Archivos necesarios:

![image-20250505135552193](./assets/image-20250505135552193.png)

* pom.xml -> Donde llamamos las librerias que trabajaran en el projecto, como enlace de JPA usamos hibernate en lugar de Eclipse al ofrecernos ventajas como la creacion automatica de tablas.
* persistence.xml -> Nuestra unidad de persistencia donde establecemos el nombre de la unidad (ejm: Proyecto_PU), libreria de persistencia (Hibernate) y la conexion JDBC donde conectaremos todo nuestro proyecto hacia una base de datos con sus tablas automaticamente creadas gracias a Hibernate JPA
* Clases del paquete Presentacion -> Interfaces graficas mostradas (para registrar propietarios, vehiculos y reservar uno o mas  turnos para el mismo) 
* Clase del paquete Logica -> Logica de Verificacion del anden y el dia para cada vehiculo, en este paquete colocaremos mas clases de validacion y control de las reglas del negocio a futuro.
* Clases del paquete "Clases" (valga la redundancia) -> Entidades que se crearan como tablas en nuestra base de datos gracias a Hibernate JPA

## Resultado

### Tablas:

![Tabla propietario](./assets/image-20250505140223329.png)

![Tabla vehiculo](./assets/image-20250505140307921.png)

![Tabla turno](./assets/image-20250505140325874.png)



## FAQ:

* Se pueden registrar 2 vehiculos de un mismo propietario en el mismo anden, el mismo dia y la misma hora?

  > Si

  ![image-20250505135330278](./assets/image-20250505135330278.png)

  ![image-20250505135411666](./assets/image-20250505135411666.png)

# Projecto 2 -> Sistema de Saldo de Celulares

## Interfaz grafica:

![image-20250505141715935](./assets/image-20250505141715935.png)

![image-20250505141729152](./assets/image-20250505141729152.png)

![image-20250505141758335](./assets/image-20250505141758335.png)

## Tablas:

* 3 :

![image-20250505140756400](./assets/image-20250505140756400.png)

## Logica:

## Archivos necesarios:

![image-20250505141633546](./assets/image-20250505141633546.png)

![image-20250505141641205](./assets/image-20250505141641205.png)

* Lo mismo que el anterior caso

## Resultado



# Autor:

* MRodzDirect