/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package elements;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Esta classe é responsável pelo desenrolar do jogo, tem um user, um tabuleiro,
 * uma lista de blocos disponíveis de acordo com o modo e os 3 blocos
 * disponíveis por cada rodada
 * 
 * @author Sérgio Simões
 * @version 2ªFase
 */
public class Game implements Serializable{
    private User user;
    private Board board;
    private Blocks blocks;
    private int currentScore;
    private String blockA;
    private String blockB;
    private String blockC;
    private Block a;
    private Block b;
    private Block c;
    private int availableBlocks;
    
    /**
     * Construtor sem argumentos que inicializa todos os atributos sem valores
     * lógicos ou a zero
     */
    public Game(){
        user = new User();
        board = new Board();
        blocks = new Blocks();
        currentScore = 0;
        blockA = "";
        blockB = "";
        blockC = "";
        a = new Block("");
        b = new Block("");
        c = new Block("");
        availableBlocks = 0;
    }
    
    /**
     * Cria um user com o nome e password dados, um tabuleiro de 9x9, a lista de
     * blocos é criada de acordo com o modo dado, os blocos disponíveis começam
     * ainda a 0 tal como o score do user
     * 
     * @param name, nome do user
     * @param password, password do user
     * @param mode, modo dos blocos
     */
    public Game(String name, String password, Mode mode){
        user = new User(name, password);
        board = new Board(9, 9);
        if(mode == Mode.EASY){
            this.blocks = new Blocks(Mode.EASY);
        }else{
            this.blocks = new Blocks(Mode.HARD);
        }
        currentScore = 0;
        blockA = "";
        blockB = "";
        blockC = "";
        a = new Block("");
        b = new Block("");
        c = new Block("");
        availableBlocks = 0;
    }
    
    /**
     * Retorna o nome do user deste jogo
     * 
     * @return user name
     */
    public String getUserName(){
        return user.getName();
    }
    
    /**
     * Retorna o texto correspondente à opção do bloco A
     * Ex:
     * Bloco A
     * ##
     *  ##
     * @return String com o texto
     */
    public String getBlockA() {
        return blockA;
    }
    
    /**
     * Retorna o texto correspondente à opção do bloco B
     * Ex:
     * Bloco B
     * ##
     *  ##
     * @return String com o texto
     */
    public String getBlockB() {
        return blockB;
    }
    
    /**
     * Retorna o texto correspondente à opção do bloco C
     * Ex:
     * Bloco C
     * ##
     *  ##
     * @return String com o texto
     */
    public String getBlockC() {
        return blockC;
    }
    
    /**
     * Retorna o bloco correspondente à opção A
     * 
     * @return bloco da classe Block
     */
    public Block getA() {
        return a;
    }
    
    /**
     * Retorna o bloco correspondente à opção B
     * 
     * @return bloco da classe Block
     */
    public Block getB() {
        return b;
    }
    
    /**
     * Retorna o bloco correspondente à opção C
     * 
     * @return bloco da classe Block
     */
    public Block getC() {
        return c;
    }
    
    /**
     * Retorna o tabuleiro que está a ser usado neste jogo
     * 
     * @return tabuleiro da classe Board
     */
    public Board getBoard(){
        return this.board;
    }
    
    /**
     * Retorna o número de blocos disponíveis na jogada
     * 
     * @return inteiro correspondente ao número de blocos disponíveis
     */
    public int getAvailableBlocks() {
        return availableBlocks;
    }
    
    /**
     * Altera o texto correspondente à opção do bloco A
     * 
     * @param blockA texto correspondente à opção
     */
    public void setBlockA(String blockA) {
        this.blockA = blockA;
    }
    
    /**
     * Altera o texto correspondente à opção do bloco B
     * 
     * @param blockB texto correspondente à opção
     */
    public void setBlockB(String blockB) {
        this.blockB = blockB;
    }
    
    /**
     * Altera o texto correspondente à opção do bloco C
     * 
     * @param blockC texto correspondente à opção
     */
    public void setBlockC(String blockC) {
        this.blockC = blockC;
    }
    
