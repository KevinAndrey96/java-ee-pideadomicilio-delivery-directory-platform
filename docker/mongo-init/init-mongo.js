// MongoDB Initialization & Seed Data for PideADomicilio Platform
db = db.getSiblingDB('pedidosadomicilio');

// 1. Seed Cities (Ciudades de Colombia)
db.Ciudad.insertMany([
  { _id: "Bogota", nombre: "Bogotá D.C." },
  { _id: "Medellin", nombre: "Medellín" },
  { _id: "Cali", nombre: "Cali" },
  { _id: "Ibague", nombre: "Ibagué" },
  { _id: "Barranquilla", nombre: "Barranquilla" }
]);

// 2. Seed Zones (Zonas Urbanas)
db.Zona.insertMany([
  { _id: "Chapinero_Bogota", nombre: "Chapinero", ciudad: { "$ref": "Ciudad", "$id": "Bogota" } },
  { _id: "Usaquen_Bogota", nombre: "Usaquén", ciudad: { "$ref": "Ciudad", "$id": "Bogota" } },
  { _id: "ZonaRosa_Bogota", nombre: "Zona Rosa", ciudad: { "$ref": "Ciudad", "$id": "Bogota" } },
  { _id: "Suba_Bogota", nombre: "Suba", ciudad: { "$ref": "Ciudad", "$id": "Bogota" } },
  { _id: "ElPoblado_Medellin", nombre: "El Poblado", ciudad: { "$ref": "Ciudad", "$id": "Medellin" } },
  { _id: "Laureles_Medellin", nombre: "Laureles", ciudad: { "$ref": "Ciudad", "$id": "Medellin" } },
  { _id: "Granada_Cali", nombre: "Granada", ciudad: { "$ref": "Ciudad", "$id": "Cali" } },
  { _id: "ElPenon_Cali", nombre: "El Peñón", ciudad: { "$ref": "Ciudad", "$id": "Cali" } },
  { _id: "Centro_Ibague", nombre: "Centro", ciudad: { "$ref": "Ciudad", "$id": "Ibague" } }
]);

// 3. Seed Categories (Categorías Gastronómicas y Delivery)
db.Categoria.insertMany([
  { _id: "Hamburguesas", nombre: "Hamburguesas y Sandwiches" },
  { _id: "Pizza", nombre: "Pizzas y Pastas Italianas" },
  { _id: "ComidaTipica", nombre: "Comida Típica Colombiana" },
  { _id: "Sushi", nombre: "Sushi y Comida Asiática" },
  { _id: "Pollo", nombre: "Pollo Asado y Broaster" },
  { _id: "Farmacia", nombre: "Droguerías y Farmacias" },
  { _id: "Licores", nombre: "Licores, Bebidas y Snacks" },
  { _id: "Mascotas", nombre: "Veterinaria y Mascotas" }
]);

