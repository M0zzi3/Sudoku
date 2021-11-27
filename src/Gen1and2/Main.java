package Gen1and2;




import java.util.Random;

public class Main {
////    public static void show_map() {
////        for (int[] row : map) {
////            for (int place : row) {
////                System.out.print(" " + place + " ");
////            }
////            System.out.println("");
////        }
////    }
////
////
////    public static int[] generate_cords(int[] startP){
////        Random r = new Random();
////        int[] cords = {r.nextInt(size)+startP[0],r.nextInt(size)+startP[1]};
////        return cords;
////    }
////
////    public static boolean find_duplicate (int[] cords, int numb){
////        List<List> scan = new ArrayList<List>();
////        //Scan row
////        List<Integer> scanned_row = new ArrayList<Integer>();
////
////        for(int row_val :map[cords[0]]){
////            scanned_row.add(row_val);
////        }
////        scan.add(scanned_row);
////
////        //Scan column
////        List<Integer> scanned_column = new ArrayList<Integer>();
////        for(int i = 0; i <  gameSize; i++){
////            scanned_column.add(map[i][cords[1]]);
////        }
////        scan.add(scanned_column);
////
////
////        for(List<Integer> path : scan) {
////            for (int x : path) {
////                if(x == numb){
////                    return false;
////                }
////            }
////        }
////        return true;
////    }
////
////    public static int[] find_cords (int[] startP, int symb){
////        while (true){
////            int[] genCords = generate_cords(startP);
////            if (find_duplicate(genCords, symb) & map[genCords[0]][genCords[1]] == 0){
////                return genCords;
////            }
////        }
////
////    }
////
////
////    static int size;
////    static int gameSize;
////    static int[][] map;
////    public static void main(String[] args) {
////        size = 3;
////        gameSize = size * size;
////        map = new int[gameSize][gameSize];
////        ArrayList<List> answers = new ArrayList<List>();
////        boolean kill_switch = true;
////
////        List<int[]> areas_startPoint_list = new ArrayList<int[]>();
////
////        for (int y = 0; y <= gameSize-size; y += size) {
////            for (int x = 0; x <= gameSize - size; x += size) {
////                areas_startPoint_list.add(new int[]{y,x});
////            }
////        }
////
////        //Test Fill
//////        int c = 1;
//////        for(int[] as : areas_startPoint_list){
//////            map[as[0]][as[1]] = c;
//////            c++;
//////        }
////
////        //Generate Answers
////        for (int[] square_startPoint : areas_startPoint_list){
////            for(int i : square_startPoint){System.out.print(i+" ");}
////            System.out.println("");
////
////            //for each number
////            for(int numb = 1; numb <= gameSize; numb++){
////                System.out.print(numb+" - ");
////
////                int[] newNumb_cords = find_cords(square_startPoint, numb);
////                map[newNumb_cords[0]][newNumb_cords[1]] = numb;
////
////                for(int i : newNumb_cords){System.out.print(i+" ");}
////                System.out.println("");
////
////
////
////            }
////
////        }
////
////        show_map();
////
////
////    public static void show_map(int[][]m) {
////        for (int[] row : m) {
////            for (int place : row) {
////                System.out.print(" " + place + " ");
////            }
////            System.out.println("");
////        }
////    }
////
////
////    public static int[] generate_random_cords(int[] startP){
////        Random r = new Random();
////        int[] cords = {r.nextInt(size)+startP[0],r.nextInt(size)+startP[1]};
////        return cords;
////    }
////
////    public static boolean test_cords(int[] tcords, int tnumb){
////        List<Integer> scanList = new ArrayList<Integer>();
////
////        //Scan row
////        for (int x : anwsers[tcords[0]]){scanList.add(x);}
////
////        //Scan column
////        for (int y = 0 ; y < gameSize ; y++){scanList.add(anwsers[y][tcords[1]]);}
////
////        //Test
////        for (int t : scanList){
////            if (t == tnumb){
////                return false;
////            }
////        }
////
////        return true;
////    }
////
////
////
////
////    public static void getL (int[] list){
////        System.out.print("[ ");
////        for(int x : list){
////            System.out.print(x+" ");
////        }
////        System.out.print("]");
////        System.out.println(" ");
////    }
////
////    static int [][] anwsers;
////    static int size;
////    static int gameSize;
////    public static void main(String[] args) {
////
////
////        //Generate anwsers
////        size = 3; //User input
////
////        gameSize = size*size;
////        int[][] map = new int[gameSize][gameSize];
////        anwsers = new int[gameSize][gameSize];
////
////
////        //Find Squares Start Points
////        List<int[]> areas_startPoint_list = new ArrayList<int[]>();
////
////        for (int y = 0; y <= gameSize-size; y += size) {
////            for (int x = 0; x <= gameSize - size; x += size) {
////                areas_startPoint_list.add(new int[]{y,x});
////            }
////        }
////
////        //Fill Squares
////        for (int[] sq_startPoint : areas_startPoint_list){
////            //Get in square
////            System.out.println("Start Point: "+sq_startPoint[0]+" "+sq_startPoint[1]);
////
////            for (int n = 1; n <= gameSize; n++) {
////                //Go by every number
////                int[] newNumb_cords;
////                // Look for duplicates
////                int rte = 0;
////                while (true){
////                    newNumb_cords = generate_random_cords(sq_startPoint);
////                    if (anwsers[newNumb_cords[0]][newNumb_cords[1]] == 0 & test_cords(newNumb_cords, n)){
////                        anwsers[newNumb_cords[0]][newNumb_cords[1]] = n;
////                        break;
////                    }else {
////                        //Runtime error
////                        rte++;
////                        if(rte == gameSize){
////                            //Reset square
////                            for (int square_Y = 0 ; square_Y < size; square_Y++){
////                                for (int square_X = 0 ; square_Y < size; square_X++){
////                                    anwsers[sq_startPoint[0] + square_Y][sq_startPoint[1] + square_X] = 0;
////                                }
////                            }
////                        }
////
////                    }
////
////
////                }
////                getL(newNumb_cords);
////                show_map(anwsers);
////
////
////            }
////        }
//
//    public static void show_map(int[][]m) {
//        for (int[] row : m) {
//            for (int place : row) {
//                System.out.print(" " + place + " ");
//            }
//            System.out.println("");
//        }
//    }
//
//    public static boolean test_palce (int tplace, int tsymb){
//        for (int i = 0; i < gameSize; i++){
//            if (answer[i][tplace] == tsymb){
//                return false;
//            }
//        }
//        return true;
//    }
//
//    public static int random_palce(int range){
//        Random r = new Random();
//        int rplace = r.nextInt(range);
//        return rplace;
//    }
//
//    public static void find_place(int y_pos){
//        for (int numb = 1; numb <= gameSize; numb++){
//            int rte = 0;
//            while(true){
//                int rplace = random_palce(gameSize);
//
//                if (answer[y_pos][rplace] == 0 & test_palce(rplace, numb)) {
//                    answer[y_pos][rplace] = numb;
//                    System.out.println("-");
//                    show_map(answer);
//                    break;
//
//                }else{
//                    rte++;
//                    if (rte == gameSize*100){
//                        answer[y_pos] = new int[gameSize];
//                        find_place(y_pos);
//                    }
//
//                }
//            }
//        }
//    }
//
//
//    static int[][] answer;
//    static int[][]game;
//    static int size;
//    static int gameSize;
    public static void main(String[] args) {
//        size = 3; // User input
//        gameSize = size * size;
//        answer = new int[gameSize][gameSize];
//        game = new int[gameSize][gameSize];
//
//
//        //Find answers
//
//        //Go to every row
//        for (int y_pos = 0; y_pos < gameSize; y_pos++) {
//            System.out.println("Row: "+y_pos);
//            find_place(y_pos);
//
//        }
//        Sudoku_Map s2 = new Sudoku_Map(3);
//        s2.Show_map();

    }
}
