USE censo_inegi;

-- =====================================
-- USUARIO
-- =====================================

INSERT INTO usuario
(usuario,password,nombre_completo)
VALUES
('admin','123456','Administrador General');

-- =====================================
-- TIPOS DE VIVIENDA
-- =====================================

INSERT INTO tipo_vivienda(nombre)
VALUES
('Concreto'),
('Adobe Antiguo'),
('Ladrillo'),
('Madera'),
('Cartón'),
('Piedra'),
('Prefabricada'),
('Material Ecológico'),
('Paja, Ramas o Caña'),
('Adobe Moderno');

-- =====================================
-- ACTIVIDADES ECONOMICAS
-- =====================================

INSERT INTO actividad_economica(nombre)
VALUES
('Comerciante'),
('Empleado'),
('Profesor'),
('Ingeniero'),
('Medico'),
('Agricultor'),
('Ganadero'),
('Taxista'),
('Albanil'),
('Estudiante');

-- =====================================
-- MUNICIPIOS
-- =====================================

INSERT INTO municipio
(clave_municipio,nombre)
VALUES
('030','Saltillo'),
('035','Torreón'),
('018','Monclova');

-- =====================================
-- LOCALIDADES
-- =====================================

INSERT INTO localidad
(id_municipio,clave_localidad,nombre,ambito)
VALUES
(1,'0001','Saltillo Centro','Urbano'),
(1,'0002','La Encantada','Rural'),
(2,'0001','Torreón Centro','Urbano');

-- =====================================
-- VIVIENDAS
-- =====================================

INSERT INTO vivienda
(
id_localidad,
id_tipo_vivienda,
calle,
numero,
colonia,
num_habitaciones,
agua,
luz,
drenaje,
internet
)
VALUES
(1,1,'Juarez','123','Centro',4,1,1,1,1),
(2,3,'Morelos','45','Ejido',3,1,1,0,0);

-- =====================================
-- HABITANTES
-- =====================================

INSERT INTO habitante
(
id_vivienda,
nombre,
apellido_paterno,
apellido_materno,
sexo,
edad,
fecha_nacimiento,
estado_civil,
escolaridad,
estatura
)
VALUES
(1,'Juan','Perez','Lopez','M',35,'1991-05-01','Casado','Universidad',1.75),

(1,'Maria','Perez','Garcia','F',33,'1993-08-10','Casada','Preparatoria',1.65),

(2,'Carlos','Rodriguez','Martinez','M',26,'2000-01-15','Soltero','Universidad',1.80);

-- =====================================
-- ACTIVIDADES DE LOS HABITANTES
-- =====================================

INSERT INTO habitante_actividad
(id_habitante,id_actividad,ingreso_mensual)
VALUES
(1,8,12000.00),
(1,1,5000.00),
(2,3,14000.00),
(3,4,22000.00);
