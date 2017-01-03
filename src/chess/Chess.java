/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

//IMPORTS
import java.awt.*;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;
import static javax.swing.JFrame.EXIT_ON_CLOSE;



public class Chess extends JFrame implements KeyListener, MouseListener{
    
    //Initialization of variables
    Toolkit tk = Toolkit.getDefaultToolkit();
    
    int xSize = (int) tk.getScreenSize().getWidth();
    int ySize = (int) tk.getScreenSize().getHeight();
    int whitePoints, blackPoints = 0;
    int mouseX, mouseY;
    int numSquares = 8;
    int boardSize = 800;
    int increment = boardSize / numSquares;
    
    int[] c, cTemp;
    
    static Chess g;
        
    boolean kingMovedW, kingMovedB = false;
    boolean whiteTurn = true;
    
    ArrayList<Integer> pPos = new ArrayList<>();
    
    ArrayList<String> sideWhite = new ArrayList<>();
    ArrayList<String> sideBlack = new ArrayList<>();
    
    String[] wPieces = {"wPawnSprite.gif","wRookSprite.gif","wKnightSprite.gif","wBishopSprite.gif","wQueenSprite.gif","wKingSprite.gif"};
    String[] bPieces = {"bPawnSprite.gif","bRookSprite.gif","bKnightSprite.gif","bBishopSprite.gif","bQueenSprite.gif","bKingSprite.gif"};
    String[] pieceNames = {"P", "R", "H", "B", "Q", "K"};

