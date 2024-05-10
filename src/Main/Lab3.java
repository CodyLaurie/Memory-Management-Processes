package Main;

import java.util.*;


public class Lab3{
	static Scanner scanner = new Scanner(System.in);
	static int[][] maxclaim;
	static int[][] allocation;
	static int[][] potential;
	static int[] avalible;
	public static void main(String[] args) {
		while(true==true) {
			loop();
		}
	}
	
	public static void loop() {
		int reply;
		pn("Banker's Algorithm");
		pn("------------------");
		pn("1) Enter parameters");
		pn("2) Determine safe sequence");
		pn("3) Quit program");
		pn("");
		pl("Enter selection: ");
		reply = scanner.nextInt();
		switch (reply){
	    	case 1:
	            Entpar();
	            break;
	        case 2:
	        	if(potential.length==0){
	    			pn("Input invalid please enter parameters first");
	    			break;
	    		}
	        	Detsafe();
	            break;
	        case 3:
	        	pn("Quitting program...");
	        	System.exit(0);
	            break;
	        default:
	            pn("Please enter a valid input");
	            loop();
	            break;
	      }
	}
	public static void Entpar() {
		pl("Enter number of processes: ");
		int numpro = scanner.nextInt();
		
		pl("Enter number of resources: ");
		int numres = scanner.nextInt();
		
		pl("Enter number of units for resources(r0 to r" + (numres-1) + "):");
		int[] units = new int[numres];
		for(int i = 0; i < numres;i++){
			units[i]=scanner.nextInt();
		}
		
		maxclaim = new int[numres][numpro];
		for(int i = 0; i < numpro;i++) {
			pl("Enter maximum number of units process p"+i+" will request from each resource (r0 to r2) ");
			for(int c = 0; c < numres;c++){
				maxclaim[c][i] = scanner.nextInt();
			}
		}
		
		allocation = new int[numres][numpro];
		for(int i = 0; i < numpro;i++) {
			pl("Enter number of units of each resource (r0 to r"+(numres-1)+") allocated to process p"+i+": ");
			for(int c = 0; c < numres;c++){
				allocation[c][i] = scanner.nextInt();
			}
		}
		
		potential = new int[numres][numpro];
		for(int i = 0; i < numpro;i++) {
			for(int c = 0; c < numres;c++){
				potential[c][i] = maxclaim[c][i] - allocation[c][i];
			}
		}
		
		avalible = new int[numres];
		avalible = sumcollumns(allocation);
		pn("");
		pn("        Units   Available");
		pn("-------------------------");
		for(int i = 0;i < numres;i++) {
			pl("r"+i+"     ");       
			int dummy = units[i];
			pl(dummy);
			spaceout("r"+dummy);
			pl(dummy-avalible[i]);
			avalible[i] = dummy-avalible[i];
			pn("");
		}
		pn("");
		pn("        Max claim                       Current                         Potential");
		pl("       ");
		
		for(int c = 0; c < 3; c++){
			for(int i = 0; i < numres;i++){
				pl("r"+i);
				spaceout("r"+i);
			}
            pl("    ");
		}
		pn("");
		pn("-------------------------------------------------------------------------------------------");
		for(int c = 0; c < numpro; c++){
			pl("p"+c+"     ");       
			for(int i = 0;i < numres;i++) {
				int dummy = maxclaim[i][c];
				pl(dummy);
				spaceout(Integer.toString(dummy));
			}    
			pl("    ");
			for(int i = 0;i < numres;i++) {
				int dummy = allocation[i][c];
				pl(dummy);
				spaceout(Integer.toString(dummy));
			}    
			pl("    ");
			for(int i = 0;i < numres;i++) {
				int dummy = potential[i][c];
				pl(dummy);
				spaceout(Integer.toString(dummy));
			}    
			pn("");
		}
		//print out with spaces
		pn("");
		pn("");
		
	}
	public static void Detsafe() {
		Queue<int[]> q = new LinkedList<>();
		Queue<Integer> index = new LinkedList<>();
		for (int i = 0 ; i < potential[0].length ; i++){
			int[] dummy = new int[potential.length];
			for (int c = 0; c < potential.length;c++){
				dummy[c] = potential[c][i];
			}
			q.add(dummy);
			index.add(i);
		}
		while(!q.isEmpty())	{
			boolean safe = true;
			int size = potential.length;
			for(int i = 0; i < size; i++){
				if(q.peek()[i] <= avalible[i]){
					
				}else{
					safe = false;
				}	
			}
			if(safe==true){
				pl("Checking: <");
				for(int i = 0; i <size;i++){
					pl(q.peek()[i]);
				}
				pl("> <= <");
				for(int i = 0; i < avalible.length;i++){
					pl(avalible[i]);
					avalible[i]= avalible[i]+allocation[i][index.peek()];
				}
				pl("> :p" + index.peek() + " safely sequenced");
				q.remove();
				index.remove();
			}else{
				pl("Checking: <");
				for(int i = 0; i <size;i++){
					pl(q.peek()[i]);
				}
				pl("> <= <");
				for(int i = 0; i < avalible.length;i++){
					pl(avalible[i]);
				}
				pl("> :p" + index.peek() + " could not be safely sequenced");
				index.add(index.remove());
				q.add(q.remove());
			}
			pn("");
		}
		pn("");
	}
	public static void spaceout(String holder) {
		int space = 8;
		space = space - holder.length();
		for(int c = 0; c<space;c++) {
			pl("");
		}
	}
	public static int[] sumcollumns(int[][] array){
		int reg = array.length;
		int proc = array[0].length;
		int[] sums = new int[reg];
		for(int i = 0; i < reg; i++){
			sums[i] = 0;
			for(int c = 0; c < proc; c++){
				sums[i] = array[i][c] + sums[i];
			}
		}
		return sums;
	}
	public static <E> void pl(E item){
		System.out.print(item + " ");
	}	
	
	public static <E> void pn(E item){
		System.out.println(item);
	}
}