USE MARCOSTV
GO

------------------PARTE RAQUEL-------------------

//***********************************************//

CREATE or ALTER FUNCTION valoracionContenido(@titulo VARCHAR(200))
RETURNS FLOAT
AS
BEGIN
	DECLARE @valoracion FLOAT
	SELECT @valoracion=valoracion FROM contenido WHERE titulo = @titulo
	RETURN @valoracion
END

DECLARE @valoracion FLOAT
SET @valoracion = dbo.valoracionContenido('El Padrino')
PRINT @valoracion

select * from contenido

//********************************************//

CREATE OR ALTER PROCEDURE InsertarContenido (@codigo INT, @titulo NVARCHAR(200), @descripcion NVARCHAR(MAX), @duracionSegundos INT, @valoracion FLOAT, @anyoLanzamiento DATE, @presupuesto MONEY,@edadRecomendada INT, @fechaAlta DATE)
AS
BEGIN
    INSERT INTO contenido (codigo, titulo, descripcion, duracionSegundos, valoracion, anyo_lanzamiento, presupuesto, edad_recomendada, fecha_alta)
    VALUES (@codigo, @titulo, @descripcion, @duracionSegundos, @valoracion, @anyoLanzamiento, @presupuesto, @edadRecomendada, @fechaAlta)
END

EXEC InsertarContenido 51,'Interestellar', 'Viajes en el teimpo', 140000, 5, '2013-01-01', 165000000, 14 , '2023-06-01'


//********************************************//

CREATE OR ALTER VIEW contenidoMasCaro
AS
	SELECT titulo, presupuesto FROM contenido 

SELECT TOP(3)* FROM dbo.contenidoMasCaro 



------------------PARTE KIKO--------------------



/* PROCEDURE KIKO */
CREATE OR ALTER PROCEDURE TarjetasCaducadas @idUsuario INT, @NumTarjetasCaducadas INT OUTPUT
AS
BEGIN
SET @NumTarjetasCaducadas = (SELECT COUNT(*) FROM tarjetaUsuario tu INNER JOIN tarjeta t ON tu.NumTarjeta = t.numero
WHERE tu.idUsuario = @idUsuario AND t.caducidad < GETDATE())
END

/* PRUEBA PROCEDURE KIKO */
DECLARE @idUsuario INT
SET @idUsuario = 4
DECLARE @NumTarjetasCaducadas INT
EXEC TarjetasCaducadas @idUsuario, @NumTarjetasCaducadas OUTPUT
PRINT 'Numero de tarjetas caducadas del cliente ' + CAST(@idUsuario AS NVARCHAR(100)) + ': ' + CAST(@NumTarjetasCaducadas AS NVARCHAR(100))

/* FUNCI�N KIKO */ 
CREATE OR ALTER FUNCTION VerTodoCategoria (@idCategoria INT)
RETURNS INT
AS
BEGIN
DECLARE @NumContCategoria INT
SET @NumContCategoria = (SELECT COUNT(*) FROM categoriacontenido WHERE @idCategoria = idCategoria)
RETURN @NumContCategoria
END

/* PRUEBA FUNCI�N KIKO */
DECLARE @NumCategorias INT
DECLARE @idCategoria INT
SET @idCategoria = 1
SET @NumCategorias = dbo.VerTodoCategoria(@idCategoria)
PRINT 'Cantidad de contenido con la categor�a ' + CAST(@idCategoria AS NVARCHAR(100)) + ': ' + CAST(@NumCategorias AS NVARCHAR(100))

/* VISTA KIKO */
CREATE OR ALTER VIEW SoloDirectorasyPremios
AS
SELECT nombre, apellidos, edad, numpremios FROM director WHERE sexo = '0'

/* PRUEBA VISTA KIKO */
SELECT * FROM dbo.SoloDirectorasyPremios



------------------PARTE TROY--------------------

-- PROCEDURES
CREATE OR ALTER PROCEDURE nombreDepartamento 
@codigo INT, @nombreDepartamento VARCHAR(25) OUTPUT
AS
	SELECT @nombreDepartamento = nombre
	FROM departamento
	WHERE codigo = @codigo

DECLARE @nombreDepartamento VARCHAR(25)
EXEC dbo.nombreDepartamento 1, @nombreDepartamento OUT
PRINT @nombreDepartamento

