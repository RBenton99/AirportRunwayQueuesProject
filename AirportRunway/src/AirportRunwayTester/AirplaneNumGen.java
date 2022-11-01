package AirportRunwayTester;

/*
Josh Riddle
CS 331 - 001
Tue, Thurs 9:15 - 10:45
Fall 2022
*/
public class AirplaneNumGen {
    private final String airplaneNumString;

    public AirplaneNumGen() {
        this.airplaneNumString = "";
    }
    
    // Setter
    public String setAirplaneNumString() {
        int alphaMax = 89;
        int alphaMin = 65;
        int numMax = 9999;
        int numMin = 1000;
        int number;
        String planeNum = "";
        
        number = (int)((Math.random() * (alphaMax - alphaMin)) + alphaMin);
        planeNum += Character.toString((char)number);
        number = (int)((Math.random() * (alphaMax - alphaMin)) + alphaMin);
        planeNum += Character.toString((char)number);
        number = (int)((Math.random() * (alphaMax - alphaMin)) + alphaMin);
        planeNum += Character.toString((char)number);
        
        number = (int)((Math.random() * (numMax - numMin)) + numMin);
        planeNum = planeNum + "-" + Integer.toString(number) + "-";
        
        number = (int)((Math.random() * (alphaMax - alphaMin)) + alphaMin);
        planeNum += Character.toString((char)number);
        
        System.out.println(planeNum);
        
//        for(int i = 0; i < 6; i++) {
//            if (planeNum.length() < 3 || planeNum.length() == 9) {
//                number = (int)((Math.random() * (alphaMax - alphaMin)) + alphaMin);
//                planeNum += Character.toString((char)number);
//            } else if (planeNum.length() == 3) {
//                number = (int)((Math.random() * (numMax - numMin)) + numMin);
//                planeNum = planeNum + "-" + Integer.toString(number) + "-";
//            } else {
//                System.out.println(planeNum);
//                planeNum = "";
//            }
//        }
        return planeNum;
    }
    
    // Getter
    public String getAirplaneNumString() {
        return setAirplaneNumString();
    }
}