    /**
     * Muda o tabuleiro que está a ser usado neste jogo
     * (Este método foi criado para poder fazer as verificações quando se coloca
     * um bloco no tabuleiro (ainda não está a ser usado))
     * 
     * @param board, novo tabuleiro para substituir o antigo
     */
    public void setBoard(Board board){
        this.board = board;
    }
    
    /**
     * Decrementa o número de blocos disponíveis na jogada em 1 unidade
     */
    public void decrementAvailableBlocks(){
        availableBlocks--;
    }
    
    /**
     * Imprime o tabuleiro no formato do método toString() da classe Board
     */
    public void showBoard(){
        System.out.println(board.toString());
    }
    
    /**
     * Retorna o atual score do user
     * 
     * @return score atual do user
     */
    public int getCurrentScore() {
        return currentScore;
    }
    
    /**
     * Adiciona ao score do user o valor dado
     * 
     * @param score, valor a adicionar ao score atual do user
     */
    public void setCurrentScore(int score){
        this.currentScore += score;
    }
    
    /**
     * Este método é utilizado quando o jogo termina, avisa o jogador, mostra o
     * score final e "limpa" os atributos do objeto da classe Game (voltam ao
     * estado inicial)
     * 
     * @throws IOException 
     */
    public void finishGame() throws IOException{
        System.out.println("Jogo terminado! Score: " + currentScore);
        User saveUser = user.loadUser(user.getName());
        saveUser.addScore(currentScore);
        saveUser.saveUser();
    }
    
    private ArrayList<Integer> fullColumn(){
        ArrayList<Integer> columns = new ArrayList<>();
        boolean result = true;
        for(int i = 0; i < board.getLenght(); i++){
            result = true;
            for(int j = 0; j < board.getHeight(); j++){
                if(board.get(i, j).equals(".")){
                    result = false;
                }
            }
            if(result == true){
                int c = i+1;
                columns.add(c);
            }
        }
        return columns;
    }
    
    private void clearColumn(int column) throws FilledSquareException{
        for(int i = 1; i < board.getHeight() + 1; i++){
            board.set((char)(column+64), i, ".");
        }
        setCurrentScore(36);
    }
    
    private ArrayList<Integer> fullLine(){
        ArrayList<Integer> lines = new ArrayList<>();
        boolean result = true;
        for(int i = 0; i < board.getLenght(); i++){
            result = true;
            for(int j = 0; j < board.getHeight(); j++){
                if(board.get(j, i).equals(".")){
                    result = false;
                }
            }
            if(result == true){
                int l = i+1;
                lines.add(l);
            }
        }
        return lines;
    }
    
    private void clearLine(int line) throws FilledSquareException{
        for(int i = 1; i < board.getHeight() + 1; i++){
            board.set((char)(i+64), line, ".");
        }
        setCurrentScore(36);
    }
    
    private ArrayList<String> fullSquare(String valor){
        ArrayList<String> coords = new ArrayList<>();
        for(int i = 0; i < board.getLenght(); i+=3){
            for(int j = 0; j < board.getHeight(); j+=3){
                if(board.get(i, j).equals(valor) && board.get(i+1, j).equals(valor)
                 && board.get(i, j+1).equals(valor) && board.get(i+1, j+1).equals(valor)
                 && board.get(i+2, j).equals(valor) && board.get(i, j+2).equals(valor)
                 && board.get(i+2, j+1).equals(valor) && board.get(i+1, j+2).equals(valor)
                 && board.get(i+2, j+2).equals(valor)){
                    String coord = "" + (char)(i+65) + (j+1);
                    coords.add(coord);
                }
            }
        }
        return coords;
    }
    
