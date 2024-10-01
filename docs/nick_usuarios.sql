use proyectoToDo;
alter table Usuario
add column nick varchar(100);

DELIMITER $$
create trigger generar_nick
before insert on Usuario
for each row
begin
    set new.nick = CONCAT(new.nombre, ".", new.apellido);
end $$
DELIMITER ;

SET SQL_SAFE_UPDATES = 0;

update Usuario
set nick = CONCAT(nombre, ".", apellido)
where nick is null or nick = "";
