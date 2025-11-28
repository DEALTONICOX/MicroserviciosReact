-- Productos (color Negro) - stock 80 para cada talla (XS,S,M,L,XL)
-- XFITRX (categoría 1) - imagen: /img/PoleraStorefit.png
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (1, 1001, 'StoreFit', 'XFITRX', 'Negro', 'XS', 9990, 80, '/img/PoleraStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (1, 1002, 'StoreFit', 'XFITRX', 'Negro', 'S', 9990, 80, '/img/PoleraStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (1, 1003, 'StoreFit', 'XFITRX', 'Negro', 'M', 9990, 80, '/img/PoleraStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (1, 1004, 'StoreFit', 'XFITRX', 'Negro', 'L', 9990, 80, '/img/PoleraStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (1, 1005, 'StoreFit', 'XFITRX', 'Negro', 'XL', 9990, 80, '/img/PoleraStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);

-- WARMGLIDE (categoría 2) - imagen: /img/PoleronStorefit.png
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (2, 2001, 'StoreFit', 'WARMGLIDE', 'Negro', 'XS', 17990, 80, '/img/PoleronStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (2, 2002, 'StoreFit', 'WARMGLIDE', 'Negro', 'S', 17990, 80, '/img/PoleronStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (2, 2003, 'StoreFit', 'WARMGLIDE', 'Negro', 'M', 17990, 80, '/img/PoleronStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (2, 2004, 'StoreFit', 'WARMGLIDE', 'Negro', 'L', 17990, 80, '/img/PoleronStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (2, 2005, 'StoreFit', 'WARMGLIDE', 'Negro', 'XL', 17990, 80, '/img/PoleronStorefit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);

-- FLEXRUN (categoría 3) - imagen: /img/BuzoStoreFit.png
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (3, 3001, 'StoreFit', 'FLEXRUN', 'Negro', 'XS', 14990, 80, '/img/BuzoStoreFit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (3, 3002, 'StoreFit', 'FLEXRUN', 'Negro', 'S', 14990, 80, '/img/BuzoStoreFit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (3, 3003, 'StoreFit', 'FLEXRUN', 'Negro', 'M', 14990, 80, '/img/BuzoStoreFit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (3, 3004, 'StoreFit', 'FLEXRUN', 'Negro', 'L', 14990, 80, '/img/BuzoStoreFit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (3, 3005, 'StoreFit', 'FLEXRUN', 'Negro', 'XL', 14990, 80, '/img/BuzoStoreFit.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);

-- FITQUEEN (categoría 4) - imagen: /img/TopMujer.png
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (4, 4001, 'StoreFit', 'FITQUEEN', 'Negro', 'XS', 19990, 80, '/img/TopMujer.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (4, 4002, 'StoreFit', 'FITQUEEN', 'Negro', 'S', 19990, 80, '/img/TopMujer.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (4, 4003, 'StoreFit', 'FITQUEEN', 'Negro', 'M', 19990, 80, '/img/TopMujer.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (4, 4004, 'StoreFit', 'FITQUEEN', 'Negro', 'L', 19990, 80, '/img/TopMujer.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);
INSERT INTO producto (id_categoria, id_producto, marca, modelo, color, talla, precio, stock, image_url)
VALUES (4, 4005, 'StoreFit', 'FITQUEEN', 'Negro', 'XL', 19990, 80, '/img/TopMujer.png')
ON DUPLICATE KEY UPDATE marca=VALUES(marca), modelo=VALUES(modelo), color=VALUES(color), talla=VALUES(talla), precio=VALUES(precio), stock=VALUES(stock), image_url=VALUES(image_url);