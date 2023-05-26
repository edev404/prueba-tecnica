-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 26-05-2023 a las 14:09:48
-- Versión del servidor: 10.6.12-MariaDB-cll-lve
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `u681097678_inventarios_bd`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` binary(16) NOT NULL,
  `cantidad` decimal(30,2) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `disponible` bit(1) NOT NULL,
  `fechaCreacion` datetime DEFAULT NULL,
  `nombre` varchar(150) NOT NULL,
  `ultimaActualizacion` datetime DEFAULT NULL,
  `valorCompra` decimal(30,2) NOT NULL,
  `valorVenta` decimal(30,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `cantidad`, `codigo`, `disponible`, `fechaCreacion`, `nombre`, `ultimaActualizacion`, `valorCompra`, `valorVenta`) VALUES
(0x24def1a046b94d408acfe13889b8dd35, '15.00', 'ABC123', b'1', '2023-05-26 08:30:59', 'Producto 1', '2023-05-26 08:30:59', '50.25', '75.99'),
(0x9b072867141e4eae9fd85abc1733e12b, '20.00', 'GHI123', b'1', '2023-05-26 08:30:59', 'Producto 3', '2023-05-26 08:30:59', '20.25', '50.99'),
(0xb64ae6a488fe498385aca976d9698264, '10.50', 'MNO123', b'1', '2023-05-26 08:31:00', 'Producto 5', '2023-05-26 08:31:00', '50.25', '75.99'),
(0xc78ebcd4c62a4ca0a756894a10f278ce, '10.00', 'DEF123', b'1', '2023-05-26 08:30:59', 'Producto 2', '2023-05-26 08:30:59', '40.20', '80.99'),
(0xebffcc4ccb664e6f882ad5320bf09e0d, '10.50', 'PQR123', b'1', '2023-05-26 08:31:00', 'Producto 6', '2023-05-26 08:31:00', '50.25', '75.99');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Token`
--

CREATE TABLE `Token` (
  `id` int(11) NOT NULL,
  `expired` bit(1) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `token` varchar(255) DEFAULT NULL,
  `tokenType` varchar(255) DEFAULT NULL,
  `idUsuario` binary(16) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `Token`
--

INSERT INTO `Token` (`id`, `expired`, `revoked`, `token`, `tokenType`, `idUsuario`) VALUES
(1, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbmluaXN0cmFkb3JAZW1wcmVzYS5jb20iLCJpYXQiOjE2ODUxMDc0MDcsImV4cCI6MTY4NTE1MDYwNywicm9sZSI6IlJPTEVfQURNSU5JU1RSQURPUiJ9.Rp6MdrlC4T5TcSu8BlvAdr8JaRpMekAQhSgj044oL7M', 'BEARER', 0x617718c9a10542819a1390a17c40630a),
(2, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbmluaXN0cmFkb3JAZW1wcmVzYS5jb20iLCJpYXQiOjE2ODUxMDc0MjEsImV4cCI6MTY4NTE1MDYyMSwicm9sZSI6IlJPTEVfQURNSU5JU1RSQURPUiJ9.LOff57hyivGZtBnQ8YkuvavQciPDqiOcfyLHweuNmhA', 'BEARER', 0x617718c9a10542819a1390a17c40630a),
(3, b'1', b'1', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtcHJlc2EuY29tIiwiaWF0IjoxNjg1MTA4ODYwLCJleHAiOjE2ODUxNTIwNjAsInJvbGUiOiJST0xFX1NVUEVSVklTT1IifQ.i6SES4-yfvzypWKuBDJ3BpLDBUHO-AfIusS2RAyC5wE', 'BEARER', 0x7082c495cb2549328a672636b2ec0c44),
(4, b'0', b'0', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0ZXN0QGVtcHJlc2EuY29tIiwiaWF0IjoxNjg1MTA5MDg4LCJleHAiOjE2ODUxNTIyODgsInJvbGUiOiJST0xFX1NVUEVSVklTT1IifQ.hQmXpU2wG_ZnOuUU7ehG2Szckq3tpJxoyfU4_XEdHxo', 'BEARER', 0x7082c495cb2549328a672636b2ec0c44);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` binary(16) NOT NULL,
  `apellido` varchar(75) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `creationDateTime` datetime DEFAULT NULL,
  `email` varchar(70) NOT NULL,
  `enabled` bit(1) DEFAULT b'1',
  `lastModified` datetime DEFAULT NULL,
  `nombre` varchar(75) NOT NULL,
  `password` varchar(250) NOT NULL,
  `telefono` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `apellido`, `authority`, `creationDateTime`, `email`, `enabled`, `lastModified`, `nombre`, `password`, `telefono`) VALUES
(0x617718c9a10542819a1390a17c40630a, 'principal', 'ROLE_ADMINISTRADOR', '2023-05-26 08:23:27', 'admininistrador@empresa.com', b'1', '2023-05-26 08:23:27', 'administrador', '$2a$10$BrOSH.V/JGapVGQVsncjb.DuYD6kv64i.SFiTsxt3M5xy/do2zrca', '3012116105'),
(0x7082c495cb2549328a672636b2ec0c44, 'test2', 'ROLE_SUPERVISOR', '2023-05-26 08:47:40', 'test@empresa.com', b'1', '2023-05-26 08:47:40', 'test', '$2a$10$aa9KZV4ai3FdMasvW0pr.eT1GPuff882qfqIyIwwCjjmAWL4trPqO', '3012116105');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `productos_unique` (`codigo`);

--
-- Indices de la tabla `Token`
--
ALTER TABLE `Token`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_3wi2t4g8oiplxjflw3o2lkv2y` (`token`),
  ADD KEY `FK56xvftgniyisi7gth8mmeeq7i` (`idUsuario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `usuarios_unique` (`email`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Token`
--
ALTER TABLE `Token`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Token`
--
ALTER TABLE `Token`
  ADD CONSTRAINT `FK56xvftgniyisi7gth8mmeeq7i` FOREIGN KEY (`idUsuario`) REFERENCES `usuarios` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
