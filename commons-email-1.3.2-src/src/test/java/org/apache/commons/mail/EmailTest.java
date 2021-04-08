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
