Ques.1   Why a HumanPlayer can be returned when the return type is Player. ?
Ans....  This is possible because of polymorphism in Java, specifically subtype polymorphism (also known as dynamic method dispatch).
        - In Java, a subclass object can be assigned to a superclass reference.
        - The promptForPlayer method has a return type of Player, which is an abstract class.
        - HumanPlayer, Linus, and Omola are subclasses of Player, meaning they inherit from Player.
        - Because Java supports upcasting (assigning a subclass object to a superclass reference), we can return instances of HumanPlayer, Linus, or Omola without changing the return type.


Ques.2   Why pickNextMove must be an abstract method in Player and not just in Player subclasses. ?

Ans.... Why Did the Error Occur Initially?
  -->   The error occurred because the pickNextMove method was removed from the Player class and placed into the HumanPlayer subclass.
        The variable whoseTurn is of type Player (the abstract superclass).
        This caused a compilation error because Java did not know if whoseTurn (a Player) actually had a pickNextMove() method.

Ans.... Why Does Adding the Abstract Method Fix the Error?
   -->  By adding an abstract method signature for pickNextMove back into the Player class.
        Java knows that every object of type Player (whether it's HumanPlayer, Linus, or Omola) must have a pickNextMove method.


Ques.3   Why it is not necessary to change Main or TicTacToeGame in order to add new Player types to the game. ?
Ans....  1). Polymorphism: The Key to Flexibility
          -  Polymorphism allows us to treat different types of players (HumanPlayer, Linus, Omola, etc.) uniformly by referring to them through their shared superclass (Player).
          -  Even though whoseTurn is a Player reference, at runtime, Java determines which specific implementation of pickNextMove() to call based on the actual object type (whether it's HumanPlayer, Linus, or Omola).
          -  This means that when we add a new subclass like Omola, the same call (whoseTurn.pickNextMove(board)) will automatically work without any changes to TicTacToeGame.

   2). Abstraction: Hiding Implementation Details
 - The Player class defines a common interface for all player types by declaring pickNextMove() as an abstract method.
 - Each specific Player subclass implements this method in its own way (e.g., human input for HumanPlayer, AI logic for Linus and Omola).
 - The game logic in TicTacToeGame doesn't need to know how a player picks a move—it just calls pickNextMove() and gets a valid move.

3). Encapsulation: Keeping Player Logic Separate
      - Each Player subclass encapsulates its own logic for picking a move, meaning:
        ( HumanPlayer prompts the user for input. )
        ( Linus follows a predefined AI strategy. )
        ( Omola may use a different algorithm. )

4). The Role of Console.promptForPlayer()
 - The only minor change required when adding new Player types is inside Console.promptForPlayer(), where we instantiate the correct Player based on user input:

   public static Player promptForPlayer(PlayerToken token) {
   System.out.print("Enter 'H' for human player, 'L' for Linus, or 'O' for Omola: ");
   String choice = scanner.nextLine().trim().toUpperCase();

   switch (choice) {
   case "L":
   return new Linus();
   case "O":
   return new Omola();
   case "H":
   default:
   System.out.print("Enter player name: ");
   String name = scanner.nextLine();
   return new HumanPlayer(name);
   }
   }

 - This method creates a new Player instance and returns it as a Player reference. 
 - Because of polymorphism, the TicTacToeGame class doesn’t need to care about the specific type of Player—it just calls pickNextMove() and the correct method is executed.



5).  Correction for Main: Missing Game Initialization.
- The rubric mentioned a missing game initialization in Main.
  To fix this, ensure that Main correctly initializes and runs a TicTacToeGame instance:

    public class Main {
    public static void main(String[] args) {
        Player player1 = Console.promptForPlayer(PlayerToken.X);
        Player player2 = Console.promptForPlayer(PlayerToken.O);

        TicTacToeGame game = new TicTacToeGame(player1, player2);
        game.play();
    }
}

 
-  This ensures that the game is properly initialized and started.
   With this in place, Main successfully sets up a TicTacToeGame and allows any Player subclass to be used.



6). Summary: Why Our Design Is in a Good Place,
     
  -  We can add unlimited new player types without changing game logic.
  -  Polymorphism ensures pickNextMove() works for any player.
  -  Abstraction and encapsulation keep logic modular and separate.
  -  Only minor changes are needed in Console.promptForPlayer() to support new players.

  -  This design follows the Open/Closed Principle—our game is open for extension (we can add new Player types) but closed for modification (we don’t need to alter TicTacToeGame). 
     This makes our code scalable, maintainable, and easy







       