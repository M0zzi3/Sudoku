package Gen3;

import java.util.*;

public class Sudoku_Game {
    int sq_size;
    int[][]map_data;
    int map_size;
    Map<String, ArrayList<Integer>> candidates_D;
    ArrayList<String> places_to_fill;
    ArrayList<String> squares_quee;
    int[] numbs;
    Random rand;
    int rest;


    Sudoku_Game(int size) {
        //Start timer
        long start_time = System.nanoTime();
        System.out.println("Welcome to sudoku game by NM0zzi3");

        //Sets all require fields
        this.sq_size = size;
        this.map_size = this.sq_size *this.sq_size;
        this.map_data = new int[this.map_size][this.map_size];
        this.candidates_D = new HashMap<>();
        this.places_to_fill = new ArrayList<>();
        this.squares_quee = new ArrayList<>();
        this.numbs = new int[this.map_size];
        this.rand = new Random();
        this.rest = this.sq_size%2;


        //Generate numbs
        for(int numb = 0; numb < this.map_size; numb++){
            this.numbs[numb] = numb+1;
        }

        //Generate HashMap with all candidates for all places
        for (int y = 0; y < this.map_size; y++){
            for(int x = 0; x < this.map_size; x++){
                String place = x+" "+y;
                this.places_to_fill.add(place);
                candidates_D.put(place, new ArrayList<>());
                for(int candidateIndex = 0; candidateIndex < this.map_size; candidateIndex++){
                    candidates_D.get(place).add(this.numbs[candidateIndex]);
                }
            }
        }


        ////Generate square fill order////

        //Find first center square
        int middle_od_map = this.sq_size/2;
        int center_squarePos = middle_od_map*this.sq_size;
        this.squares_quee.add(toKey(new int[] {center_squarePos, center_squarePos}));

        //Adds remaining center squares if these exist
        if (this.rest == 0){
            this.squares_quee.add(toKey(new int[] {(middle_od_map-1)*this.sq_size, middle_od_map*this.sq_size}));
            this.squares_quee.add(toKey(new int[] {(middle_od_map-1)*this.sq_size, (middle_od_map-1)*this.sq_size}));
            this.squares_quee.add(toKey(new int[] {middle_od_map*this.sq_size, (middle_od_map-1)*this.sq_size}));
        }

        // Generate encirclement around the center
        for(int enc = 0; enc < middle_od_map-1+this.rest; enc++){

            System.out.println("Encirclement number: "+(enc+1));

            //Find all vertical squares in that level of encirclement
            for(int ver = 0; ver < 2-this.rest + enc*2; ver++){
                //Find pos correspond to enc and ver
                ;
            }

        }













        ////Generate random square////

        //Generate random order in that square
        ArrayList<Integer> candidatesIndex_List = new ArrayList<>();
        for(int i = 0 ; i < this.map_size; i++){candidatesIndex_List.add(i);}
        Collections.shuffle(candidatesIndex_List);

        //Go by every place and set random nub to that place
        int candidateIndex = 0;
        int[] randomSquare_startPoint = toData(this.squares_quee.get(0));

        for(int randomSquareY = 0; randomSquareY < this.sq_size; randomSquareY++){
            for(int randomSquareX = 0; randomSquareX < this.sq_size; randomSquareX++) {

                int rSqY = randomSquare_startPoint[0]+randomSquareY;
                int rSqX = randomSquare_startPoint[1]+randomSquareX;
                int[] place = {rSqY, rSqX};
                int numb_to_set = this.numbs[candidatesIndex_List.get(candidateIndex)];

                this.Set_Number(numb_to_set, place);

                candidateIndex++;
            }
        }
        this.squares_quee.remove(0);
















        //End all generate algorithm
        long elapsedTime = System.nanoTime() - start_time;
        double time = Math.round(elapsedTime * 0.0000001) / 100.0;
        System.out.println("Map generated in: " + time + "s");
    }


    private void Set_Number(int numb, int[] place){
        int scanY = place[0];
        int scanX = place[1];
        String key_place = toKey(place);
        ArrayList<int[]> agitated_places = new ArrayList<>();

        System.out.println("Numb "+numb+" set to "+key_place);
        this.map_data[scanY][scanX] = numb;

        //Remove place from place list to fill
        for(int i = 0; i < this.places_to_fill.size(); i++){
            if(this.places_to_fill.get(i).equals(key_place)){
                this.places_to_fill.remove(i);
                break;
            }
        }

        //Find all place that numb interact with
        for(int i = 0; i < this.map_size; i++){

            //Test place on axis Y
            if(this.map_data[scanY][i] == 0){
                agitated_places.add(new int[] {scanY, i});
            }
            //Test place on axis X
            if(this.map_data[i][scanX] == 0){
                agitated_places.add(new int[] {i, scanX});
            }
        }

        //Remove numb from paces candidates
        for(int[] agitated_place : agitated_places){
            String keyAg_place = toKey(agitated_place);

            //Look for numb in candidates
            int test_numbIndex = 0;
            for (int test_numb : this.candidates_D.get(keyAg_place)){
                if (test_numb == numb){
                    this.candidates_D.get(keyAg_place).remove(test_numbIndex);
                    break;
                }
                test_numbIndex++;
            }
        }

    }

    public void Show_Map(){
        int mapSize_length = Integer.toString(this.map_size).length();

        for (int y = 0; y < this.map_size; y++) {
            for (int x = 0; x < this.map_size; x++) {
                System.out.print(this.map_data[y][x]+" ");
                int numb_length = Integer.toString(this.map_data[y][x]).length();
                int length = mapSize_length - numb_length;

                for(int i = 0; i < length; i++){
                    System.out.print(" ");
                }

            }System.out.println("");
        }
    }


    private String toKey (int[] placeID){
        return placeID[0]+" "+placeID[1];
    }

    private int[] toData (String placeKey){
        String[] placeKey_list = placeKey.split(" ");

        return new int[]{Integer.parseInt(placeKey_list[0]),Integer.parseInt(placeKey_list[1])};
    }

    private void Show_candidates(){
        for (Map.Entry<String, ArrayList<Integer>> entry : candidates_D . entrySet()){
            System.out.println(entry.getKey()+" : "+entry.getValue());
        }
    }


}