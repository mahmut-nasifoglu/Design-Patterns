# Design-Patterns
Design Patterns Exercises
# Problems

# 1-Adapter and proxy exercise
  
 Write a simple database of arrays Data. This database is visualized in the control (left panel) of the class JList (it needs an external data model derived from the class AbstractListModel). 
Complete the program skeleton: https://github.com/mhmtnasif/Design-Patterns/blob/master/skeleton/aoopt4.java.txt

* write a simple implementation of the data - RealData (eg. as int[] or ArrayList/LinkedList);
* complete the Database class (don't forget about calling fireIntervalAdded and fireIntervalRemoved).

Choosing an array on the list should show it in the editable table on the right panel. To achieve it create an object adapter implementing the interface type TableModel or (better) extending the class AbstractTableModel.

* it should present the array data in two columns: index and value;
* except methods: getValueAt, getRowCount, getColumnName, getColumnCount, implement also isCellEditable, setValueAt i getColumnClass - they will allow modification of values;
* create on adapter object and put it in the control JTable in the marked (in the source code) place;
* change on the array list should also change the data pointed to in the adapter;
* don't forget to call  fireTableStructureChanged method.
 
 Add a virtual proxy to data realizing its lazy initialization:

* the database should not contain the real array but proxies to them;
* it is not necessary to create the array immediately - until the set method is called for the first time (first modification);
* it is not necessary to create the array to show it int he table  - it is empty so all its values are initialized to 0.

Add a button copying the chosen array using the copy-on-write proxy.

* at the beginning it is a shallow copy - proxy to the original array;
* on the first modification of the "copy" a deep copy will be created and the proxy will switch to point to the just created copy;
* in the above implementation only modification of the copy will cause creation of the real copy - change it to fire this mechanism also on modification of the original array - you will need a list of the created copies in every real array.

# 2-Command excercise - Stack with undo and redo operations

Write a class **Stack** with 2 extra operations: **'undo'** and **'redo'** 

The first should invert the last operation(s) push/pop, the second - invert the inversion (cancel the 'undo' operation).

**Example:**
Stack S = new Stack(); S={} \
S.push(2);S={2}\
S.push(4);S={2, 4}\
S.push(6); S={2, 4, 6}\
S.undo(); S={2, 4} undoing push(6) operation\
S.undo(); S={2} undoing push(4) operation\
S.redo();  S={2, 4}redoing the last undone operation (push(4))\
S.push(10); S={2, 4, 10}
* S.redo() => impossible, since no previous 'undo' operation\
S.pop(); S={2, 4}\
S.undo();  S={2, 4, 10} //undoing pop() operation\
S.redo();  S={2, 4} //redoing the last undone operation (pop())


To do it, you need a Command interface:\
interface Command {\
void undo();\
void redo();\
}\
and two concrete classes implementing this interface: PopCommand, and PushCommand.


In each 'pop' and 'push' operations of the Stack class, except updating the stack state (content), a new object of the corresponding class (PopCommand, PushCommand) is created and stored in a separate list (history) inside the stack object (for instance called undoCommandList). This object should know: (1) what number was poped/pushed (2) from which stack. The (2) functionality can be realized as an inner Java class (preferably) - PopCommand and PushCommand can be inner classes of the Stack class.

Finally, the 'undo' operation of the class Stack should take the last object from the undoCommandList, perform its own 'undo' operation and move it to the redoCommandList. Similarly the 'redo' operation. The above example, with contents of undoCommandList (U) and redoCommandList(R) - PopCommand and PushCommand objects - are presented below:

Stack S = new Stack(); S={}, U={}, R={}, \
S.push(2); S={2}, U={PUSH(2)}, R={}, \
S.push(4);S={2, 4}, U={PUSH(2), PUSH(4)}, R={}, \
S.push(6); S={2, 4, 6}, U={PUSH(2), PUSH(4), PUSH(6)}, R={}, \
S.undo(); S={2, 4},U={PUSH(2), PUSH(4)}, R={PUSH(6)}, \
S.undo(); S={2}, U={PUSH(2)}, R={PUSH(6), PUSH(4)}\
S.redo(); S={2, 4}, U={PUSH(2), PUSH(4)}, R={PUSH(6)}\
S.push(10); S={2, 4, 10}, U={PUSH(2), PUSH(4), PUSH(10)}, R={}\
* S.redo() => impossible, since no previous 'undo' operation\
S.pop(); S={2, 4}, U={PUSH(2), PUSH(4), PUSH(10), POP(10)}, R={}\
S.undo(); S={2, 4, 10}, U={PUSH(2), PUSH(4), PUSH(10)}, R={POP(10)}\
S.redo(); S={2, 4},U={PUSH(2), PUSH(4), PUSH(10), POP(10)}, R={}

# 3-Document (Composite and optionallyFlyweight)

Write a class hierarchy that can be used to represent an internal document stucture (classes **Document, Chapter, Paragraph, Character**). They should implement the Composite design pattern. Each one should have methods 'print' and 'add...' ('addChapter' in the Document class, 'addParagraph' in the ChapterClass, etc.).


Classes Character and Paragraph can also implement the **Flyweight** design pattern: similar objects will be represented by only 1 object instance. The Inner state of the Character class (stored as a field in this class) will be simply an ASCII code (or 'char' variable). The Outer state of the Character class (stored outside this class - e.g. in the Paragraph) will be the boolean variable "big letter/small letter" . So all 'a' and 'A' characters in the entire document will be represented by only 1 Character('a') object, the paragraph objects will contain many references to this object. The outer state (boolean) shall be passed as a parameter to the 'print' method.

The Character objects should be managed by a Character pool/factory class and must not be created by the end-user (like in the Singleton pattern, private constructor, etc).This functionality can be implemented as static components of the Character class, for instance:
public static Character getCharacter(char c);
and
private static HashMap<char, Character> characters; (Java 1.5)

The Outer state should be stored outside the Character class - in the Paragraph class - in a compressed form. A RLE compression can be used: it compresses a sequence as quantities of the identical neighbour objects, here: quantity of big letter | quantity of small letter | quantity of big letters | ... For instance a phrase 'ABcdefGhijk' can be stored as a chain of Character objects ('a' 'b' 'c' 'd' 'e' 'f' 'g' 'h' 'i' 'j' 'k') and 2-4-1-4 sequence (2 big letters, 4 small letters, 1 big letter, 4 small letters).

The 'print' method, because of the Composite pattern requirement, should have the same interface in all the classes. In the Character class it needs 1 boolean parameted (big/small), so all these methods must take 1 object parameter: in the class Character it will be casted to the Boolean class and then to the bool primitive variable, in the other classes it will ignored:
void print(Object param);
in Character:
{ bool big = ((Boolean)(param)).booleanValue(); ... }


In the function main, create 1 simple document and print it:

Document d=new Document()
Chapter c=new Chapter()
Paragraph p = new Paragraph("A simple paragraph. Build from 2 sentences.")**inside this constructor it must be converted to the collection of references to 'Character' objects and the RLE sequence.**
c.addParagraph(p)
d.addChapter(c)

d.print(null)
c.print(null)

# 4-Factory Method and Prototype excercise
Write a class PairDatabase storing pairs of numbers : (1,2), (4,0), etc.

It sould have at least 3 functions: 

* put(int number1, int number2); **adding the pair (number1, number2) to the database**
* remove(int number1, int number2); **removing this pair**
* boolean contains(int number1, number2); **checking if the given pair is present in the database (so returning "true") or not (returning "false")**

Implement it using a class HashMap of lists. Each list is described by the 'number1' (Integer) and contains all possible 'number2's. So the database containing the folowing pairs: (1,2), (1,5), (1,1), (5,3) should have the form given below:

HashMap<Integer>, List<Integer> > map;

map:

[Integer(1), List{2, 5, 1} ]
[Integer(5), List{3} ]
When you put a new pair 'number1, numbers' (2, 3) , you have to create a new entry in the HashMap, containing this new 'number1' (2) as the key and a new list with the new 'number2' (3) added to this list as the value.


"List" is an intefrace type and you should use one of 2 real classes: ArrayList or LinkedList. They have the same interface. The decision which class to use should be taken:

Using "FactoryMethod" pattern
Using "Prototype" pattern

ad. 1.
Create one abstract class FMDatabase with all needed methods and inside 'put' method, when you have to create a new list, instead of doing:

List newList=new ArrayList(); or List newList=new LinkedList();
write:

List newList=createList();

where 'protected abstract List createList();' method in the FMDatabase will be finally implemented in 2 subclasses of FMDatabase: ALDatabase, LLDatabase, producing one concrete list object of the corresponding class - it will be the Factory Method

ad. 2
Write one class PrototypeDatabase with all needed methods. During construction, give it as a parameter one object list of a specific list class:

PrototypeDatabase linkedDatabase=new PrototypeDatabase(new LinkedList());
or

PrototypeDatabase arrayDatabase=new PrototypeDatabase(new ArrayList());

This object list will be a protoype for all new lists to create. When you need to create a new list, instead of doing it in the following manner: 

List newList=new ArrayList(); or List newList=new LinkedList();
write :

List newList=prototype.clone(); (*)

PS1. to use ArrayList, LinkedList and HashMap include this line at the begining of your program:
import java.util.*; 
Look at the description of these classes in the Java API documentation.

(*) 
Actually, it is not possible to call 'clone()' method from the interface type List (and the real list class is unknown).
To do it, use the following construction without asking too much :-) (it uses the Java reflections mechanism):

