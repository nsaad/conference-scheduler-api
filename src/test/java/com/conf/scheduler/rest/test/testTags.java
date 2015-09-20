package com.conf.scheduler.rest.test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.jaxrs.JacksonJaxbJsonProvider;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.conf.scheduler.model.Tag;

public class testTags {
	
	private Client client;
	private WebTarget target;
	private String newTagId;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		client = createClient();
		target = client.target("http://localhost:8080/ConferenceSchedule-API/rest");
        target = target.path("tags");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
    public void postTestTag() {
         
        Tag tag = new Tag();
        tag.setTagname("testTag");
        	
        Entity<Tag> ent = Entity.entity(tag, MediaType.APPLICATION_JSON); 
        Response response = target.request().post(ent);
		
		Integer responseStatus = (Integer)response.getStatus();
        Assert.assertEquals("Response status not created", 201, responseStatus.intValue());
        
        String locationNewTag = response.getHeaderString("location");
        String newTagId = locationNewTag.substring(locationNewTag.lastIndexOf('/') + 1);
        System.out.println("ID of new posted object: " + newTagId);
        
    }

	@Test
    public void getTestTag() {
        
		String strRes = getLastTag();
        JSONObject json = new JSONObject(strRes);
        String tagName = json.getString("tagname");
        Assert.assertTrue(tagName.equals("testTag") || tagName.equals("testTag-testTag"));
        
    }

	@Test
    public void updateLastTestTag() {
        
		String strRes = getLastTag();
        
		try {
			Tag returnedTag = new ObjectMapper().readValue(strRes, Tag.class);
			System.out.println("Updating item " + returnedTag.getId() + " with tag: " + returnedTag.getTagname());
			if (returnedTag.getTagname().equalsIgnoreCase("testTag")) {
				returnedTag.setTagname(returnedTag.getTagname() + "-" + returnedTag.getTagname());
			} else if (!returnedTag.getTagname().equalsIgnoreCase("testTag-testTag")) {
				returnedTag.setTagname("testTag");
			} else {
				System.out.println("Changing Tag name: " + returnedTag.getTagname() + " to testTag");
				returnedTag.setTagname("testTag");
			}
			System.out.println("tag name after change, before update: " + returnedTag.getTagname());
			Entity<Tag> ent = Entity.entity(returnedTag, MediaType.APPLICATION_JSON); 
	        Response response = target.request().put(ent);
			
			Integer responseStatus = (Integer)response.getStatus();
			System.out.println(responseStatus);
	        Assert.assertEquals("Response status not updated", 204, responseStatus.intValue());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
    }
	
	private String getLastTag() {
		String lastTagId = getLastTagId();
        target = target.path(lastTagId);
         
        Response response = target.request(MediaType.APPLICATION_JSON).get();
        
        Integer responseStatus = (Integer)response.getStatus();
        Assert.assertEquals("Response status not OK", 200, responseStatus.intValue());
        
        String strRes = response.readEntity(String.class);
        return strRes;
	}
 
    private String getLastTagId() {
    	Response response = target.request(MediaType.APPLICATION_JSON).get();
        
        Integer responseStatus = (Integer)response.getStatus();
        Assert.assertEquals("Response status not OK", 200, responseStatus.intValue());
        List listOfTags = response.readEntity(List.class);
        
        Assert.assertTrue("There are not least one items in the list", listOfTags.size() > 1);
        LinkedHashMap listOfJSONFields = (LinkedHashMap)listOfTags.get(listOfTags.size() - 1);
        
		return ((Integer)listOfJSONFields.get("id")).toString();//lastObject.getId().toString();
	}

	Client createClient() {
        return ClientBuilder
                .newBuilder()
                .register(JacksonJaxbJsonProvider.class)
                .build();
    }


}
