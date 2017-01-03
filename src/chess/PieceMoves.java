/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;
import java.util.ArrayList;

/**
 *
 * @author Parikh
 */
public class PieceMoves {
    
    //PieceMoves variable initialization
    String p, pC;
    int x, y;
    Boolean wT, kmW, kmB;
    
    //Constructor method
    public PieceMoves(int[]c, String piece, String pieceC, Boolean whiteTurn, Boolean kmW, Boolean kmB){
        this.y = c[1];
        this.x = c[0];
        this.p = piece;
        this.pC = pieceC;
        this.wT = whiteTurn;
        this.kmB = kmB;
        this.kmW = kmW;
    }
    
    //finds the minimum number between two
    public int min(int x, int y){
        if(x<y){
            return x;
        }
        else{
            return y;
        }
    }
    
    //Gets coord of selected piece
    public ArrayList<Integer> getCoord(){
        //Variable initialization
        ArrayList<Integer> pieceOptions = new ArrayList<Integer>();
        Chess c = new Chess();
        String[][] chessBoard = c.getChessBoard();
        String[][] chessType = c.getChessType();
        String oppositeC;
        String sameC;
        
        //Finding opposite players colour and our own
        if(wT){
            sameC = "W";
            oppositeC = "B";
        }
        else{
            sameC = "B";
            oppositeC = "W";
        }
        
        //is selected piece my own piece
        if(sameC.equals(chessType[y][x])){
            //switching piece name
            switch(p){
                //if its a rook
                case "R":
                    //Finds all the possible piece options for upward movement
                    for(int r = 1; r<y+1; r++){
                        if (chessBoard[y-r][x].equals(" ") || chessType[y-r][x].equals(oppositeC)){
                            pieceOptions.add(x);
                            pieceOptions.add(y-r);
                            if(chessType[y-r][x].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //Finds all the possible piece options for downward movement
                    for(int r = 1; r<8-y; r++){
                        if (chessBoard[y+r][x].equals(" ") || chessType[y+r][x].equals(oppositeC)){
                            pieceOptions.add(x);
                            pieceOptions.add(y+r);
                            if(chessType[y+r][x].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //Finds all the possible piece options for left movement
                    for(int r = 1; r<x+1; r++){
                        if (chessBoard[y][x-r].equals(" ") || chessType[y][x-r].equals(oppositeC)){
                            pieceOptions.add(x-r);
                            pieceOptions.add(y);
                            if(chessType[y][x-r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //Finds all the possible piece options for right movement
                    for(int r = 1; r<8-x; r++){
                        if (chessBoard[y][x+r].equals(" ") || chessType[y][x+r].equals(oppositeC)){
                            pieceOptions.add(x+r);
                            pieceOptions.add(y);
                            if(chessType[y][x+r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //return rook move options
                    return pieceOptions;
                    
                //if its a horse
                case "H":
                    //ZIHAN
                    if (y-2 >= 0 && x-1>= 0 && (chessBoard[y-2][x-1].equals(" ") || chessType[y-2][x-1].equals(oppositeC))){
                        pieceOptions.add(x-1);
                        pieceOptions.add(y-2);

                    }

                    //up and left
                    if (y-1 >= 0 && x-2 >=0 && (chessBoard[y-1][x-2].equals(" ") || chessType[y-1][x-2].equals(oppositeC)) ){
                        pieceOptions.add(x-2);
                        pieceOptions.add(y-1);

                    }

                    if (y + 1 <= 7){

                        if (y+1<= 7 && x-2 >= 0 && (chessBoard[y+1][x-2].equals( " ") || chessType[y+1][x-2].equals(oppositeC))){
                            pieceOptions.add(x-2);
                            pieceOptions.add(y+1);

                        }  

                        if (x + 2 <= 7){
                            if (y+1 <= 7 && x+2 <= 7 && (chessBoard[y+1][x+2].equals(" " )|| chessType[y+1][x+2].equals(oppositeC))){
                                pieceOptions.add(x+2);
                                pieceOptions.add(y+1);

                           }
                        }                   
                    }

                    if (x + 1 <= 7){
                            if ( y-2 >= 0 && x+1 <=7 && (chessBoard[y-2][x+1].equals(" ") || chessType[y-2][x+1].equals(oppositeC))){
                                pieceOptions.add(x+1);
                                pieceOptions.add(y-2);

                            }                

                            if (y + 2 <= 7){
                                if ( y+2 <= 7 && x+1 <= 7 && (chessBoard[y+2][x+1].equals( " ") || chessType[y+2][x+1].equals(oppositeC))){
                                    pieceOptions.add(x+1);
                                    pieceOptions.add(y+2);

                                }
                        }
                    }

                    if (y + 2 <= 7){
                            if ( y+2 <= 7 && x-1 >=0 &&(chessBoard[y+2][x-1].equals(" ") || chessType[y+2][x-1].equals(oppositeC))){
                                pieceOptions.add(x-1);
                                pieceOptions.add(y+2);

                            }               

                        if (x + 2 <= 7){
                            if ( y-1 >= 0 && x+2 <= 7 && (chessBoard[y-1][x+2].equals(" ") || chessType[y-1][x+2].equals(oppositeC))){
                                pieceOptions.add(x+2);
                                pieceOptions.add(y-1);

                            }
                        }
                    }


                    //System.out.println("HORSE");
                    return pieceOptions;
                case "B":
                    //System.out.println("BISHOP");
                    //top left
                    for(int r = 1; r<min(x,y)+1; r++){
                        if (chessBoard[y-r][x-r].equals(" ") || chessType[y-r][x-r].equals(oppositeC)){
                            pieceOptions.add(x-r);
                            pieceOptions.add(y-r);
                            if(chessType[y-r][x-r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //top right
                    for(int r = 1; r<min(7-x, y)+1; r++){
                        if (chessBoard[y-r][x+r].equals(" ") || chessType[y-r][x+r].equals(oppositeC)){
                            pieceOptions.add(x+r);
                            pieceOptions.add(y-r);
                            if(chessType[y-r][x+r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //bottom left
                    for(int r = 1; r<min(x, 7-y)+1; r++){
                        if (chessBoard[y+r][x-r].equals(" ") || chessType[y+r][x-r].equals(oppositeC)){
                            pieceOptions.add(x-r);
                            pieceOptions.add(y+r);
                            if(chessType[y+r][x-r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //bottom left
                    for(int r = 1; r<min(7-x, 7-y)+1; r++){
                        if (chessBoard[y+r][x+r].equals(" ") || chessType[y+r][x+r].equals(oppositeC)){
                            pieceOptions.add(x+r);
                            pieceOptions.add(y+r);
                            if(chessType[y+r][x+r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    return pieceOptions;
                case "Q":
                    //System.out.println("QUEEN");
                    //top left
                    for(int r = 1; r<min(x,y)+1; r++){
                        if (chessBoard[y-r][x-r].equals(" ") || chessType[y-r][x-r].equals(oppositeC)){
                            pieceOptions.add(x-r);
                            pieceOptions.add(y-r);
                            if(chessType[y-r][x-r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //top right
                    for(int r = 1; r<min(7-x, y)+1; r++){
                        if (chessBoard[y-r][x+r].equals(" ") || chessType[y-r][x+r].equals(oppositeC)){
                            pieceOptions.add(x+r);
                            pieceOptions.add(y-r);
                            if(chessType[y-r][x+r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //bottom left
                    for(int r = 1; r<min(x, 7-y)+1; r++){
                        if (chessBoard[y+r][x-r].equals(" ") || chessType[y+r][x-r].equals(oppositeC)){
                            pieceOptions.add(x-r);
                            pieceOptions.add(y+r);
                            if(chessType[y+r][x-r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //bottom left
                    for(int r = 1; r<min(7-x, 7-y)+1; r++){
                        if (chessBoard[y+r][x+r].equals(" ") || chessType[y+r][x+r].equals(oppositeC)){
                            pieceOptions.add(x+r);
                            pieceOptions.add(y+r);
                            if(chessType[y+r][x+r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //up
                    for(int r = 1; r<y+1; r++){
                        if (chessBoard[y-r][x].equals(" ") || chessType[y-r][x].equals(oppositeC)){
                            pieceOptions.add(x);
                            pieceOptions.add(y-r);
                            if(chessType[y-r][x].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //down
                    for(int r = 1; r<8-y; r++){
                        if (chessBoard[y+r][x].equals(" ") || chessType[y+r][x].equals(oppositeC)){
                            pieceOptions.add(x);
                            pieceOptions.add(y+r);
                            if(chessType[y+r][x].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //left
                    for(int r = 1; r<x+1; r++){
                        if (chessBoard[y][x-r].equals(" ") || chessType[y][x-r].equals(oppositeC)){
                            pieceOptions.add(x-r);
                            pieceOptions.add(y);
                            if(chessType[y][x-r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    //right
                    for(int r = 1; r<8-x; r++){
                        if (chessBoard[y][x+r].equals(" ") || chessType[y][x+r].equals(oppositeC)){
                            pieceOptions.add(x+r);
                            pieceOptions.add(y);
                            if(chessType[y][x+r].equals(oppositeC)){
                                break;
                            }
                        }
                        else{
                            break;
                        }
                    }
                    return pieceOptions;
                case "K":
                    //Can white king castle on left side
                    if (wT && chessBoard[7][0].equals("R") && chessBoard[7][1].equals(" ")&& chessBoard[7][2].equals(" ")&& chessBoard[7][3].equals(" ") && kmW == false){
                        pieceOptions.add(2);
                        pieceOptions.add(7);
                    }
                    //Can white king castle on right side
                    if (wT && chessBoard[7][7].equals("R") && chessBoard[7][5].equals(" ")&& chessBoard[7][6].equals(" ") && kmW == false){
                        pieceOptions.add(6);
                        pieceOptions.add(7);
                    }
                    //Can black king castle on left side
                    if(wT == false && chessBoard[0][0].equals("R") && chessBoard[0][1].equals(" ")&& chessBoard[0][2].equals(" ")&& chessBoard[0][3].equals(" ") && kmB == false){
                        pieceOptions.add(2);
                        pieceOptions.add(0);
                    }
                    //Can black king castle on right side
                    if(wT == false && chessBoard[0][7].equals("R") && chessBoard[0][5].equals(" ")&& chessBoard[0][6].equals(" ") && kmB == false){
                        pieceOptions.add(6);
                        pieceOptions.add(0);  
                    }

                    if (y-1 >= 0 && x+1 <=7 &&(chessBoard[y-1][x+1].equals(" ") || chessType[y-1][x+1].equals(oppositeC))){
                        pieceOptions.add(x+1);
                        pieceOptions.add(y-1);

                    } 


                    if (y-1 >= 0 && x-1 >= 0 && (chessBoard[y-1][x-1].equals(" ") || chessType[y-1][x-1].equals(oppositeC))){
                        pieceOptions.add(x-1);
                        pieceOptions.add(y-1);

                     }


                    if (y-1 >= 0 && (chessBoard[y-1][x].equals(" ")|| chessType[y-1][x].equals(oppositeC))){
                        pieceOptions.add(x);
                        pieceOptions.add(y-1);

                    } 

                    if (x-1 >= 0 && (chessBoard[y][x-1].equals(" ") || chessType[y][x-1].equals(oppositeC))){
                        pieceOptions.add(x-1);
                        pieceOptions.add(y);

                    }

                    if (y + 1 <= 7){
                        if ( y +1 <= 7 && (chessBoard[y+1][x] .equals(" ")|| chessType[y+1][x].equals(oppositeC))){
                            pieceOptions.add(x);
                            pieceOptions.add(y+1);


                        }
                        if (  y+1 <= 7 && x-1 >= 0 && (chessBoard[y+1][x-1].equals(" ") || chessType[y+1][x-1].equals(oppositeC))){
                            pieceOptions.add(x-1);
                            pieceOptions.add(y+1);

                        }
                    }


                    if (x + 1 <= 7){
                        if ( x+1 <= 7 && (chessBoard[y][x+1].equals(" ") || chessType[y][x+1].equals(oppositeC))){
                            pieceOptions.add(x+1);
                            pieceOptions.add(y);


                        }
                        if (y + 1 <= 7){
                            if (y+1 <= 7 && x+1 <=7 && (chessBoard[y+1][x+1].equals(" ") ||chessType[y+1][x+1].equals(oppositeC))){
                                pieceOptions.add(x+1);
                                pieceOptions.add(y+1);

                            }
                        }
                    }

                    //System.out.println("KING");
                    return pieceOptions;
                case "P":
                    if (wT == true){
                        if (y - 1 >= 0){
                            if ( y-1 >= 0 && (chessBoard[y-1][x].equals( " " ))){
                                pieceOptions.add(x);
                                pieceOptions.add(y-1);
                            }


                        if (y - 2 >= 0 && y == 6 && chessBoard[5][x].equals(" ")) {

                            if ( y-2 >= 0 && (chessBoard[y-2][x].equals(" "))){
                                pieceOptions.add(x);
                                pieceOptions.add(y-2);
                            }
                        }


                        if( x - 1 >= 0 && y - 1 >= 0){
                            if (y-1 >= 0 && x-1 >=0 && (chessType[y-1][x-1].equals(oppositeC))){
                                pieceOptions.add(x-1);
                                pieceOptions.add(y-1);
                            }
                        }

                        if (x + 1 <= 7 && y - 1 >= 0){
                            if( y-1 >= 0 && x+1 <= 7 && (chessType[y-1][x+1].equals(oppositeC))){
                                pieceOptions.add(x+1);
                                pieceOptions.add(y-1);
                           }              
                        }
                    }
                }
                else{
                    if (y + 1 <= 7){
                        if ( y+1 <= 7 && (chessBoard[y+1][x].equals( " " ))){
                            pieceOptions.add(x);
                            pieceOptions.add(y+1);
                        }


                        if (y + 2 <= 7 && y == 1 && chessBoard[2][x].equals(" ")) {

                            if ( y+2 <= 7 && (chessBoard[y+2][x].equals(" "))){
                                pieceOptions.add(x);
                                pieceOptions.add(y+2);
                            }
                        }


                        if( x + 1 <= 7 && y + 1 <= 7){
                            if (y+1 <= 7 && x+1 <=7 && (chessType[y+1][x+1].equals(oppositeC))){
                                pieceOptions.add(x+1);
                                pieceOptions.add(y+1);
                            }
                        }

                        if (x - 1 >= 0 && y + 1 <= 7){
                            if( y+1 <= 7 && x-1 >= 0 && (chessType[y+1][x-1].equals(oppositeC))){
                                pieceOptions.add(x-1);
                                pieceOptions.add(y+1);
                           }              
                        }
                    }
                }
                return pieceOptions;
            }
        }
        return pieceOptions;
    }
}

