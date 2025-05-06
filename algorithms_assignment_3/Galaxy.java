import java.util.*;

public class Galaxy {

    private final List<Planet> planets;
    private List<SolarSystem> solarSystems;

    public Galaxy(List<Planet> planets) {
        this.planets = planets;
    }

    /**
     * Using the galaxy's list of Planet objects, explores all the solar systems in the galaxy.
     * Saves the result to the solarSystems instance variable and returns a shallow copy of it.
     *
     * @return List of SolarSystem objects.
     */
    public List<SolarSystem> exploreSolarSystems() {
        solarSystems = new ArrayList<>();
        List<List<String>> neighbors = new ArrayList<>();
        solarSystems.add(new SolarSystem());
        for(Planet i:planets){
            if(neighbors.size()==0){
                List<String> adder = new ArrayList<>();
                adder.add(i.getId());
                adder.addAll(i.getNeighbors());
                neighbors.add(adder);
                solarSystems.get(0).addPlanet(i);
            }
            else{
                boolean gg=true;
                for(int n=0;n<neighbors.size();n++){
                    for(String k:neighbors.get(n)){
                        if(i.getId().equals(k)){
                            List<String> a = neighbors.get(n);
                            gg=false;
                            solarSystems.get(n).addPlanet(i);
                            for(String j:i.getNeighbors()){
                                if(!a.contains(j)){
                                    a.add(j);
                                }
                            }
                            break;
                        }
                        else{
                            for(String j:i.getNeighbors()){
                                if(j.equals(k)){
                                    List<String> a = neighbors.get(n);
                                    gg=false;
                                    solarSystems.get(n).addPlanet(i);
                                    a.add(i.getId());
                                    for(String jj:i.getNeighbors()){
                                        if(!a.contains(jj)){
                                            a.add(jj);
                                        }
                                    }
                                    break;
                                }
                            }
                            if(!gg){
                                break;
                            }
                        }
                    }
                    if(!gg){
                        break;
                    }
                }
                if(gg){
                    List<String> adder = new ArrayList<>();
                    adder.addAll(i.getNeighbors());
                    adder.add(i.getId());
                    neighbors.add(adder);
                    SolarSystem adding = new SolarSystem();
                    adding.addPlanet(i);
                    solarSystems.add(adding);
                }
            }
        }
        return new ArrayList<>(solarSystems);
    }

    public List<SolarSystem> getSolarSystems() {
        return solarSystems;
    }

    // FOR TESTING
    public List<Planet> getPlanets() {
        return planets;
    }

    // FOR TESTING
    public int getSolarSystemIndexByPlanetID(String planetId) {
        for (int i = 0; i < solarSystems.size(); i++) {
            SolarSystem solarSystem = solarSystems.get(i);
            if (solarSystem.hasPlanet(planetId)) {
                return i;
            }
        }
        return -1;
    }

    public void printSolarSystems(List<SolarSystem> solarSystems) {
        System.out.printf("%d solar systems have been discovered.%n", solarSystems.size());
        for (int i = 0; i < solarSystems.size(); i++) {
            SolarSystem solarSystem = solarSystems.get(i);
            List<Planet> planets = new ArrayList<>(solarSystem.getPlanets());
            Collections.sort(planets);
            System.out.printf("Planets in Solar System %d: %s", i + 1, planets);
            System.out.println();
        }
    }
}
