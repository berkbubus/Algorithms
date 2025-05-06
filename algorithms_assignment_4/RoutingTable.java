import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.*;

public class RoutingTable implements Serializable {

    static final long serialVersionUID = 99L;
    private final Router router;
    private final Network network;
    private final List<RoutingTableEntry> entryList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Router getRouter() {
        return router;
    }

    public Network getNetwork() {
        return network;
    }

    public RoutingTable(Router router) {
        this.router = router;
        this.network = router.getNetwork();
        this.entryList = new ArrayList<>();
    }

    /**
     * updateTable() should calculate routing information and then instantiate RoutingTableEntry objects, and finally add
     * them to RoutingTable object’s entryList.
     */
    public void updateTable() {
        for(Router i:network.getRouters()){
            if(!i.equals(router)){
                RoutingTableEntry entry = new RoutingTableEntry(router.getIpAddress(),i.getIpAddress(),pathTo(i));
                entryList.add(entry);
            }
        }
    }

    /**
     * pathTo(Router destination) should return a Stack<Link> object which contains a stack of Link objects,
     * which represents a valid path from the owner Router to the destination Router.
     *
     * @param destination Destination router
     * @return Stack of links on the path to the destination router
     */
    public Stack<Link> pathTo(Router destination) {
        Map<Router,Double> explore = new HashMap<>();
        ArrayList<Router> explored = new ArrayList<>(network.getRouters());
        ArrayList<Router> remover=new ArrayList<>();
        for(Router i:explored){
            if(i.isDown()){
                remover.add(i);
            }
        }
        explored.removeAll(remover);
        Map<Router,Stack<Link>> linker = new HashMap<>();
        for(Router i:network.getRouters()){
            explore.put(i,Double.POSITIVE_INFINITY);
            linker.put(i, new Stack<>());
        }
        explore.replace(router, 0.0);
        while(!explored.isEmpty()){
            Router min=null;
            for(Router i:explored){
                if(min==null){
                    min=i;
                }
                else if(explore.get(i)<explore.get(min)){
                    min=i;
                }
            }
            for(Link i:min.getNetwork().getLinksOfRouter(min)){
                double a=i.getCost()+explore.get(min);
                Router other =min.getNetwork().getRouterWithIp(i.getOtherIpAddress(min.getIpAddress()));
                if(other.isDown()){
                    continue;
                }
                if(explore.get(other)>a){
                    linker.get(other).clear();
                    for(Link j:linker.get(min)){
                        linker.get(other).add(j);
                    }
                    linker.get(other).add(i);
                    explore.replace(other,a);
                }
            }
            explored.remove(min);
        }
        return linker.get(destination);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutingTable that = (RoutingTable) o;
        return router.equals(that.router) && entryList.equals(that.entryList);
    }

    public List<RoutingTableEntry> getEntryList() {
        return entryList;
    }
}
