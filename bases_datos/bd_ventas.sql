-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-02-2024 a las 16:47:47
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `IdCliente` int(11) UNSIGNED NOT NULL,
  `Dni` varchar(8) DEFAULT NULL,
  `Nombres` varchar(244) DEFAULT NULL,
  `Direccion` varchar(244) DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`IdCliente`, `Dni`, `Nombres`, `Direccion`, `Estado`) VALUES
(17, '2', 'Juan Guerrero Solis', 'Los Alamos', '1'),
(18, '1', 'Maria Rosas Villanueva', 'Los Laureles 234', '1'),
(19, '3', 'Andres de Santa Cruz', 'Av. La Frontera 347', '1'),
(20, '4', 'Andres Mendoza', 'Chosica, Lurigancho', '3'),
(22, '5', 'Juan Guerrero Solis', 'Los Alamos', '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

CREATE TABLE `detalle_ventas` (
  `IdDetalleVentas` int(11) UNSIGNED NOT NULL,
  `IdVentas` int(11) UNSIGNED NOT NULL,
  `IdProducto` int(11) UNSIGNED NOT NULL,
  `Cantidad` int(11) UNSIGNED DEFAULT NULL,
  `PrecioVenta` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `detalle_ventas`
--

INSERT INTO `detalle_ventas` (`IdDetalleVentas`, `IdVentas`, `IdProducto`, `Cantidad`, `PrecioVenta`) VALUES
(155, 95, 1, 3, 150),
(156, 96, 1, 3, 150),
(157, 97, 1, 3, 150),
(158, 97, 1, 1, 150),
(159, 98, 1, 3, 150),
(160, 98, 1, 3, 150),
(161, 98, 1, 3, 150),
(162, 98, 3, 3, 800),
(163, 99, 1, 1, 150),
(164, 100, 1, 1, 150),
(165, 101, 1, 1, 150),
(166, 102, 1, 1, 150),
(167, 102, 3, 1, 800),
(168, 103, 1, 1, 150),
(169, 103, 3, 1, 800),
(170, 104, 3, 1, 800),
(171, 105, 3, 1, 800),
(172, 106, 1, 1, 150),
(173, 106, 2, 1, 20),
(174, 107, 1, 1, 150),
(175, 107, 3, 1, 800),
(176, 108, 2, 1, 20),
(177, 109, 1, 1, 150),
(178, 109, 2, 1, 20),
(179, 110, 2, 1, 20),
(180, 111, 2, 1, 20),
(181, 111, 1, 3, 150),
(182, 112, 2, 3, 20),
(183, 112, 1, 3, 150),
(184, 113, 2, 1, 20),
(185, 114, 2, 3, 20),
(186, 115, 1, 1, 150),
(187, 116, 1, 1, 150),
(188, 116, 2, 3, 20),
(189, 117, 1, 4, 150);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `IdEmpleado` int(10) UNSIGNED NOT NULL,
  `Dni` varchar(8) NOT NULL,
  `Nombres` varchar(255) DEFAULT NULL,
  `Telefono` varchar(9) DEFAULT NULL,
  `Estado` int(100) DEFAULT NULL,
  `User` varchar(8) DEFAULT NULL,
  `contra` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`IdEmpleado`, `Dni`, `Nombres`, `Telefono`, `Estado`, `User`, `contra`) VALUES
(1, '123', 'Pedro Hernandez', '988252459', 1, 'emp01', '123'),
(2, '123', 'Roman Riquelme', '988252459', 1, 'Jo46', '123'),
(3, '123', 'Palermo Suarez', '453536458', 1, 'Em22', '123'),
(8, '123', 'Pedro Hernandez', '988252459', 1, 'emp01', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estado_emp`
--

CREATE TABLE `estado_emp` (
  `id_est` int(11) NOT NULL,
  `estado` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `estado_emp`
--

INSERT INTO `estado_emp` (`id_est`, `estado`) VALUES
(1, 'Activo'),
(2, 'En vaciones'),
(3, 'Despedido'),
(4, 'Incapacitado'),
(5, 'Despedido'),
(6, 'No disponible');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `IdProducto` int(11) UNSIGNED NOT NULL,
  `Nombres` varchar(244) DEFAULT NULL,
  `Precio` double DEFAULT NULL,
  `Stock` int(11) UNSIGNED DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`IdProducto`, `Nombres`, `Precio`, `Stock`, `Estado`) VALUES
(1, 'Teclado Logitech 345 Editado', 150, 76, '1'),
(2, 'Mouse Logitech 567', 20, 83, '1'),
(3, 'Laptop Lenovo Ideapad 520', 800, 91, '1'),
(4, 'HeadPhones Sony M333', 500, 98, '1'),
(7, 'Producto Nuevo w', 22, 35, '1');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `IdVentas` int(11) UNSIGNED NOT NULL,
  `IdCliente` int(11) UNSIGNED NOT NULL,
  `IdEmpleado` int(10) UNSIGNED NOT NULL,
  `NumeroSerie` varchar(244) DEFAULT NULL,
  `FechaVentas` date DEFAULT NULL,
  `Monto` double DEFAULT NULL,
  `Estado` varchar(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`IdVentas`, `IdCliente`, `IdEmpleado`, `NumeroSerie`, `FechaVentas`, `Monto`, `Estado`) VALUES
(95, 18, 2, '00000001', '2022-02-27', 450, '1'),
(96, 18, 2, '00000001', '2022-02-27', 450, '1'),
(97, 18, 2, '00000001', '2022-02-27', 600, '1'),
(98, 18, 2, '00000002', '2022-02-27', 450, '1'),
(99, 18, 2, '00000003', '2022-02-27', 150, '1'),
(100, 18, 2, '00000003', '2022-02-27', 150, '1'),
(101, 18, 2, NULL, '2022-02-27', 150, '1'),
(102, 18, 2, NULL, '2022-02-27', 950, '1'),
(103, 18, 2, '00000004', '2022-02-27', 950, '1'),
(104, 18, 2, '00000005', '2020-07-06', 800, '1'),
(105, 18, 2, '00000006', '2024-01-15', 800, '1'),
(106, 18, 2, '00000007', '2024-01-16', 170, '1'),
(107, 18, 2, '00000008', '2024-01-16', 950, '1'),
(108, 18, 2, '00000009', '2024-01-16', 20, '1'),
(109, 18, 2, '00000010', '2024-01-16', 170, '1'),
(110, 18, 2, '00000011', '2024-01-16', 40, '1'),
(111, 18, 2, '00000012', '2024-01-16', 940, '1'),
(112, 18, 2, '00000012', '2024-01-16', 1020, '1'),
(113, 18, 2, '00000013', '2024-01-16', 60, '1'),
(114, 18, 2, '00000013', '2024-01-16', 140, '1'),
(115, 18, 2, '00000014', '2024-01-16', 0, '1'),
(116, 18, 2, '00000014', '2024-01-16', 210, '1'),
(117, 18, 2, '00000015', '2024-01-16', 600, '1'),
(118, 18, 2, '00000016', '2024-01-17', 0, '1');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`IdCliente`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`IdDetalleVentas`,`IdVentas`,`IdProducto`),
  ADD KEY `Ventas_has_Producto_FKIndex1` (`IdVentas`),
  ADD KEY `Ventas_has_Producto_FKIndex2` (`IdProducto`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`IdEmpleado`),
  ADD KEY `Estado` (`Estado`);

--
-- Indices de la tabla `estado_emp`
--
ALTER TABLE `estado_emp`
  ADD PRIMARY KEY (`id_est`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`IdProducto`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`IdVentas`),
  ADD KEY `Ventas_FKIndex1` (`IdEmpleado`),
  ADD KEY `Ventas_FKIndex2` (`IdCliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `IdCliente` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `IdDetalleVentas` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=190;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `IdEmpleado` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `IdProducto` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `IdVentas` int(11) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=119;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD CONSTRAINT `detalle_ventas_ibfk_1` FOREIGN KEY (`IdVentas`) REFERENCES `ventas` (`IdVentas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `detalle_ventas_ibfk_2` FOREIGN KEY (`IdProducto`) REFERENCES `producto` (`IdProducto`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`IdEmpleado`) REFERENCES `empleado` (`IdEmpleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`IdCliente`) REFERENCES `cliente` (`IdCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
