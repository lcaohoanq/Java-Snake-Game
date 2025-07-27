# Design Patterns in Snake Game

This document provides an overview of the design patterns implemented in the Snake Game application.

## 1. Factory Pattern

The Factory Pattern is used to create different types of game boards without exposing the creation logic to the client.

### Components:

- **BoardFactory Interface**: Defines the interface for creating a board.
- **Concrete Factories**: Implementations for different board types (NoMaze, Box, Classic, etc.).
- **BoardFactoryProvider**: Returns the appropriate factory based on the game mode.

### Benefits:

- Encapsulates board creation logic
- Makes it easy to add new board types
- Centralizes board creation code

### Usage Example:

```java
BoardFactory factory = BoardFactoryProvider.getFactory(mode);
Board board = factory.createBoard(currentUser);
```

## 2. Strategy Pattern

The Strategy Pattern is used for snake movement, allowing different movement algorithms to be interchanged.

### Components:

- **MovementStrategy Interface**: Defines the interface for movement algorithms.
- **Concrete Strategies**: Implementations for different movement directions (Left, Right, Up, Down).
- **MovementContext**: Manages and executes the current movement strategy.

### Benefits:

- Separates movement logic from the board class
- Makes it easy to modify or add new movement behaviors
- Eliminates conditional statements for movement direction

### Usage Example:

```java
movementContext.setStrategy(directionState.isLeftDirection(), 
                          directionState.isRightDirection(),
                          directionState.isUpDirection(), 
                          directionState.isDownDirection());
movementContext.executeStrategy(x, y, dots);
```

## 3. Observer Pattern

The Observer Pattern is used for game events, allowing different components to react to game events without tight coupling.

### Components:

- **GameEventListener Interface**: Defines methods for different game events.
- **GameEventPublisher**: Manages listeners and notifies them of events.
- **Concrete Listeners**: Implementations for different reactions to events (ScoreUpdateListener, AudioEventListener).

### Benefits:

- Decouples event generation from event handling
- Makes it easy to add new event listeners
- Centralizes event notification logic

### Usage Example:

```java
eventPublisher.addListener(new ScoreUpdateListener(scoreLabel));
eventPublisher.addListener(new AudioEventListener(audioHandler, soundEnabled));
// When an event occurs
eventPublisher.notifyAppleEaten(score);
```

## 4. Command Pattern

The Command Pattern is used for user input handling, encapsulating requests as objects.

### Components:

- **Command Interface**: Defines the interface for executing commands.
- **Concrete Commands**: Implementations for different user inputs (MoveLeft, MoveRight, MoveUp, MoveDown).
- **CommandInvoker**: Maps key events to commands and executes them.
- **DirectionState**: Tracks the current direction of the snake.

### Benefits:

- Decouples input handling from game logic
- Makes it easy to add new commands or modify existing ones
- Centralizes input handling logic

### Usage Example:

```java
commandInvoker.registerCommand(KeyEvent.VK_LEFT, new MoveLeftCommand(movementContext, directionState));
commandInvoker.executeCommand(keyCode);
```

## 5. Singleton Pattern

The Singleton Pattern is used for services that should have only one instance throughout the application.

### Components:

- **ServiceLocator**: Provides centralized access to services.
- **AudioService**: Manages game audio with a single instance.

### Benefits:

- Ensures only one instance of a service exists
- Provides a global point of access to the service
- Centralizes service management

### Usage Example:

```java
AudioService audioService = AudioService.getInstance();
audioService.playAudio(inputStream);

// Or using ServiceLocator
UserService userService = ServiceLocator.getInstance().getService(UserService.class);
```

## Integration of Design Patterns

The design patterns work together to create a flexible, maintainable, and extensible game architecture:

1. **Factory Pattern** creates the appropriate board based on the game mode.
2. **Strategy Pattern** handles snake movement based on the current direction.
3. **Observer Pattern** notifies components of game events like eating apples or game over.
4. **Command Pattern** processes user input and updates the direction state.
5. **Singleton Pattern** provides global access to services like audio and user management.

This integration results in a clean separation of concerns, making the code more modular and easier to maintain and extend.