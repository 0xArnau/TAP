package p1.tests;

import org.junit.Test;

import p1.mailbox.MailBox;
import p1.mailstore.InMemory;
import p1.mailstore.MailStore;
import p1.system.MailSystem;
import p1.users.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;

public class TestMailSystem {

    MailSystem system = new MailSystem();
    MailStore test0Mem = new InMemory();
    MailStore test1Mem = new InMemory();
    User user = new User("Test0", "Mike", 1999);
    User user1 = new User("Test1", "Troy", 2001);
    MailBox Test0MailBox = system.newUser(user, test0Mem);
    MailBox Test1MailBox = system.newUser(user1, test1Mem);

    @Test
    public void testLogIn() {
        System.out.println("Test: logIn");
        boolean isloged = system.logIn(String.valueOf(user.getUserName()));
        assertEquals(true, isloged);
    }

    @Test
    public void testGetAllMessages() throws Exception {
        System.out.println("Test: getAllMessages");
        system.updateMessages();
        assertEquals(false, system.getAllMessages().isEmpty());
    }

    @Test
    public void testGetAllUsers() {
        System.out.println("Test: getAllUsers");
        assertEquals(false, system.getAllUsers().isEmpty());
    }

    @Test
    public void testFilter() throws Exception {
        System.out.println("Test: filter");
        Test1MailBox.updateMail();
        assertEquals(1, system.filter("profe").count());
    }

    @Test
    public void testCountMessages() throws Exception {
        System.out.println("Test: countMessages");
        Test1MailBox.updateMail();
        assertEquals(1, system.countMessages());
    }

    @Test
    public void testFilterSubject() throws Exception {
        System.out.println("Test: filterSubject");
        Test1MailBox.updateMail();
        assertEquals(1, system.filterSubject("Nota").count());
    }

    @Test
    public void testFilterBySender() throws Exception {
        System.out.println("Test: filterBySender");
        Test1MailBox.updateMail();
        assertEquals(1, system.filterBySender("Test0", system.filterSubject("Nota")).count());
    }

    @Test
    public void testFilterBySender2() throws Exception {
        System.out.println("Test: filterBySender2");
        Test1MailBox.updateMail();
        assertEquals(1, system.filterBySender("Test0").count());
    }

    @Test
    public void testCountWordsOfMessagesFromUser() throws Exception {
        System.out.println("Test: countWordsOfMessagesFromUser");
        Test1MailBox.updateMail();
        assertEquals(9, system.countWordsOfMessagesFromUser("Mike"));
    }

    @Test
    public void testUsersBornBeforeXYear() throws Exception {
        System.out.println("Test: usersBornBeforeXYear");
        system.updateMessages();
        system.getAllMessages();
        assertEquals(false, system.usersBornBeforeXYear(2002).isEmpty());
    }

    @Test
    public void testUsersBornAfterXYear() throws Exception {
        System.out.println("Test: usersBornAfterXYear");
        system.updateMessages();
        system.getAllMessages();
        assertEquals(false, system.usersBornAfterXYear(1998).isEmpty());
    }

    @Test
    public void testContainsXWordAndLessthanNWords() throws Exception {
        System.out.println("Test: containsXWordAndLessthanNWords");
        system.updateMessages();
        system.getAllMessages();
        assertEquals(1, system.containsXWordAndLessthanNWords(system.getAllMessages(), "profe", 10).count());
    }

    @Test
    public void testContainsXWord() throws Exception {
        System.out.println("Test: containsXWord");
        system.updateMessages();
        system.getAllMessages();
        assertEquals(1, system.containsXWord(system.getAllMessages(), "profe").count());
    }

    @Test
    public void testLessthanNWords() throws Exception {
        System.out.println("Test: lessthanNWords");
        system.updateMessages();
        assertEquals(1, system.lessthanNWords(system.getAllMessages(), 10).count());
    }

    @Test
    public void testSubjectSingleWord() throws Exception {
        System.out.println("Test: subjectSingleWord");
        system.updateMessages();
        assertEquals(1, system.subjectSingleWord(system.getAllMessages()).count());
    }

    @Test
    public void testGetUser() throws Exception {
        System.out.println("Test: getUser");
        assertEquals(user, system.getUser("Test0"));
    }

    @Test
    public void testGetMailBoxOfUser() throws Exception {
        System.out.println("Test: getMailBoxOfUser");
        assertNotNull(system.getMailBoxOfUser("Test0"));
    }

    @Test
    public void testGetMailBoxOfUser2() throws Exception {
        System.out.println("Test: getMailBoxOfUser2");
        assertNotNull(system.getMailBoxOfUser(user));
    }

    @Test
    public void testGetExist() throws Exception {
        System.out.println("Test: getExist");
        assertEquals(true, system.getExist("Test0"));
    }

    @BeforeClass
    public static void enviarCorreo() {
        MailSystem system = new MailSystem();
        MailStore test0Mem = new InMemory();
        User user = new User("Test0", "Mike", 1999);
        MailBox Test0MailBox = system.newUser(user, test0Mem);
        Test0MailBox.sendMail("Test1", "Nota", "Hola profe, quina nota tindré al final del treball?");
    }
}
