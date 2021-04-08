package org.apache.commons.mail;
import static org.junit.Assert.assertEquals;
import java.sql.Date;
import java.util.Properties;
import javax.mail.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmailTest {

	private static final String[] TEST_EMAILS = {"finalFantasy@squareEnix.com", "squareEnix@finalFantasy.org", "abcdefghijklmnopqrst@abcdefghijklmnopqrst.com.bd"};
	private static final String[] Empty_Emails = {};

	private String[] Test_Names = {"Noctis", "Ignis", "Prompto"}; //Characters from Final Fantasy XV! :D

	private String[] testValidChars = {" ", "a", "A", "\uc5ec", "0123456789", "01234567890123456789","\n" }; //I just used what you had, Professor

	private EmailConcrete email;

	@Before
	public void setUpEmailTest() throws Exception
	{

		email = new EmailConcrete(); //new instance of concrete email!
	}

	@After
	public void tearDownEmailTest() throws Exception
	{
		//this is a tearDown! :D
	}

  //The following code tests addBcc
  	@Test
  	public void testAddBcc() throws Exception
  	{
  		email.addBcc(TEST_EMAILS);
  		assertEquals(3, email.getBccAddresses().size());
  	}

  	@Test(expected = EmailException.class)
  	public void testAddBccEmpty() throws Exception
  	{
  		email.addBcc(Empty_Emails);
  		assertEquals(0, email.getBccAddresses().size());
  	}

    //The following code tests addCC
    	@Test
    	public void testAddCc() throws Exception
    	{
    		email.addCc(TEST_EMAILS[0]);
    		assertEquals(1, email.getCcAddresses().size());
    	}

    	@Test(expected = EmailException.class)
    	public void testAddCcEmpty() throws Exception
    	{
    		email.addCc("");
    		assertEquals(0, email.getCcAddresses().size());
    	}

      //The following code tests addHeader!
	@Test
	public void testAddHeader() throws Exception
	{
		email.addHeader(Test_Names[0] , testValidChars[3]);
		assertEquals(1, email.getHeaders().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddHeaderValue() throws Exception
	{
		email.addHeader("" , testValidChars[3]);
		assertEquals(0, email.getHeaders().size());
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddHeaderName() throws Exception
	{
		email.addHeader(Test_Names[1], "");
		assertEquals(0, email.getHeaders().size());
	}

  //The following code tests the method addReplyTo!
	@Test
	public void testAddReplyTo() throws Exception
	{
		email.addReplyTo(TEST_EMAILS[1], Test_Names[2]);
		assertEquals(1, email.getReplyToAddresses().size());
	}

  //Below tests the method buildMimeMessage!
	@Test
	public void testBuildMimeMessage() throws EmailException
	{
		email.setHostName("royal host");
		email.setSmtpPort(1234);
		email.setFrom("finalFantasy@squareEnix.com");
		email.addTo("squareEnix@finalFantasy.org");
		email.setSubject("Testing");
     	email.setContent("Testing Content", "text/plain");
     	email.addCc("testCC@squareEnix.com");
     	email.addBcc("testBcc@finalFantasy.org");
     	email.addHeader("testingHeader", "Scientia");
     	email.addReplyTo("testReply@squareEnix.com");
		email.buildMimeMessage();
	}

	@Test(expected = IllegalStateException.class)
	public void testBuildMimeMessageException() throws EmailException
	{
		Properties prop = new Properties();
		Session session = Session.getDefaultInstance(prop,null);
		prop.put(EmailConstants.MAIL_HOST, "Insomnia.host");
		email.setHostName("royal host");
	    email.message=email.createMimeMessage(session);
		email.buildMimeMessage();
	}

	@Test
	public void testBuildMimeMessageNullContent() throws EmailException
	{
		email.setHostName("royal host");
		email.setSmtpPort(1234);
		email.setFrom("finalFantasy@squareEnix.com");
		email.addTo("squareEnix@finalFantasy.org");
		email.setSubject("Testing");
     	email.setContent(null);
     	email.buildMimeMessage();
	}

	@Test(expected=EmailException.class)
	public void testBuildMimeMessageNull() throws EmailException
	{
		email.setHostName("royal host");
     	email.buildMimeMessage();
	}

	@Test(expected=EmailException.class)
	public void testBuildMimeMessage2() throws EmailException
	{
		email.setHostName("royal host");
 		email.setSmtpPort(1234);
		email.setFrom("finalFantasy@squareEnix.com");
		email.setSubject("This is a Test Email!");
     	email.setContent("Testing Content", "text/plain");
     	email.addHeader("TestingHeader", "Argentum");
     	email.addReplyTo("testReply@squareEnix.com");
     	email.buildMimeMessage();
	}
