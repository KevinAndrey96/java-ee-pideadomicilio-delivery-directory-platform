package com.infinitec.pideadomicilio.model;

import com.infinitec.pideadomicilio.model.entity.*;
import com.infinitec.pideadomicilio.model.entity.pojo.ResumenBusqueda;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ModelEntityTest {

    @Test
    public void testCiudadAndZonaRelationships() {
        Ciudad bogota = new Ciudad();
        bogota.setId("Bogota");
        bogota.setNombre("Bogotá D.C.");

        Zona usaquen = new Zona();
        usaquen.setId("Usaquen_Bogota");
        usaquen.setNombre("Usaquén");
        usaquen.setCiudad(bogota);

        Assert.assertEquals("Bogota", bogota.getId());
        Assert.assertEquals("Bogotá D.C.", bogota.getNombre());
        Assert.assertEquals("Usaquen_Bogota", usaquen.getId());
        Assert.assertEquals("Usaquén", usaquen.getNombre());
        Assert.assertEquals(bogota, usaquen.getCiudad());
        Assert.assertEquals("Usaquén", usaquen.getNombreZona());
    }

    @Test
    public void testComercioEntityWithTagsAndPhones() {
        Comercio comercio = new Comercio();
        comercio.setId("uuid-corral-001");
        comercio.setNombre("Burger House Artesanal");
        comercio.setDireccion("Cra 13 # 85-24");
        comercio.setUrl("https://pideadomicilio.local/burger-house");
        comercio.setImg("burger.png");

        Set<String> tels = new HashSet<>();
        tels.add("+5717441111");
        tels.add("+573001234567");
        comercio.setTelefonos(tels);

        Set<String> tags = new HashSet<>();
        tags.add("hamburguesa");
        tags.add("malteada");
        tags.add("combo");
        comercio.setTags(tags);

        Assert.assertEquals("uuid-corral-001", comercio.getId());
        Assert.assertEquals("Burger House Artesanal", comercio.getNombre());
        Assert.assertEquals(2, comercio.getTelefonos().size());
        Assert.assertEquals(3, comercio.getTags().size());
        Assert.assertTrue(comercio.getTelefonos().contains("+5717441111"));
        Assert.assertTrue(comercio.getTags().contains("hamburguesa"));
    }

    @Test
    public void testUserEntityRoles() {
        User admin = new User();
        admin.setUsername("ADMIN");
        admin.setPassword("encryptedHash");
        admin.setRole("ADMIN");

        User regular = new User();
        regular.setUsername("CLIENTE1");
        regular.setPassword("encryptedHash2");
        regular.setRole("USUARIO");

        Assert.assertEquals("ADMIN", admin.getUsername());
        Assert.assertEquals("ADMIN", admin.getRole());
        Assert.assertEquals("CLIENTE1", regular.getUsername());
        Assert.assertEquals("USUARIO", regular.getRole());
    }

    @Test
    public void testEventoAndBannerEntities() {
        Evento evento = new Evento();
        evento.setTipo("BUSQUEDA");
        evento.setBusqueda("Pizza");
        evento.setZona("Chapinero");
        evento.setCategoria("Comida Rápida");
        Date now = new Date();
        evento.setFecha(now);

        Assert.assertEquals("BUSQUEDA", evento.getTipo());
        Assert.assertEquals("Pizza", evento.getBusqueda());
        Assert.assertEquals("Chapinero", evento.getZona());
        Assert.assertEquals(now, evento.getFecha());

        Banner banner = new Banner();
        banner.setId("banner-01");
        banner.setNombre("Promo Viernes");
        banner.setImg("/file/banner1.jpg");
        banner.setImgResp("/file/banner1_mobile.jpg");
        banner.setPeso(5);

        Assert.assertEquals("Promo Viernes", banner.getNombre());
        Assert.assertEquals("/file/banner1.jpg", banner.getImg());
        Assert.assertEquals("/file/banner1_mobile.jpg", banner.getImgResp());
        Assert.assertEquals(5, banner.getPeso());
    }

    @Test
    public void testResumenBusquedaPojo() {
        ResumenBusqueda rb = new ResumenBusqueda();
        rb.setBusqueda("Pizza");
        rb.setTipo("CATEGORIA");
        rb.setCantidad(42.0f);

        Assert.assertEquals("Pizza", rb.getBusqueda());
        Assert.assertEquals("CATEGORIA", rb.getTipo());
        Assert.assertEquals(42.0f, rb.getCantidad(), 0.001f);
    }
}
