package ua.training.model.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class UserTest {
    private User user;

    private static final Logger logger = LogManager.getLogger(UserTest.class);

    @BeforeClass
    public void before() {
        user = new User();
        logger.info("User created");
    }

    @AfterClass
    public void after() {
        user = null;
        logger.info("User deleted");
    }

    @Test
    public void setId() {
        user.setId(5);
        assertEquals(5, user.getId());
    }

    @Test
    public void setSurname() {
        user.setSurname("name");
        assertEquals("name", user.getSurname());
    }

    @Test
    public void setEmail() {
        user.setEmail("email");
        assertEquals("email", user.getEmail());
    }
}
