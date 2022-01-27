import java.util.ArrayList;
import java.util.Random;

public class Neuron
{
    //features
    private double eta; //learning rate of neuron
    private double alpha; //momentum
    private double gradient;
    private double outcome;
    private int neuronId;
    private int numberOfWeightsFromNextNeuron;
    private ArrayList <Synapse> weights;
    
    
    //constructor
    public Neuron ( double eta, double alpha, int numberOfWeightsFromNextNeuron, int neuronId )
    {
        this.eta = eta;
        this.alpha = alpha;
        this.numberOfWeightsFromNextNeuron = numberOfWeightsFromNextNeuron;
        this.neuronId = neuronId;
        gradient = 0.0;
        
        weights = new ArrayList <Synapse>  ( );
        
        for ( int wI = 0; wI < numberOfWeightsFromNextNeuron; wI ++ )
        {
            weights.add ( new Synapse ( ) );
            weights.get ( wI ).setWeight ( new Random ( ).nextDouble ( ) );
        }
    }
    
    //methods
    //observe stuff about neuron
    public double getOutcome ( )
    {
        return outcome;
    }
    public double getGradient ( )
    {
        return gradient;
    }
    public ArrayList <Synapse> getWeights ( )
    {
        return weights;
    }
    public double getActivation ( double value )
    {
        return Math.tanh ( value );
    }
    public double getPrimeActivation ( double value )
    {
        return 1 - Math.pow ( value, 2 );
    }
    public double getDistributedWeight ( Layer nextLayer )
    {
        double sigma = 0.0;
        
        //this neuron's weights * the gradient of other neurons
        for ( int nLI = 0; nLI < nextLayer.size ( ) - 1; nLI ++ )
            sigma += this.getWeights ( ).get ( nLI ).getWeight ( ) * nextLayer.get ( nLI ).getGradient ( );    
        
        return sigma;
    }
    
    
    //modify stuff about neuron
    public void setOutcome ( double value )
    {
        this.outcome = value;
    }
    public void setGradient ( double value )
    {
        this.gradient = value;
    }
    public void setHiddenGradient  ( Layer nextLayer )
    {
        double delta = getDistributedWeight ( nextLayer );
        
        setGradient ( getPrimeActivation ( outcome ) * delta );
    }
    public void setOutcomeGradient ( int target )
    {
        double delta = target - this.outcome;
        
        setGradient ( getPrimeActivation ( outcome ) * delta );
    }
    
    public void doForwardPropagation ( Layer priorLayer )
    {
        double sigma = 0.0;
        
        //other layer weights * other layer outcome
        for ( int pLI = 0; pLI < priorLayer.size ( ); pLI ++ )
            sigma += priorLayer.get ( pLI ).getWeights ( ).get ( neuronId ).getWeight ( ) * priorLayer.get ( pLI ).getOutcome ( );
        
        setOutcome ( getActivation ( sigma ) );
    }
    
    
    public void updateWeight ( Layer priorLayer )
    {
        for ( int pLI = 0; pLI < priorLayer.size ( ); pLI ++ )
        {
            
            double priorDeltaWeight = priorLayer.get ( pLI ).getWeights ( ).get ( neuronId ).getDeltaWeight ( );
            
            //FORM: ( eta * this gradient * outcome ) + ( alpha * priorDeltaWeight )
            double newDeltaWeight = ( eta * getGradient ( ) * outcome ) + ( alpha * priorDeltaWeight );
            
            //set our delta weight
            priorLayer.get ( pLI ).getWeights ( ).get ( neuronId ).setDeltaWeight ( newDeltaWeight );
            
            //set our weights
            priorLayer.get ( pLI ).getWeights ( ).get ( neuronId ).setWeight ( priorLayer.get ( pLI ).getWeights ( ).get ( neuronId ).getWeight ( ) + newDeltaWeight );
        }
    }
}