    private void clearSquare(String coord) throws FilledSquareException{
        char col = coord.charAt(0);
        int line = coord.charAt(1)-'0';
        board.set((char)(col), line, ".");
        board.set((char)(col+1), line, ".");
        board.set((char)(col), line+1, ".");
        board.set((char)(col+1), line+1, ".");
        board.set((char)(col+2), line, ".");
        board.set((char)(col), line+2, ".");
        board.set((char)(col+2), line+1, ".");
        board.set((char)(col+1), line+2, ".");
        board.set((char)(col+2), line+2, ".");
        setCurrentScore(46);
    }
    
    private Block getRandomBlock(){
        return blocks.getRandomBlock();
    }
    
    /**
     * Altera os blocos disponíveis, sendo que os blocos são escolhidos aleatoriamente
     */
    public void setRandomBlocks(){
        a = getRandomBlock();
        b = getRandomBlock();
        c = getRandomBlock();
        blockA = "Bloco A\n" + a.toString();
        blockB = "Bloco B\n" + b.toString();
        blockC = "Bloco C\n" + c.toString();
        availableBlocks = 3;
    }
    
    /**
     * Imprime todos os blocos disponíveis
     */
    public void showBlocks(){
        showBoard();
        System.out.println("Blocos a jogar:");
        System.out.println(blockA + blockB + blockC);
        System.out.println("Indique a próxima jogada (Bloco-ColunaLinha):");
    }
    
    public String getBoardAndBlocks(){
        return getBoard().toString() + "\nBlocos a jogar:\n" + blockA + blockB + blockC;
    }
    
    /**
     * Coloca o bloco dado a partir da posição dada no tabuleiro, verifica se
     * alguma coluna ou linha ou grande quadrado foram totalmente preenchidos,
     * se sim, "limpa". Ainda aumenta o score do user
     * 
     * @param chosenBlock, bloco escolhido
     * @param pos, posição escolhida
     * @return true, colocar o bloco com sucesso
     *         false, caso contrário
     * @throws FilledSquareException 
     */
    public boolean playBlock(Block chosenBlock, String pos) throws FilledSquareException{
        boolean putBlock = putBlock(chosenBlock, pos.charAt(2), pos.charAt(3) - '0', "#");
        if(putBlock == true){
            ArrayList<Integer> columns = fullColumn();
            ArrayList<Integer> lines = fullLine();
            ArrayList<String> squares = fullSquare("#");
            if (columns.size() > 0) {
                for (int column : columns) {
                    clearColumn(column);
                }
            } else {
                if (lines.size() > 0) {
                    for (int line : lines) {
                        clearLine(line);
                    }
                }else{
                    if(squares.size() > 0){
                        for(String coord : squares){
                            clearSquare(coord);
                        }
                    }
                }
            }
            return true;
        }else{
            return false;
        }
    }
    
    /**
     * guarda os dados do score history deste user num ficheiro com o seu nome
     * 
     * @param fileName, nome do ficheiro em que pretende guardar o jogo
     * 
     * @throws FileNotFoundException
     * @throws IOException 
     */
    public void saveGame(String fileName) throws FileNotFoundException, IOException{
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Retorna o jogo guardado 
     * 
     * @param fileName, nome do ficheiro onde o jogo foi guardado, assim, sabe-se qual o jogo
     *                  que se deve carregar
     * 
     * @return scores, LinkedHashMap com a informação dos scores do user
     */
    public Game loadGame(String fileName){
        Game game;
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            game = (Game) ois.readObject();
            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            game = new Game();
        }
        return game;
    }
    
