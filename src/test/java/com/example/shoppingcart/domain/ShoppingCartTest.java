package com.example.shoppingcart.domain;

import com.akkaserverless.javasdk.eventsourcedentity.EventSourcedEntity;
import com.akkaserverless.javasdk.eventsourcedentity.EventSourcedEntityContext;
import com.akkaserverless.javasdk.testkit.EventSourcedResult;
import com.example.shoppingcart.ShoppingCartApi;
import com.google.protobuf.Empty;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class ShoppingCartTest {

  @Test
  public void addItemTest() {

      ShoppingCartTestKit testKit = ShoppingCartTestKit.of(ShoppingCart::new); // <1>

      ShoppingCartApi.AddLineItem apples = ShoppingCartApi.AddLineItem.newBuilder().setProductId("idA")
              .setName("apples").setQuantity(1).build();
      EventSourcedResult<Empty> addingApplesResult = testKit.addItem(apples); // <2>

      ShoppingCartApi.AddLineItem bananas = ShoppingCartApi.AddLineItem.newBuilder().setProductId("idB")
              .setName("bananas").setQuantity(2).build();
      testKit.addItem(bananas); // <3>

      assertEquals(1, addingApplesResult.getAllEvents().size()); // <4>
      assertEquals(2, testKit.getAllEvents().size()); // <5>

      ShoppingCartDomain.ItemAdded addedApples = addingApplesResult.getNextEventOfType(ShoppingCartDomain.ItemAdded.class); // <6>
      assertEquals("apples", addedApples.getItem().getName());
      assertThrows(NoSuchElementException.class, () ->  addingApplesResult.getNextEventOfType(ShoppingCartDomain.ItemAdded.class)); // <7>
      assertEquals(Empty.getDefaultInstance(), addingApplesResult.getReply()); // <8>

      ShoppingCartDomain.LineItem expectedApples = ShoppingCartDomain.LineItem.newBuilder().setProductId("idA")
              .setName("apples").setQuantity(1).build();
      ShoppingCartDomain.LineItem expectedBananas = ShoppingCartDomain.LineItem.newBuilder().setProductId("idB")
              .setName("bananas").setQuantity(2).build();
      ShoppingCartDomain.Cart expectedState = ShoppingCartDomain.Cart.newBuilder()
              .addItems(expectedApples)
              .addItems(expectedBananas)
              .build();
      assertEquals(expectedState, testKit.getState()); // <9>
  }

}
