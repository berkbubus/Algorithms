import java.util.ArrayList;
import java.util.Collections;

/**
 * This class accomplishes Mission Exterminate
 */
public class OptimalFinalDefenseGP
{
    private ArrayList<Integer> bombWeights;

    public OptimalFinalDefenseGP(ArrayList<Integer> bombWeights) {
        this.bombWeights = bombWeights;
    }

    public ArrayList<Integer> getBombWeights() {
        return bombWeights;
    }

    /**
     *
     * @param maxNumberOfAvailableAUAVs the maximum number of available AUAVs to be loaded with bombs
     * @param maxAUAVCapacity the maximum capacity of an AUAV
     * @return the minimum number of AUAVs required using first fit approach over reversely sorted items.
     * Must return -1 if all bombs can't be loaded onto the available AUAVs
     */
    public int getMinNumberOfAUAVsToDeploy(int maxNumberOfAvailableAUAVs, int maxAUAVCapacity)
    {
        int uauvCounter=0;
        int bombCounter=0;
        bombWeights.sort(Collections.reverseOrder());
        int numBombs=bombWeights.size();
        int[] remains = new int[maxNumberOfAvailableAUAVs];
        for(int i=0;i<maxNumberOfAvailableAUAVs;i++){
            remains[i]=maxAUAVCapacity;
        }
        for(int i=0;i<numBombs;i++){
            for(int j=0;j<maxNumberOfAvailableAUAVs;j++){
                if(remains[j]>=bombWeights.get(i)){
                    if(remains[j]==maxAUAVCapacity){
                        uauvCounter+=1;
                    }
                    remains[j]-=bombWeights.get(i);
                    bombCounter+=1;
                    break;
                }
            }
        }
        if(uauvCounter>0 && bombCounter==numBombs){
            return uauvCounter;
        }
        return -1;
    }
    public void printFinalDefenseOutcome(int maxNumberOfAvailableAUAVs, int AUAV_CAPACITY){
        int minNumberOfAUAVsToDeploy = this.getMinNumberOfAUAVsToDeploy(maxNumberOfAvailableAUAVs, AUAV_CAPACITY);
        if(minNumberOfAUAVsToDeploy!=-1) {
            System.out.println("The minimum number of AUAVs to deploy for complete extermination of the enemy army: " + minNumberOfAUAVsToDeploy);
        }
        else{
            System.out.println("We cannot load all the bombs. We are doomed.");
        }
    }
}