    public boolean isFinished() throws CloneNotSupportedException{
        boolean resultA = true;
        boolean resultB = true;
        boolean resultC = true;
            if(!getBlockA().equals("")){
                for(int i = 0; i < getBoard().getLenght(); i++){
                    for(int j = 1; j < getBoard().getHeight()+1; j++){
                        try{
                            if(putBlock(getA(), (char)(i+65), j, "#")){
                                putBlock(getA(), (char)(i+65), j, ".");
                                resultA = false;
                            }else{
                                putBlock(getA(), (char)(i+65), j, ".");
                            }
                        } catch (FilledSquareException e){
                            System.out.println("");
                        }
                    }
                }
            }
            if(!getBlockB().equals("")){
                for(int i = 0; i < getBoard().getLenght(); i++){
                    for(int j = 1; j < getBoard().getHeight()+1; j++){
                        try{
                            if(putBlock(getB(), (char)(i+65), j, "#")){
                                putBlock(getB(), (char)(i+65), j, ".");
                                resultB = false;
                            }else{
                                putBlock(getB(), (char)(i+65), j, ".");
                            }
                        } catch (FilledSquareException e){
                            System.out.println("");
                        }
                    }
                }
            }
            if(!getBlockC().equals("")){
                for(int i = 0; i < getBoard().getLenght(); i++){
                    for(int j = 1; j < getBoard().getHeight()+1; j++){
                        try{
                            if(putBlock(getC(), (char)(i+65), j, "#")){
                                putBlock(getC(), (char)(i+65), j, ".");
                                resultC = false;
                            }else{
                                putBlock(getC(), (char)(i+65), j, ".");
                            }
                        } catch (FilledSquareException e){
                            System.out.println("");
                        }
                    }
                }
            }
        if(resultA == false || resultB == false || resultC == false) return false;
        return true;
    }
    
