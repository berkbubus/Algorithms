import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SubspaceCommunicationNetwork {

    private List<SolarSystem> solarSystems;

    /**
     * Perform initializations regarding your implementation if necessary
     * @param solarSystems a list of SolarSystem objects
     */
    public SubspaceCommunicationNetwork(List<SolarSystem> solarSystems) {
        // TODO: YOUR CODE HERE
        this.solarSystems = solarSystems;
    }

    /**
     * Using the solar systems of the network, generates a list of HyperChannel objects that constitute the minimum cost communication network.
     * @return A list HyperChannel objects that constitute the minimum cost communication network.
     */
    public List<HyperChannel> getMinimumCostCommunicationNetwork() {
        List<HyperChannel> minimumCostCommunicationNetwork = new ArrayList<>();
        List<Planet> technologyList = new ArrayList<>();
        Planet theOne = new Planet(null,0,null);
        for(SolarSystem i:solarSystems){
            Planet best= new Planet(null,0,null);
            for(Planet j:i.getPlanets()){
                if(j.getTechnologyLevel()>best.getTechnologyLevel()){
                    best =j;
                    if(j.getTechnologyLevel()>theOne.getTechnologyLevel()){
                        theOne=j;
                    }
                }
            }
            technologyList.add(best);
        }
        for(Planet j:technologyList){
            if(!theOne.equals(j)){
                float k=(theOne.getTechnologyLevel()+j.getTechnologyLevel())/2f;
                HyperChannel adder = new HyperChannel(j,theOne,Constants.SUBSPACE_COMMUNICATION_CONSTANT/k);
                minimumCostCommunicationNetwork.add(adder);
            }
        }
        minimumCostCommunicationNetwork.sort(Comparator.comparing(HyperChannel::getWeight));
        return minimumCostCommunicationNetwork;
    }

    public void printMinimumCostCommunicationNetwork(List<HyperChannel> network) {
        double sum = 0;
        for (HyperChannel channel : network) {
            Planet[] planets = {channel.getFrom(), channel.getTo()};
            Arrays.sort(planets);
            System.out.printf("Hyperchannel between %s - %s with cost %f", planets[0], planets[1], channel.getWeight());
            System.out.println();
            sum += channel.getWeight();
        }
        System.out.printf("The total cost of the subspace communication network is %f.", sum);
        System.out.println();
    }
}
