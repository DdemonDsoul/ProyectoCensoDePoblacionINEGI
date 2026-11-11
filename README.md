# Censo INEGI

Proyecto académico desarrollado para la materia de Programación Orientada a Objetos.

## Descripción

Sistema Desktop para el registro y consulta de información censal de viviendas y habitantes del estado de Coahuila.

El sistema permite:

- Autenticación de usuarios.
- Registro de municipios y localidades.
- Registro de viviendas.
- Registro de habitantes.
- Asociación de actividades económicas.
- Consultas estadísticas.
- Dashboard informativo.

## Tecnologías

- Java
- Eclipse IDE
- MySQL
-mas a incluir...

## Arquitectura

El proyecto utiliza la arquitectura MVC: 

```text
Model
View
Controller
```

### Patrones de Diseño

#### Singleton

Utilizado para gestionar una única conexión a la base de datos MySQL.

#### Strategy

Utilizado para la generación de reportes estadísticos y dashboards.

## Base de Datos

Nombre de la base de datos:

```sql
censo_inegi
```

Tablas principales:

```text
usuario
municipio
localidad
tipo_vivienda
vivienda
habitante
actividad_economica
habitante_actividad
vivienda_actividad
```

## Estructura del Proyecto(PENDIENTE NO FINAL)

```text
src/

├── model/
├── view/
├── controller/
├── dao/
├── strategy/
├── singleton/
└── Main.java
```

## Funcionalidades

- Login de administradores.
- CRUD de Municipios.
- CRUD de Localidades.
- CRUD de Viviendas.
- CRUD de Habitantes.
- Gestión de Actividades Económicas.
- Reportes Estadísticos.
- Dashboard de Población.

## Integrantes

- Esteban
- [Nombre integrante]
- [Nombre integrante]