    private boolean putBlock(Block block, char x, int y, String valor) throws FilledSquareException{
        try{
            if(x > (char)(board.getLenght()+64) && y < 1) return false;
            board.set(x, y, valor);
            char x1 = (char) (x+1);
            char x2 = (char) (x+2);
            char x3 = (char) (x+3);
            switch(block.getName()){
                case "i4": 
                    if(y+3 <= board.getHeight()){
                        board.set(x, y+1, valor);
                        board.set(x, y+2, valor);
                        board.set(x, y+3, valor);
                        setCurrentScore(4);
                        decrementAvailableBlocks();
                        return true;
                    }else{
                        board.set(x, y, ".");
                        return false;
                    }
                case "i4-": 
                    if(x3 <= (char)(board.getLenght()+64)){
                        board.set(x1, y, valor);
                        board.set(x2, y, valor);
                        board.set(x3, y, valor);
                        setCurrentScore(4);
                        decrementAvailableBlocks();
                        return true;
                    }else{
                        board.set(x, y, ".");
                        return false;
                    }
                case "q": 
                    if(x1 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                        board.set(x1, y, valor);
                        board.set(x, y+1, valor);
                        board.set(x1, y+1, valor);
                        setCurrentScore(4);
                        decrementAvailableBlocks();
                        return true;
                    }else{
                        board.set(x, y, ".");
                        return false;
                    }    
                case "t": 
                    if(x2 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                        board.set(x1, y, valor);
                        board.set(x2, y, valor);
                        board.set(x1, y+1, valor);
                        setCurrentScore(4);
                        decrementAvailableBlocks();
                        return true;
                    }else{
                        board.set(x, y, ".");
                        return false;
                    }
                case "t<":
                        if(x1 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight() && y-1 > 0){
                           board.set(x1, y-1, valor);
                           board.set(x1, y, valor);
                           board.set(x1, y+1, valor);
                           setCurrentScore(4);
                           decrementAvailableBlocks();
                           return true;
                        }else{
                            board.set(x, y, ".");
                            return false;
                        }

                case "t>":  if(x1 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                board.set(x, y+1, valor);
                                board.set(x1, y+1, valor);
                                board.set(x, y+2, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "t-":  if(x2 <= (char)(board.getLenght()+64) && y-1 > 0){
                                board.set(x1, y-1, valor);
                                board.set(x1, y, valor);
                                board.set(x2, y, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "l":  if(x1 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                board.set(x, y+1, valor);
                                board.set(x, y+2, valor);
                                board.set(x1, y+2, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                           }else{
                                board.set(x, y, ".");
                                return false;
                           }
                case "l<":  if(x2 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                                board.set(x1, y, valor);
                                board.set(x2, y, valor);
                                board.set(x, y+1, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "l>":  if(x2 <= (char)(board.getLenght()+64) && y-1 > 0){
                                board.set(x1, y, valor);
                                board.set(x2, y, valor);
                                board.set(x2, y-1, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "l-":  if(x1 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                board.set(x1, y, valor);
                                board.set(x1, y+1, valor);
                                board.set(x1, y+2, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "j":  if(x1 <= (char)(board.getLenght()+64) && y-2 > 0){
                                board.set(x1, y, valor);
                                board.set(x1, y-1, valor);
                                board.set(x1, y-2, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                           }else{
                               board.set(x, y, ".");
                               return false;
                           }
                case "j<":  if(x2 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                                board.set(x, y+1, valor);
                                board.set(x1, y+1, valor);
                                board.set(x2, y+1, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "j>":  if(x2 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                                board.set(x1, y, valor);
                                board.set(x2, y, valor);
                                board.set(x2, y+1, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "j-":  if(x1 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                board.set(x1, y, valor);
                                board.set(x, y+1, valor);
                                board.set(x, y+2, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "s":  if(x2 <= (char)(board.getLenght()+64) && y-1 > 0){
                                board.set(x1, y, valor);
                                board.set(x1, y-1, valor);
                                board.set(x2, y-1, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "s-":  if(x1 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                board.set(x, y+1, valor);
                                board.set(x1, y+1, valor);
                                board.set(x1, y+2, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
                case "z":  if(x2 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                                board.set(x1, y, valor);
                                board.set(x1, y+1, valor);
                                board.set(x2, y+1, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                           }else{
                                board.set(x, y, ".");
                                return false;
                           }
                case "z-":  if(x1 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight() && y-1 > 0){
                                board.set(x, y+1, valor);
                                board.set(x1, y, valor);
                                board.set(x1, y-1, valor);
                                setCurrentScore(4);
                                decrementAvailableBlocks();
                                return true;
                            }else{
                                board.set(x, y, ".");
                                return false;
                            }
            }
            if(this.blocks.getMode() == Mode.HARD){
                switch(block.getName()){
                    case "i1": setCurrentScore(2);
                               decrementAvailableBlocks();
                               return true;
                    case "i2":  if(y+1 <= board.getHeight()){
                                    board.set(x, y+1, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(4);
                                    return true;
                                }else{
                                    board.set(x, y, ".");
                                    return false;
                                }
                    case "i2-":  if(x1 <= (char)(board.getLenght()+64)){
                                    board.set(x1, y, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(4);
                                    return true;
                                 }else{
                                    board.set(x, y, ".");
                                    return false;
                                 }
                    case "i3":  if(y+2 <= board.getHeight()){
                                    board.set(x, y+1, valor);
                                    board.set(x, y+2, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(6);
                                    return true;
                                }else{
                                    board.set(x, y, ".");
                                    return false;
                                }
                    case "i3-":  if(x2 <= (char)(board.getLenght()+64)){
                                    board.set(x1, y, valor);
                                    board.set(x2, y, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(6);
                                    return true;
                                 }else{
                                    board.set(x, y, ".");
                                    return false;
                                 }
                    case "lmin":  if(x1 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                                    board.set(x, y+1, valor);
                                    board.set(x1, y+1, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(6);
                                    return true;
                                  }else{
                                    board.set(x, y, ".");
                                    return false;
                                  }
                    case "lmin<":  if(x1 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                                        board.set(x1, y, valor);
                                        board.set(x, y+1, valor);
                                        decrementAvailableBlocks();
                                        setCurrentScore(6);
                                        return true;
                                   }else{
                                        board.set(x, y, ".");
                                        return false;
                                   }
                    case "lmin>":  if(x1 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight()){
                                        board.set(x1, y, valor);
                                        board.set(x1, y+1, valor);
                                        decrementAvailableBlocks();
                                        setCurrentScore(6);
                                        return true;
                                   }else{
                                        board.set(x, y, ".");
                                        return false;
                                   }
                    case "lmin-":  if(x1 <= (char)(board.getLenght()+64) && y-1 > 0){
                                        board.set(x1, y, valor);
                                        board.set(x1, y-1, valor);
                                        decrementAvailableBlocks();
                                        setCurrentScore(6);
                                        return true;
                                   }else{
                                        board.set(x, y, ".");
                                        return false;
                                   }
                    case "lmax":  if(x2 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                    board.set(x, y+1, valor);
                                    board.set(x, y+2, valor);
                                    board.set(x1, y+2, valor);
                                    board.set(x2, y+2, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(10);
                                    return true;
                                  }else{
                                    board.set(x, y, ".");
                                    return false;
                                  }
                    case "lmax<":  if(x2 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                        board.set(x1, y, valor);
                                        board.set(x2, y, valor);
                                        board.set(x, y+1, valor);
                                        board.set(x, y+2, valor);
                                        decrementAvailableBlocks();
                                        setCurrentScore(10);
                                        return true;
                                   }else{
                                        board.set(x, y, ".");
                                        return false;
                                   }
                    case "lmax>":  if(x2 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                        board.set(x1, y, valor);
                                        board.set(x2, y, valor);
                                        board.set(x2, y+1, valor);
                                        board.set(x2, y+2, valor);
                                        decrementAvailableBlocks();
                                        setCurrentScore(10);
                                        return true;
                                   }else{
                                        board.set(x, y, ".");
                                        return false;
                                   }
                    case "lmax-":  if(x2 <= (char)(board.getLenght()+64) && y-2 > 0){
                                        board.set(x1, y, valor);
                                        board.set(x2, y, valor);
                                        board.set(x2, y-1, valor);
                                        board.set(x2, y-2, valor);
                                        decrementAvailableBlocks();
                                        setCurrentScore(10);
                                        return true;
                                   }else{
                                        board.set(x, y, ".");
                                        return false;
                                   }
                    case "T":  if(x2 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                    board.set(x1, y, valor);
                                    board.set(x2, y, valor);
                                    board.set(x1, y+1, valor);
                                    board.set(x1, y+2, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(10);
                                    return true;
                               }else{
                                    board.set(x, y, ".");
                                    return false;
                               }
                    case "T<":  if(x2 <= (char)(board.getLenght()+64) && y+1 <= board.getHeight() && y-1 > 0){
                                    board.set(x1, y, valor);
                                    board.set(x2, y, valor);
                                    board.set(x2, y-1, valor);
                                    board.set(x2, y+1, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(10);
                                    return true;
                                }else{
                                    board.set(x, y, ".");
                                    return false;
                                }
                    case "T>":  if(x2 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                    board.set(x, y+1, valor);
                                    board.set(x, y+2, valor);
                                    board.set(x1, y+1, valor);
                                    board.set(x2, y+1, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(10);
                                    return true;
                                }else{
                                    board.set(x, y, ".");
                                    return false;
                                }
                    case "T-":  if(x2 <= (char)(board.getLenght()+64) && y-2 > 0){
                                    board.set(x1, y, valor);
                                    board.set(x2, y, valor);
                                    board.set(x1, y-1, valor);
                                    board.set(x1, y-2, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(10);
                                    return true;
                                }else{
                                    board.set(x, y, ".");
                                    return false;
                                }
                    case "Q":  if(x2 <= (char)(board.getLenght()+64) && y+2 <= board.getHeight()){
                                    board.set(x1, y, valor);
                                    board.set(x2, y, valor);
                                    board.set(x, y+1, valor);
                                    board.set(x1, y+1, valor);
                                    board.set(x2, y+1, valor);
                                    board.set(x, y+2, valor);
                                    board.set(x1, y+2, valor);
                                    board.set(x2, y+2, valor);
                                    decrementAvailableBlocks();
                                    setCurrentScore(18);
                                    return true;
                               }else{
                                    board.set(x, y, ".");
                                    return false;
                               }
                }
            }return false;
        } catch(FilledSquareException e){
            throw new FilledSquareException(e.getMessage());
        }
    }
}