// 4. Seed Merchants (Comercios y Restaurantes de Demostracion)
db.Comercio.insertMany([
  {
    _id: "c001-burger-house",
    nombre: "Burger House Artesanal",
    direccion: "Cra 13 # 85-24, Zona Rosa",
    url: "https://pideadomicilio.local/burger-house",
    img: "burger_logo.png",
    peso: 10.0,
    telefonos: ["+5717441111", "+573001234567"],
    tags: ["hamburguesa", "papas", "malteada", "combo", "artesanal"],
    zona: { "$ref": "Zona", "$id": "ZonaRosa_Bogota" },
    categoria: { "$ref": "Categoria", "$id": "Hamburguesas" }
  },
  {
    _id: "c002-napoli-pizza",
    nombre: "Pizzería Trattoria Napoli",
    direccion: "Calle 10 # 32-15, Granada",
    url: "https://pideadomicilio.local/napoli-pizza",
    img: "napoli_pizza.png",
    peso: 9.5,
    telefonos: ["+5728892020", "+573159876543"],
    tags: ["pizza", "calzone", "lasagna", "ensaladas", "pastas"],
    zona: { "$ref": "Zona", "$id": "Granada_Cali" },
    categoria: { "$ref": "Categoria", "$id": "Pizza" }
  },
  {
    _id: "c003-pollo-dorado",
    nombre: "Asadero Pollo Dorado",
    direccion: "Cra 43A # 7-50, El Poblado",
    url: "https://pideadomicilio.local/pollo-dorado",
    img: "pollo_logo.png",
    peso: 9.0,
    telefonos: ["+5744488888"],
    tags: ["pollo", "broaster", "alitas", "frispapas", "asado"],
    zona: { "$ref": "Zona", "$id": "ElPoblado_Medellin" },
    categoria: { "$ref": "Categoria", "$id": "Pollo" }
  },
  {
    _id: "c004-farmavida",
    nombre: "Droguería Farmavida Express",
    direccion: "Av 19 # 118-05, Usaquén",
    url: "https://pideadomicilio.local/farmavida",
    img: "farmavida_logo.png",
    peso: 8.5,
    telefonos: ["+5713077011"],
    tags: ["medicamentos", "salud", "primeros auxilios", "drogueria"],
    zona: { "$ref": "Zona", "$id": "Usaquen_Bogota" },
    categoria: { "$ref": "Categoria", "$id": "Farmacia" }
  },
  {
    _id: "c005-bodega-central",
    nombre: "Bodega de Bebidas Central",
    direccion: "Calle 60 # 9-22, Chapinero",
    url: "https://pideadomicilio.local/bebidas-central",
    img: "bebidas_logo.png",
    peso: 8.0,
    telefonos: ["+573105556677"],
    tags: ["cerveza", "vino", "whisky", "ron", "aguardiente", "hielo"],
    zona: { "$ref": "Zona", "$id": "Chapinero_Bogota" },
    categoria: { "$ref": "Categoria", "$id": "Licores" }
  }
]);

// 5. Seed Users (ADMIN y OPERADOR con hash AES-128-CBC)
// admin123 -> mmPyKBphNnsKMxyT3y1R6w==
db.User.insertMany([
  {
    _id: "ADMIN",
    username: "ADMIN",
    password: "mmPyKBphNnsKMxyT3y1R6w==",
    role: "ADMIN"
  },
  {
    _id: "OPERADOR",
    username: "OPERADOR",
    password: "mmPyKBphNnsKMxyT3y1R6w==",
    role: "USUARIO"
  }
]);

// 6. Seed Banners
db.Banner.insertMany([
  {
    _id: "banner-01",
    nombre: "Festival de Hamburguesas Gourmet",
    img: "/file/banner_burger.jpg",
    tipo: "PORTADA",
    url: "https://pideadomicilio.local/festivales/hamburguesa",
    categoria: { "$ref": "Categoria", "$id": "Hamburguesas" }
  },
  {
    _id: "banner-02",
    nombre: "Semana de la Pizza Artesanal",
    img: "/file/banner_pizza.jpg",
    tipo: "PORTADA",
    url: "https://pideadomicilio.local/festivales/pizza",
    categoria: { "$ref": "Categoria", "$id": "Pizza" }
  }
]);

// 7. Seed Sample Search Events (para telemetría y analítica MapReduce)
db.Evento.insertMany([
  { tipo: "BUSQUEDA", fecha: new Date(), busqueda: "hamburguesa", zona: "Zona Rosa (Bogotá D.C.)", categoria: "Hamburguesas y Sandwiches" },
  { tipo: "BUSQUEDA", fecha: new Date(), busqueda: "hamburguesa", zona: "Zona Rosa (Bogotá D.C.)", categoria: "Hamburguesas y Sandwiches" },
  { tipo: "BUSQUEDA", fecha: new Date(), busqueda: "pizza", zona: "Granada (Cali)", categoria: "Pizzas y Pastas Italianas" },
  { tipo: "BUSQUEDA", fecha: new Date(), busqueda: "pizza", zona: "Chapinero (Bogotá D.C.)", categoria: "Pizzas y Pastas Italianas" },
  { tipo: "BUSQUEDA", fecha: new Date(), busqueda: "pollo", zona: "El Poblado (Medellín)", categoria: "Pollo Asado y Broaster" },
  { tipo: "BUSQUEDA", fecha: new Date(), busqueda: "drogueria", zona: "Usaquén (Bogotá D.C.)", categoria: "Droguerías y Farmacias" }
]);

print("PideADomicilio MongoDB database initialized and seeded successfully.");