try {\
newList=(List)(prototype.getClass().getMethod("clone",new Class[0]).invoke(prototype, new Object[0]));\
} catch (Exception e) {e.printStackTrace();}
and ignore all warnings.

# 5-Snake - iterator

Compile and run the given program:https://github.com/mhmtnasif/Design-Patterns/blob/master/skeleton/SnakeIterator.txt 
then analyze it.

Inside - you will find a board being a mesh of tiles. Clicking a tile stats a new thread changing colors of the following tiles.

In the present form, the thread goes through the tiles using a double 'for' loop statement (in method run). 

Modify the thread class to use (instead of loops) an iterator object to visit all tiles. Iterator class:

* should keep the current state of iteration (position of the current tile),
* should have the code to proceed with iteration: 
* method next going to the next tile and returning it,
* method hasNext to check if there is a next tile
(you can omit the remove method).
Also add to the Board class a new method creating the iterator object.
You don't need anymore three methods here: getRows(),getCols() andgetAt().

At the end write few other versions of Iterator class changing the order of "visiting" tiles (eg. random order or reverse order or spiral order or ...) and use them randomly, in succession or give a choice to the user.

# 6 -Memento excercise - Stack with undo and redo operations 

Implement the **Stack** class with the same functionalities as in the Command excercise (undo and redo), but using the alternative design pattern: ** Memento ** The Originator and Caretaker roles will be played by the same class - the Stack.

