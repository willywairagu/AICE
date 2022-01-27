import java.util.Scanner;
import java.util.ArrayList;

public class RunNeuralNetwork
{
    //features
    private static NeuralNetwork neuralNetwork = new NeuralNetwork ( );
    
    private static Scanner userScanner = new Scanner ( System.in );
    
    //main function
    public static void main ( String [ ] anything )
    {
        doTraining ( );
        showMainMenu ( );
    }
    
    
    //test the neural network
    public static void showMainMenu ( )
    {
        System.out.println ( "Universal Ai Diploma Neural Network" );
        System.out.println ( "1. Get neural answer for xor input 0, 0 " );
        System.out.println ( "2. Get neural answer for xor input 0, 1 " );
        System.out.println ( "3. Get neural answer for xor input 1, 0 " );
        System.out.println ( "4. Get neural answer for xor input 1, 1 " );
        System.out.println ( "Select a number to proceed... " );
        
        int option = userScanner.nextInt ( );
        
        switch ( option )
        {
            case 1: 
            {
            int [ ] inputs = new int  [] { 0,0};
            neuralNetwork.doForwardPropagation ( inputs );
            double answer = neuralNetwork.getOutcome();
            System.out.println ("Answer for input 0,0 is : " + answer);
            returnToMainMenu ( );
            }
            break;
            
            case 2: 
            {
            int [ ] inputs = new int  [] { 0,1};
            neuralNetwork.doForwardPropagation ( inputs );
            double answer = neuralNetwork.getOutcome();
            System.out.println ("Answer for input 0,1 is : " + answer);
            returnToMainMenu ( );
            }
            break;
            
            
            case 3: 
            {
            int [ ] inputs = new int  [] { 1,0};
            neuralNetwork.doForwardPropagation ( inputs );
            double answer = neuralNetwork.getOutcome();
            System.out.println ("Answer for input 1,0 is : " + answer);
            returnToMainMenu ( );
            }
            break;
           
            
            case 4: 
            {
            int [ ] inputs = new int  [] { 1,1};
            neuralNetwork.doForwardPropagation ( inputs );
            double answer = neuralNetwork.getOutcome();
            System.out.println ("Answer for input 1,1 is : " + answer);
            returnToMainMenu ( );
            }
            break;
            
            
            
            
            case 5: 
            {
            System.exit(0);
            }
            break;
        }
    }
    
    
    public static void returnToMainMenu ( )
    {
        userScanner.nextLine ( );
        showMainMenu ();
    }
    
    public static void doTraining ( )
    {
        ArrayList <String> trainingData =  getTrainingData ( );
        
        
        for ( int tDI = 0; tDI < trainingData.size ( ); tDI ++)
        {
            //forward prop
            String trainingInputLine = trainingData.get ( tDI ).split ( "::" ) [ 0 ];
            int firstInput = Integer.parseInt ( trainingInputLine.split ( "," ) [ 0 ] );
            int secondInput = Integer.parseInt ( trainingInputLine.split ( "," ) [ 1 ] );
            int inputs [ ] = new  int [ ] { firstInput,secondInput };
            
            neuralNetwork.doForwardPropagation ( inputs );
            
            
            
            //backward propagation
            int outputValuePerTrainingLine = Integer.parseInt( trainingData.get ( tDI ).split ( "::" ) [ 1 ] );
            
            neuralNetwork.doBackwardPropagation ( outputValuePerTrainingLine );
        }
    }
    
    
    public static ArrayList <String> getTrainingData ( )
    {
        ArrayList <String> returnValue = new ArrayList <String> ( );
        
        return returnValue;
    }
}