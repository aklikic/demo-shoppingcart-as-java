package com.example.shoppingcart;

import akka.stream.javadsl.Source;
import com.akkaserverless.javasdk.testkit.ActionResult;
import com.example.shoppingcart.ShoppingCartToTopicAction;
import com.example.shoppingcart.ShoppingCartToTopicActionTestKit;
import com.example.shoppingcart.ShoppingCartTopicApi;
import com.example.shoppingcart.domain.ShoppingCartDomain;
import org.junit.Test;
import static org.junit.Assert.*;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class ShoppingCartToTopicActionTest {

  @Test
  public void exampleTest() {
    ShoppingCartToTopicActionTestKit testKit = ShoppingCartToTopicActionTestKit.of(ShoppingCartToTopicAction::new);
    // use the testkit to execute a command
    // ActionResult<SomeResponse> result = testKit.someOperation(SomeRequest);
    // verify the response
    // SomeResponse actualResponse = result.getReply();
    // assertEquals(expectedResponse, actualResponse);
  }

  @Test
  public void checkedOutTest() {
    ShoppingCartToTopicActionTestKit testKit = ShoppingCartToTopicActionTestKit.of(ShoppingCartToTopicAction::new);
    // ActionResult<ShoppingCartTopicApi.CheckedOutTopicEvent> result = testKit.checkedOut(ShoppingCartDomain.CheckedOut.newBuilder()...build());
  }

}
