# 💬 One-to-One Chat Application (Spring Boot + WebSocket + STOMP)

A real-time **one-to-one chat application** built with **Spring Boot**, **WebSockets**, and **STOMP protocol**.  
This project demonstrates how to create private messaging between users using WebSocket connections and a simple in-memory message broker.

---

## 🚀 Features

- 🔄 Real-time messaging between two users (one-to-one)
- 💬 WebSocket communication via **STOMP**
- 🧠 Message persistence using `ChatMessageService`
- 💌 Notifications delivered directly to the recipient’s personal queue
- 🧩 Automatic chat room creation per user pair
- 🛠 Configurable backend using Spring Boot and Lombok

---

## 🧱 Project Structure

src/
├── main/
│ ├── java/com/example/websocket_one_to_one/
│ │ ├── chat/ # Chat controllers and message handling
│ │ ├── chatroom/ # Chat room management logic
│ │ ├── user/ # User logic
│ │ └── config/ # WebSocket and STOMP configuration
│ └── resources/
│ └── application.properties
└── test/ # Unit tests (if applicable)

yaml

---

## ⚙️ Technologies Used

| Category | Technology |
|-----------|-------------|
| Backend   | Spring Boot 3.x |
| WebSocket | STOMP over SockJS |
| Messaging | `SimpMessagingTemplate` |
| JSON      | Jackson |
| Persistence | Spring Data JPA (optional) |
| Tools     | Maven, Lombok |

---

## 🔌 How It Works

### WebSocket Endpoint
All clients connect to the WebSocket endpoint:
/ws

markdown

### Message Flow
1. **Client** sends a message to `/app/chat`
2. **ChatController** receives it via:
   ```java
   @MessageMapping("/chat")
The message is saved via ChatMessageService

Then it’s forwarded to the recipient’s queue:

bash
/user/{recipientId}/queue/messages
The recipient’s WebSocket listener receives the message in real-time.

🧭 REST Endpoints
Method	Endpoint	Description
GET	/messages/{senderId}/{recipientId}	Get chat history between two users

🧩 Example Configuration (WebSocket)
java
@Override
public void configureMessageBroker(MessageBrokerRegistry registry) {
    registry.enableSimpleBroker("/user");
    registry.setApplicationDestinationPrefixes("/app");
    registry.setUserDestinationPrefix("/user");
}

@Override
public void registerStompEndpoints(StompEndpointRegistry registry) {
    registry.addEndpoint("/ws").withSockJS();
}
🧠 Key Classes
ChatController.java
Handles incoming WebSocket messages and forwards them to the appropriate user queue.

ChatRoomService.java
Manages chat room creation and lookup for user pairs.

WebSocketConfig.java
Configures WebSocket endpoint, message broker, and JSON converters.

🧑‍💻 Running the Project
Prerequisites
Java 17+

Maven 3.8+

(Optional) Database like PostgreSQL or MySQL if persistence is enabled

Steps
bash
# Clone the repository
git clone https://github.com/<your-username>/one-to-one-chat.git

# Navigate to the project
cd one-to-one-chat

# Build and run
mvn spring-boot:run
Once started, your WebSocket endpoint will be available at:

bash ->

ws://localhost:8080/ws
🧩 Frontend Integration Example (JavaScript)
javascript

import SockJS from "sockjs-client";
import Stomp from "stompjs";

const socket = new SockJS("http://localhost:8080/ws");
const stompClient = Stomp.over(socket);

stompClient.connect({}, () => {
  stompClient.subscribe("/user/123/queue/messages", (message) => {
    console.log("Received:", JSON.parse(message.body));
  });

  // Send a message
  stompClient.send("/app/chat", {}, JSON.stringify({
    senderId: "123",
    recipientId: "456",
    content: "Hello there!"
  }));
});

🧰 Future Improvements
🗄 Database integration for persistent chat history

🔐 Authentication and user sessions

📱 Frontend UI for chat interface (React / Vue / Angular)

🕐 Message timestamps and read receipts

🧑‍🎨 Author
DenisJr
📫 denisjr229@gmail.com
