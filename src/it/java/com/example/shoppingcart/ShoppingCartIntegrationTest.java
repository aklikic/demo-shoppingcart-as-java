package com.example.shoppingcart;

import com.akkaserverless.javasdk.testkit.junit.AkkaServerlessTestKitResource;
import com.example.shoppingcart.domain.ShoppingCartDomain;
import com.google.protobuf.Empty;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.List;

import static java.util.concurrent.TimeUnit.*;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

// Example of an integration test calling our service via the Akka Serverless proxy
// Run all test classes ending with "IntegrationTest" using `mvn verify -Pit`
public class ShoppingCartIntegrationTest {

  /**
   * The test kit starts both the service container and the Akka Serverless proxy.
   */
  @ClassRule
  public static final AkkaServerlessTestKitResource testKit =
    new AkkaServerlessTestKitResource(Main.createAkkaServerless());

  /**
   * Use the generated gRPC client to call the service through the Akka Serverless proxy.
   */
  private final ShoppingCartService client;

  public ShoppingCartIntegrationTest() {
    client = testKit.getGrpcClient(ShoppingCartService.class);
  }

  @Test
  public void addItemsToCart() throws Exception {
      addItem("cart2", "a", "Apple", 1);
      addItem("cart2", "b", "Banana", 2);
      addItem("cart2", "c", "Cantaloupe", 3);
      ShoppingCartApi.Cart cart = getCart("cart2");
      Assert.assertEquals("shopping cart should have 3 items", 3, cart.getItemsCount());
      Assert.assertEquals(
              "shopping cart should have expected items",
              cart.getItemsList(),
              List.of(item("a", "Apple", 1), item("b", "Banana", 2), item("c", "Cantaloupe", 3))
      );
  }
  void addItem(String cartId, String productId, String name, int quantity) throws Exception {
      client
              .addItem(
                      ShoppingCartApi.AddLineItem.newBuilder()
                              .setCartId(cartId)
                              .setProductId(productId)
                              .setName(name)
                              .setQuantity(quantity)
                              .build())
              .toCompletableFuture()
              .get();
  }
  ShoppingCartApi.LineItem item(String productId, String name, int quantity) {
      return ShoppingCartApi.LineItem.newBuilder()
              .setProductId(productId)
              .setName(name)
              .setQuantity(quantity)
              .build();
  }

  ShoppingCartApi.Cart getCart(String cartId) throws Exception {
      return client
              .getCart(ShoppingCartApi.GetShoppingCart.newBuilder().setCartId(cartId).build())
              .toCompletableFuture()
              .get();
  }
}
