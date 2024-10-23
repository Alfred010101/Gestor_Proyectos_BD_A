-- 1 Crear la tabla Roles
CREATE TABLE Roles(
    pk_id INTEGER PRIMARY KEY,
    nombre VARCHAR(35) NOT NULL
);

-- 2 Crear la tabla Departamentos, pero sin definir el campo jefe
CREATE TABLE Departamentos(
    pk_id AUTOINCREMENT PRIMARY KEY,
    fk_jefe INTEGER,
    nombre VARCHAR(32) NOT NULL,
    telefono VARCHAR(15)
);

-- 3 Crear la tabla Personal, con sus llaves foraneas
CREATE TABLE Personal(
    pk_id AUTOINCREMENT PRIMARY KEY,
    fk_rol INTEGER NOT NULL,
    fk_departamentos INTEGER NOT NULL,
    nombre VARCHAR(35) NOT NULL,
    ap_paterno VARCHAR(20) NOT NULL,
    ap_materno VARCHAR(20) NOT NULL,
    password VARCHAR(64) NOT NULL,
    email VARCHAR(64),
    direccion VARCHAR(64),
    telefono VARCHAR(15),
    FOREIGN KEY (fk_rol) REFERENCES Roles(pk_id),
    FOREIGN KEY (fk_departamentos) REFERENCES Departamentos(pk_id)
);

-- Luego, agregar la clave foranea
ALTER TABLE Departamentos
ADD CONSTRAINT fk_jefe FOREIGN KEY (fk_jefe) REFERENCES Personal(pk_id);  

-- 4 Crear la tabla Proyectos
CREATE TABLE Proyectos (
    pk_id AUTOINCREMENT PRIMARY KEY,
    fk_lider INTEGER,
    nombre VARCHAR(50) NOT NULL,  
    descripcion VARCHAR(128),  
    estado INTEGER NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_termino DATE,
    FOREIGN KEY (fk_lider) REFERENCES Personal(pk_id) 
);
--ON DELETE SET NULL

-- 5 Crear la tabla Colaboradores
CREATE TABLE Colaboradores (
    pk_id AUTOINCREMENT PRIMARY KEY,
    fk_proyecto INTEGER NOT NULL,
    fk_colaborador INTEGER NOT NULL,
    FOREIGN KEY (fk_proyecto) REFERENCES Proyectos(pk_id),
    FOREIGN KEY (fk_colaborador) REFERENCES Personal(pk_id)
);
--ON DELETE CASCADE

-- 6 Crear la tabla Tareas
CREATE TABLE Tareas(
    pk_id AUTOINCREMENT PRIMARY KEY,
    fk_proyecto INTEGER NOT NULL,
    fk_responsable INTEGER NOT NULL,
    descripcion VARCHAR(124),
    estado INTEGER NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_termino DATE,
    fecha_programada_termino DATE NOT NULL,
    FOREIGN KEY (fk_proyecto) REFERENCES Proyectos(pk_id),
    FOREIGN KEY (fk_responsable) REFERENCES Colaboradores(pk_id)
);

-- 7 Crear la tabla Recursos
CREATE TABLE Recursos(
    pk_id AUTOINCREMENT PRIMARY KEY,
    nombre VARCHAR(25) NOT NULL,
    total INTEGER NOT NULL,
    disponible INTEGER NOT NULL
);

-- 8 Crear la tabla Recursos de Tarea
CREATE TABLE RecursosTarea(
    pk_id AUTOINCREMENT PRIMARY KEY,
    fk_recurso INTEGER NOT NULL,
    fk_tarea INTEGER NOT NULL,
    cantidad INTEGER NOT NULL,
    FOREIGN KEY (fk_recurso) REFERENCES Recursos(pk_id),
    FOREIGN KEY (fk_tarea) REFERENCES Tareas(pk_id)
);