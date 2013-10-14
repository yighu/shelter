package com.ffg.shelter.controller;

import org.resthub.test.AbstractWebTest;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: N060974
 * Date: 7/1/13
 * Time: 7:05 PM
 * To change this template use File | Settings | File Templates.
 */
public class ClientControllerTest extends AbstractWebTest {
    public ClientControllerTest() {
        // Activate resthub-web-server and resthub-jpa Spring profiles
        super("resthub-web-server,resthub-jpa");
    }

    @Test
    public void testFindByName() {

        this.request("/api/client/clients");
        //     this.request("api/task").xmlPost(new Task("task2"));
        //     Task task1 = this.request("api/task/name/newTask1").jsonGet().resource(Task.class);
        //    Assertions.assertThat(task1).isNotNull();
        //     Assertions.assertThat(task1.getName()).isEqualTo("newTask1");
    }

}