In this version, each 'pop' and 'push' command, before changing the stack, should create a memento - object holding a copy of the stack current state (i.e. a colection of its numbers) - and store it in a first list of mementos 

The 'undo' operation should take the last memento (from the the first list of mementos) and pass it to a special function using this memento to replace the current stack state. 

Before doing so, a new memento, representing the current stack state, should be created and stored in a second list of mementos. This second list will be used by the 'redo' operations. 

In general, (1)'pop', (2)'push', (3)'redo' and (4)'undo' operations create a new memento (representing the current stack state), but (1-3) store it in the first list, and (4) in the second list of mementos.
(3-4) restores the stack state from a memento read from one of the mementos lists.

Below is presented an example of same operations as in the Command excercise and the content of internal stack lists: 

(new created memento)\
Stack S = new Stack(); S={}, 1={}, 2={}, \
S.push(2); S={2}, 1={M{}}, 2={}, \
S.push(4);S={2, 4}, 1={M{}, M{2}}, 2={}, \
S.push(6); S={2, 4, 6}, 1={M{}, M{2}, M{2,4}}, 2={}, \
S.undo(); S={2, 4},1={M{}, M{2}}, 2={M{2,4,6}}\
S.undo(); S={2}, 1={M{}}, 2={M{2,4,6}, M{2,4}}\
S.redo(); S={2, 4}, 1={M{}, M{2}}, 2={M{2,4,6}}\
S.push(10); S={2, 4, 10}, 1={M{}, M{2}, M{2,4}}, 2={}\
* S.redo() => impossible, since no previous 'undo' memento\
S.pop(); S={2, 4}, 1={M{}, M{2}, M{2,4}, M{2,4,10}}, 2={}\
S.undo(); S={2, 4, 10}, 1={M{}, M{2}, M{2,4}}, 2={M{2,4}}\
S.redo(); S={2, 4},1={M{}, M{2}, M{2,4}, M{2,4,10}}, 2={}\

# 7- Observer excercise - MoneyUnit observing ExchangeRatio

* Observer
Using the Java library interface Observer and class Observable (read about them in Java API Documentation) implement a following currency system:

