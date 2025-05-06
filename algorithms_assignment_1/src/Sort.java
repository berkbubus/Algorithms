

public class Sort {
    public void Insertion(int[] Array){
        int key,j;
        for(int i = 1;i< Array.length;i++){
            key = Array[i];
            j = i - 1;
            while(j>-1 && Array[j]>key){
                Array[j+1]=Array[j];
                j=j-1;
            }
            Array[j+1]=key;
        }
    }

    public int[] Merge(int[] Array){
        int n = Array.length;
        if(n<=1)
            return Array;
        int[] left = new int[(n/2)];
        int[] right = new int[n-(n/2)];
        for(int i = 0;i<n/2;i++)
            left[i]=Array[i];
        for(int i = 0;i<n-(n/2);i++)
            right[i]=Array[i+ n/2];
        left=Merge(left);
        right=Merge(right);
        return Merger(left,right);
    }
    public int[] Merger(int[] Array, int[] Brray){
        int[] Crray = new int[Array.length+ Brray.length];
        int a=0;
        int b=0;
        int c=0;
        int ar = Array.length;
        int br = Brray.length;
        while(ar>0 && br>0){
            if (Array[0]> Brray[0]){
                Crray[c++]= Brray[0];
                if(br>1)
                    Brray[0] = Brray[++a];
                br--;
            }
            else{
                Crray[c++]= Array[0];
                if(ar>1)
                    Array[0]=Array[++b];

                ar--;
            }
        }
        while(ar>0){
            Crray[c++]= Array[0];
            if(ar>1)
                Array[0]=Array[++b];
            ar--;
        }
        while(br>0){
            Crray[c++]= Brray[0];
            if(br>1)
                Brray[0] = Brray[++a];
            br--;
        }
        return Crray;
    }
    public int[] Pigeonhole(int[] Array){
        int n=Array.length;
        int min = Array[0];
        int max = Array[0];
        for(int a=0; a<n; a++) {
            if(Array[a] > max)
                max = Array[a];
            if(Array[a] < min)
                min = Array[a];
        }
        int range = max-min+1;
        int[] output = new int[n];
        int[][] holes = new int[range][];
        int h;
        int[] holes2;
        for (int k : Array) {
            if(holes[k - min]==null)
                holes[k - min]=new int[0];
            h = holes[k - min].length;
            holes2 = new int[h+1];
            for(int i=0;i<h;i++)
                holes2[i]=holes[k - min][i];
            holes2[h]=k;
            holes[k-min]=holes2;
        }
        int index=0;
        for(int i=0;i<range;i++)
            if(holes[i]!=null)
                for (int hole : holes[i])
                    output[index++]=hole;
        return output;
    }

    public int[] Counting(int[] Array,int k){
        int[] count = new int[k+1];
        for(int i=0;i<k+1;i++)
            count[i]=0;
        int size = Array.length;
        int[] output = new int[size];
        int j;
        for(int i=0;i<size;i++){
            j= Array[i];
            count[j]+=1;
        }
        for(int i=1;i<k+1;i++)
            count[i]+=count[i-1];
        for(int i=size-1;i>-1;i--){
            j= Array[i];
            count[j]-=1;
            output[count[j]]=Array[i];
        }
        return output;
    }
}
