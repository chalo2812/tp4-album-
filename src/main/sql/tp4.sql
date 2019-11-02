SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "-03:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `tp4`
--
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `album`
--
CREATE DATABASE `tp4`;

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `artista`
--
CREATE TABLE `tp4`.`artista` (
  `NRO_ARTISTA` int(11) NOT NULL,
  `NOMBREARTISTA` varchar(100) NOT NULL,
  `NACIONALIDAD` varchar(30) NOT NULL,
  `GENERO_MUSICAL` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
--
-- Volcado de datos para la tabla `artista`
--
INSERT INTO `tp4`.`artista` (`NRO_ARTISTA`, `NOMBREARTISTA`, `NACIONALIDAD`, `GENERO_MUSICAL`) VALUES
(1, 'DIEGO TORRES', 'ARGENTINA', 'ROCK NACIONAL'),
(2, 'SHAKIRA', 'COLOMBIANA', 'POP');


CREATE TABLE `tp4`.`album` (
  `NRO_ALBUM` int(11) NOT NULL,
  `NRO_ARTISTA` int(11) NOT NULL,
  `FECHA_PUBLICACION` varchar(20) NOT NULL,
  `DISCOGRAFICA` varchar(30) NOT NULL,
  `NOMBRE_ALBUM` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `album`
--

INSERT INTO `tp4`.`album` (`NRO_ALBUM`, `NRO_ARTISTA`, `FECHA_PUBLICACION`, `DISCOGRAFICA`, `NOMBRE_ALBUM`) VALUES
(1, 2, '06/10/1995', 'EPIC RECORD', 'PIES DESCALZOS'),
(2, 0, '10/07/1997', 'RCA RECORD', 'TRATAR DE ESTAR MEJOR');

-- --------------------------------------------------------
--
-- Estructura de tabla para la tabla `tema`
--
CREATE TABLE `tp4`.`tema` (
  `NRO_TEMA` int(11) NOT NULL,
  `NRO_ALBUM` int(11) NOT NULL,
  `NRO_ARTISTA` int(11) NOT NULL,
  `DESCRIPCION` varchar(30) NOT NULL,
  `DURACION` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
INSERT INTO `tp4`.`tema` (`NRO_TEMA`, `NRO_ALBUM`, `NRO_ARTISTA`,`DESCRIPCION`, `DURACION`) VALUES 
('1', '1','2','Estoy aquí','5'),
('2', '1','2','¿Dónde estas, corazón?','5'),
('3', '1','2','Pies descalzos, sueños blancos','5'),
('4', '1','2','Un poco de amor','5'),
('5', '1','2','Antología','5'),
('6','1','2','Se quiere, se mata','5'),
('1','2','1','Tratar de estar mejor', '3'),
('2','2','1','Todo cambia (Y todo se termina)','5'),
('3','2','1','Dame una razón','4'),
('4','2','1','Aunque quieras','4'),
('5','2','1','Deja de pedir perdón', 	'3'),
('6','2','1','Te pido que vuelvas', 	'4'),
('7','2','1','Pensar (Que siempre hay alguien más)', 	'4'),
('8','2','1','San Salvador', 	'4'),
('9','2','1','Secretos del mar'	,'3'),
('10','2','1','Por la vereda del sol (Walking On Sunshine)',	'4');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `artista`
--
ALTER TABLE `tp4`.`artista`
  ADD KEY `NRO_ARTISTA` (`NRO_ARTISTA`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