    static String[][] chessBoard = new String[][]{
        {"R", "H", "B", "Q", "K", "B", "H", "R"},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"P", "P", "P", "P", "P", "P", "P", "P"},
        {"R", "H", "B", "Q", "K", "B", "H", "R"}
    };
    
    static String[][] chessType = new String[][]{
        {"B", "B", "B", "B", "B", "B", "B", "B"},
        {"B", "B", "B", "B", "B", "B", "B", "B"},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {" ", " ", " ", " ", " ", " ", " ", " "},
        {"W", "W", "W", "W", "W", "W", "W", "W"},
        {"W", "W", "W", "W", "W", "W", "W", "W"}
    };
    
    Image[]  wImage = new Image[wPieces.length];
    Image[]  bImage = new Image[bPieces.length]; 


    //Gets piece image
    public Image getPieceImage(String piece, String type){
       for (int i = 0; i < pieceNames.length; i++){
           //if piece name matches piece
            if (pieceNames[i].equals(piece)){
                //b or w piece?
                if (type.equals("B")){
                    return bImage[i]; 
                }
                else{
                    return wImage[i];
                }
            }
        }
       return null;
    }
    
    //loads all the images into arrays
    void loadImages() throws IOException {
        
        for (int i = 0; i<wPieces.length;i++){
            //loading b and w piece images
            wImage[i] = tk.getImage(wPieces[i]); 
            bImage[i] = tk.getImage(bPieces[i]); 
        }

    }
    
    //Initializing the window
    public void initializeWindow(){
       setTitle("Chess");
       setUndecorated(true);
       setAlwaysOnTop(true);
       setResizable(false);
       setSize(xSize,ySize);         
       setDefaultCloseOperation(EXIT_ON_CLOSE);            
       setVisible(true); 
    }
    
    //Escape button to exit the program
    @Override
    public void keyPressed(KeyEvent e){
        
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE){ 
            System.exit(0);
        }
       
    }
    
    //Draws the background
    public void drawBack(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(0,0,xSize,ySize);
    }

    //ZIHANs
    public void drawBoard(Graphics g){
        int XforSidebar = 0;
        int alignmentFactor = (int) (boardSize/this.numSquares *0.175);
        int Y = (ySize-boardSize)/2;
        for (int i = 0;i < 8;i++){
            int X = (ySize-boardSize)/2 +(xSize-ySize)/2 ;
            for(int j = 0;j < 8;j++){
                if (j%2 == 0){
                    if (i%2 == 0){
                       g.setColor(Color.decode("#E9C2A6")); 
                    }
                    else{
                        g.setColor(Color.decode("#5C3317"));
                    }
                }
                else{
                    if (i%2 == 0){
                       g.setColor(Color.decode("#5C3317")); 
                    }
                    else{
                        g.setColor(Color.decode("#E9C2A6"));
                    }
                }

                //Lighting up the selected square
                if (X < mouseX && X+increment > mouseX && Y < mouseY && Y+increment > mouseY){
                    if (!" ".equals(chessBoard[i][j])){
                        g.setColor(Color.BLUE);
                    }
                }
                
                //Lighting up all the piece possible moves
                for(int k = 0; k<pPos.size(); k+=2){
                    
                    if(j == pPos.get(k) && i == pPos.get(k+1)){
                        g.setColor(Color.BLUE);
                    }
                }
                    
                
                g.fillRect(X, Y, increment, increment);
                g.setColor(Color.BLACK);
                g.drawRect(X, Y, increment, increment);
                
                //Drawing a chess piece at specified location
                if (!" ".equals(chessBoard[i][j])){
                    //Getting the piece image
                    Image p = getPieceImage(chessBoard[i][j], chessType[i][j]);
                    //Drawing the piece image
                    g.drawImage(p, X+alignmentFactor, Y+alignmentFactor, increment - alignmentFactor*2, increment-alignmentFactor*2, this);
                }
                
                X += increment;
                XforSidebar = X;
                
            }
            Y += increment;
        }
        g.setColor(Color.decode("#B6B6B4"));      
        int YtakenBlack = (ySize-boardSize)/2;
        for (int i = 0;i < 8;i++){
            int XtakenBlack = (ySize-boardSize)/2 +(xSize-ySize)/2 -2*increment - increment/4 ;
            for(int j = 0;j < 2;j++){
                g.fillRect(XtakenBlack, YtakenBlack, increment, increment);
                
                if (sideWhite.size() > ((2*i)+(j+1))){
                    Image p = getPieceImage(sideWhite.get(((2*i)+(j+1))), "B");
                    g.drawImage(p, XtakenBlack+alignmentFactor, YtakenBlack+alignmentFactor, increment - alignmentFactor*2, increment-alignmentFactor*2, this);
                    
                }
                
                
                XtakenBlack += increment;
                
                
                
            }
           YtakenBlack += increment; 
        }
        
        int YtakenWhite = (ySize-boardSize)/2;
        for (int i = 0;i < 8;i++){
            int XtakenWhite = XforSidebar + increment/4 ;
            for(int j = 0;j < 2;j++){
                g.fillRect(XtakenWhite, YtakenWhite, increment, increment);
                if(sideBlack.size() > ((2*i)+(j+1))){
                    Image p = getPieceImage(sideBlack.get(((2*i)+(j+1))), "W");
                    g.drawImage(p, XtakenWhite+alignmentFactor, YtakenWhite+alignmentFactor, increment - alignmentFactor*2, increment-alignmentFactor*2, this);
                }
                XtakenWhite += increment;
                
                
                
            }
           YtakenWhite += increment; 
        }
        g.setColor(Color.white);
        g.drawString("Points: " + whitePoints, ((ySize-boardSize)/2 +(xSize-ySize)/2)/2, (ySize-boardSize)/4);
        g.drawString("Points: " + blackPoints, (xSize-(((ySize-boardSize)/2 +(xSize-ySize))/2)), (ySize-boardSize)/4); 
    }    

    //Getting a square coord based on mouse clicked
     public int[] whatSquareCord(){
        int Y = (ySize-boardSize)/2;
        for (int j = 0;j < 8;j++){
            int X = (ySize-boardSize)/2 +(xSize-ySize)/2 ;
            for(int i = 0;i < 8;i++){
                //if mouse clicked is in the specified i and j sqaure
                if (X < mouseX && X+increment > mouseX && Y < mouseY && Y+increment > mouseY){
                    return new int[]{i, j};
                }
                X += increment;
            }
            Y += increment;
        }
        return null;
    }
    
    //Getting piece
    public String whatPieceisIt(){
         return chessBoard[c[1]][c[0]];
    }
    
    //Getting piece type
    public String whatColourisIt(){
        return chessType[c[1]][c[0]];
    }
    
    //Getting chess board
    public String[][] getChessBoard(){
        return chessBoard;
    }
    
    //Getting chess type
    public String[][] getChessType(){
        return chessType;
    }
    
    //painting the screen
    public void paint(Graphics g){
        //Background
        drawBack(g);
        //The grid and chess pieces
        drawBoard(g);

    }
    
    //Moving a piece
    public void movePiece(){
        for(int k = 0; k<pPos.size(); k+=2){
            if(c[0] == pPos.get(k) && c[1] == pPos.get(k+1)){
                
                //ZIHAN
                if((whiteTurn) && (!(" ").equals(chessBoard[c[1]][c[0]]))){
                        sideWhite.add(chessBoard[c[1]][c[0]]);
                        if (chessBoard[c[1]][c[0]].equals("P")){
                            whitePoints ++;
                        }
                        else if (chessBoard[c[1]][c[0]].equals("H") ||chessBoard[c[1]][c[0]].equals("B") ){
                            whitePoints += 3;
                        }
                        else if (chessBoard[c[1]][c[0]].equals("R")){
                            whitePoints +=5;
                        }
                        
                        else if (chessBoard[c[1]][c[0]].equals("Q")){
                            whitePoints += 9;
                        }
                }
                else if((whiteTurn == false) && (!(" ").equals(chessBoard[c[1]][c[0]]))){
                        sideBlack.add(chessBoard[c[1]][c[0]]);
                        if (chessBoard[c[1]][c[0]].equals("P")){
                            blackPoints ++;
                        }
                        else if (chessBoard[c[1]][c[0]].equals("H") ||chessBoard[c[1]][c[0]].equals("B") ){
                            blackPoints += 3;
                        }
                        else if (chessBoard[c[1]][c[0]].equals("R")){
                            blackPoints +=5;
                        }
                        
                        else if (chessBoard[c[1]][c[0]].equals("Q")){
                            blackPoints += 9;
                        }
                }   
                    
                
                //If piece being moved is a king
                if(chessBoard[cTemp[1]][cTemp[0]].equals("K")){
                    //Making kingMovedB/W true to prevent future castling
                    if(chessType[cTemp[1]][cTemp[0]].equals("W")){
                        kingMovedW = true;
                    } else if(chessType[cTemp[1]][cTemp[0]].equals("B")){
                        kingMovedB = true;
                    } 
                    
                    //white turn
                    if(whiteTurn){
                        //Switching rook if castling on left
                        if(pPos.get(k) == 2 && pPos.get(k+1) == 7 && cTemp[1] == 7 && cTemp[0] == 4){
                            chessBoard[7][0] = " ";
                            chessType[7][0] = " ";
                            chessBoard[7][3] = "R";
                            chessType[7][3] = "W";
                        }
                        //Switching rook if castling on right
                        else if(pPos.get(k) == 6 && pPos.get(k+1) == 7 && cTemp[1] == 7 && cTemp[0] == 4){
                            chessBoard[7][7] = " ";
                            chessType[7][7] = " ";
                            chessBoard[7][5] = "R";
                            chessType[7][5] = "W";
                        }
                    }
                    //black turn
                    else{
                        //Switching rook if castling on left
                        if(pPos.get(k) == 2 && pPos.get(k+1) == 0 && cTemp[1] == 0 && cTemp[0] == 4){
                            chessBoard[0][0] = " ";
                            chessType[0][0] = " ";
                            chessBoard[0][3] = "R";
                            chessType[0][3] = "B";
                        }
                        //Switching rook if castling on right
                        else if(pPos.get(k) == 6 && pPos.get(k+1) == 0 && cTemp[1] == 0 && cTemp[0] == 4){
                            chessBoard[0][7] = " ";
                            chessType[0][7] = " ";
                            chessBoard[0][5] = "R";
                            chessType[0][5] = "B";
                        }
                    }
                }
                
                //Displaying who won the chess game
                if(chessBoard[c[1]][c[0]].equals("K")){
                    String text;
                    setAlwaysOnTop(false);
                    //if whtie wins
                    if (chessType[c[1]][c[0]].equals("B")){
                        text = "Chess Game Status: White Wins";
                    }
                    //if black wins
                    else{
                        text = "Chess Game Status: Black Wins";
                    }
                    //Displaying the message
                    JOptionPane.showMessageDialog(null, text, "Chess",JOptionPane.WARNING_MESSAGE);
                    
                    //Exit the game
                    System.exit(0);
                }

                
                //Moving piece from one location to another
                chessBoard[c[1]][c[0]] = chessBoard[cTemp[1]][cTemp[0]];
                chessType[c[1]][c[0]] = chessType[cTemp[1]][cTemp[0]];
                chessBoard[cTemp[1]][cTemp[0]] = " ";
                chessType[cTemp[1]][cTemp[0]] = " ";
                //Switching turns
                if(whiteTurn){
                    whiteTurn = false;
                }
                else{
                    whiteTurn = true;
                }
            }
        }
    }
    
    
    //If pawn makes it to the end promote it
    public void checkPromotion(){
        //Checking promotion for white
        for(int i =0; i < 7; i++){
            if (chessBoard[0][i].equals("P")){
                chessBoard[0][i] = "Q";
            }
        }
        //Checking promotion for black
        for(int j =0; j < 7; j++){
            if (chessBoard[7][j].equals("P")){
                chessBoard[7][j] = "Q";
            }
        }
    }
    
    //Maint method
    public static void main(String[] args) throws IOException {
        //Initilizing chess
        g = new Chess();
        g.sideWhite.add(" ");
        g.sideBlack.add(" ");
        g.initializeWindow();
        g.addKeyListener(g);
        g.addMouseListener(g);
        g.loadImages();
        g.repaint();       
    }
    
    //mouse cliked
    @Override
    public void mouseClicked(MouseEvent e) {
        //Get mouse click location
        mouseX = e.getX();
        mouseY = e.getY();
        //convert mouse location to coords
        cTemp = c;
        c = whatSquareCord();
        //Move a piece if applicable and check for promotion
        movePiece();
        checkPromotion();
        //reseting possible positions
        pPos = new ArrayList<Integer>();
        //Check selection is not blank
        if(!" ".equals(chessBoard[c[1]][c[0]])){
            //Get piece and its colour
            String p = whatPieceisIt();
            String pc = whatColourisIt();
            //Get the pieces possible moves
            PieceMoves pm = new PieceMoves(c, p, pc, whiteTurn, kingMovedW, kingMovedB);
            pPos.addAll(pm.getCoord());
        }
        //Repaint the screen
        g.repaint();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void keyTyped(KeyEvent e){  
        
    }
    
    public void keyReleased(KeyEvent e){
        
    }
}
 