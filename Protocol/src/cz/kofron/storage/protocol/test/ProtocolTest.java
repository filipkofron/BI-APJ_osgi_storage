package cz.kofron.storage.protocol.test;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Authenticator.RequestorType;

import junit.framework.Assert;

import org.junit.Test;

import cz.kofron.storage.model.entity.Item;
import cz.kofron.storage.protocol.request.Request;
import cz.kofron.storage.protocol.request.RequestItem;

public class ProtocolTest
{

	@Test
	public void test()
	{
		try
		{
			Item test = new Item(3);
			RequestItem requestItem = new RequestItem(test, RequestItem.Operation.ADD);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream bos = new ObjectOutputStream(baos);
			bos.writeObject(requestItem);
			
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			ObjectInputStream ois = new ObjectInputStream(bais);
			
			Object o = ois.readObject();
			assertTrue(o instanceof RequestItem);
			RequestItem ri = (RequestItem) o;
			assertEquals(requestItem.getType(), ri.getType());
			assertEquals(requestItem.getItem().getId(), ri.getItem().getId());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail("Testing protocol failed due to an exception above.");
		}
		
	}
}
