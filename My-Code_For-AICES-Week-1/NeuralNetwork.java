public class NeuralNetwork
{
    //features
    private double eta = 0.2;
    private double alpha = 0.5;
    private Layers layers = new Layers ( );
    private Architecture architecture = new Architecture ( "2,2,1" );
    
    //constructor
    public NeuralNetwork ( )
    {
        for ( int lSI = 0; lSI < architecture.size ( ); lSI ++ )
        {
            layers.add ( new Layer ( ) );
            
            for ( int lI = 0; lI <= architecture.get ( lSI ); lI ++ )
            {
                //Neuron ( double eta, double alpha, int numberOfWeightsFromNextNeuron, int neuronId )
                
                //FORM 
                //IF lSI + 1 < architecture.size ( ) 
                    //arch ( lSI + 1 ) 
                //else
                    //0
                int numberOfWeightsFromNextNeuron = lSI + 1 < architecture.size ( ) ? architecture.get ( lSI + 1 ) : 0;
                
                Neuron newNeuron = new Neuron ( eta, alpha, numberOfWeightsFromNextNeuron, lI );
                
                layers.get ( lSI ).add ( newNeuron );
                
                layers.get ( layers.size ( ) - 1 ).get ( lSI ).setOutcome ( 1.0 );
            }
        }
    }
    
    //do forward propagation
    public void doForwardPropagation ( int [ ] inputs )
    {
        //pass inputs to 1st layer of neural network
        for ( int iI = 0; iI < inputs.length; iI ++ )
            layers.get ( 0 ).get ( iI ).setOutcome ( inputs [ iI ] );
            
            
        //
        for ( int lSI = 1; lSI < architecture.size ( ); lSI ++ )
        {
            Layer priorLayer = layers.get ( lSI - 1 );
            
            for ( int lI = 0; lI < architecture.get ( lSI ); lI ++ )
            {
                layers.get ( lSI ).get ( lI ).doForwardPropagation ( priorLayer );
            }
        }
    }
    
    //backward propagation
    public void doBackwardPropagation ( int target ) 
    {
        //set outcome gradient
        Neuron outcomeNeuron = layers.get ( layers.size ( ) - 1 ).get ( 0 );
        
        outcomeNeuron.setOutcomeGradient ( target );
        
        //set hidden gradient
        for ( int lSI = layers.size ( ) - 2; lSI > 0; lSI -- )
        {
            Layer currentLayer = layers.get ( lSI );
            Layer nextLayer = layers.get ( lSI + 1 );
            
            for ( int lI = 0; lI < currentLayer.size ( ) - 1; lI ++ )
                currentLayer.get ( lI ).setHiddenGradient ( nextLayer );
        }
        
        //update weights
        for ( int lSI = layers.size ( ) - 1; lSI > 0; lSI -- )
        {
            Layer currentLayer = layers.get ( lSI );
            Layer priorLayer = layers.get ( lSI - 1 );
            
            for ( int lI = 0; lI < currentLayer.size ( ); lI ++ )
                currentLayer.get ( lI ).updateWeight ( priorLayer );
        } 
    }
    
    
    //get outcome of neural network
    public double getOutcome ( )
    {
        return layers.get ( layers.size ( ) - 1 ).get ( 0 ).getOutcome ( );
    }
}
