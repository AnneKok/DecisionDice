import nl.tue.id.oocsi.*;
import nl.tue.id.oocsi.client.services.*;

// Setup of general variables
OOCSI oocsi;
String oocsiServer;
String coffeeChannel;
String choosePizzaChannel;

public void setup() {
  // Declaring settings (for OOCSI)
  oocsiServer = "oocsi.id.tue.nl";
  coffeeChannel = "coffee_channelTest";
  choosePizzaChannel = "choosePizzaDiceTest";
  
  // Setup OOCSI and subscribe to the pizza-channel aswell
  oocsi = new OOCSI(this, coffeeChannel, oocsiServer);
  oocsi.subscribe(choosePizzaChannel);
  println("ChoosePizza channel subscribed");
}

// Event handler: runs on both channels
void handleOOCSIEvent(OOCSIEvent event) {
  // When run: read one of the messages on both channels
  int mCoffee = event.getInt("caffee_who", 0);
  String mPizza = event.getString("address", "");
  
  // Print those messages
  println("[CoffeeChannel]: " + mCoffee);
  println("[ChooseChannel]: " + mPizza);
  
  // Send response to the pizza channel when there was a pizza-message
  if (mPizza != "") {
    oocsi.channel(choosePizzaChannel).data("response", "this is a pizza response").send();
    System.out.println("Sending pizza response");
  }
  
  // Send response to the coffee channel when there was a coffee-message
  if (mCoffee != 0) {
    oocsi.channel(coffeeChannel).data("Coffee", "Antwoord").send();
    System.out.println("Sending coffee response");
  }
}