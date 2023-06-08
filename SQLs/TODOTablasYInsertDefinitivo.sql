CREATE DATABASE MARCOSTV;
GO
USE MARCOSTV;

CREATE TABLE contenido(
	codigo INT,
	titulo VARCHAR(200) NOT NULL,
	descripcion TEXT,
	duracionSegundos INT NOT NULL,
	valoracion FLOAT,
	anyo_lanzamiento DATE,
	presupuesto MONEY NOT NULL,
	edad_recomendada INT,
	fecha_alta DATE NOT NULL,
	CONSTRAINT pkContenido PRIMARY KEY (codigo)
);

CREATE TABLE categoria(
	codigo int NOT NULL,
	nombre varchar(200),
	descripcion varchar(250),
	CONSTRAINT pkcategorias PRIMARY KEY (codigo)
);

CREATE TABLE idioma (
	id INT,
	nombre VARCHAR(100),
	CONSTRAINT pk_idioma PRIMARY KEY (id)
);

CREATE TABLE pais (
	id INT NOT NULL,
	nombre VARCHAR (50),
	CONSTRAINT pkPais PRIMARY KEY (id)
);

CREATE TABLE tarjeta(
	numero BIGINT PRIMARY KEY,
	caducidad date NOT NULL,
	titular varchar(200) NOT NULL,
	cvv int NOT NULL,
	banco varchar(200) NOT NULL
);

CREATE TABLE director(
	codigo int,
	nombre Varchar(50),
	apellidos Varchar(80),
	edad int,
	nacionalidad Varchar(50),
	sexo char(1),
	anyosexperiencia int,
	numpremios int,
	idPais int,
	CONSTRAINT pkDirector PRIMARY KEY (codigo),
	CONSTRAINT fkDirectorPais FOREIGN KEY (idPais) REFERENCES pais(id),
);

CREATE TABLE documental(
codigo int,
director int,
tipoContenido VARCHAR(10) CHECK (tipoContenido = 'documental'),
CONSTRAINT pkDocumental PRIMARY KEY (codigo),
CONSTRAINT fkDocumentalContenido FOREIGN KEY (codigo) REFERENCES contenido(codigo),
CONSTRAINT fkcodigoDirector FOREIGN KEY (director) REFERENCES director(codigo)
);

CREATE TABLE pelicula(
	codigoPelicula int NOT NULL,
	director int,
 	tipoContenido VARCHAR(10) CHECK (tipoContenido = 'pelicula'),
	CONSTRAINT pkPelicula PRIMARY KEY (codigoPelicula),
	CONSTRAINT fkPeliculaContenido FOREIGN KEY (codigoPelicula) REFERENCES contenido(codigo),
	CONSTRAINT fkPeliculaDirector FOREIGN KEY (director) REFERENCES director(codigo),
);

CREATE TABLE serie(
	codigo INT,
	director INT,
 	tipoContenido VARCHAR(10) CHECK (tipoContenido = 'serie'),
	CONSTRAINT pkSerie PRIMARY KEY (codigo),
	CONSTRAINT fkSerieContenido FOREIGN KEY (codigo) REFERENCES contenido(codigo),
	CONSTRAINT fkSerieDirector FOREIGN KEY (director) REFERENCES director(codigo),
);

CREATE TABLE temporada(
	numero int,
	serie int,
	resumen TEXT,
	CONSTRAINT pkTemporada PRIMARY KEY (numero, serie),
	CONSTRAINT fkTemporadaSerie FOREIGN KEY (serie) REFERENCES serie(codigo)
);

CREATE TABLE episodio (
	id_episodio INT NOT NULL,
	id_temporada INT,
	id_Serie INT,
	nombre VARCHAR (100),
	fecha_de_lanzamiento DATE NOT NULL,
	duracion INT NOT NULL,
	breve_descripcion TEXT,
	CONSTRAINT pkEpisodio PRIMARY KEY (id_episodio, id_temporada, id_Serie),
	CONSTRAINT fkEpisodio_numero_temporada FOREIGN KEY (id_temporada, id_Serie) REFERENCES temporada(numero, serie)
);

CREATE TABLE usuario (
	codigo INT NOT NULL,
	nombre VARCHAR (100) NOT NULL,
	telefono VARCHAR(50) NOT NULL,
	email VARCHAR (100) NOT NULL,
	edad INT NOT NULL,
	nacionalidad VARCHAR (100),
	CONSTRAINT pkUsuario PRIMARY KEY (codigo)
);

CREATE TABLE tarjetaUsuario (
	idUsuario INT,
	NumTarjeta BIGINT,
	CONSTRAINT pk_TarjetaUsuario PRIMARY KEY (idUsuario, NumTarjeta),
	CONSTRAINT fk_TarjetaUsuario_Tarjeta FOREIGN KEY (NumTarjeta) REFERENCES tarjeta (numero),
	CONSTRAINT fk_TarjetaUsuario_Usuario FOREIGN KEY (idUsuario) REFERENCES usuario (codigo)
);

CREATE TABLE suscripcion (
	id INT,
	nombre VARCHAR(50),
	descripción TEXT,
	precio MONEY,
	duracion_meses INT,
	cantidaPersona INT,
	CONSTRAINT pkSuscripcion PRIMARY KEY (id)
);

CREATE TABLE usuarioSuscripcion(
	id_usuario INT,
	id_suscripcion INT,
	fecha_pago DATETIME,
	CONSTRAINT pkUsuarioSuscripcion PRIMARY KEY (id_usuario, id_suscripcion, fecha_pago),
	CONSTRAINT fkUsuarioSuscripcion_Usuario FOREIGN KEY (id_usuario) REFERENCES usuario(codigo),
	CONSTRAINT fkUsuarioSuscripcion_Suscripcion FOREIGN KEY (id_suscripcion) REFERENCES suscripcion(id)
);

CREATE TABLE visualizar(
	codigoUsuario int NOT NULL,
	codigoContenido int NOT NULL,
	fechaStream Date NOT NULL, 
	duracion Time, 
	valoracion FLOAT,
	CONSTRAINT pkvisualizacion PRIMARY KEY (codigoUsuario,codigoContenido, fechaStream),
	CONSTRAINT fkVisualizarUsuario FOREIGN KEY (codigoUsuario) REFERENCES usuario(codigo),
	CONSTRAINT fkVisualizarContenido FOREIGN KEY (codigoContenido) REFERENCES contenido(codigo),
);

CREATE TABLE idiomacontenido (
	idIdioma INT,
	codigoContenido INT,
	subtitulo VARCHAR(100),
	CONSTRAINT pk_idiomacontenido PRIMARY KEY (idIdioma, codigoContenido),
	CONSTRAINT fk_idioma FOREIGN KEY (idIdioma) REFERENCES idioma(id),
	CONSTRAINT fk_contenido FOREIGN KEY (codigoContenido) REFERENCES contenido(codigo)
);

CREATE TABLE tiposuscripcion(
	id int PRIMARY KEY,
	metodoPago varchar NOT NULL, 
	cantidadpersonas int NOT NULL,
	 precio DECIMAL(6,2) NOT NULL, 
	 nombre VARCHAR(100), 
	 idSuscripcion int,
	 CONSTRAINT FK_Suscripcion FOREIGN KEY (idSuscripcion) REFERENCES suscripcion(id)
);

CREATE TABLE categoriacontenido(
    codigoContenido INT,
    idCategoria INT,
    CONSTRAINT fk_lenguajecontenido FOREIGN KEY (codigoContenido) REFERENCES contenido(codigo),
    CONSTRAINT fk_codigocategoria FOREIGN KEY (idCategoria) REFERENCES categoria(codigo),
    CONSTRAINT pk_categoriacontenido PRIMARY KEY (codigoContenido, idCategoria)
);

CREATE TABLE pais_idioma(
	id_pais INT,
	id_idioma INT,
	CONSTRAINT pkPaisIdioma PRIMARY KEY (id_pais, id_idioma),
	CONSTRAINT fkPaisIdiomaPais FOREIGN KEY (id_pais) REFERENCES pais(id),
	CONSTRAINT fkPaisIdiomaIdioma FOREIGN KEY (id_idioma) REFERENCES idioma(id)
);

CREATE TABLE departamento(
	codigo INT,
	nombre VARCHAR(50),
	descripcion TEXT,
	CONSTRAINT pkdepartamento PRIMARY KEY (codigo)
);

CREATE TABLE sesion (
	idSesion INT IDENTITY(1,1),
	nombre VARCHAR(50) NOT NULL,
	contrasenya VARCHAR(50),
	rol VARCHAR(10) NOT NULL,
	CONSTRAINT pkSession PRIMARY KEY (idSesion)
)

CREATE TABLE empleado(
	codigo INT,
	nif VARCHAR(10) NOT NULL,
	nombre VARCHAR(20) NOT NULL,
	primer_apellido VARCHAR(20) NOT NULL,
	segundo_apellido VARCHAR(20),
	telefono INT NOT NULL,
	email VARCHAR(50) NOT NULL,
	direccion VARCHAR(256) NOT NULL,
	codigo_departamento INT,
	idSesion INT,
	CONSTRAINT pkEmpleado PRIMARY KEY (codigo),
	CONSTRAINT fkEmpleadoDepartamento FOREIGN KEY (codigo_departamento) REFERENCES departamento(codigo),
	CONSTRAINT fkEmpleadoSesion FOREIGN KEY (idSesion) REFERENCES sesion(idSesion)
);

CREATE TABLE perfil (
codigoPerfil INT,
nombre VARCHAR(50),
codigoUsuario INT,
CONSTRAINT pk_perfil PRIMARY KEY (codigoPerfil),
CONSTRAINT fk_perfil_usuario FOREIGN KEY (codigoUsuario) REFERENCES usuario(codigo)
);


------------------------------------------Tabla contenido-----------------------------------------

INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (1, 'El Gran Escape', 'Un grupo de prisioneros de guerra planea una audaz fuga durante la Segunda Guerra Mundial.', 7200, 4.5, '1963-07-04', 5000000, 14, '2023-05-01');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (2, 'Breaking Bad', 'Un profesor de química se convierte en un poderoso narcotraficante tras ser diagnosticado con cáncer.', 2700, 4.8, '2008-01-20', 2000000, 18, '2023-05-02');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (3, 'The Social Dilemma', 'Un documental que examina el impacto negativo de las redes sociales en la sociedad moderna.', 5400, 4.7, '2020-09-09', 1000000, 16, '2023-05-03');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (4, 'Pulp Fiction', 'Una serie de historias interconectadas sobre crimen, redención y violencia en Los Ángeles.', 9300, 4.9, '1994-10-14', 3000000, 18, '2023-05-04');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (5, 'Stranger Things', 'Un grupo de niños descubre un mundo paralelo y se enfrenta a fuerzas sobrenaturales en la década de 1980.', 3600, 4.6, '2016-07-15', 1500000, 12, '2023-05-05');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (6, 'El Padrino', 'La saga de una poderosa familia de la mafia italiana y su lucha por el poder y la supervivencia.', 10800, 4.9, '1972-03-24', 7000000, 18, '2023-05-06');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (7, 'La Casa de Papel', 'Un grupo de criminales lleva a cabo un gran atraco a la Fábrica Nacional de Moneda y Timbre de España.', 5400, 4.7, '2017-05-02', 2500000, 16, '2023-05-07');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (8, 'The Shawshank Redemption', 'La historia de un hombre inocente que es condenado a cadena perpetua y su lucha por la libertad.', 8520, 4.8, '1994-09-23', 4000000, 14, '2023-05-08');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (9, 'Black Mirror', 'Una serie de antología que muestra los aspectos oscuros de la tecnología y su impacto en la sociedad.', 3000, 4.5, '2011-12-04', 1500000, 16, '2023-05-09');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (10, 'Inception', 'Un ladrón de sueños es contratado para realizar una implantación de ideas en la mente de un empresario.', 8880, 4.7, '2010-07-16', 3500000, 14, '2023-05-10');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (11, 'Game of Thrones', 'Una épica serie basada en la serie de novelas "Canción de hielo y fuego" de George R.R. Martin.', 5400, 4.9, '2011-04-17', 5000000, 18, '2023-05-11');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (12, 'The Dark Knight', 'El Caballero Oscuro lucha contra el crimen en Gotham City mientras se enfrenta al Joker.', 9120, 4.8, '2008-07-18', 3000000, 14, '2023-05-12');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (13, 'Friends', 'La vida de un grupo de amigos en Nueva York mientras atraviesan diferentes situaciones cómicas y emocionales.', 1800, 4.7, '1994-09-22', 2000000, 12, '2023-05-13');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (14, 'The Godfather', 'Un mafioso italoamericano se ve envuelto en el mundo del crimen y la violencia en la década de 1940.', 10500, 4.9, '1972-03-24', 5000000, 18, '2023-05-14');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (15, 'Stranger Things 2', 'La continuación de la exitosa serie, donde los personajes se enfrentan a nuevas amenazas en Hawkins.', 3600, 4.6, '2017-10-27', 2500000, 12, '2023-05-15');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (16, 'The Avengers', 'Un grupo de superhéroes se une para proteger la Tierra de una amenaza alienígena.', 8580, 4.7, '2012-04-11', 4000000, 12, '2023-05-16');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (17, 'Breaking Bad 2', 'La continuación de la aclamada serie, donde los personajes se enfrentan a las consecuencias de sus acciones.', 2700, 4.8, '2019-01-20', 2000000, 18, '2023-05-17');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (18, 'The Matrix', 'Un hacker descubre la verdad sobre la realidad y se une a una rebelión contra las máquinas.', 8160, 4.6, '1999-03-31', 3500000, 14, '2023-05-18');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (19, 'The Witcher', 'Un cazador de monstruos se embarca en una aventura épica en un mundo lleno de criaturas y magia.', 3600, 4.7, '2019-12-20', 3000000, 16, '2023-05-19');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (20, 'The Lord of the Rings', 'Una trilogía que sigue la misión de un grupo de personajes para destruir un poderoso anillo.', 10800, 4.9, '2001-12-19', 8000000, 12, '2023-05-20');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (21, 'La La Land', 'Un pianista y una aspirante a actriz se enamoran mientras persiguen sus sueños en Los Ángeles.', 7680, 4.8, '2016-12-09', 2000000, 12, '2023-05-21');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (22, 'Breaking Bad 3', 'La última temporada de la exitosa serie que sigue la transformación del profesor de química en un narcotraficante.', 2700, 4.9, '2021-01-20', 2500000, 18, '2023-05-22');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (23, 'Interstellar', 'Un grupo de astronautas se embarca en un viaje interestelar para salvar a la humanidad de la extinción.', 8880, 4.7, '2014-11-05', 5000000, 14, '2023-05-23');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (24, 'The Office', 'Una comedia que sigue la vida diaria de los empleados de una oficina a través del formato de falso documental.', 1800, 4.5, '2005-03-24', 1500000, 12, '2023-05-24');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (25, 'Forrest Gump', 'La historia de un hombre con discapacidad intelectual que vive una vida extraordinaria llena de momentos icónicos.', 8520, 4.9, '1994-07-06', 3000000, 14, '2023-05-25');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (26, 'Narcos', 'Una serie que narra la vida del famoso narcotraficante Pablo Escobar y el auge y caída del Cartel de Medellín.', 3600, 4.6, '2015-08-28', 2000000, 18, '2023-05-26');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (27, 'The Lion King', 'Un joven león debe enfrentarse a su malvado tío para reclamar su lugar legítimo como rey de la sabana.', 6780, 4.8, '1994-06-15', 4000000, 6, '2023-05-27');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (28, 'Stranger Things 3', 'La tercera temporada de la serie que sigue las aventuras de los niños en la lucha contra seres sobrenaturales.', 3600, 4.7, '2019-07-04', 3000000, 12, '2023-05-28');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (29, 'The Godfather: Part II', 'Una continuación de la historia de la familia Corleone, mostrando el pasado y el presente.', 11160, 4.8, '1974-12-20', 6000000, 18, '2023-05-29');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (30, 'Stranger Things 4', 'La cuarta temporada de la serie que continúa la historia de los personajes en el mundo del revés.', 3600, 4.7, '2023-07-15', 3500000, 14, '2023-05-30');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (41, 'Game of Thrones', 'Una serie épica que narra las luchas de poder entre familias nobles en el continente de Westeros.', 3600, 4.6, '2011-04-17', 3000000, 18, '2023-06-10');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (42, 'The Shawshank Redemption', 'La historia de un hombre injustamente condenado a cadena perpetua y su lucha por encontrar la libertad.', 8520, 4.9, '1994-09-23', 2000000, 16, '2023-06-11');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (43, 'Stranger Things 6', 'La sexta temporada de la serie que continúa explorando los misterios del Upside Down.', 3600, 4.7, '2024-07-15', 3500000, 14, '2023-06-12');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (44, 'The Simpsons', 'Una comedia animada que sigue las desventuras de una familia disfuncional en la ciudad de Springfield.', 1800, 4.5, '1989-12-17', 1500000, 10, '2023-06-13');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (45, 'The Social Network', 'La historia de los orígenes de Facebook y la controversia que rodea su creación.', 7200, 4.6, '2010-10-01', 2500000, 14, '2023-06-14');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (46, 'Stranger Things 7', 'La séptima temporada de la serie que continúa la lucha contra criaturas sobrenaturales en Hawkins.', 3600, 4.7, '2025-07-31', 3000000, 12, '2023-06-15');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (47, 'The Godfather', 'La historia de una familia criminal italiana y el ascenso de su hijo al poder.', 10500, 4.9, '1972-03-24', 6000000, 18, '2023-06-16');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (48, 'The Matrix', 'Un hacker descubre que el mundo en el que vive es una simulación controlada por máquinas.', 8160, 4.8, '1999-03-31', 4000000, 16, '2023-06-17');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (49, 'Breaking Bad 5', 'La última temporada de la serie que sigue la historia del profesor de química convertido en narcotraficante.', 2700, 4.9, '2024-01-20', 2500000, 18, '2023-06-18');
INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
VALUES (50, 'The Lord of the Rings: The Fellowship of the Ring', 'El inicio de la trilogía que sigue la lucha por destruir un anillo mágico y salvar la Tierra Media.', 10380, 4.8, '2001-12-19', 5000000, 12, '2023-06-19');


