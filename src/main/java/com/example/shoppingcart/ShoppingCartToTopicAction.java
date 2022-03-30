package com.example.shoppingcart;

import com.akkaserverless.javasdk.action.ActionCreationContext;
import com.example.shoppingcart.ShoppingCartTopicApi.CheckedOutTopicEvent;
import com.example.shoppingcart.domain.ShoppingCartDomain;
import com.example.shoppingcart.domain.ShoppingCartDomain.CheckedOut;
import com.google.protobuf.Any;
import com.google.protobuf.Empty;

import static com.example.shoppingcart.ShoppingCartTopicApi.CheckedOutTopicEvent.newBuilder;
import static com.google.protobuf.Empty.getDefaultInstance;

// This class was initially generated based on the .proto definition by Akka Serverless tooling.
// This is the implementation for the Action Service described in your com/example/shoppingcart/shoppingcart_topic.proto file.
//
// As long as this file exists it will not be overwritten: you can maintain it yourself,
// or delete it so it is regenerated as needed.

public class ShoppingCartToTopicAction extends AbstractShoppingCartToTopicAction {

  public ShoppingCartToTopicAction(ActionCreationContext creationContext) {}

  @Override
  public Effect<CheckedOutTopicEvent> checkedOut(CheckedOut checkedOut) {
      CheckedOutTopicEvent topicEvent = newBuilder()
              .setCartId(actionContext().eventSubject().get())
              .setCheckedOutTimestamp(checkedOut.getCheckedOutTimestamp())
              .build();
      return effects().reply(topicEvent);
  }

  @Override
  public Effect<Empty> ignore(Any any) {
      return effects().reply(getDefaultInstance());
  }
}
