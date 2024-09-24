create database proyectoToDo;
use proyectoToDo;
create table Usuario (
    id int auto_increment primary key,
    nombre varchar(50) not null,
    apellido varchar(50) not null,
    contrasena varchar(255) not null
);
create table Tarea (
    id int auto_increment primary key,
    nombre varchar(100) not null,
    estado varchar(50) not null,
    descripcion text,
    prioridad varchar(100) not null,
    fecha_creacion datetime default CURRENT_TIMESTAMP not null,
    fecha_inicio datetime not null,
    fecha_fin_estimado datetime not null,
    fecha_final datetime
);

create table Etiqueta (
    id int auto_increment primary key,
    nombre varchar(50) not null,
    descripcion text
);

create table Usuario_Tarea (
    usuario_id int,
    tarea_id int,
    primary key (usuario_id, tarea_id),
    foreign key (usuario_id) references Usuario(id) on delete cascade,
    foreign key (tarea_id) references Tarea(id) on delete cascade
);

create table Tarea_Etiqueta (
    tarea_id int,
    etiqueta_id int,
    primary key (tarea_id, etiqueta_id),
    foreign key (tarea_id) references Tarea(id) on delete cascade,
    foreign key (etiqueta_id) references Etiqueta(id) on delete cascade
);