------------------------------Categoria--------------------------------------
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (1, 'Acción', 'Películas y series llenas de emocionantes escenas de acción.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (2, 'Comedia', 'Películas y series que buscan hacer reír al espectador con situaciones cómicas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (3, 'Drama', 'Películas y series que presentan historias intensas y emotivas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (4, 'Romance', 'Películas y series que se centran en relaciones románticas y emociones amorosas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (5, 'Ciencia ficción', 'Películas y series que exploran conceptos científicos y tecnológicos imaginarios.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (6, 'Fantasía', 'Películas y series que presentan elementos y seres mágicos en un mundo imaginario.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (7, 'Animación', 'Películas y series animadas, tanto para niños como para adultos.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (8, 'Documental', 'Películas y series que presentan hechos y eventos reales de forma informativa.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (9, 'Suspenso', 'Películas y series que mantienen al espectador en tensión y expectativa.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (10, 'Terror', 'Películas y series que buscan generar miedo y susto en el espectador.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (11, 'Aventura', 'Películas y series que siguen a los personajes en emocionantes viajes y exploraciones.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (12, 'Misterio', 'Películas y series que presentan enigmas y puzzles a resolver.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (13, 'Thriller', 'Películas y series que generan suspenso y emoción con tramas intensas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (14, 'Histórico', 'Películas y series que se basan en hechos históricos y periodos pasados.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (15, 'Western', 'Películas y series ambientadas en el viejo oeste americano.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (16, 'Familia', 'Películas y series adecuadas para disfrutar en familia.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (17, 'Fantasía épica', 'Películas y series que presentan grandes batallas y mundos épicos.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (18, 'Comedia romántica', 'Películas y series que combinan elementos de comedia y romance.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (19, 'Acción y aventura', 'Películas y series que combinan emocionantes escenas de acción con viajes y exploraciones.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (20, 'Crimen', 'Películas y series que se centran en la investigación y resolución de crímenes.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (21, 'Superhéroes', 'Películas y series que presentan a superhéroes con habilidades especiales.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (22, 'Musical', 'Películas y series con números musicales y canciones.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (23, 'Deporte', 'Películas y series que se centran en actividades deportivas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (24, 'Biografía', 'Películas y series basadas en la vida de personas reales.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (25, 'Guerra', 'Películas y series que se desarrollan en el contexto de conflictos bélicos.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (26, 'Política', 'Películas y series que exploran temas y situaciones políticas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (27, 'Fantasía oscura', 'Películas y series que presentan un enfoque más sombrío y siniestro de la fantasía.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (28, 'Histórico-ficción', 'Películas y series que combinan elementos históricos con elementos de ficción.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (29, 'Espionaje', 'Películas y series que se centran en actividades de espionaje e inteligencia.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (30, 'Animación para adultos', 'Películas y series animadas con temáticas más maduras y dirigidas a un público adulto.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (31, 'Musical de fantasía', 'Películas y series que combinan elementos musicales con un entorno de fantasía.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (32, 'Comedia dramática', 'Películas y series que mezclan elementos de comedia y drama.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (33, 'Aventura espacial', 'Películas y series que se desarrollan en el espacio y exploran nuevas fronteras.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (34, 'Sobrenatural', 'Películas y series que presentan elementos y fenómenos sobrenaturales.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (35, 'Comedia de acción', 'Películas y series que combinan escenas de acción con situaciones cómicas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (36, 'Intriga', 'Películas y series que mantienen al espectador en suspenso y con dudas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (37, 'Terror sobrenatural', 'Películas y series de terror con elementos sobrenaturales.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (38, 'Romance dramático', 'Películas y series que combinan elementos románticos con momentos intensos.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (39, 'Acción sobrenatural', 'Películas y series de acción con elementos y criaturas sobrenaturales.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (40, 'Comedia de aventuras', 'Películas y series que combinan la comedia con emocionantes aventuras.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (41, 'Misterio sobrenatural', 'Películas y series que presentan misterios sobrenaturales por resolver.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (42, 'Drama romántico', 'Películas y series que mezclan elementos dramáticos con historias de amor.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (43, 'Acción espacial', 'Películas y series de acción que se desarrollan en el espacio.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (44, 'Crimen dramático', 'Películas y series de crimen con elementos intensos y emotivos.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (45, 'Comedia de ciencia ficción', 'Películas y series que combinan la comedia con elementos de ciencia ficción.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (46, 'Aventura de fantasía', 'Películas y series de aventuras con elementos y criaturas fantásticas.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (47, 'Thriller psicológico', 'Películas y series de thriller que exploran la mente y la psicología.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (48, 'Animación de fantasía', 'Películas y series animadas que se desarrollan en un mundo de fantasía.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (49, 'Acción histórica', 'Películas y series de acción ambientadas en periodos históricos.');
INSERT INTO categoria (codigo, nombre, descripcion)
VALUES (50, 'Suspenso psicológico', 'Películas y series de suspenso que exploran aspectos psicológicos.');

----------------------------------Tabla IDIOMAS-----------------------------


INSERT INTO idioma (id, nombre)
VALUES (1, 'Inglés');
INSERT INTO idioma (id, nombre)
VALUES (2, 'Español');
INSERT INTO idioma (id, nombre)
VALUES (3, 'Francés');
INSERT INTO idioma (id, nombre)
VALUES (4, 'Alemán');
INSERT INTO idioma (id, nombre)
VALUES (5, 'Italiano');
INSERT INTO idioma (id, nombre)
VALUES (6, 'Portugués');
INSERT INTO idioma (id, nombre)
VALUES (7, 'Ruso');
INSERT INTO idioma (id, nombre)
VALUES (8, 'Japonés');
INSERT INTO idioma (id, nombre)
VALUES (9, 'Chino');
INSERT INTO idioma (id, nombre)
VALUES (10, 'Coreano');
INSERT INTO idioma (id, nombre)
VALUES (11, 'Holandés');
INSERT INTO idioma (id, nombre)
VALUES (12, 'Polaco');
INSERT INTO idioma (id, nombre)
VALUES (13, 'Sueco');
INSERT INTO idioma (id, nombre)
VALUES (14, 'Noruego');
INSERT INTO idioma (id, nombre)
VALUES (15, 'Danés');
INSERT INTO idioma (id, nombre)
VALUES (16, 'Finlandés');
INSERT INTO idioma (id, nombre)
VALUES (17, 'Árabe');
INSERT INTO idioma (id, nombre)
VALUES (18, 'Hebreo');
INSERT INTO idioma (id, nombre)
VALUES (19, 'Turco');
INSERT INTO idioma (id, nombre)
VALUES (20, 'Hindi');
INSERT INTO idioma (id, nombre)
VALUES (21, 'Bengalí');
INSERT INTO idioma (id, nombre)
VALUES (22, 'Tailandés');
INSERT INTO idioma (id, nombre)
VALUES (23, 'Malayo');
INSERT INTO idioma (id, nombre)
VALUES (24, 'Vietnamita');
INSERT INTO idioma (id, nombre)
VALUES (25, 'Tagalo');
INSERT INTO idioma (id, nombre)
VALUES (26, 'Griego');
INSERT INTO idioma (id, nombre)
VALUES (27, 'Checo');
INSERT INTO idioma (id, nombre)
VALUES (28, 'Húngaro');
INSERT INTO idioma (id, nombre)
VALUES (29, 'Rumano');
INSERT INTO idioma (id, nombre)
VALUES (30, 'Eslovaco');
INSERT INTO idioma (id, nombre)
VALUES (31, 'Esloveno');
INSERT INTO idioma (id, nombre)
VALUES (32, 'Croata');
INSERT INTO idioma (id, nombre)
VALUES (33, 'Serbio');
INSERT INTO idioma (id, nombre)
VALUES (34, 'Búlgaro');
INSERT INTO idioma (id, nombre)
VALUES (35, 'Macedonio');
INSERT INTO idioma (id, nombre)
VALUES (36, 'Albanés');
INSERT INTO idioma (id, nombre)
VALUES (37, 'Ucraniano');
INSERT INTO idioma (id, nombre)
VALUES (38, 'Estonio');
INSERT INTO idioma (id, nombre)
VALUES (39, 'Letón');
INSERT INTO idioma (id, nombre)
VALUES (40, 'Lituano');
INSERT INTO idioma (id, nombre)
VALUES (41, 'Islandés');
INSERT INTO idioma (id, nombre)
VALUES (42, 'Irlandés');
INSERT INTO idioma (id, nombre)
VALUES (43, 'Galés');
INSERT INTO idioma (id, nombre)
VALUES (44, 'Escocés gaélico');
INSERT INTO idioma (id, nombre)
VALUES (45, 'Afrikáans');
INSERT INTO idioma (id, nombre)
VALUES (46, 'Swahili');
INSERT INTO idioma (id, nombre)
VALUES (47, 'Zulú');
INSERT INTO idioma (id, nombre)
VALUES (48, 'Xhosa');
INSERT INTO idioma (id, nombre)
VALUES (49, 'Sotho');
INSERT INTO idioma (id, nombre)
VALUES (50, 'Sesotho');


-----------------------------------------TABLA PAIS------------------------------------------------------
INSERT INTO pais (id, nombre) VALUES (1, 'Argentina');
INSERT INTO pais (id, nombre) VALUES (2, 'Brasil');
INSERT INTO pais (id, nombre) VALUES (3, 'Colombia');
INSERT INTO pais (id, nombre) VALUES (4, 'España');
INSERT INTO pais (id, nombre) VALUES (5, 'Estados Unidos');
INSERT INTO pais (id, nombre) VALUES (6, 'México');
INSERT INTO pais (id, nombre) VALUES (7, 'Canadá');
INSERT INTO pais (id, nombre) VALUES (8, 'Francia');
INSERT INTO pais (id, nombre) VALUES (9, 'Italia');
INSERT INTO pais (id, nombre) VALUES (10, 'Alemania');
INSERT INTO pais (id, nombre) VALUES (11, 'Reino Unido');
INSERT INTO pais (id, nombre) VALUES (12, 'Australia');
INSERT INTO pais (id, nombre) VALUES (13, 'Japón');
INSERT INTO pais (id, nombre) VALUES (14, 'China');
INSERT INTO pais (id, nombre) VALUES (15, 'India');
INSERT INTO pais (id, nombre) VALUES (16, 'Corea del Sur');
INSERT INTO pais (id, nombre) VALUES (17, 'Rusia');
INSERT INTO pais (id, nombre) VALUES (18, 'Nueva Zelanda');
INSERT INTO pais (id, nombre) VALUES (19, 'Marruecos');
INSERT INTO pais (id, nombre) VALUES (20, 'Sudáfrica');
INSERT INTO pais (id, nombre) VALUES (21, 'Chile');
INSERT INTO pais (id, nombre) VALUES (22, 'Perú');
INSERT INTO pais (id, nombre) VALUES (23, 'Ecuador');
INSERT INTO pais (id, nombre) VALUES (24, 'Venezuela');
INSERT INTO pais (id, nombre) VALUES (25, 'Bolivia');
INSERT INTO pais (id, nombre) VALUES (26, 'Uruguay');
INSERT INTO pais (id, nombre) VALUES (27, 'Paraguay');
INSERT INTO pais (id, nombre) VALUES (28, 'Costa Rica');
INSERT INTO pais (id, nombre) VALUES (29, 'Panamá');
INSERT INTO pais (id, nombre) VALUES (30, 'Guatemala');
INSERT INTO pais (id, nombre) VALUES (31, 'Honduras');
INSERT INTO pais (id, nombre) VALUES (32, 'El Salvador');
INSERT INTO pais (id, nombre) VALUES (33, 'Nicaragua');
INSERT INTO pais (id, nombre) VALUES (34, 'Belice');
INSERT INTO pais (id, nombre) VALUES (35, 'Haití');
INSERT INTO pais (id, nombre) VALUES (36, 'República Dominicana');
INSERT INTO pais (id, nombre) VALUES (37, 'Cuba');
INSERT INTO pais (id, nombre) VALUES (38, 'Puerto Rico');
INSERT INTO pais (id, nombre) VALUES (39, 'Suiza');
INSERT INTO pais (id, nombre) VALUES (40, 'Nigeria');
INSERT INTO pais (id, nombre) VALUES (41, 'Egipto');
INSERT INTO pais (id, nombre) VALUES (42, 'Kenia');
INSERT INTO pais (id, nombre) VALUES (43, 'Portugal');
INSERT INTO pais (id, nombre) VALUES (44, 'Países Bajos');
INSERT INTO pais (id, nombre) VALUES (45, 'Bélgica');
INSERT INTO pais (id, nombre) VALUES (46, 'Suecia');
INSERT INTO pais (id, nombre) VALUES (47, 'Noruega');
INSERT INTO pais (id, nombre) VALUES (48, 'Dinamarca');
INSERT INTO pais (id, nombre) VALUES (49, 'Finlandia');
INSERT INTO pais (id, nombre) VALUES (50, 'Austria');

---------------------------------------------Tabla suscripciones-----------------------------------------

INSERT INTO suscripcion (id, precio, nombre, descripción, duracion_meses, cantidaPersona)
VALUES (1, 7, 'Suscripción 1', 'Descripción de la suscripción 1', 1, 1),
(2, 12, 'Suscripción 2', 'Descripción de la suscripción 2', 1, 2),
(3, 15, 'Suscripción 3', 'Descripción de la suscripción 3', 1, 4);

------------------------------------------Tabla PaisIdioma------------------------------------------

INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (1, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (2, 6);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (3, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (4, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (5, 1);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (6, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (7, 1);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (8, 3);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (9, 5);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (10, 4);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (11, 1);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (12, 1);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (13, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (14, 9);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (15, 20);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (16, 10);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (17, 7);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (18, 1);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (19, 1);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (20, 1);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (21, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (22, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (23, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (24, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (25, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (26, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (27, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (28, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (29, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (30, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (31, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (32, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (33, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (34, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (35, 3);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (36, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (37, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (38, 2);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (39, 3);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (40, 45);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (41, 46);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (42, 46);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (43, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (44, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (45, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (46, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (47, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (48, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (49, 8);
INSERT INTO pais_idioma (id_pais, id_idioma)
VALUES (50, 8);

--------------------------------Tabla Directores----------------------------------------------

INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (1, 'Ricardo', 'Silva', 35, 'Mexicano', 'H', 6, 4, 6);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (2, 'Hugo', 'Leclerc', 35, 'Frances', 'H', 6, 4, 8);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (3, 'Eva', 'Müller', 35, 'Alemana', 'M', 6, 4, 10);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (4, 'Sofia', 'Rojas', 36, 'Chilena', 'M', 6, 4, 21);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (5, 'Gabriela', 'Fernández', 29, 'Mexicana', 'M', 2, 0, 6);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (6, 'Hanna', 'Andersson', 31, 'Sueca', 'M', 2, 0, 46);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (7, 'William', 'Johnson', 38, 'Estadounidense', 'H', 8, 2, 5);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (8, 'Aleksandr', 'Ivanov', 31, 'Ruso', 'H', 2, 0, 17);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (9, 'Matias', 'Hakkarainen', 33, 'Finlandes', 'H', 3, 1, 49);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (10, 'Giulia', 'Bianchi', 37, 'Italiana', 'M', 6, 3, 9);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (11, 'Lucas', 'Gutiérrez', 37, 'Español', 'H', 4, 2, 4);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (12, 'Emily', 'Smith', 34, 'Britanica', 'M', 5, 3, 11);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (13, 'Henrik', 'Lundgerg', 40, 'Sueco', 'H', 7, 5, 46);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (14, 'Maria', 'García', 32, 'Española', 'M', 4, 1, 4);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (15, 'Antonio', 'Rossi', 36, 'Italiano', 'H', 6, 3, 9);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (16, 'Sara', 'Andersson', 29, 'Sueca', 'M', 3, 1, 46);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (17, 'Mohammed', 'Ali', 39, 'Marroqui', 'H', 8, 6, 19);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (18, 'Lena', 'Müller', 35, 'Alemana', 'M', 6, 4, 10);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (19, 'Alexandra', 'Fernández', 30, 'Uruguaya', 'M', 3, 1, 26);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (20, 'Oliver', 'Andersen', 33, 'Danes', 'H', 5, 2, 48);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (21, 'Emma', 'Kovács', 31, 'NeoZelandesa', 'M', 4, 1, 18);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (22, 'Max', 'González', 38, 'Español', 'H', 7, 4, 4);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (23, 'Sophie', 'Bouchard', 36, 'Francesa', 'M', 6, 3, 8);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (24, 'Daniel', 'Smith', 35, 'Britanico', 'H', 7, 4, 11);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (25, 'Martina', 'Petrova', 32, 'Haitiana', 'M', 5, 2, 35);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (26, 'Sebastian', 'López', 29, 'Guatemalteco', 'H', 4, 1, 30);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (27, 'Odile', 'Schneider', 33, 'Alemana', 'M', 5, 2, 10);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (28, 'Guilherme', 'Sousa', 34, 'Croata', 'H', 6, 3, 43);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (29, 'Hannah', 'Richter', 30, 'Española', 'M', 4, 1, 50);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (30, 'Luca', 'Ricci', 36, 'Italiano', 'H', 7, 4, 9);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (31, 'Frank', 'Nieminen', 30, 'Finlandes', 'M', 4, 1, 49);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (32, 'Ahmed', 'Khan', 39, 'Marroqui', 'H', 8, 6, 19);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (33, 'Hans', 'Meyer', 35, 'Aleman', 'H', 6, 4, 10);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (34, 'Pedro', 'Alcocer', 30, 'Panameño', 'M', 3, 1, 29);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (35, 'Erik', 'Hansen', 33, 'Danés', 'H', 5, 2, 48);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (36, 'Ibrahim', 'Akpan', 31, 'Nigeriano', 'H', 4, 1, 40);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (37, 'Fernando', 'Alonso', 33, 'Español', 'H', 7, 4, 4);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (38, 'Juliette', 'Bouchard', 36, 'Francesa', 'M', 6, 3, 8);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (39, 'Nathan', 'Smith', 35, 'Británico', 'H', 7, 4, 11);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (40, 'Kai', 'Pedersen', 32, 'Noruego', 'M', 5, 2, 47);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (41, 'Alfonso', 'López', 29, 'Colombiano', 'H', 4, 1, 5);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (42, 'Linda', 'Thomas', 33, 'Alemana', 'M', 5, 2, 10);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (43, 'Sun hee', 'Hwang', 34, 'Coreano', 'H', 6, 3, 16);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (44, 'Paula', 'García', 30, 'Boliviana', 'M', 4, 1, 25);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (45, 'Devmani', 'Yadav', 36, 'Indú', 'H', 7, 4, 15);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (46, 'Karl', 'Karlsson', 29, 'Sueco', 'M', 3, 1, 46);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (47, 'Hamid', 'Abdelaziz', 39, 'Marroquí', 'H', 8, 6, 19);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (48, 'Hans', 'Weber', 35, 'Aleman', 'H', 6, 4, 10);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (49, 'Verónica', 'Gutierrez', 30, 'Mexicana', 'M', 3, 1, 6);
INSERT INTO director (Codigo, Nombre, Apellidos, Edad, Nacionalidad, Sexo, AnyosExperiencia, NumPremios, idPais)
VALUES (50, 'Lynn', 'Vermeulen', 40, 'Belga', 'M', 5, 2, 45);

-----------------------------------Tabla IdiomaContenido--------------------------------------------

INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 1, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 1, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 2, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 2, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 3, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 3, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 4, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 4, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 5, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 5, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 6, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 6, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 7, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 7, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 8, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 8, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 9, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 9, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 10, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 10, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 11, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 11,'Francés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 12, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 12,'Francés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 13, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 13, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 14, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 14, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 15, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 15, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 16, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 16, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 17, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 17, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 18, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 18, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 19, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 19, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 20, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 20, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 21, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 21, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 22, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 22, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 23, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 23, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 24, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 24, 'Inglés');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (1, 25, 'Español');
INSERT INTO IDIOMACONTENIDO (idIdioma, codigoContenido, subtitulo) VALUES (2, 25, 'Inglés');

---------------------------Tablas CategoríaContenido-------------------------------------

INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (1, 1);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (1, 2);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (2, 1);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (2, 3);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (3, 2);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (3, 3);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (4, 4);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (4, 5);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (5, 6);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (5, 7);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (6, 8);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (6, 9);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (7, 10);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (7, 11);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (8, 12);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (8, 13);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (9, 14);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (9, 15);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (10, 16);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (10, 17);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (11, 18);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (11, 19);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (12, 20);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (12, 21);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (13, 22);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (13, 23);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (14, 24);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (14, 25);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (15, 26);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (15, 27);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (16, 28);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (16, 29);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (17, 30);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (17, 31);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (18, 32);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (18, 33);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (19, 34);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (19, 35);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (20, 36);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (20, 37);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (21, 38);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (21, 39);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (22, 40);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (22, 41);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (23, 42);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (23, 43);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (24, 44);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (24, 45);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (25, 46);
INSERT INTO CATEGORIACONTENIDO (codigoContenido,idCategoria) VALUES (25, 47);

--------------------------------Tabla departamento----------------------------------

INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (1, 'Recursos Humanos', 'Encargado de gestionar el talento humano de la organización.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (2, 'Finanzas', 'Responsable de las actividades financieras y contables.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (3, 'Ventas', 'Encargado de las actividades relacionadas con la venta de productos o servicios.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (4, 'Marketing', 'Responsable de promocionar y posicionar la marca y productos de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (5, 'Tecnología de la Información', 'Encargado de los sistemas y tecnologías de la información.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (6, 'Desarrollo de Productos', 'Responsable del desarrollo y mejora de productos.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (7, 'Operaciones', 'Encargado de gestionar las operaciones diarias de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (8, 'Logística', 'Responsable de la gestión y distribución eficiente de los productos.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (9, 'Calidad', 'Encargado de garantizar la calidad de los productos o servicios.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (10, 'Investigación y Desarrollo', 'Responsable de la investigación y desarrollo de nuevos productos.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (11, 'Compras', 'Encargado de la adquisición de bienes y servicios.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (12, 'Recursos Materiales', 'Responsable de la gestión de los recursos materiales de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (13, 'Legal', 'Encargado de los asuntos legales y jurídicos de la organización.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (14, 'Comunicación', 'Responsable de la comunicación interna y externa de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (15, 'Servicio al Cliente', 'Encargado de brindar un excelente servicio a los clientes.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (16, 'Proyectos', 'Responsable de la gestión de proyectos de la organización.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (17, 'Administración', 'Encargado de la gestión administrativa de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (18, 'Desarrollo de Negocios', 'Responsable de identificar oportunidades y desarrollar nuevos negocios.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (19, 'Servicios Técnicos', 'Encargado de brindar servicios técnicos especializados.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (20, 'Planificación Estratégica', 'Responsable de la planificación estratégica de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (21, 'Auditoría', 'Encargado de realizar auditorías internas y externas.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (22, 'Relaciones Públicas', 'Responsable de gestionar las relaciones públicas de la organización.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (23, 'Desarrollo Organizacional', 'Encargado de desarrollar la organización y el talento.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (24, 'Formación y Desarrollo', 'Responsable de la formación y desarrollo del personal.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (25, 'Gestión de Proveedores', 'Encargado de gestionar la relación con los proveedores.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (26, 'Relaciones Laborales', 'Responsable de las relaciones laborales y sindicales.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (27, 'Seguridad y Salud Laboral', 'Encargado de la seguridad y salud laboral en la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (28, 'Asuntos Regulatorios', 'Responsable de cumplir con los requisitos regulatorios.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (29, 'Gestión Ambiental', 'Encargado de la gestión y protección del medio ambiente.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (30, 'Compliance', 'Responsable de asegurar el cumplimiento normativo de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (31, 'Desarrollo Sostenible', 'Encargado de promover el desarrollo sostenible en la organización.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (32, 'Investigación de Mercados', 'Responsable de investigar y analizar los mercados.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (33, 'Desarrollo Web', 'Encargado del desarrollo y mantenimiento de sitios web.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (34, 'Diseño Gráfico', 'Responsable del diseño gráfico y visual de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (35, 'Redes Sociales', 'Encargado de gestionar las redes sociales de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (36, 'Comunicación Digital', 'Responsable de la comunicación digital de la organización.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (37, 'Soporte Técnico', 'Encargado de brindar soporte técnico a los usuarios.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (38, 'Infraestructura', 'Responsable de la infraestructura tecnológica de la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (39, 'Seguridad de la Información', 'Encargado de la seguridad de la información.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (40, 'Desarrollo de Software', 'Responsable del desarrollo de software de la organización.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (41, 'Base de Datos', 'Encargado de la gestión y administración de bases de datos.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (42, 'Soporte de Infraestructura', 'Responsable del soporte de infraestructura tecnológica.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (43, 'Ciberseguridad', 'Encargado de la seguridad informática y protección contra amenazas.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (44, 'Inteligencia Artificial', 'Responsable del desarrollo de soluciones de inteligencia artificial.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (45, 'Análisis de Datos', 'Encargado del análisis de datos y generación de información.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (46, 'Gestión de Proyectos de TI', 'Responsable de la gestión de proyectos de tecnología.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (47, 'Innovación Tecnológica', 'Encargado de promover la innovación tecnológica en la empresa.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (48, 'Arquitectura de Software', 'Responsable de definir la arquitectura de software.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (49, 'Desarrollo Mobile', 'Encargado del desarrollo de aplicaciones móviles.');
INSERT INTO departamento (codigo, nombre, descripcion)
VALUES (50, 'E-commerce', 'Responsable de la gestión del comercio electrónico de la empresa.');

---------------------------------Tabla Empleados----------------------------------------

INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (1, '12345678A', 'Juan', 'Gómez', 'López', '123456789', 'juan.gomez@example.com', 'Calle Principal 123', 1);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (2, '23456789B', 'María', 'Rodríguez', 'Martínez', '234567890', 'maria.rodriguez@example.com', 'Avenida Central 456', 2);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (3, '34567890C', 'Pedro', 'Fernández', 'García', '345678901', 'pedro.fernandez@example.com', 'Plaza Mayor 789', 3);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (4, '45678901D', 'Ana', 'López', 'Sánchez', '456789012', 'ana.lopez@example.com', 'Calle Secundaria 321', 4);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (5, '56789012E', 'Carlos', 'González', 'Romero', '567890123', 'carlos.gonzalez@example.com', 'Avenida Principal 654', 5);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (6, '67890123F', 'Laura', 'Pérez', 'Hernández', '678901234', 'laura.perez@example.com', 'Calle Central 987', 6);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (7, '78901234G', 'Manuel', 'Torres', 'Jiménez', '789012345', 'manuel.torres@example.com', 'Avenida Principal 654', 7);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (8, '89012345H', 'Carmen', 'Vargas', 'Ruiz', '890123456', 'carmen.vargas@example.com', 'Calle Principal 321', 8);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (9, '90123456I', 'Miguel', 'Moreno', 'López', '901234567', 'miguel.moreno@example.com', 'Avenida Central 654', 9);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (10, '01234567J', 'Luisa', 'Soto', 'Gómez', '012345678', 'luisa.soto@example.com', 'Calle Principal 987', 10);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (11, '12345678K', 'Javier', 'Ramírez', 'Vega', '123456789', 'javier.ramirez@example.com', 'Avenida Principal 321', 11);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (12, '23456789L', 'Elena', 'López', 'Morales', '234567890', 'elena.lopez@example.com', 'Calle Secundaria 654', 12);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (13, '34567890M', 'Pablo', 'González', 'Santos', '345678901', 'pablo.gonzalez@example.com', 'Avenida Central 987', 13);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (14, '45678901N', 'Sofía', 'Hernández', 'Muñoz', '456789012', 'sofia.hernandez@example.com', 'Plaza Mayor 321', 14);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (15, '56789012O', 'Jorge', 'Sánchez', 'Fernández', '567890123', 'jorge.sanchez@example.com', 'Calle Central 654', 15);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (16, '67890123P', 'Isabel', 'Gómez', 'Rodríguez', '678901234', 'isabel.gomez@example.com', 'Avenida Principal 987', 16);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (17, '78901234Q', 'Alejandro', 'Martínez', 'Torres', '789012345', 'alejandro.martinez@example.com', 'Calle Principal 654', 17);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (18, '89012345R', 'Natalia', 'Ruiz', 'Pérez', '890123456', 'natalia.ruiz@example.com', 'Avenida Central 321', 18);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (19, '90123456S', 'Roberto', 'López', 'Moreno', '901234567', 'roberto.lopez@example.com', 'Calle Secundaria 654', 19);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (20, '01234567T', 'Beatriz', 'Gómez', 'Soto', '012345678', 'beatriz.gomez@example.com', 'Plaza Principal 987', 20);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (21, '12345678U', 'Daniel', 'Vega', 'Ramírez', '123456789', 'daniel.vega@example.com', 'Avenida Principal 321', 21);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (22, '23456789V', 'Marina', 'Morales', 'López', '234567890', 'marina.morales@example.com', 'Calle Central 654', 22);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (23, '34567890W', 'Gabriel', 'Santos', 'González', '345678901', 'gabriel.santos@example.com', 'Avenida Principal 987', 23);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (24, '45678901X', 'Valeria', 'Muñoz', 'Hernández', '456789012', 'valeria.munoz@example.com', 'Calle Principal 654', 24);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (25, '56789012Y', 'Óscar', 'Fernández', 'Sánchez', '567890123', 'oscar.fernandez@example.com', 'Avenida Central 321', 25);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (26, '67890123Z', 'Camila', 'Rodríguez', 'Gómez', '678901234', 'camila.rodriguez@example.com', 'Calle Secundaria 654', 26);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (27, '78901234AA', 'Santiago', 'Torres', 'Martínez', '789012345', 'santiago.torres@example.com', 'Plaza Principal 987', 27);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (28, '89012345BB', 'Valentina', 'Pérez', 'Ruiz', '890123456', 'valentina.perez@example.com', 'Avenida Central 321', 28);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (29, '90123456CC', 'Andrés', 'Moreno', 'López', '901234567', 'andres.moreno@example.com', 'Calle Principal 654', 29);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (30, '01234567DD', 'Lucía', 'Gómez', 'Soto', '012345678', 'lucia.gomez@example.com', 'Avenida Central 987', 30);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (31, '12345678EE', 'Diego', 'Ramírez', 'Vega', '123456789', 'diego.ramirez@example.com', 'Calle Principal 321', 31);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (32, '23456789FF', 'Daniela', 'López', 'Morales', '234567890', 'daniela.lopez@example.com', 'Avenida Central 654', 32);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (33, '34567890GG', 'Maximiliano', 'González', 'Santos', '345678901', 'maximiliano.gonzalez@example.com', 'Calle Principal 987', 33);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (34, '45678901HH', 'Martina', 'Hernández', 'Muñoz', '456789012', 'martina.hernandez@example.com', 'Avenida Central 321', 34);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (35, '56789012II', 'Emilio', 'Sánchez', 'Fernández', '567890123', 'emilio.sanchez@example.com', 'Calle Principal 654', 35);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (36, '67890123JJ', 'Valeria', 'Gómez', 'Rodríguez', '678901234', 'valeria.gomez@example.com', 'Avenida Central 987', 36);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (37, '78901234KK', 'Sebastián', 'Martínez', 'Torres', '789012345', 'sebastian.martinez@example.com', 'Calle Principal 654', 37);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (38, '89012345LL', 'Julieta', 'Ruiz', 'Pérez', '890123456', 'julieta.ruiz@example.com', 'Avenida Central 321', 38);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (39, '90123456MM', 'Alejandro', 'Moreno', 'López', '901234567', 'alejandro.moreno@example.com', 'Calle Principal 654', 39);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (40, '01234567NN', 'Carolina', 'Gómez', 'Soto', '012345678', 'carolina.gomez@example.com', 'Avenida Central 987', 40);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (41, '12345678OO', 'Gabriel', 'Ramírez', 'Vega', '123456789', 'gabriel.ramirez@example.com', 'Calle Principal 321', 41);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (42, '23456789PP', 'Valentina', 'López', 'Morales', '234567890', 'valentina.lopez@example.com', 'Avenida Central 654', 42);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (43, '34567890QQ', 'Andrés', 'González', 'Santos', '345678901', 'andres.gonzalez@example.com', 'Calle Principal 987', 43);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (44, '45678901RR', 'Lucía', 'Hernández', 'Muñoz', '456789012', 'lucia.hernandez@example.com', 'Avenida Central 321', 44);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (45, '56789012SS', 'Diego', 'Sánchez', 'Fernández', '567890123', 'diego.sanchez@example.com', 'Calle Principal 654', 45);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (46, '67890123TT', 'Daniela', 'Rodríguez', 'Gómez', '678901234', 'daniela.rodriguez@example.com', 'Avenida Central 987', 46);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (47, '78901234UU', 'Maximiliano', 'Martínez', 'Torres', '789012345', 'maximiliano.martinez@example.com', 'Calle Principal 654', 47);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (48, '89012345VV', 'Martina', 'Ruiz', 'Pérez', '890123456', 'martina.ruiz@example.com', 'Avenida Central 321', 48);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (49, '90123456WW', 'Emilio', 'Moreno', 'López', '901234567', 'emilio.moreno@example.com', 'Calle Principal 654', 49);
INSERT INTO empleado (codigo, nif, nombre, primer_apellido, segundo_apellido, telefono, email, direccion, codigo_departamento)
VALUES (50, '01234567XX', 'Valeria', 'Gómez', 'Soto', '012345678', 'valeria.gomez@example.com', 'Avenida Central 987', 50);

------------------------------Tabla Usuario------------------------------------------------

INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(1,'Ivory Steele','0825 606 6324','phasellus.dolor@protonmail.edu',70,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(2,'Noelle Larsen','056 4291 1163','orci.lobortis@google.couk',80,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(3,'Arden Maxwell','056 9339 2395','quam.a@outlook.org',68,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(4,'Brendan Giles','0851 674 3774','a@outlook.net',79,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(5,'Cassady Leach','0800 721 2238','ut.ipsum@outlook.net',23,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(6,'Cameran Paul','(0171) 263 5611','amet.ante@icloud.net',80,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(7,'Georgia Torres','(0115) 865 5464','augue.porttitor@aol.net',75,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(8,'Orli Gonzalez','0500 161142','dis.parturient@icloud.couk',61,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(9,'Grady Dudley','076 9363 6043','et@protonmail.ca',71,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(10,'Jescie Gates','(01415) 521582','maecenas@protonmail.net',74,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(11,'Adrian Carlson','0845 46 42','eget.mollis@google.org',43,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(12,'Dexter Massey','(01479) 33011','tincidunt.tempus@aol.edu',56,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(13,'Courtney Page','0845 46 48','malesuada.vel@outlook.net',24,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(14,'Linus Sloan','0800 1111','non@outlook.net',26,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(15,'May Sampson','(01425) 588292','mus.proin@outlook.edu',42,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(16,'Nyssa Wilson','0845 46 47','vitae.diam@icloud.com',21,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(17,'Sybil Wynn','(0141) 589 1300','pede.ac.urna@icloud.com',58,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(18,'Wynne Hull','0845 46 43','eu.accumsan@google.couk',72,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(19,'Armando Simmons','0800 757296','mauris.aliquam@yahoo.edu',40,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(20,'Nevada Velez','0858 332 3719','aliquam@yahoo.net',68,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(21,'Geraldine Hudson','(0141) 921 2673','magna.suspendisse@google.com',67,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(22,'Hunter Lindsey','0845 46 42','est.mollis.non@hotmail.org',20,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(23,'Sylvester Cantrell','(026) 7237 5531','eu.tellus@icloud.ca',79,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(24,'Kylynn Bell','(01717) 56134','cras.eu@outlook.org',35,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(25,'Malachi Mejia','(015264) 61059','lorem.fringilla.ornare@icloud.com',29,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(26,'Fatima Bishop','(0111) 347 1454','praesent@icloud.couk',59,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(27,'Ryan Osborne','0845 46 41','ridiculus.mus@protonmail.com',60,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(28,'Irma Lynch','(0181) 770 8746','phasellus.nulla@google.ca',79,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(29,'Lilah Bradford','(027) 3363 1237','quam.a.felis@aol.couk',46,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(30,'Alexander Martin','0368 217 7705','metus.aliquam@yahoo.com',29,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(31,'Wynter Rivas','0845 46 45','enim.sed@aol.ca',33,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(32,'Roth Bryant','(020) 4533 6685','ut.nec.urna@hotmail.edu',24,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(33,'Hiram Vasquez','0800 686 6927','mi@aol.couk',49,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(34,'Demetrius Sexton','0845 46 42','mi@icloud.edu',27,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(35,'Xandra Nunez','056 1290 0121','quis.massa@hotmail.ca',29,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(36,'Ross Howard','055 9843 6585','pede.nunc.sed@yahoo.net',78,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(37,'Timothy Lara','055 3275 6178','sit.amet@aol.ca',74,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(38,'Suki Hicks','056 0733 4644','dolor.fusce@google.com',46,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(39,'Kevyn Jackson','07013 941965','nulla.cras@icloud.ca',17,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(40,'Lavinia Potts','0500 558369','magna.lorem.ipsum@google.ca',25,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(41,'Felicia Pierce','0800 239516','nunc@yahoo.edu',67,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(42,'Marvin Quinn','(016977) 1626','tempus.non.lacinia@yahoo.couk',17,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(43,'Imelda Mckenzie','056 7505 5686','dictum.phasellus@yahoo.edu',53,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(44,'Marny Knox','0826 906 2369','eleifend.nunc@aol.ca',39,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(45,'Chelsea Mcdonald','07624 018678','velit@outlook.couk',30,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(46,'Quinn Chan','(01627) 43766','facilisis.non@google.ca',37,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(47,'Shaine Chandler','(0181) 316 2273','erat.eget@hotmail.ca',63,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(48,'Cameron Lawson','0996 277 3972','ac.libero@icloud.com',65,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(49,'August Johnson','(016977) 1357','nunc.sollicitudin@protonmail.com',47,'United Kingdom');
INSERT INTO usuario (codigo,nombre,telefono,email,edad,nacionalidad)
VALUES(50,'George Burks','076 5223 1151','eu.dui.cum@google.net',54,'United Kingdom');

------------------------------------------Tabla Perfil-----------------------------------------------

INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  001  ,   'MovieMaster', 001);
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  002  ,  'BingeWatcher',002);
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  003  ,   'SeriesAddict',003);
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  004  ,   'Cinephile',004);
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  005  ,   'StreamGuru',005);
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  006  ,  'CinemaFanatic',006);
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  007  ,   'MovieMania',007);
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  008  ,   'ShowTime',008);
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  009  ,   'FilmGeek'  ,   009  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  010  ,   'SerialWatcher'  ,   010  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  011  ,   'StreamQueen'  ,   011  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  012  ,   'Cinephile21'  ,   012  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  013  ,   'BingeLover'  ,   013  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  014  ,   'FilmBuff'  ,   014  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  015  ,   'SeriesJunkie'  ,   015  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  016  ,   'MovieAddict'  ,   016  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  017  ,   'CinemaEnthusiast'  ,   017  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  018  ,   'StreamSavvy'  ,   018  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  019  ,   'ShowBinger'  ,   019  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  020  ,   'FilmFreak'  ,   020  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  021  ,   'MovieGuru'  ,   021  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  022  ,   'SeriesWhiz'  ,   022  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  023  ,   'CineMaster'  ,   023  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  024  ,   'StreamHolic'  ,   024  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  025  ,   'ShowAficionado'  ,   025  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  026  ,   'FilmFan'  ,   026  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  027  ,   'SerialBinger'  ,   027  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  028  ,   'MovieManiac'  ,   028  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  029  ,   'CinemaLover'  ,   029  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  030  ,   'StreamCraze'  ,   030  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  031  ,   'BingeWatcher23'  ,   031  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  032  ,   'FilmFollower'  ,   032  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  033  ,   'SeriesLover'  ,   033  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  034  ,   'MovieFanatic'  ,   034  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  035  ,   'Cinephile12'  ,   035  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  036  ,   'StreamSage'  ,   036  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  037  ,   'ShowBinge'  ,   037  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  038  ,   'FilmDevotee'  ,   038  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  039  ,   'SeriesAddict9'  ,   039  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  040  ,   'MovieEnthusiast'  ,   040  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  041  ,   'CinemaGeek'  ,   041  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  042  ,   'StreamChampion'  ,   042  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  043  ,   'BingeLover88'  ,   043  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  044  ,   'FilmCritic'  ,   044  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  045  ,   'SeriesFan ' ,   045  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  046  ,   'BingeMaster'  ,   046  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  047  ,   'FilmFiesta'  ,   047  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  048  ,   'StreamWizard'  ,   048  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  049  ,   'CineSquad'  ,   049  );
INSERT INTO PERFIL (codigoPerfil, nombre, codigoUsuario) VALUES (  050  ,   'MovieMagic'  ,   050  );

-----------------------------------Tabla Tarjeta----------------------------------------------------

INSERT INTO tarjeta (numero, caducidad, titular, cvv, banco)
VALUES 
(4929669928335, '04/10/2026', 'Aristotle Benson', 223, 'Tincidunt Aliquam Arcu Incorporated'),
(4494788659476876, '05/12/2027', 'Patricia Forbes', 703, 'Mauris Ut PC'),
(4916949732470, '20/01/2026', 'Francesca Nieves', 419, 'Enim Corp.'),
(4716535857454359, '20/05/2026', 'Penelope Booker', 241, 'Integer Tincidunt Consulting'),
(4539978918653194, '29/08/2027', 'Ciara Solis', 590, 'Suspendisse Ac Metus Company'),
(4916426294390, '14/01/2025', 'Simone Boyd', 550, 'Luctus LLC'),
(4539848775323, '19/11/2027', 'Jenette Hooper', 891, 'Cras LLP'),
(4024007189323576, '04/11/2023', 'Nigel Bray', 442, 'Sociosqu Ad Litora Limited'),
(4716441163384, '12/03/2028', 'Kiayada Oneal', 148, 'Fusce Mi Lorem Limited'),
(4485962453373578, '12/01/2025', 'Brendan Travis', 258, 'Placerat Velit Inc.'),
(4532572769598884, '10/07/2025', 'Scarlett Huber', 133, 'Accumsan Limited'),
(4539517745739121, '06/01/2025', 'Abel Cobb', 844, 'Gravida Sagittis PC'),
(4929271336747766, '19/02/2026', 'Bianca Carpenter', 981, 'Magnis Dis Inc.'),
(4916728898732, '12/07/2025', 'Raya Willis', 802, 'Et Nunc Quisque Company'),
(4916285254872153, '23/02/2026', 'Hedwig Boyer', 256, 'Lorem Lorem Inc.'),
(4481531365178252, '05/03/2026', 'Leonard Owen', 862, 'Non Limited'),
(4916525649832721, '18/02/2024', 'Deborah Martin', 585, 'Gravida Nunc Corporation'),
(4716787785724367, '06/07/2023', 'Grant Golden', 201, 'Vitae Industries'),
(4024007144745756, '06/01/2027', 'Colt Mann', 803, 'Parturient Montes Corp.'),
(4024007137172331, '13/11/2027', 'Davis Sherman', 730, 'Aenean Inc.'),
(4539345882716235, '19/02/2027', 'Todd Gardner', 317, 'Arcu Vivamus Sit Inc.'),
(4916388376516, '03/12/2026', 'Ursula Gomez', 540, 'Erat Neque Inc.'),
(4556429432359262, '21/09/2025', 'Jana Curry', 113, 'Ac Risus Morbi Corporation'),
(4916298886633661, '18/07/2025', 'Adara Mitchell', 232, 'Ultricies Sem PC'),
(4929752545752549, '21/11/2025', 'Thor Marsh', 625, 'Ac Ipsum Corp.'),
(4916747845325645, '29/09/2026', 'Nina Saunders', 669, 'Ut Tincidunt Vehicula Incorporated'),
(4539444611754219, '27/11/2023', 'Ifeoma Guthrie', 593, 'A Purus Duis Corporation'),
(4556395928647595, '12/11/2026', 'Damon Flynn', 605, 'Non Enim Industries'),
(4024007153678971, '27/01/2027', 'Elijah Moon', 497, 'Commodo Hendrerit PC'),
(4539266694248367, '06/03/2027', 'Benedict Dejesus', 227, 'Magna Ut Tincidunt Associates'),
(4929663725570, '02/02/2026', 'Abraham Guy', 925, 'Auctor Incorporated'),
(4539926955926858, '25/06/2025', 'Anthony Michael', 411, 'Nulla Interdum PC'),
(4532667385347664, '11/07/2024', 'Salvador Castro', 265, 'Id Industries'),
(4929527568832232, '19/04/2025', 'Abra Rhodes', 754, 'Vitae Erat Corporation'),
(4539321732668, '26/05/2027', 'Cassidy Booth', 375, 'Semper Erat Inc.'),
(4024007156766898, '02/09/2025', 'Xandra Erickson', 901, 'Pharetra Incorporated'),
(4835845278437875, '28/10/2026', 'Ray Edwards', 157, 'Pellentesque Eget Institute'),
(4532737546287982, '01/08/2026', 'Martina Ray', 634, 'Ut Molestie PC'),
(4532583743747, '28/01/2028', 'Uriel Sherman', 264, 'Proin Institute'),
(4929137487427633, '16/04/2025', 'Florence Rogers', 335, 'Nunc Sed Incorporated'),
(4485488756399, '14/04/2024', 'Ella Harding', 322, 'Consectetuer Adipiscing Elit Inc.'),
(4929334335767, '16/10/2024', 'Flavia Ellison', 375, 'Nulla PC'),
(402400715314955, '15/09/2024', 'Beau Briggs', 704, 'Phasellus Nulla PC'),
(4024007137448343, '29/03/2027', 'Drake ONeill', 320, 'Morbi Sit Corp.'),
(4929468755312490, '14/10/2024', 'Stacy Valencia', 139, 'Libero Lacus Varius Foundation'),
(4539877488566, '05/07/2025', 'Troy Gray', 979, 'Pellentesque A PC'),
(4024007189337725, '17/12/2024', 'Fiona Nunez', 421, 'Nisl Nulla Corp.'),
(4485415865774339, '08/08/2023', 'Hiram Cochran', 291, 'Feugiat Limited'),
(4556256742854587, '13/08/2023', 'Evangeline Welch', 253, 'Orci Foundation'),
(4716557363356, '18/07/2025', 'Eleanor Baxter', 538, 'Tincidunt Pede Incorporated');

-------------------------------Tabla tarjetaUsuario------------------------------------------------------------------

INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (1, 4929669928335);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (2, 4494788659476876);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (3, 4916949732470);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (4, 4716535857454359);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (5, 4539978918653194);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (6, 4916426294390);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (7, 4539848775323);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (8, 4024007189323576);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (9, 4716441163384);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (10, 4485962453373578);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (11, 4532572769598884);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (12, 4539517745739121);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (13, 4929271336747766);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (14, 4916728898732);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (15, 4916285254872153);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (16, 4481531365178252);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (17, 4916525649832721);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (18, 4716787785724367);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (19, 4024007144745756);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (20, 4024007137172331);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (21, 4539345882716235);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (22, 4916388376516);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (23, 4556429432359262);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (24, 4916298886633661);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (25, 4929752545752549);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (26, 4916747845325645);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (27, 4539444611754219);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (28, 4556395928647595);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (29, 4024007153678971);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (30, 4539266694248367);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (31, 4929663725570);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (32, 4539926955926858);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (33, 4532667385347664);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (34, 4929527568832232);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (35, 4539321732668);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (36, 4024007156766898);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (37, 4835845278437875);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (38, 4532737546287982);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (39, 4532583743747);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (40, 4929137487427633);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (41, 4485488756399);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (42, 4929334335767);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (43, 402400715314955);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (44, 4024007137448343);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (45, 4929468755312490);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (46, 4539877488566);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (47, 4024007189337725);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (48, 4485415865774339);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (49, 4556256742854587);
INSERT INTO tarjetaUsuario (idUsuario, NumTarjeta) VALUES (50, 4716557363356);

-------------------------------Tabla UsuarioSuscripción--------------------------------------------------------------

INSERT INTO USUARIOSUSCRIPCION (id_usuario, id_suscripcion, fecha_pago)
VALUES(1, 3, '01/05/2023'),
(2, 1, '15/04/2023'),
(3, 2, '10/05/2023'),
(4, 3, '05/05/2023'),
(5, 2, '20/04/2023'),
(6, 1, '02/05/2023'),
(7, 3, '15/05/2023'),
(8, 2, '28/04/2023'),
(9, 1, '12/05/2023'),
(10, 3, '03/05/2023'),
(11, 2, '18/04/2023'),
(12, 1, '09/05/2023'),
(13, 3, '14/05/2023'),
(14, 2, '25/04/2023'),
(15, 1, '07/05/2023'),
(16, 3, '04/05/2023'),
(17, 2, '22/04/2023'),
(18, 1, '11/05/2023'),
(19, 3, '06/05/2023'),
(20, 2, '30/04/2023'),
(21, 1, '13/05/2023'),
(22, 3, '08/05/2023'),
(23, 2, '23/04/2023'),
(24, 1, '05/05/2023'),
(25, 3, '16/05/2023'),
(26, 2, '26/04/2023'),
(27, 1, '14/05/2023'),
(28, 3, '02/05/2023'),
(29, 2, '19/04/2023'),
(30, 1, '10/05/2023'),
(31, 3, '07/05/2023'),
(32, 2, '24/04/2023'),
(33, 1, '15/05/2023'),
(34, 3, '09/05/2023'),
(35, 2, '29/04/2023'),
(36, 1, '16/05/2023'),
(37, 3, '03/05/2023'),
(38, 2, '21/04/2023'),
(39, 1, '12/05/2023'),
(40, 3, '01/05/2023'),
(41, 2, '27/04/2023'),
(42, 1, '13/05/2023'),
(43, 3, '04/05/2023'),
(44, 2, '16/04/2023'),
(45, 1, '11/05/2023'),
(46, 3, '06/05/2023'),
(47, 2, '17/04/2023'),
(48, 1, '08/05/2023'),
(49, 3, '05/05/2023'),
(50, 2, '18/04/2023');

----------------------------------Tabla Visualizar-----------------------------------------

INSERT INTO VISUALIZAR (codigoUsuario, codigoContenido, fechaStream, duracion, valoracion)
VALUES(1, 3, '01/05/2023', '00:25:37', 4.5),
(2, 1, '15/04/2023', '00:12:45', 3.8),
(3, 2, '10/05/2023', '00:42:19', 4.2),
(4, 3, '05/05/2023', '00:31:02', 4.0),
(5, 2, '20/04/2023', '00:18:59', 3.5),
(6, 1, '02/05/2023', '00:37:28', 4.7),
(7, 3, '15/05/2023', '00:23:10', 4.3),
(8, 2, '28/04/2023', '00:29:45', 4.1),
(9, 1, '12/05/2023', '00:14:52', 3.9),
(10, 3, '03/05/2023', '00:41:18', 4.4),
(11, 2, '18/04/2023', '00:27:05', 4.2),
(12, 1, '09/05/2023', '00:36:11', 4.6),
(13, 3, '14/05/2023', '00:21:48', 4.1),
(14, 2, '25/04/2023', '00:19:33', 3.7),
(15, 1, '07/05/2023', '00:34:12', 4.5),
(16, 3, '04/05/2023', '00:39:27', 4.3),
(17, 2, '22/04/2023', '00:26:14', 4.0),
(18, 1, '11/05/2023', '00:35:29', 4.8),
(19, 3, '06/05/2023', '00:17:55', 3.9),
(20, 2, '30/04/2023', '00:23:47', 4.2),
(21, 1, '13/05/2023', '00:32:06', 4.6),
(22, 3, '08/05/2023', '00:19:21', 4.0),
(23, 2, '23/04/2023', '00:28:34', 4.3),
(24, 1, '05/05/2023', '00:36:49', 4.7),
(25, 3, '16/05/2023', '00:22:57', 4.2),
(26, 2, '26/04/2023', '00:21:12', 3.6),
(27, 1, '14/05/2023', '00:33:23', 4.4),
(28, 3, '02/05/2023', '00:18:41', 4.1),
(29, 2, '19/04/2023', '00:26:28', 4.3),
(30, 1, '10/05/2023', '00:35:45', 4.7),
(31, 3, '07/05/2023', '00:22:06', 4.0),
(32, 2, '24/04/2023', '00:27:23', 4.5),
(33, 1, '15/05/2023', '00:34:37', 4.8),
(34, 3, '09/05/2023', '00:19:54', 4.2),
(35, 2, '29/04/2023', '00:25:21', 4.1),
(36, 1, '16/05/2023', '00:33:38', 4.6),
(37, 3, '03/05/2023', '00:20:49', 4.0),
(38, 2, '21/04/2023', '00:28:56', 4.4),
(39, 1, '12/05/2023', '00:37:03', 4.8),
(40, 3, '01/05/2023', '00:24:17', 4.3),
(41, 2, '27/04/2023', '00:23:28', 3.9),
(42, 1, '13/05/2023', '00:32:45', 4.5),
(43, 3, '04/05/2023', '00:18:02', 4.2),
(44, 2, '16/04/2023', '00:26:39', 4.1),
(45, 1, '11/05/2023', '00:35:56', 4.7),
(46, 3, '06/05/2023', '00:17:13', 3.8),
(47, 2, '17/04/2023', '00:24:30', 4.2),
(48, 1, '08/05/2023', '00:33:46', 4.6),
(49, 3, '05/05/2023', '00:23:03', 4.1),
(50, 2, '18/04/2023', '00:29:20', 4.4);

------------------------------Tabla Documental----------------------------------------

INSERT INTO DOCUMENTAL( codigo,tipoContenido, director) VALUES
(3,'documental',3),
(8,'documental',8),
(14,'documental',14),
(21,'documental',21),
(26,'documental',26),
(29,'documental',29),
(41,'documental',41),
(42,'documental',42),
(45,'documental',45),
(47,'documental',47);

----------------------------Tabla Pelicula-----------------------------------------------

INSERT INTO PELICULA( codigoPelicula, director,tipoContenido)
 VALUES

(4,4,'pelicula'),
(6,6,'pelicula'),
(10,10,'pelicula'),
(12,12,'pelicula'),
(16,16,'pelicula'),
(18,18,'pelicula'),
(20,20,'pelicula'),
(23,23,'pelicula'),
(25,25,'pelicula'),
(27,27,'pelicula'),
(48,48,'pelicula');

--------------------------------Tabla Serie-----------------------------------------------

INSERT INTO SERIE (codigo, director,tipoContenido)
VALUES
(2,2,'serie'),
(5,5,'serie'),
(7,7,'serie'),
(9,9,'serie'),
(11,11,'serie'),
(13,13,'serie'),
(15,15,'serie'),
(17,17,'serie'),
(19,19,'serie'),
(22,22,'serie'),
(24,24,'serie'),
(28,28,'serie'),
(30,30,'serie'),
(43,43,'serie'),
(44,44,'serie'),
(46,46,'serie'),
(49,49,'serie');

--------------------------------Tabla Temporada---------------------------------------------


INSERT INTO temporada(numero, resumen, serie)
VALUES (1, 'Walter White, un profesor de química de secundaria, se adentra en el mundo del narcotráfico después de ser diagnosticado con cáncer terminal. Se asocia con un exestudiante, Jesse Pinkman, y juntos comienzan a fabricar y vender metanfetaminas.', 2),
(2, 'Walter y Jesse enfrentan diversos obstáculos mientras intentan expandir su imperio de metanfetaminas. Son perseguidos por el implacable narcotraficante Tuco Salamanca y deben lidiar con la creciente sospecha de sus seres queridos.', 2),
(3, 'Walter se encuentra cada vez más inmerso en el peligroso mundo del crimen organizado. Mientras tanto, su relación con Skyler se vuelve tensa y Jesse enfrenta las consecuencias de sus acciones pasadas. El narcotraficante Gus Fring emerge como una amenaza.', 2),
(1, 'En la pequeña ciudad de Hawkins, un niño desaparece misteriosamente. Sus amigos y una niña con habilidades sobrenaturales se unen para encontrarlo, enfrentándose a un mundo paralelo lleno de peligros.', 5),
(1, 'Un misterioso hombre conocido como "El Profesor" recluta a ocho personas con habilidades especiales para llevar a cabo un ambicioso atraco a la Fábrica Nacional de Moneda y Timbre de España.', 7),
(2, 'El atraco a la Fábrica Nacional de Moneda y Timbre continúa mientras los rehenes y los atracadores enfrentan situaciones cada vez más peligrosas. La policía se acerca cada vez más al descubrir la identidad de El Profesor.', 7),
(3, 'Después de un tiempo de paz, El Profesor se ve obligado a reunir a su equipo nuevamente cuando uno de ellos es capturado. Mientras tanto, un nuevo enemigo amenaza con arruinar el plan original del atraco.', 7),
(4, 'El atraco a la Fábrica Nacional de Moneda y Timbre se complica aún más cuando el grupo se enfrenta a la policía y a un ejército privado. Los secretos y las traiciones ponen en peligro el éxito de su plan.', 7),
(5, 'La banda se enfrenta a su atraco más grande hasta ahora: asaltar el Banco de España. Mientras tanto, la inspectora Sierra se convierte en una amenaza formidable para El Profesor y su equipo.', 7),
(1, 'Esta temporada presenta episodios independientes que exploran temas relacionados con la tecnología y su impacto en la sociedad. Cada episodio muestra una historia única y distópica.', 9),
(2, 'La segunda temporada sigue explorando las repercusiones de la tecnología en la vida cotidiana. Los episodios examinan temas como la privacidad, el control social y la manipulación de la realidad.', 9),
(3, 'En la tercera temporada, "Black Mirror" continúa explorando los aspectos oscuros de la tecnología y su influencia en el futuro. Los episodios exploran temas como la inteligencia artificial, la realidad virtual y la vigilancia.', 9),
(4, 'La cuarta temporada sigue presentando episodios independientes que abordan la relación entre la tecnología y la sociedad. Los temas explorados incluyen la identidad digital, los avances médicos y las relaciones interpersonales.', 9),
(1, 'La lucha por el Trono de Hierro se intensifica mientras las familias nobles de los Siete Reinos se enfrentan entre sí en una guerra política y militar. Además, se acerca el invierno y se despiertan fuerzas misteriosas.', 11),
(2, 'La guerra de los reinos continúa mientras los personajes luchan por el poder y la supervivencia. El invierno se acerca y las criaturas legendarias amenazan con invadir los Siete Reinos.', 11),
(1, 'La primera temporada sigue las vidas de seis amigos en la ciudad de Nueva York mientras navegan por el amor, el trabajo y la amistad. Juntos enfrentan desafíos, se apoyan mutuamente y experimentan divertidas situaciones.', 13),
(2, 'En la segunda temporada, las aventuras de los amigos continúan. Experimentan cambios en sus relaciones, carreras y vidas personales. La comedia y el drama se entrelazan en su vida cotidiana.', 13),
(3, 'En la tercera temporada, los amigos enfrentan nuevos desafíos y situaciones hilarantes. Desde romances complicados hasta situaciones laborales complicadas, la amistad y el humor siguen siendo el núcleo de su vida.', 13),
(1, 'Un año después de los eventos de la primera temporada, los habitantes de Hawkins enfrentan una nueva amenaza proveniente del Upside Down. Mientras tanto, Will Byers lucha por recuperarse de su experiencia traumática.', 15),
(1, 'La situación se vuelve cada vez más peligrosa para Walter White y su socio Jesse Pinkman. Mientras intentan expandir su imperio de metanfetaminas, se ven envueltos en conflictos con rivales y enfrentan amenazas tanto internas como externas.', 17),
(1, 'La primera temporada sigue al cazador de monstruos Geralt de Rivia mientras navega por un mundo lleno de magia, criaturas peligrosas y conflictos políticos. Su destino se entrelaza con el de la poderosa hechicera Yennefer y la princesa Ciri.', 19),
(2, 'En la segunda temporada, Geralt, Yennefer y Ciri continúan sus respectivos viajes y se enfrentan a nuevos desafíos. Se revelan secretos del pasado y las alianzas se ponen a prueba en un mundo donde la línea entre el bien y el mal es difusa.', 19),
(5, ' Walter White se encuentra cada vez más inmerso en el mundo del narcotráfico mientras lucha por mantener su imperio de metanfetaminas. Mientras tanto, la DEA y otros enemigos cercanos se acercan peligrosamente.', 22),
(1, 'La primera temporada sigue el día a día de los empleados de la sucursal de Scranton de la empresa Dunder Mifflin. Las dinámicas del lugar de trabajo, los conflictos y las situaciones cómicas son el enfoque principal.', 24),
(2, 'En la segunda temporada, las hilarantes interacciones entre los empleados continúan, mientras se desarrollan relaciones, surgen rivalidades y se exploran las personalidades excéntricas de los personajes.', 24),
(3, 'En la tercera temporada, los empleados de Dunder Mifflin enfrentan cambios en la empresa, nuevos desafíos laborales y complicaciones en sus vidas personales. El humor y las situaciones incómodas son una constante.', 24),
(4, 'La cuarta temporada continúa explorando la vida de los empleados de Dunder Mifflin, mientras se enfrentan a cambios en la empresa y nuevos retos. El humor absurdo y las dinámicas de oficina son el sello distintivo de la serie.', 24),
(1, 'Los habitantes de Hawkins se enfrentan a una nueva amenaza sobrenatural mientras descubren más secretos sobre el Upside Down y el pasado de la ciudad. Los lazos entre los personajes se fortalecen mientras luchan juntos contra fuerzas oscuras.', 28),
(1, 'Un nuevo y poderoso enemigo surge en Hawkins. Los niños protagonistas, ahora adolescentes, deben enfrentar mayores desafíos mientras descubren misterios más oscuros que nunca. Nuevas relaciones y alianzas se forman, y secretos del pasado son revelados, poniendo en peligro el equilibrio entre el mundo real y el Upside Down.', 30),
(1, 'Un nuevo y poderoso enemigo amenaza con destruir Hawkins y desencadenar el caos en el mundo. Los protagonistas, ahora adultos, deben unirse nuevamente para enfrentar esta amenaza sobrenatural. Secretos del pasado serán revelados y nuevas alianzas se formarán en esta épica batalla final.', 43),
(11, 'El destino de la ciudad y el mundo entero está en juego mientras luchan contra fuerzas oscuras y descubren la verdad detrás del Upside Down. Los personajes se enfrentan a decisiones difíciles y sacrificios personales en esta conclusión épica.', 46),
(1,'La única serie vista por Kiko referente mundial de la televisión, una familia de clase media con un  hijo malo, una hija lista y un bebe que se les olvida',44),
(1,'Final de la serie todo era un sueño del protagonista de los Serrano en coma', 49);

-------------------------------------------Tabla Episodio------------------------------------------------------

INSERT INTO EPISODIO (id_episodio, nombre, fecha_de_lanzamiento, duracion, breve_descripcion, id_temporada, id_Serie)
VALUES

(1, 'Piloto', '2000-06-02',1245,'inicio de la serie', 1,2),
(1, 'Piloto2', '2020-06-02',1345,'inicio de la serie', 1,5),
(1, 'Piloto3', '2018-06-02',1045,'inicio de la serie', 1,7),
(1, 'Piloto4', '2000-05-15',1450,'inicio de la serie', 1,9),
(1, 'Piloto5', '2020-12-05',1405,'inicio de la serie', 2,2),
(1, 'Piloto6', '2012-12-12',1453,'inicio de la serie',3,2),
(1, 'Piloto7', '2006-06-06',1458,'inicio de la serie',2,7),
(1, 'Piloto8', '2008-08-08',1005,'inicio de la serie', 3,7),
(1, 'Piloto9', '2021-09-02',1856,'inicio de la serie', 4,7),
(1, 'Piloto10', '2000-01-22',1458,'inicio de la serie', 5,7),
(1, 'Piloto11', '2020-04-04',1222,'inicio de la serie',2,9),
(1, 'Piloto12', '2008-06-01',1546,'inicio de la serie',3,9),
(1, 'Piloto13', '2020-06-02',1555,'inicio de la serie',4,9),
(1, 'Piloto14', '2023-06-03',1354,'inicio de la serie',1,11),
(1, 'Piloto15', '2009-06-04',1022,'inicio de la serie',2,11),
(1, 'Piloto16', '2003-06-05',1145,'inicio de la serie',1,13),
(1, 'Piloto17', '2005-06-06',1645,'inicio de la serie',2,13),
(1, 'Piloto18', '2008-06-07',1445,'inicio de la serie',3,13),
(1, 'Piloto19', '2020-06-08',1345,'inicio de la serie',1,15),
(1, 'Piloto20', '2021-06-09',1745,'inicio de la serie',1,17),
(1, 'Piloto21', '2022-01-10',1055,'inicio de la serie',1,19),
(1, 'Piloto22', '2001-02-11',1445,'inicio de la serie',2,19),
(1, 'Piloto23', '2002-03-12',1523,'inicio de la serie',5,22),
(1, 'Piloto24', '2003-04-13',1888,'inicio de la serie',1,24),
(1, 'Piloto25', '2004-05-14',1200,'inicio de la serie',2,24),
(1, 'Piloto26', '2005-07-15',1586,'inicio de la serie',3,24),
(1, 'Piloto27', '2006-08-16',1300,'inicio de la serie',4,24),
(1, 'Piloto28', '2007-09-17',1255,'inicio de la serie',1,28),
(1, 'Piloto29', '2008-10-18',1254,'inicio de la serie',1,30),
(1, 'Piloto30', '2010-06-19',1566,'inicio de la serie',1,43),
(1, 'Piloto34', '2012-06-23',1005,'inicio de la serie',1,49),
(1, 'Piloto33', '2012-06-23',1555,'inicio de la serie',1,44);