A class MoneyBag contains a collection (list) of MoneyUnits. Each MoneyUnit is an amount of money in a specific currency (USD, PLN, EUR, GBP, ...). It has also a third field: value holding the value of this unit in Euro independently of the real unit currency.

In the system there is also a CurrencyRatios class containing a collection of ExchangeRatios for each used in the system currency (ex. for PLN - how many PLN one should pay for 1 EUR). ExchangeRatio objects can be stored in CurrencyRatios object in a HashMap<String,ExchangeRatio> for easier currency finding. This class can be implemented with the Singleton pattern.

When one currency exchange ratio changes, all MoneyUnit expressed in this currency should update their value fields (expressed always in EUR). To achieve this, make each MoneyUnit observing (by implementing the Observer interface and registering it to the correct ExchangeRatio object) one specific ExchangeRatio (which should inherit from the Observable class)

Create one or more MoneyBags filled with several MoneyUnits in different currencies. Change few exchange ratios and observe, how the money unit values react and stay up to date.


* Hint:
1. each method changing the exchange ratio should also call a setChanged() method (inherited from the Observable class) before the notifyObservers method (also inherited from the Observable class) is called .
2. managing the observers (e.g. specifying which observer observes which observable - in other words: registering observers to the observable) should be done using methods inherited from the Observable class (addObserver, ...)

# 8- Singleton and Multiton
Database (table of characters) can be accessed only through a class implementing the interface type IConnection. The Database should be the Singleton but only a concrete connection should use it (not the main function). The connections should be Multitons: there exists only three of them returned by the getInstance method in the round-robin fasion (1 2 3 1 2 3 ...). 
The client (main function) gets the connection object and using them it accesses the database. 
Complete the following code. In the main function: get 4 connections and prove that they all use the same database. Then prove also that in fact (inspite of 4 initialized references) there exists only 3 connection objects. 


https://github.com/mhmtnasif/Design-Patterns/blob/master/skeleton/singleton

# 9- State - Flying flies

Given is the following program FlyStates.java:https://github.com/mhmtnasif/Design-Patterns/blob/master/skeleton/FlyStates.java.txt

simulation flying flies in 2D space. Every fly has randomly chosen: initial position and constant velocity. After touching the window border the fly rebounds and for 2 seconds gets dizzy: it changes its color and slows its moving.

Change this program according to the State design pattern to move all the code dependent on the fly state to separate state classes. Fly begins with the normal state, changes it to the dizzy one after hitting the border and returns to the original one after 2 seconds. 

Also add a third state of "freezing" following the "dizzy" one: after hitting the border and being 2 seconds "dizzy" the fly gets frozen for the following 2 seconds: it stops and changes color to yellow before returning to the normal state. Remark how it is easy to add new states.
At the end add a new state - frozen - after getting dizzy the fly should stop for 2 seconds without moving, changing its state to yellow. Then it should return to its normal (initial) state.
# 10- Strategy excercise - tic tac toe game
You have attached a simple SWING TicTacToe game. https://github.com/mhmtnasif/Design-Patterns/blob/master/skeleton/TicTacToe.java.txt 
It uses the Strategy design pattern to calculate the next computer move. The algorithm employed is very easy - it takes a random free field as the next computer move. Write 2 more strategies to make the game harder to the player:
* defensive - looking for the player marks ("O") sequences and putting the computer mark ("X") at one end of the sequence
* offensive - building its own sequence of winning computer marks ("X").
It should be possible to change the strategy in run-time.

If you are familiar with Java Swing Framework, add a start window that allows the player to choose:
* size of the board
* length of the winning sequence 

# 11-Template Method excercise - Tic Tac Toe game again
Change the Tic Tac Toe game : https://github.com/mhmtnasif/Design-Patterns/blob/master/skeleton/TicTacToe.java.txt
to implement the Template Method pattern instead of the Strategy one.

To do this:
* make the Game class abstract with the mehod 'calculateNextMove' as its abstract memeber
* each concrete "strategy" will be represented as a class inheriting from the Game implementing the 'calculateNextMove' method in its specific manner
 
# 12-Visitor excercise
Write a figures hierarchy (Figure, Circle, Square, Triangle - every side equal). Use the Visitor pattern to add to them the following methods: 'area', 'move', 'scale'.

* How to use it.

VisitorMethod s2 = new Scale(2);\
VisitorMethod s3 = new Scale(3);\
Figure[] figures=...;\
for (Figure f: figures) f.call(s2); //and not f.scale(2);\
