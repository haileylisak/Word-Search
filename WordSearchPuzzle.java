import java.util.Stack;

public class WordSearchPuzzle implements WordSearchInterface {

    private char[][] puzzle; //the 2-d grid of letters
    private int size; //the grid size
    private static Direction[] directions = {Direction.N, Direction.E, 
                                             Direction.S, Direction.W, 
                                             Direction.NE, 
                                             Direction.SE, 
                                             Direction.SW, 
                                             Direction.NW};


    @Override
    /**
     * Loop through every single cell in the word search, one by one.
     * Check if the character in the cell is the same as the first character in @param word.
     * If so, push the cell to the positions stack, update the StringBuilder,
     * and make the character in the cell uppercase.
     * If @param word is only one character, then the word was found and set result to true. 
     * If there are more letters, call the solve method. 
     * If the complete word is found, set result to true.
     * Reset every cell in the word search to lower case.
     * If the word was never found, return null.
     * If the word was found, return the positions stack.
     * @param word the word to find in the word search
     * @return result 
     * @return null if the word was never found
     */
    public Stack<Cell> findWord(String word) 
    {
        Stack<Cell> positions = new Stack<>();
        //TODO complete this method. You will need to call the helper solve method for each 
        //cell in the grid
        boolean result = false;
        StringBuilder str = new StringBuilder();
        word = word.toLowerCase();
        for(int i=0; i<puzzle.length;i++)
        {
            for(int k=0; k<puzzle.length; k++)
            {
              if(puzzle[i][k] == word.charAt(0))
              {
                positions.push(new Cell(i,k));
                str.append(puzzle[i][k]);
                puzzle[i][k] = Character.toUpperCase(puzzle[i][k]);
                if(word.length()==1)
                {
                    result =  true;
                    break;
                }
                    
                else
                {
                    if(solve(i,k,str,word, positions)==true)
                    {
                        result = true;
                        break;
                    } 
                
                }
                positions.pop();
                str.delete(0,str.length());
                puzzle[i][k] = Character.toLowerCase(puzzle[i][k]);
              }
              if(result==true)
              {
                break;
              }

            }
            if(result==true)
            {
                break;
            }
        }

        for(int i=0; i<puzzle.length; i++)
        {
            for(int k=0; k<puzzle.length;k++)
            {
                puzzle[i][k] = Character.toLowerCase(puzzle[i][k]);
            }
        }

        if(positions.size()==0)
        {
            return null;
        }
        
        return positions;
    }

    /**
     * A helper method for findWord. This method uses backtracking to find a word in the 2-d grid of letters puzzle.
     * Loop through every single possible direction around the cell.
     * If the cell is valid and @param wordToFind at the specified index is 
     * equal to the character at the cell, update @param current and push the cell to @param positions.
     * Make the character upper case.
     * If the index is equal to the length of @param wordtoFind, then set result to true.
     * If it is not equal, then recursively call *solve*. 
     * If the word is ever found, set the result to true and break.
     * If not, then delete the last character in @param current, and pop @param positions.
     * Make the cell's character lower case.
     * @param row the row of the starting cell
     * @param col the col of the starting cell
     * @param current the subword found so far
     * @param wordToFind the word to find
     * @param positions the stack of positions corresponding to the subword found fo far
     * @return result
     */    
    private boolean solve(int row, int col, StringBuilder current, String wordToFind, Stack<Cell> positions)
    {
        boolean result = false;
        int index = current.length();
        //for all possible options
        for(int d=0; d<directions.length; d++){ //8 directions

            //TODO Complete the method. You can use the provided helper methods, 
            //nextRow, nextCol, and isValid. Don't forget to push and pop from the positions stack
            //as needed
            int nextRow = nextRow(row,directions[d]);
            int nextCol = nextCol(col,directions[d]);
            if(isValid(nextRow, nextCol)==true && wordToFind.charAt(index)==puzzle[nextRow][nextCol])
            {
                //mark neighbor as used (a.k.a. make letter uppercase in puzzle)
                char letterToChange = puzzle[nextRow][nextCol]; 
                current.append(puzzle[nextRow][nextCol]);
                positions.push(new Cell(nextRow, nextCol));
                puzzle[nextRow][nextCol] = Character.toUpperCase(letterToChange);
                //index++; incrementing here, it increments after it checks wordToFind.charAt(index) so it stops after the second letter
                //ALSO it capitalizes EVERY occurence of each letter
                if(index==wordToFind.length()-1)
                {
                    return true;
                }
                else
                {
                    if(solve(nextRow, nextCol, current, wordToFind, positions))
                    {
                        result =  true;
                        break;
                    }
                    else 
                    {
                        current.deleteCharAt(current.length()-1);
                        positions.pop();
                    }

                }
                puzzle[nextRow][nextCol] = Character.toLowerCase(puzzle[nextRow][nextCol]);
            }    
        }
        return result;
    }

