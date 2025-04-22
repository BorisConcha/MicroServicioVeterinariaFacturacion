DROP TABLE facturacionVeterinaria CASCADE CONSTRAINTS;

CREATE TABLE facturacionVeterinaria (
  id_factura                NUMBER(8) NOT NULL,
  fecha_factura             VARCHAR2(100) NOT NULL,
  nombre_veterinario        VARCHAR2(100) NOT NULL,
  nombre_mascota            VARCHAR2(250) NOT NULL,
  edad_mascota              NUMBER(8) NOT NULL,
  genero_mascota            VARCHAR2(250) NOT NULL,
  tipo_servicio             VARCHAR2(250) NOT NULL,
  descripcion_servicio      VARCHAR2(700) NOT NULL
);


ALTER TABLE facturacionVeterinaria ADD CONSTRAINT pk_facturacionVeterinaria PRIMARY KEY ( id_factura );


INSERT INTO facturacionVeterinaria VALUES (1, '05/01/2020', 'Dr. Martínez', 'Zeus', 2, 'Masculino', 'Vacunación', 'Se le suministró al animal una vacuna antirrábica');
INSERT INTO facturacionVeterinaria VALUES (2, '14/03/2021', 'Dr. Rodríguez', 'Luna', 1, 'Femenino', 'Cirugía', 'Se esterilizó el animal, se le recetó desinflamatorio y antibiótico');
INSERT INTO facturacionVeterinaria VALUES (3, '27/10/2024', 'Dr. Gómez', 'Duke', 3, 'Masculino', 'Desparasitación', 'Se aplicó al perro antiparasitario oral y externo');
INSERT INTO facturacionVeterinaria VALUES (4, '18/08/2024', 'Dr. Sánchez', 'Leo', 5, 'Masculino', 'Cirugía', 'Se realizó extracción de tumor benigno, se le recetó dieta blanda además de reposo de al menos una semana además de chequeos recurrentes por parte del dueño para ver la cicatrización de las heridas');
INSERT INTO facturacionVeterinaria VALUES (5, '10/02/2025', 'Dra. López', 'Nala', 1, 'Femenino', 'Revisión General', 'Se le hizo un chequeo completo, en el cual se demostró un estado de salud óptimo en la pequeña Nala');