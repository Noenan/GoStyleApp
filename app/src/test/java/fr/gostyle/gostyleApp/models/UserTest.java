package fr.gostyle.gostyleApp.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest {

    String nom = "Henry", prenom = "Philippe", mail = "henry@gmail.com", password = "12345678";
    User userTest = new User();

    @Before
    public void init() {
        userTest.setNom("Henry");
        userTest.setPrenom("Philippe");
        userTest.setEmail("henry@gmail.com");
        userTest.setPassword("12345678");
    }


    @Test
    public void setNomTest() {
        Assert.assertEquals(nom, userTest.getNom());

    }

    @Test
    public void getNomTest() {
        Assert.assertTrue(userTest.getNom().equals(nom));

    }


    @Test
    public void setPrenomTest() {
        Assert.assertEquals(prenom, userTest.getPrenom());

    }

    @Test
    public void getPrenomTest() {
        Assert.assertTrue(userTest.getPrenom().equals(prenom));

    }

    @Test
    public void setEmailTest() {
        Assert.assertEquals(mail, userTest.getEmail());

    }

    @Test
    public void setPasswordTest() {

        Assert.assertEquals(password, userTest.getPassword());
    }
}
