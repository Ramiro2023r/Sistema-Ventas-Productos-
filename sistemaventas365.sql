-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 24-10-2024 a las 01:19:42
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `sistemaventas365`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `abonos`
--

CREATE TABLE `abonos` (
  `id` int(11) NOT NULL,
  `abono` decimal(10,2) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `id_credito` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ajustes`
--

CREATE TABLE `ajustes` (
  `ID_AJUSTE` int(11) NOT NULL,
  `NOMBRE` varchar(50) NOT NULL,
  `VALOR` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ajustes`
--

INSERT INTO `ajustes` (`ID_AJUSTE`, `NOMBRE`, `VALOR`) VALUES
(1, 'KEY_PERSONAL_DENTISTA', '8'),
(2, 'KEY_CITA', '5'),
(3, 'KEY_PRE_CITA', '6'),
(4, 'APP_TITLE', 'Sistema clínica dental del pueblo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `apartados`
--

CREATE TABLE `apartados` (
  `id` int(11) NOT NULL,
  `fecha_apartado` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `fecha_retiro` datetime NOT NULL,
  `abono` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `color` varchar(15) NOT NULL DEFAULT '#ffa426',
  `estado` int(11) NOT NULL DEFAULT 1,
  `id_cliente` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `apartados`
--

INSERT INTO `apartados` (`id`, `fecha_apartado`, `fecha_retiro`, `abono`, `total`, `color`, `estado`, `id_cliente`) VALUES
(1, '2023-12-06 04:33:12', '2023-12-05 18:32:00', 60.00, 160.00, '#198754', 0, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `caja`
--

CREATE TABLE `caja` (
  `id` int(11) NOT NULL,
  `caja` varchar(50) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `caja`
--

INSERT INTO `caja` (`id`, `caja`, `fecha`, `estado`) VALUES
(1, 'GENERAL', '2022-05-28 21:18:55', 1),
(2, 'CAJA1', '2023-12-06 04:21:06', 2),
(3, 'CAJA4', '2023-12-06 04:21:11', 2),
(4, 'Caja5', '2024-10-14 00:00:00', 1),
(5, '6y5tggtrgtr', '2024-10-19 00:00:00', 1),
(6, 'Caja6 ', '2024-10-20 00:00:00', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias`
--

CREATE TABLE `categorias` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `categorias`
--

INSERT INTO `categorias` (`id`, `nombre`, `fecha`, `estado`) VALUES
(1, 'Celulares', '2022-10-11 23:59:22', 1),
(4, 'dcdcc', '2024-10-23 00:00:00', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cierre_caja`
--

CREATE TABLE `cierre_caja` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `monto_inicial` decimal(10,2) NOT NULL,
  `fecha_apertura` date NOT NULL,
  `fecha_cierre` date DEFAULT NULL,
  `monto_final` decimal(10,2) NOT NULL DEFAULT 0.00,
  `total_ventas` int(11) NOT NULL DEFAULT 0,
  `monto_total` decimal(10,2) NOT NULL DEFAULT 0.00,
  `estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cierre_caja`
--

INSERT INTO `cierre_caja` (`id`, `id_usuario`, `monto_inicial`, `fecha_apertura`, `fecha_cierre`, `monto_final`, `total_ventas`, `monto_total`, `estado`) VALUES
(1, 1, 500.00, '2022-10-11', NULL, 0.00, 0, 0.00, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(11) NOT NULL,
  `dni` varchar(10) DEFAULT NULL,
  `nombre` varchar(100) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `direccion` text NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `dni`, `nombre`, `telefono`, `direccion`, `fecha`, `estado`) VALUES
(1, '34545454', 'Ramiro Acosta', '676568867', 'LIMA PERU', '2022-10-29 01:28:01', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compras`
--

CREATE TABLE `compras` (
  `id` int(11) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `serie` int(11) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT 1,
  `metodo` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `compras`
--

INSERT INTO `compras` (`id`, `id_proveedor`, `id_usuario`, `total`, `fecha`, `hora`, `serie`, `estado`, `metodo`) VALUES
(1, 1, 1, 750.00, '2023-12-05', '23:27:33', 1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `configuracion`
--

CREATE TABLE `configuracion` (
  `id` int(11) NOT NULL,
  `ruc` varchar(20) NOT NULL,
  `nombre` varchar(200) NOT NULL,
  `telefono` varchar(15) NOT NULL,
  `correo` varchar(100) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `mensaje` text NOT NULL,
  `logo` varchar(10) NOT NULL,
  `moneda` int(11) NOT NULL,
  `impuesto` int(11) NOT NULL,
  `cant_factura` int(11) NOT NULL DEFAULT 0,
  `site` varchar(250) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `configuracion`
--

INSERT INTO `configuracion` (`id`, `ruc`, `nombre`, `telefono`, `correo`, `direccion`, `mensaje`, `logo`, `moneda`, `impuesto`, `cant_factura`, `site`) VALUES
(1, '6456665478', 'nombre de tu empresa', '900897537', 'yampier20es@gmail.com', 'LIMA - PERÚ - Lorem ipsum dolor sit amt.', '<p>GRACIAS POR LA PREFERENCIAs</p>', 'logo.png', 1, 18, 1000, 'tuweb.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cotizaciones`
--

CREATE TABLE `cotizaciones` (
  `id` int(11) NOT NULL,
  `productos` longtext NOT NULL,
  `total` decimal(10,2) NOT NULL DEFAULT 0.00,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `validez` varchar(50) NOT NULL DEFAULT '',
  `comentario` longtext DEFAULT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `cotizaciones`
--

INSERT INTO `cotizaciones` (`id`, `productos`, `total`, `fecha`, `hora`, `validez`, `comentario`, `id_cliente`, `id_usuario`) VALUES
(1, '[{\"id\":1,\"precio\":\"160.00\",\"cantidad\":1,\"nombre\":\"PRUEBA DEL PRODUCTO\",\"medida\":\"UND\",\"descuento\":\"0.00\",\"impuesto\":13},{\"id\":2,\"precio\":\"170.00\",\"cantidad\":1,\"nombre\":\"OTRO PRODCTO\",\"medida\":\"UND\",\"descuento\":\"0.00\",\"impuesto\":18},{\"id\":3,\"precio\":\"350.00\",\"cantidad\":1,\"nombre\":\"PRODEUCTO CON IGV 18\",\"medida\":\"UND\",\"descuento\":\"0.00\",\"impuesto\":11}]', 680.00, '2023-12-05', '23:29:39', '5 DIAS', 'gracias por la preferencia', 1, 1),
(2, '[{\"id\":1,\"precio\":\"160.00\",\"cantidad\":1,\"nombre\":\"PRUEBA DEL PRODUCTO\",\"medida\":\"UND\",\"descuento\":\"0.30\",\"impuesto\":18},{\"id\":3,\"precio\":\"350.00\",\"cantidad\":1,\"nombre\":\"PRODEUCTO CON IGV 18\",\"medida\":\"UND\",\"descuento\":\"0.10\",\"impuesto\":0},{\"id\":2,\"precio\":\"170.00\",\"cantidad\":1,\"nombre\":\"OTRO PRODCTO\",\"medida\":\"UND\",\"descuento\":\"0.30\",\"impuesto\":0}]', 679.30, '2023-12-16', '14:19:49', '5 DIAS', '', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `creditos`
--

CREATE TABLE `creditos` (
  `id` int(11) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` int(11) NOT NULL DEFAULT 1,
  `id_venta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle`
--

CREATE TABLE `detalle` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_apartados`
--

CREATE TABLE `detalle_apartados` (
  `id` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_apartado` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_apartados`
--

INSERT INTO `detalle_apartados` (`id`, `cantidad`, `precio`, `id_producto`, `id_apartado`) VALUES
(1, 1, 160.00, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_compras`
--

CREATE TABLE `detalle_compras` (
  `id` int(11) NOT NULL,
  `id_compra` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` decimal(10,2) NOT NULL,
  `precio` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_compras`
--

INSERT INTO `detalle_compras` (`id`, `id_compra`, `id_producto`, `cantidad`, `precio`) VALUES
(1, 1, 1, 5.00, 150.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_permisos`
--

CREATE TABLE `detalle_permisos` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_permiso` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_temp`
--

CREATE TABLE `detalle_temp` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_ventas`
--

CREATE TABLE `detalle_ventas` (
  `id` int(11) NOT NULL,
  `id_venta` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `cantidad` decimal(10,2) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `fecha` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `detalle_ventas`
--

INSERT INTO `detalle_ventas` (`id`, `id_venta`, `id_producto`, `cantidad`, `precio`, `fecha`) VALUES
(1, 1, 1, 1.00, 160.00, '2023-12-16'),
(2, 2, 1, 1.00, 160.00, '2023-12-16');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `inventario`
--

CREATE TABLE `inventario` (
  `id` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `entradas` decimal(10,2) NOT NULL DEFAULT 0.00,
  `fecha` date NOT NULL,
  `salidas` decimal(10,2) NOT NULL DEFAULT 0.00
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `inventario`
--

INSERT INTO `inventario` (`id`, `id_producto`, `id_usuario`, `entradas`, `fecha`, `salidas`) VALUES
(1, 1, 1, 5.00, '2023-12-05', 0.00),
(2, 1, 1, 0.00, '2023-12-16', 1.00),
(3, 1, 1, 0.00, '2023-12-16', 1.00);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `landing`
--

CREATE TABLE `landing` (
  `id` int(11) NOT NULL,
  `hora_registro` time NOT NULL,
  `fecha_registro` date NOT NULL,
  `pagina` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `nombre` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `telefono` varchar(14) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `correo` varchar(40) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `negocio` varchar(20) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `control` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `landing`
--

INSERT INTO `landing` (`id`, `hora_registro`, `fecha_registro`, `pagina`, `nombre`, `telefono`, `correo`, `negocio`, `control`) VALUES
(1, '23:23:20', '2023-12-05', 'MACRO INFORMATICA', 'YHAMPHIER EVANGELISTA', '92323232323', 'macroinformaticaedu@gmail.com', 'EMPRESA DE SOFTWARE', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medidas`
--

CREATE TABLE `medidas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `nombre_corto` varchar(5) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `medidas`
--

INSERT INTO `medidas` (`id`, `nombre`, `nombre_corto`, `fecha`, `estado`) VALUES
(1, 'Kilo', 'UND', '2022-10-11 23:59:40', 1),
(2, 'SET', 'SET', '2022-10-11 23:59:40', 1),
(3, 'CAJAS', 'CAJA', '2023-12-16 19:17:43', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `moneda`
--

CREATE TABLE `moneda` (
  `id` int(11) NOT NULL,
  `simbolo` varchar(10) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `moneda`
--

INSERT INTO `moneda` (`id`, `simbolo`, `nombre`, `fecha`, `estado`) VALUES
(1, 'BitCoin', 'Moneda Digital', '2022-05-28 21:18:27', 1),
(2, 'USD', 'DOLARES', '2023-12-06 04:18:22', 1),
(3, 'PESOS', '$$$', '2023-12-07 22:58:23', 1),
(7, 'ss', 'sss', '2024-10-23 07:38:05', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `permisos`
--

CREATE TABLE `permisos` (
  `id` int(11) NOT NULL,
  `permiso` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `permisos`
--

INSERT INTO `permisos` (`id`, `permiso`) VALUES
(1, 'crear_usuario'),
(2, 'modificar_usuario'),
(3, 'eliminar_usuario'),
(4, 'restaurar_usuario'),
(5, 'configuracion'),
(6, 'crear_moneda'),
(7, 'modificar_moneda'),
(8, 'eliminar_moneda'),
(9, 'crear_caja'),
(10, 'modificar_caja'),
(11, 'eliminar_caja'),
(12, 'restaurar_caja'),
(13, 'crear_cliente'),
(14, 'modificar_cliente'),
(15, 'eliminar_cliente'),
(16, 'restaurar_cliente'),
(17, 'crear_proveedor'),
(18, 'modificar_proveedor'),
(19, 'eliminar_proveedor'),
(20, 'restaurar_proveedor'),
(21, 'inventario'),
(22, 'crear_medida'),
(23, 'modificar_medida'),
(24, 'eliminar_medida'),
(25, 'restaurar_medida'),
(26, 'crear_categoria'),
(27, 'modificar_categoria'),
(28, 'eliminar_categoria'),
(29, 'restaurar_categoria'),
(30, 'crear_producto'),
(31, 'modificar_producto'),
(32, 'eliminar_producto'),
(33, 'restaurar_producto'),
(34, 'nueva_compra'),
(35, 'anular_compra'),
(36, 'reporte_compras'),
(37, 'nueva_venta'),
(38, 'anular_venta'),
(39, 'reporte_ventas'),
(40, 'reporte_pdf_inventario'),
(41, 'reporte_pdf_compras'),
(42, 'reporte_pdf_ventas'),
(43, 'abrir_caja'),
(44, 'cerrar_caja'),
(46, 'landing'),
(47, 'cotizaciones');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(11) NOT NULL,
  `codigo` varchar(20) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio_compra` decimal(10,2) NOT NULL,
  `precio_venta` decimal(10,2) NOT NULL,
  `cantidad` decimal(10,2) NOT NULL DEFAULT 0.00,
  `id_medida` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `foto` varchar(50) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT 1,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `codigo`, `descripcion`, `precio_compra`, `precio_venta`, `cantidad`, `id_medida`, `id_categoria`, `foto`, `estado`, `fecha`) VALUES
(1, '133444343', 'PRUEBA DEL PRODUCTO', 150.00, 160.00, 2.00, 1, 1, 'default.png', 1, '2023-12-16 19:22:30'),
(2, '56563564', 'OTRO PRODCTO', 123.00, 170.00, 0.00, 1, 1, 'default.png', 1, '2022-10-29 01:23:19'),
(3, '65454564', 'PRODEUCTO CON IGV 18', 300.00, 350.00, 0.00, 1, 1, 'default.png', 1, '2022-10-29 08:06:04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(11) NOT NULL,
  `ruc` varchar(20) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `telefono` varchar(20) NOT NULL,
  `direccion` varchar(255) NOT NULL,
  `estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `ruc`, `nombre`, `telefono`, `direccion`, `estado`) VALUES
(1, '987798944323', 'Angel Proveedor', '965874125', 'PERU - ANCASH', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temp_apartados`
--

CREATE TABLE `temp_apartados` (
  `id` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `temp_cotizaciones`
--

CREATE TABLE `temp_cotizaciones` (
  `id` int(11) NOT NULL,
  `precio` decimal(10,2) NOT NULL DEFAULT 0.00,
  `cantidad` int(11) NOT NULL,
  `medida` varchar(50) NOT NULL DEFAULT 'UND',
  `descuento` decimal(10,2) NOT NULL DEFAULT 0.00,
  `impuesto` int(11) NOT NULL DEFAULT 0,
  `id_producto` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `terminos`
--

CREATE TABLE `terminos` (
  `id` int(11) NOT NULL,
  `titulo` varchar(150) NOT NULL,
  `descripcion` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `terminos`
--

INSERT INTO `terminos` (`id`, `titulo`, `descripcion`) VALUES
(1, 'NUESTROS COSTOS', 'EXPRESADOS EN SOLES, PRECIOS UNITARIOS NO INCLUYEN IGV'),
(2, 'FORMA DE PAGO', 'AL CONTADO Y/O'),
(3, 'TIEMPO DE ENTREGA', ': DIAS HÁBILES DESPUES DE LA OC Y DEPOSITO EN CUENTA'),
(4, ' LUGAR DE ENTREGA', 'A'),
(5, ' DURACIÓN DEL HDPE', 'HASTA 20 AÑOS COMO MATERIAL'),
(6, 'GARANTÍA', 'HASTA 36 MESES POR');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) DEFAULT NULL,
  `email` varchar(80) NOT NULL,
  `telefono` varchar(20) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `perfil` varchar(50) NOT NULL DEFAULT 'avatar.svg',
  `password` varchar(100) NOT NULL,
  `id_caja` int(11) NOT NULL,
  `token` varchar(50) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `estado` int(11) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id`, `nombre`, `apellido`, `email`, `telefono`, `direccion`, `perfil`, `password`, `id_caja`, `token`, `fecha`, `estado`) VALUES
(1, 'SUPER ADMINISTRADOR', NULL, 'ramiro@gmail.com', NULL, NULL, 'avatar.svg', '$2a$10$qDBgWryN3PN8o3OQGVrw/uRZxqc0XKnhQdwxKNMBIQdRsTDTzqta2', 1, NULL, '2024-10-10 03:28:29', 1),
(2, 'EVANGELISTA', NULL, 'yampier56es@gmail.com', NULL, NULL, 'avatar.svg', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 1, NULL, '2023-12-06 04:19:53', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `estado` int(11) NOT NULL DEFAULT 1,
  `apertura` int(11) NOT NULL DEFAULT 1,
  `serie` int(11) NOT NULL DEFAULT 1,
  `metodo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `id_usuario`, `id_cliente`, `total`, `fecha`, `hora`, `estado`, `apertura`, `serie`, `metodo`) VALUES
(1, 1, 1, 160.00, '2023-12-16', '14:21:21', 1, 1, 1, 1),
(2, 1, 1, 160.00, '2023-12-16', '14:22:30', 1, 1, 1, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `abonos`
--
ALTER TABLE `abonos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ajustes`
--
ALTER TABLE `ajustes`
  ADD PRIMARY KEY (`ID_AJUSTE`),
  ADD UNIQUE KEY `IX_NOMBRE_SETTINGS` (`NOMBRE`) USING BTREE;

--
-- Indices de la tabla `apartados`
--
ALTER TABLE `apartados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `caja`
--
ALTER TABLE `caja`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `categorias`
--
ALTER TABLE `categorias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `cierre_caja`
--
ALTER TABLE `cierre_caja`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `compras`
--
ALTER TABLE `compras`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_proveedor` (`id_proveedor`);

--
-- Indices de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `moneda` (`moneda`);

--
-- Indices de la tabla `cotizaciones`
--
ALTER TABLE `cotizaciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `creditos`
--
ALTER TABLE `creditos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `detalle_apartados`
--
ALTER TABLE `detalle_apartados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `detalle_compras`
--
ALTER TABLE `detalle_compras`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_compra` (`id_compra`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `detalle_permisos`
--
ALTER TABLE `detalle_permisos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_permiso` (`id_permiso`);

--
-- Indices de la tabla `detalle_temp`
--
ALTER TABLE `detalle_temp`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_venta` (`id_venta`),
  ADD KEY `id_producto` (`id_producto`);

--
-- Indices de la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_producto` (`id_producto`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `landing`
--
ALTER TABLE `landing`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `medidas`
--
ALTER TABLE `medidas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `moneda`
--
ALTER TABLE `moneda`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `permisos`
--
ALTER TABLE `permisos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_medida` (`id_medida`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `temp_apartados`
--
ALTER TABLE `temp_apartados`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `temp_cotizaciones`
--
ALTER TABLE `temp_cotizaciones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `terminos`
--
ALTER TABLE `terminos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_caja` (`id_caja`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `abonos`
--
ALTER TABLE `abonos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `ajustes`
--
ALTER TABLE `ajustes`
  MODIFY `ID_AJUSTE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `apartados`
--
ALTER TABLE `apartados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `caja`
--
ALTER TABLE `caja`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `categorias`
--
ALTER TABLE `categorias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `cierre_caja`
--
ALTER TABLE `cierre_caja`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `compras`
--
ALTER TABLE `compras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `configuracion`
--
ALTER TABLE `configuracion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cotizaciones`
--
ALTER TABLE `cotizaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `creditos`
--
ALTER TABLE `creditos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalle`
--
ALTER TABLE `detalle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalle_apartados`
--
ALTER TABLE `detalle_apartados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalle_compras`
--
ALTER TABLE `detalle_compras`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `detalle_permisos`
--
ALTER TABLE `detalle_permisos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalle_temp`
--
ALTER TABLE `detalle_temp`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `inventario`
--
ALTER TABLE `inventario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `landing`
--
ALTER TABLE `landing`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `medidas`
--
ALTER TABLE `medidas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `moneda`
--
ALTER TABLE `moneda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `permisos`
--
ALTER TABLE `permisos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=48;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `temp_apartados`
--
ALTER TABLE `temp_apartados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `temp_cotizaciones`
--
ALTER TABLE `temp_cotizaciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `terminos`
--
ALTER TABLE `terminos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cierre_caja`
--
ALTER TABLE `cierre_caja`
  ADD CONSTRAINT `cierre_caja_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `compras`
--
ALTER TABLE `compras`
  ADD CONSTRAINT `compras_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `compras_ibfk_2` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `configuracion`
--
ALTER TABLE `configuracion`
  ADD CONSTRAINT `configuracion_ibfk_1` FOREIGN KEY (`moneda`) REFERENCES `moneda` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle`
--
ALTER TABLE `detalle`
  ADD CONSTRAINT `detalle_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_compras`
--
ALTER TABLE `detalle_compras`
  ADD CONSTRAINT `detalle_compras_ibfk_1` FOREIGN KEY (`id_compra`) REFERENCES `compras` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_compras_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_permisos`
--
ALTER TABLE `detalle_permisos`
  ADD CONSTRAINT `detalle_permisos_ibfk_1` FOREIGN KEY (`id_permiso`) REFERENCES `permisos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_permisos_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_temp`
--
ALTER TABLE `detalle_temp`
  ADD CONSTRAINT `detalle_temp_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_temp_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `detalle_ventas`
--
ALTER TABLE `detalle_ventas`
  ADD CONSTRAINT `detalle_ventas_ibfk_1` FOREIGN KEY (`id_venta`) REFERENCES `ventas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `detalle_ventas_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`);

--
-- Filtros para la tabla `inventario`
--
ALTER TABLE `inventario`
  ADD CONSTRAINT `inventario_ibfk_1` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `inventario_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_medida`) REFERENCES `medidas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`id_categoria`) REFERENCES `categorias` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `usuarios_ibfk_1` FOREIGN KEY (`id_caja`) REFERENCES `caja` (`id`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
