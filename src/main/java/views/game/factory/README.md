# Factory Pattern Implementation for Snake Game

## Overview

This package implements the Factory Pattern to create different types of game boards in the Snake Game. The Factory Pattern is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created.

## Components

### BoardFactory Interface

The `BoardFactory` interface defines a contract for all concrete factories:

```java
public interface BoardFactory {
    Board createBoard(UserScore user);
}
```

### Concrete Factories

Each game mode has its own factory implementation:

- `NoMazeFactory` - Creates NoMaze boards
- `BoxFactory` - Creates Box boards
- `TunnelFactory` - Creates Tunnel boards
- `MillFactory` - Creates Mill boards
- `RailsFactory` - Creates Rails boards
- `ApartmentFactory` - Creates Apartment boards
- `CampaignFactory` - Creates Campaign boards

### BoardFactoryProvider

The `BoardFactoryProvider` class is responsible for returning the appropriate factory based on the game mode:

```java
public static BoardFactory getFactory(String mode) {
    // Returns the appropriate factory based on the mode
}
```

## Usage

In the `Snake` class, instead of using conditional logic to create boards, we now use the factory pattern:

```java
BoardFactory factory = BoardFactoryProvider.getFactory(mode);
Board board = factory.createBoard(currentUser);
add(board);
```

## Benefits

1. **Encapsulation** - Board creation logic is encapsulated in factory classes
2. **Single Responsibility** - Each factory is responsible for creating only one type of board
3. **Open/Closed Principle** - New board types can be added without modifying existing code
4. **Maintainability** - Easier to maintain and extend the codebase
5. **Testability** - Factories can be mocked for testing purposes