CREATE OR ALTER PROCEDURE newSesion @nif VARCHAR(9), @nombre VARCHAR(50), @contrasenya VARCHAR(50), @rol VARCHAR(10), @return INT OUTPUT
AS
IF (EXISTS (SELECT nif FROM empleado WHERE nif = @nif))
BEGIN
	DECLARE @codigo INT
	SELECT @codigo = codigo FROM empleado WHERE nif = @nif

	IF ((SELECT idSesion FROM empleado WHERE @codigo = codigo) IS NULL)
	BEGIN
		IF (EXISTS (SELECT * FROM Idsesion WHERE nombre = @nombre))
		BEGIN
			SET @return = -2
		END
		ELSE
		BEGIN
		INSERT INTO sesion(nombre, contrasenya, rol) VALUES (@nombre, @contrasenya, @rol)
		DECLARE @idSesion INT
		SELECT @idSesion = MAX(idSesion) FROM sesion
		UPDATE empleado SET idSesion = @idSesion WHERE codigo = @codigo
		SET @return = 1;
		END
	END
	ELSE
	BEGIN
		SET @return = 0;
	END
END
ELSE
BEGIN
	SET @return = -1;
END

-- FUNCTIONES
CREATE OR ALTER FUNCTION nombreCompletoEmpleado (@idEmpleado INT)
RETURNS VARCHAR(50)
AS
BEGIN
	DECLARE @nombreCompleto VARCHAR(50) 
	SELECT @nombreCompleto=(nombre + ' ' + primer_apellido + ' ' + segundo_apellido) 
	FROM empleado 
	WHERE codigo = @idEmpleado
	RETURN @nombreCompleto
END

DECLARE @nombreCompleto VARCHAR(50)
SET @nombreCompleto = dbo.nombreCompletoEmpleado(1)
PRINT @nombreCompleto

-- VIEWS
CREATE OR ALTER VIEW informacionLimitadaEmpleado
AS
	SELECT codigo, nif, (nombre + ' ' + primer_apellido + ' ' + segundo_apellido) AS 'Nombre Completo' from empleado


SELECT * FROM dbo.informacionLimitadaEmpleado


------------------PARTE CARLOS--------------------
-- Tablas Usuarios y suscripciones ---

-- Procedure para seleccionar la localizaci�n
CREATE PROCEDURE SeleccionLocalizacion  
@nacion VARCHAR(100)
AS
SELECT * FROM usuario WHERE nacionalidad = @nacion


-- Funci�n para ver si el mail est� disponible.
CREATE FUNCTION CompSameMail (@mail VARCHAR(50))
RETURNS BIT
AS
BEGIN
    DECLARE @existe BIT
	DECLARE @Contador int
	SET @Contador = (SELECT COUNT(email) FROM usuario WHERE email = @mail);

    IF (@Contador >= 1)
        SET @existe = 1 -- Existe el mail
    ELSE
        SET @existe = 0 -- No existe el mail

    RETURN @existe
END

--- Funci�n para comprobar caducidad suscripci�n
CREATE FUNCTION ComprobarCaducidad
(
    @FechaInicio datetime,
    @DuracionMeses int
)
RETURNS bit
AS
BEGIN
    DECLARE @FechCadu datetime
    SET @FechCadu = DATEADD(month, @DuracionMeses, @FechaInicio)

    IF GETDATE() > @FechCadu
        RETURN 1 -- La suscripci�n SI est� caducada
    ELSE
        RETURN 0 -- La suscripci�n NO est� caducada
END


--Funci�n para validar numero de telefono de muchos paises (Depende de la longitud del n�mero):
CREATE FUNCTION ValidarNumeroTelefono (@numtel VARCHAR(20))
RETURNS BIT
AS
BEGIN
    DECLARE @minimo INT = 7 -- N�mero m�nimo 
    DECLARE @maximo INT = 12 -- N�mero m�ximo

	--Longitudes del valor numero telefono
    IF LEN(@numtel) >= @minimo AND LEN(@numtel) <= @maximo
        RETURN 1 -- n�mero de d�gitos correcto
    ELSE
        RETURN 0 -- n�mero de d�gitos incorrecto
END


-- Views para ver usuarios que tengan x minimo de edad.

CREATE VIEW usuarios_mayores_30 AS
SELECT * FROM usuario WHERE edad >= 30;

CREATE VIEW usuarios_mayores_50 AS
SELECT * FROM usuario WHERE edad >= 50;

CREATE VIEW usuarios_mayores_18 AS
SELECT * FROM usuario WHERE edad >= 18;


