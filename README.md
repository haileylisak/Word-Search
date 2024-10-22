# CS 0445 – Algorithms and Data Structures 1 – Assignment #3

Due: Friday Novemver 17th @ 11:59pm on Gradescope

Late submission deadline: Sunday November 19th @11:59pm with 10% penalty per late day

You should submit the Java file `WordSearchPuzzle.java` to GradeScope (the link is on Canvas). You must also submit an Assignment Information Sheet `InfoSheet.md` as described below.

## Table of Contents

- [Overview](#overview)
- [Background](#background)
- [TASK 1 - Load a puzzle from a 2-d array of letters](#TASK-1---Load-a-puzzle-from-a-2-d-array-of-letters)
- [TASK 2 - Retrieve a deep copy of the underlying 2-d array of letters](#TASK-2---Retrieve-a-deep-copy-of-the-underlying-2-d-array-of-letters)
- [TASK 3 - Find a word in the puzzle](#TASK-3---Find-a-word-in-the-puzzle)
- [Test Program](#test-program)
- [Submission Requirements](#submission-requirements)
- [Rubrics](#rubrics)

## Overview
 
* __Purpose__:  To understand and modify a backtracking algorithm that finds words inside a grid of lower-case letters as specified in detail below.

The class `WordSearchPuzzle` implements `WordSearchInterface` defined in `WordSearchInterface.java`. The interface has three methods that are partially implemented in `WordSearchPuzzle.java`.

```java
 public interface WordSearchInterface {

    //The eight directions of cell adjacency
    public enum Direction {N, NE, E, SE, S, SW, W, NW};

    /**
     * loads an n-by-n Word Search Puzzle from a 2-d array of characters
     * @param puzzle the char[][] array containing the puzzle characters
     * @param n the int puzzle size
     */
    public void initialize(char[][] puzzle, int n);

    /**
     * finds a given word in the Puzzle. The solution is a Stack of unique cells 
     * containing-from bottom to top-the letters of the word and each two 
     * consecutive cells in the Stack are adjacent in the grid.
     * 
     * @param word the String word to find
     * @return a stack of Cells with last letter's position at the top 
     *          or null if word is not found in the grid.
     */
    public Stack<Cell> findWord(String word);

    /**
     * retrieves a deep copy of the underlying grid of lower-case letters. 
     * @return a deep copy of the 2-d char array representing the underlying 
     *          grid
     */
    public char[][] getPuzzle();  
    
}
```

## Background

We will implement a modified version of "Word search" puzzles, in which a 2-dimensional grid of letters is presented side-by-side with a list of words to find inside the grid.  Our modified problem is different from the common format in the following aspects: (1) words may be formed by adjacent cells in _any_ direction – N, NE, E, SE, S, SW, W, NW, and (2) the eight directions *wrap around* the edges of the grid, that is, the first and last row are adjacent and the first and last column are adjacent.

## TASK 1 - Load a puzzle from a 2-d array of letters


```java
    /**
     * loads an n-by-n Word Search Puzzle from a 2-d array of characters
     * @param puzzle the char[][] array containing the puzzle characters
     * @param n the int puzzle size
     */
    public void initialize(char[][] puzzle, int n);
```


## TASK 2 - Retrieve a deep copy of the underlying 2-d array of letters

```java
     /**
     * retrieves a deep copy of the underlying grid of lower-case letters. 
     * @return a deep copy of the 2-d char array representing the underlying 
     *          grid
     */
    public char[][] getPuzzle();  
```


## TASK 3 - Find a word in the puzzle

```java
    /**
     * finds a given word in the Puzzle. The solution is a Stack of unique cells 
     * containing-from bottom to top-the cells holding the letters of the word 
     * such that each two consecutive cells in the Stack are adjacent in the grid.
     * 
     * @param word the String word to find
     * @return a stack of Cells with last letter's position at the top 
     *          or null if word is not found in the grid.
     */
    public Stack<Cell> findWord(String word);
```
Read the `solve` method inside `WordSearchPuzzle.java` and notice the introduction of a method parameter
`Stack<Cell> positions`.

## Test Program

You can use the test program `A4Test.java` to test your implementation. Below is a sample run of that program.

```
> java A4Test 
*********************************
Welcome to Four Four Five Word Puzzle Program!
1. Read a puzzle from a file
2. Display the puzzle
3. Find a word in the puzzle
4. Exit.
*********************************
Please choose a menu option (1-4): 1
Please enter filename:
445.txt
Puzzle file loaded successfully!
Press ENTER to continue ...
*********************************
Welcome to Four Four Five Word Puzzle Program!
1. Read a puzzle from a file
2. Display the puzzle
3. Find a word in the puzzle
4. Exit.
*********************************
Please choose a menu option (1-4): 2
b l n k s r e o o m h s 
e a o c y t s i l e o t 
s t g a r e r t r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s q u e u e r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Press ENTER to continue ...
Please enter a word to search for:
bag
B l n k s r e o o m h s 
e A o c y t s i l e o t 
s t G a r e r t r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s q u e u e r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
stack
b l n K s r e o o m h s 
e a o C y t s i l e o t 
s t g A r e r t r r g t 
d r l T a s w e s g i t 
e o a S n n o g o e b t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s q u e u e r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
list
b l n k s r e o o m h s 
e a o c y t S I L e o t 
s t g a r e r T r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s q u e u e r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
queue
b l n k s r e o o m h s 
e a o c y t s i l e o t 
s t g a r e r t r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s Q U E U E r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
quicksort
b l n k s r e o o m h s 
e a o c y t s i l e o t 
s t g a r e r t r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o Q U I C K S O R T s 
r i s q u e u e r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
mergesort
b l n k s r e o o M h s 
e a o c y t s i l E o t 
s t g a r e r t r R g t 
d r l t a s w e s G i t 
e o a s n n o g o E b t 
z s t t i i t n f S e r 
i n r j b i o a h O l o 
t o q u i c k s o R T s 
r i s q u e u e r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
selectionsort
b L n k s r e o o m h S 
E a o c y t s i l e o t 
s T g a r e r t r r g t 
d R l t a s w e s g i t 
e O a s n n o g o e b t 
z S t t i i t n f s e r 
i N r j b i o a h o l o 
t O q u i c k s o r t s 
r I s q u e u e r t c l 
o T x d r c h o e u e l 
m C i a a c u s c r r e 
a E l r e p r n n i s h 
Please enter a word to search for:
shellsort
b l n k s r e o o m h S 
e a o c y t s i l e o t 
s t g a r e r t r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b T 
z s t t i i t n f s e R 
i n r j b i o a h o l O 
t o q u i c k s o r t S 
r i s q u e u e r t c L 
o t x d r c h o e u e L 
m c i a a c u s c r r E 
a e l r e p r n n i s H 
Please enter a word to search for:
insertionsort
b l n k s r e O o m h s 
e a o c y t s I l e o t 
s t g a r E R T r r g t 
d r l t a S w e s g i t 
e o a s N n o g o e b t 
z s t t I i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s q u e u e R T c l 
o t x d r c h O e u e l 
m c i a a c u S c r r e 
a e l r e p r N n i s h 
Please enter a word to search for:
amortized
b l n k s r e o o m h s 
e a o c y t s i l e o t 
s t g a r e r t r r g t 
D r l t a s w e s g i t 
E o a s n n o g o e b t 
Z s t t i i t n f s e r 
I n r j b i o a h o l o 
T o q u i c k s o r t s 
R i s q u e u e r t c l 
O t x d r c h o e u e l 
M c i a a c u s c r r e 
A e l r e p r n n i s h 
Please enter a word to search for:
binarysearch
b l n k S r e o o m h s 
e a o c Y t s i l e o t 
s t g a R e r t r r g t 
d r l t A s w e s g i t 
e o a s N n o g o e b t 
z s t t I i t n f s e r 
i n r j B i o a h o l o 
t o q u i c k s o r t s 
r i s q u e u e r t c l 
o t x d R C H o e u e l 
m c i a A c u s c r r e 
a e l r E p r n n i s h 
Please enter a word to search for:
bigoh
b l n k s r e o o m H s 
e a o c y t s i l e O t 
s t g a r e r t r r G t 
d r l t a s w e s g I t 
e o a s n n o g o e B t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s q u e u e r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
towersofhanoi
b l n k s r e o o m h s 
e a o c y t s i l e o t 
s t g a r e r t R r g t 
d r l t a s W E S g i t 
e o a s n n O g O e b t 
z s t t i i T N F s e r 
i n r j b I O A H o l o 
t o q u i c k s o r t s 
r i s q u e u e r t c l 
o t x d r c h o e u e l 
m c i a a c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
radixsort
b l n k s r e o o m h s 
e a o c y t s i l e o t 
s t g a r e r t r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b t 
z s T t i i t n f s e r 
i n R j b i o a h o l o 
t O q u i c k s o r t s 
r i S q u e u e r t c l 
o t X D R c h o e u e l 
m c I a A c u s c r r e 
a e l r e p r n n i s h 
Please enter a word to search for:
recursion
b l n k s r e o O m h s 
e a o c y t s i l e o t 
s t g a r e r t r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s q u e u e R t c l 
o t x d r c h o E U e l 
m c i a a c u s C r R e 
a e l r e p r n N I S h 
Please enter a word to search for:
recurrence
b l n k s R E o o m h s 
e a o c y t s i l e o t 
s t g a r e r t r r g t 
d r l t a s w e s g i t 
e o a s n n o g o e b t 
z s t t i i t n f s e r 
i n r j b i o a h o l o 
t o q u i c k s o r t s 
r i s q u E u e r t c l 
o t x d R C h o E u e l 
m c i a a c U s C r r e 
a e l r e p R N n i s h 
Please enter a word to search for:
backtracking
Word was not found in the puzzle
Press ENTER to continue ...
```
## Submission Requirements

You must submit your Github repository to GradeScope. We will only grade the following file:
1)	`WordSearchPuzzle.java`
4)	`InfoSheet.md`: Assignment Information Sheet (including compilation and execution information).

_The idea from your submission is that your TA (and/or the autograder if available) can compile and run your programs from the command line WITHOUT ANY additional files or changes, so be sure to test it thoroughly before submitting it. If the TA (and/or the autograder if available) cannot compile or run your submitted code it will be graded as if the program does not work.
If you cannot get the programs working as given, clearly indicate any changes you made and clearly indicate why on your Assignment Information Sheet.  You will lose some credit for not getting it to work properly, but getting the main programs to work with modifications is better than not getting them to work at all.  A template for the Assignment Information Sheet can be found in this repository. You do not have to use this template, but your sheet should contain the same information._

_Note: If you use an IDE, such as NetBeans, Eclipse, or IntelliJ, to develop your programs, make sure the programs will compile and run on the command-line before submitting – this may require some modifications to your program (e.g., removing package information)._

## Rubrics 

Please note that if an autograder is available, its score will be used as a guidance for the TA, not as an official final score. Please also note that the autograder rubrics are the definitive rubrics for the assignment. The rubrics below will be used by the TA to assign partial credit in case your code scored less than 40% of the autograder score. If your code is manually graded for partial credit, the maximum you can get for the autograded part is 60%.

Item|Points
----|------
`loadPuzzle`|	30
`getPuzzle`|	30
`findWord` | 30
Coding style and documentation|	5
Assignment Information Sheet|	5
