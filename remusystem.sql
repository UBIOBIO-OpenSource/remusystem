-- phpMyAdmin SQL Dump
-- version 3.5.1
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 23-07-2013 a las 16:02:40
-- Versión del servidor: 5.5.29
-- Versión de PHP: 5.3.20

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `remusystem`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `abonos`
--

CREATE TABLE IF NOT EXISTS `abonos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `numero_cuotas` int(11) DEFAULT NULL,
  `fecha_final` date DEFAULT NULL,
  `monto` int(11) DEFAULT NULL,
  `tipo_abono` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `agrupar`
--

CREATE TABLE IF NOT EXISTS `agrupar` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `id_grupo_hab_desc` int(11) DEFAULT NULL,
  `id_tipo_hab_desc` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_grupo_hab_desc` (`id_grupo_hab_desc`),
  KEY `id_tipo_hab_desc` (`id_tipo_hab_desc`),
  KEY `FKC17CF318919FDF42` (`id_grupo_hab_desc`),
  KEY `FKC17CF3186A5A9EF1` (`id_tipo_hab_desc`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion_familiar`
--

CREATE TABLE IF NOT EXISTS `asignacion_familiar` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `tramo` varchar(255) DEFAULT NULL,
  `monto` int(11) DEFAULT NULL,
  `desde` int(11) DEFAULT NULL,
  `hasta` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `asignacion_familiar`
--

INSERT INTO `asignacion_familiar` (`Id`, `tramo`, `monto`, `desde`, `hasta`) VALUES
(1, 'A', 7744, 0, 202516),
(2, 'B', 5221, 202516, 317407),
(3, 'C', 1650, 317407, 495047),
(4, 'D', 0, 495047, 99999999);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja_compensacion`
--

CREATE TABLE IF NOT EXISTS `caja_compensacion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `comision` decimal(10,5) DEFAULT NULL,
  `porcentaje_descuento` decimal(10,5) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `caja_compensacion`
--

INSERT INTO `caja_compensacion` (`Id`, `nombre`, `comision`, `porcentaje_descuento`) VALUES
(1, 'Los Andes', NULL, NULL),
(2, 'Caja 18', NULL, NULL),
(3, 'La Araucana', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_liquidacion`
--

CREATE TABLE IF NOT EXISTS `detalle_liquidacion` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(255) DEFAULT NULL,
  `monto` decimal(20,2) DEFAULT NULL,
  `posicion` int(255) DEFAULT NULL,
  `id_liquidacion_sueldo` int(11) DEFAULT NULL,
  `en_palabras` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_liquidacion_sueldo` (`id_liquidacion_sueldo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empresa`
--

CREATE TABLE IF NOT EXISTS `empresa` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `rut` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `giro` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `id_mutual` int(11) DEFAULT NULL,
  `id_caja_compensacion` int(11) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_mutual` (`id_mutual`),
  KEY `id_caja_compensacion` (`id_caja_compensacion`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `empresa`
--

INSERT INTO `empresa` (`Id`, `rut`, `nombre`, `giro`, `direccion`, `telefono`, `fax`, `email`, `id_mutual`, `id_caja_compensacion`, `id_usuario`) VALUES
(1, '22.222.222-2', 'Empresa de Prueba de remusystem', 'Comercio', 'Libertad 500, Chillán', '5555555', '5555555', 'empresa_de_prueba@remusystem.org', 3, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `grupo_hab_desc`
--

CREATE TABLE IF NOT EXISTS `grupo_hab_desc` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `grupo_hab_desc`
--

INSERT INTO `grupo_hab_desc` (`Id`, `cargo`) VALUES
(1, 'Auxiliar Contable'),
(4, 'Empleado Sucursal'),
(5, 'Gerente General'),
(6, 'Panadero'),
(7, 'Empleado'),
(8, 'Empleado Ingeniero Comercial');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `impuesto_segunda_categoria`
--

CREATE TABLE IF NOT EXISTS `impuesto_segunda_categoria` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `desde` decimal(10,3) DEFAULT NULL,
  `hasta` decimal(10,3) DEFAULT NULL,
  `factor` decimal(10,3) DEFAULT NULL,
  `rebaja` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `impuesto_segunda_categoria`
--

INSERT INTO `impuesto_segunda_categoria` (`Id`, `desde`, `hasta`, `factor`, `rebaja`) VALUES
(1, 0.000, 13.500, 0.000, 0.000),
(2, 13.500, 30.000, 0.040, 0.540),
(3, 30.000, 50.000, 0.080, 1.740),
(4, 50.000, 70.000, 0.135, 4.490),
(5, 70.000, 90.000, 0.230, 11.140),
(6, 90.000, 120.000, 0.304, 17.800),
(7, 120.000, 150.000, 0.355, 23.920),
(8, 150.000, 0.000, 0.400, 30.670);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `info_complementaria`
--

CREATE TABLE IF NOT EXISTS `info_complementaria` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `id_detalle_liquidacion` int(11) DEFAULT NULL,
  `base_de_calculo` decimal(20,5) DEFAULT NULL,
  `factor` decimal(20,5) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_detalle_liquidacion` (`id_detalle_liquidacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `institucion_prevision`
--

CREATE TABLE IF NOT EXISTS `institucion_prevision` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `comision` decimal(10,5) DEFAULT NULL,
  `porcentaje_descuento` decimal(10,5) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `institucion_prevision`
--

INSERT INTO `institucion_prevision` (`Id`, `nombre`, `comision`, `porcentaje_descuento`) VALUES
(1, 'Capital', 0.00000, 0.11440),
(2, 'Cuprum', 0.00000, 0.11480),
(3, 'Habitat', 0.00000, 0.11270),
(4, 'Plan Vital', 0.00000, 0.12360),
(5, 'Provida', 0.00000, 0.11540),
(6, 'Modelo', 0.00000, 0.11160),
(8, 'Nueva AFP', NULL, 0.15000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `institucion_salud`
--

CREATE TABLE IF NOT EXISTS `institucion_salud` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `comision` decimal(10,5) DEFAULT NULL,
  `porcentaje_descuento` decimal(10,5) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `institucion_salud`
--

INSERT INTO `institucion_salud` (`Id`, `nombre`, `comision`, `porcentaje_descuento`) VALUES
(1, 'FONASA', 0.00000, 0.07000),
(2, 'Banmedica', NULL, NULL),
(3, 'Consalud', NULL, NULL),
(4, 'Masvida', NULL, NULL),
(5, 'Colmena Golden Cross', NULL, NULL),
(6, 'Cruz Blanca', NULL, NULL),
(7, 'Vida Tres', NULL, NULL),
(8, 'Isapres Cruz del Norte', NULL, NULL),
(9, 'Rio Blanco', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `liquidacion_de_sueldo`
--

CREATE TABLE IF NOT EXISTS `liquidacion_de_sueldo` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `anio` varchar(255) DEFAULT NULL,
  `mes` varchar(255) DEFAULT NULL,
  `fecha_emision` date DEFAULT NULL,
  `fecha_pago` date DEFAULT NULL,
  `id_relacion_laboral` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_relacion_laboral` (`id_relacion_laboral`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mutual`
--

CREATE TABLE IF NOT EXISTS `mutual` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `comision` decimal(10,5) DEFAULT NULL,
  `porcentaje_descuento` decimal(10,5) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `mutual`
--

INSERT INTO `mutual` (`Id`, `nombre`, `comision`, `porcentaje_descuento`) VALUES
(1, 'Asociacion Chilena de Seguridad ACHS', NULL, NULL),
(2, 'Instituto de Seguridad del Trabajo', NULL, NULL),
(3, 'Mutual de Seguridad', NULL, NULL),
(4, 'Instituto de Seguridad Laboral', NULL, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `otros_descuentos`
--

CREATE TABLE IF NOT EXISTS `otros_descuentos` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `numer_cuotas` int(11) DEFAULT NULL,
  `fecha_final` date DEFAULT NULL,
  `monto` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `privilegio`
--

CREATE TABLE IF NOT EXISTS `privilegio` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `id_rol` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_rol` (`id_rol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `relacion_laboral`
--

CREATE TABLE IF NOT EXISTS `relacion_laboral` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_contrato` varchar(255) DEFAULT NULL,
  `fecha_inicio` date DEFAULT NULL,
  `fecha_fin` date DEFAULT NULL,
  `sueldo_base` int(11) DEFAULT NULL,
  `ruta_archivo_respaldo` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  `id_institucion_salud` int(11) DEFAULT NULL,
  `id_institucion_prevision` int(11) DEFAULT NULL,
  `id_grupo_hab_desc` int(11) DEFAULT NULL,
  `id_trabajador` int(11) DEFAULT NULL,
  `valor_plan_isapre` decimal(10,3) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_empresa` (`id_empresa`),
  KEY `id_institucion_salud` (`id_institucion_salud`),
  KEY `id_grupo_hab_desc` (`id_grupo_hab_desc`),
  KEY `id_trabajador` (`id_trabajador`),
  KEY `id_institucion_prevision` (`id_institucion_prevision`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `relacion_laboral`
--

INSERT INTO `relacion_laboral` (`Id`, `tipo_contrato`, `fecha_inicio`, `fecha_fin`, `sueldo_base`, `ruta_archivo_respaldo`, `estado`, `id_empresa`, `id_institucion_salud`, `id_institucion_prevision`, `id_grupo_hab_desc`, `id_trabajador`, `valor_plan_isapre`) VALUES
(1, 'indefinido', '2013-05-16', NULL, 193000, NULL, b'1', 1, 1, 1, 1, 1, 0.000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `rol`
--

CREATE TABLE IF NOT EXISTS `rol` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_usuario` (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `seguro_cesantia`
--

CREATE TABLE IF NOT EXISTS `seguro_cesantia` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_contrato` varchar(255) DEFAULT NULL,
  `empleador` decimal(10,2) DEFAULT NULL,
  `trabajador` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `seguro_cesantia`
--

INSERT INTO `seguro_cesantia` (`Id`, `tipo_contrato`, `empleador`, `trabajador`) VALUES
(1, 'Indefinido', 2.40, 0.60),
(2, 'Plazo Fijo', 3.00, NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud`
--

CREATE TABLE IF NOT EXISTS `solicitud` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `id_relacion_laboral` int(11) DEFAULT NULL,
  `id_otros_descuentos` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_otros_descuentos` (`id_otros_descuentos`),
  KEY `id_relacion_laboral` (`id_relacion_laboral`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `solicitud_abono`
--

CREATE TABLE IF NOT EXISTS `solicitud_abono` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `id_relacion_laboral` int(11) DEFAULT NULL,
  `id_abonos` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_relacion_laboral` (`id_relacion_laboral`),
  KEY `id_abonos` (`id_abonos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipo_haber_desc`
--

CREATE TABLE IF NOT EXISTS `tipo_haber_desc` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `posicion` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `topes`
--

CREATE TABLE IF NOT EXISTS `topes` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `monto_uf` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `topes`
--

INSERT INTO `topes` (`Id`, `nombre`, `monto_uf`) VALUES
(1, 'Tope Imponible', 67.40),
(2, 'Tope Seguro Cesantia', 101.10),
(3, 'Tope APV', 50.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `trabajador`
--

CREATE TABLE IF NOT EXISTS `trabajador` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `rut` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `apellido_paterno` varchar(255) DEFAULT NULL,
  `apellido_materno` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `sexo` bit(1) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `telefono_fijo` varchar(255) DEFAULT NULL,
  `celular` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `numero_cargas` int(11) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `trabajador`
--

INSERT INTO `trabajador` (`Id`, `rut`, `nombre`, `apellido_paterno`, `apellido_materno`, `fecha_nacimiento`, `nacionalidad`, `sexo`, `direccion`, `telefono_fijo`, `celular`, `email`, `numero_cargas`) VALUES
(1, '16.937.507-0', 'pato carlos', 'melo', 'asd', '1988-05-12', 'Chilena', b'1', 'asas 43', '8888888', '88888888', 'c@a.cl', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `rut` varchar(255) DEFAULT NULL,
  `contrasena` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) DEFAULT NULL,
  `apellido_paterno` varchar(255) DEFAULT NULL,
  `apellido_materno` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`Id`, `rut`, `contrasena`, `tipo`, `nombres`, `apellido_paterno`, `apellido_materno`, `email`) VALUES
(1, '11.111.111-1', 'admin2013', 'admin', 'Administrador de Sistema', NULL, NULL, NULL),
(2, '22.222.222-2', 'remusystem', 'empresa', 'Empresa de Prueba de remusystem', NULL, NULL, 'empresa_de_prueba@remusystem.org'),
(3, '16.937.507-0', '16.937.507-0', 'trabajador', 'pato carlos', 'melo', 'asd', 'c@a.cl');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valores`
--

CREATE TABLE IF NOT EXISTS `valores` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) DEFAULT NULL,
  `monto` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `valores`
--

INSERT INTO `valores` (`Id`, `Nombre`, `monto`) VALUES
(1, 'UF Mes Anterior', 22840.75),
(2, 'UF Mes Actual', 22807.54),
(3, 'UTM Mes Actual', 40125.00);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `agrupar`
--
ALTER TABLE `agrupar`
  ADD CONSTRAINT `FKC17CF3186A5A9EF1` FOREIGN KEY (`id_tipo_hab_desc`) REFERENCES `tipo_haber_desc` (`Id`),
  ADD CONSTRAINT `FKC17CF318919FDF42` FOREIGN KEY (`id_grupo_hab_desc`) REFERENCES `grupo_hab_desc` (`Id`);

--
-- Filtros para la tabla `detalle_liquidacion`
--
ALTER TABLE `detalle_liquidacion`
  ADD CONSTRAINT `detalle_liquidacion_ibfk_2` FOREIGN KEY (`id_liquidacion_sueldo`) REFERENCES `liquidacion_de_sueldo` (`Id`);

--
-- Filtros para la tabla `empresa`
--
ALTER TABLE `empresa`
  ADD CONSTRAINT `empresa_ibfk_1` FOREIGN KEY (`id_mutual`) REFERENCES `mutual` (`Id`),
  ADD CONSTRAINT `empresa_ibfk_2` FOREIGN KEY (`id_caja_compensacion`) REFERENCES `caja_compensacion` (`Id`),
  ADD CONSTRAINT `empresa_ibfk_3` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`Id`);

--
-- Filtros para la tabla `info_complementaria`
--
ALTER TABLE `info_complementaria`
  ADD CONSTRAINT `info_complementaria_ibfk_1` FOREIGN KEY (`id_detalle_liquidacion`) REFERENCES `detalle_liquidacion` (`Id`);

--
-- Filtros para la tabla `liquidacion_de_sueldo`
--
ALTER TABLE `liquidacion_de_sueldo`
  ADD CONSTRAINT `liquidacion_de_sueldo_ibfk_1` FOREIGN KEY (`id_relacion_laboral`) REFERENCES `relacion_laboral` (`Id`);

--
-- Filtros para la tabla `privilegio`
--
ALTER TABLE `privilegio`
  ADD CONSTRAINT `privilegio_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`Id`);

--
-- Filtros para la tabla `relacion_laboral`
--
ALTER TABLE `relacion_laboral`
  ADD CONSTRAINT `relacion_laboral_ibfk_1` FOREIGN KEY (`id_empresa`) REFERENCES `empresa` (`Id`),
  ADD CONSTRAINT `relacion_laboral_ibfk_2` FOREIGN KEY (`id_institucion_salud`) REFERENCES `institucion_salud` (`Id`),
  ADD CONSTRAINT `relacion_laboral_ibfk_3` FOREIGN KEY (`id_grupo_hab_desc`) REFERENCES `grupo_hab_desc` (`Id`),
  ADD CONSTRAINT `relacion_laboral_ibfk_4` FOREIGN KEY (`id_trabajador`) REFERENCES `trabajador` (`Id`),
  ADD CONSTRAINT `relacion_laboral_ibfk_5` FOREIGN KEY (`id_institucion_prevision`) REFERENCES `institucion_prevision` (`Id`);

--
-- Filtros para la tabla `rol`
--
ALTER TABLE `rol`
  ADD CONSTRAINT `rol_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`Id`);

--
-- Filtros para la tabla `solicitud`
--
ALTER TABLE `solicitud`
  ADD CONSTRAINT `solicitud_ibfk_1` FOREIGN KEY (`id_otros_descuentos`) REFERENCES `otros_descuentos` (`Id`),
  ADD CONSTRAINT `solicitud_ibfk_2` FOREIGN KEY (`id_relacion_laboral`) REFERENCES `relacion_laboral` (`Id`);

--
-- Filtros para la tabla `solicitud_abono`
--
ALTER TABLE `solicitud_abono`
  ADD CONSTRAINT `solicitud_abono_ibfk_1` FOREIGN KEY (`id_relacion_laboral`) REFERENCES `relacion_laboral` (`Id`),
  ADD CONSTRAINT `solicitud_abono_ibfk_2` FOREIGN KEY (`id_abonos`) REFERENCES `abonos` (`Id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