    /**
     * check is a cell is legal and hasn't been used before
     * @param row the row of the cell
     * @param col the col of the cell
     * @return true if cell is legal and false otherwise
     */
    private boolean isValid(int row, int col){
        boolean result = false;
        if((row >= 0) && (col >= 0) 
            && (row < puzzle.length) && (col < puzzle[0].length) 
            && Character.isLowerCase(puzzle[row][col])){
                result = true;
            }
        return result;
    }

    /**
     * return the next row down a certain direction
     * @param row the current row
     * @param direction the direction
     * @return the next row down the given direction
     */
    private int nextRow(int row, Direction direction){
        int result = row;
        if(direction == Direction.N || direction == Direction.NE || 
            direction == Direction.NW){
            result = (row - 1 + size)%size;
        }
        if(direction == Direction.S || direction == Direction.SE || 
            direction == Direction.SW){
            result = (row + 1)%size;
        }
        return result;
    }

    /**
     * return the next column down a certain direction
     * @param row the current column
     * @param direction the direction
     * @return the next column down the given direction
     */
    private int nextCol(int col, Direction direction){
        int result = col;
        if(direction == Direction.E || direction == Direction.NE || 
            direction == Direction.SE){
            result = (col + 1)%size;
        }
        if(direction == Direction.W || direction == Direction.NW || 
            direction == Direction.SW){
            result = (col - 1 + size)%size;
        }
        return result;
    }
   
    /**
     * loads an n-by-n Word Search Puzzle from a 2-d array of characters
     * @param puzzle the char[][] array containing the puzzle characters
     * @param n the int puzzle size
     */
    @Override
    public void initialize(char[][] puzzle, int n) 
    {
        //TODO: implement this method  
        this.puzzle = puzzle;
        size = n;
    }

     /**
     * retrieves a deep copy of the underlying grid of lower-case letters. 
     * @return a deep copy of the 2-d char array representing the underlying 
     *          grid
     */
    @Override
    public char[][] getPuzzle() 
    {
        //TODO: implement this method
        char[][]temp = new char[puzzle.length][puzzle.length];

        for(int i=0; i<puzzle.length; i++)
        {
            for(int j=0; j<puzzle[i].length; j++)
            {
                char copyChar = puzzle[i][j];
                temp[i][j] = copyChar;
            }
        } 
        return temp;
    }    
}

/**
 * nextCol and nextRow to get next col and next row
 * 
 * Backtracking
 * ------------
 * THIS CAN BE FOUND ON THE RECITATION SLIDES
 * boolean solve(current spot in the grid that we are on (row and col of current cell--our choices are 8 directions), how much of the word we have so far)
 * {
 * for each choice at the current decision
 * {
 *      if the word == letter (you cannot reuse letters) && letter has already been used in the word (you'll know if it is capitol then its already been used)
 *          then add to partial solution
 *      if the partial solution is valid, then we are on the last letter and return true
 *      if the word has not been completely found then we run through it all again
 * }
    }


 * you can find an example of word search backtracking example in one of the code respoitory handouts in canvas
 * every time you find valid letter, push the position
 * the stack will include all of the valid positions of the word
 */