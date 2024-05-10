package Main;

import java.util.*;


public class Lab2{
	
	static Scanner scanner = new Scanner(System.in);
	static List<Integer> ID = new ArrayList<Integer>();
	static List<Integer> Arrival = new ArrayList<Integer>();
	static List<Integer> Total = new ArrayList<Integer>();
	static List<Integer> Start = new ArrayList<Integer>();
	static List<Integer> End = new ArrayList<Integer>();
	static List<Integer> Turnaround = new ArrayList<Integer>();
	public static void main(String[] args) {
		while(true==true) {
			loop();
		}
	}
	
	public static void loop() {
		int reply = 1;
		pn("Batch scheduling");
		pn("----------------");
		pn("1) Enter parameters");
		pn("2) Schedule processes with FIFO algorithm");
		pn("3) Schedule processes with SJF algorithm");
		pn("4) Schedule processes with SRT algorithm");
		pn("5) Quit and free memory");
		pn("");
		pl("Enter Selection: ");
		reply = scanner.nextInt();
		 switch (reply){
	        case 1:
	            parameters();
	            break;
	        case 2:
	        	FIFO();
	            break;
	        case 3:
	            SJF();
	            break;
	        case 4:
	        	SRT();
	            break;
	        case 5:
	        	freeup();
	        	System.exit(0);
	            break;
	        default:
	            pn("Please enter a valid input");
	            loop();
	            break;
	      }
	}
	
	public static void parameters() {
		pl("Enter total number of processes: ");
		int nump = scanner.nextInt();
		for(int i = 0; i < nump;i++) {
			pl("Enter process id: ");
			int pid = scanner.nextInt();
			ID.add(pid);
			pl("Enter arrival cycle for process P["+ pid + "]: ");
			int arrival = scanner.nextInt();
			Arrival.add(arrival);
			pl("Enter total cycles for process P["+ pid + "]: ");
			int totcycle = scanner.nextInt();
			Total.add(totcycle);
		}
		printall();
	}
	
	public static void FIFO() {
		if(ID.size()==0) {
			pn("This feature is unavalible till you enter parameters");
			return;
		}
		
		prep();
		
		
		List<Integer> skip = new ArrayList<>();
		int counter = 0;
		int index;
		for(int i = 0; i < Arrival.size();i++) {
			index = smallestindex(Arrival,skip);
			Start.set(index, counter);
			counter = counter + Total.get(index);
			End.set(index,counter);
			Turnaround.set(index, End.get(index)-Arrival.get(index));
			skip.add(index);
		}
		
		printall();
	}
	
	public static void SJF() {
		if(ID.size()==0) {
			pn("This feature is unavalible till you enter parameters");
			return;
		}
		
		prep();
		
		List<Integer> skip = new ArrayList<>();
		int counter = 0;
		int index;
		Start.set(0, 0);
		counter = counter + Total.get(0);
		End.set(0,counter);
		Turnaround.set(0, End.get(0)-Arrival.get(0));
		skip.add(0);
		for(int i = 1; i < Arrival.size();i++) {
			index = smallestindex(Total,skip);
			Start.set(index, counter);
			counter = counter + Total.get(index);
			End.set(index,counter);
			Turnaround.set(index, End.get(index)-Arrival.get(index));
			skip.add(index);
		}
		
		printall();
	}
	
	public static void SRT() {
		if(ID.size()==0) {
			pn("This feature is unavalible till you enter parameters");
			return;
		}
		
		prep();
		int dummy;
		int dummy2;
		int sum = 0;
		List<Integer> loadedtotals = new ArrayList<>();
		List<Integer> tracker = new ArrayList<>();
		List<Boolean> haschanged = new ArrayList<>();
		for(int i = 0; i < Total.size();i++) {
			sum = Total.get(i) + sum;
		}
		for(int i = 0; i < sum;i++) {
			if(Arrival.contains(i)) {
				dummy = Arrival.indexOf(i);
				loadedtotals.add(Total.get(dummy));
				haschanged.add(false);
				tracker.add(dummy);
			}
			dummy = smallestindex(loadedtotals);
			if(haschanged.get(dummy)==false){
				haschanged.set(dummy,true);
				Start.set(tracker.get(dummy),i);
			}
			loadedtotals.set(dummy,loadedtotals.get(dummy)-1);
			
			if(loadedtotals.get(dummy)==0) {
				loadedtotals.remove(dummy);
				haschanged.remove(dummy);
				dummy2 = tracker.get(dummy);
				End.set(dummy2,i+1);
				Turnaround.set(dummy2, End.get(dummy2)-Arrival.get(dummy2));
				tracker.remove(dummy);
			}
		}
		
		printall();
	}
	
	public static void freeup() {
		ID.clear();
		Arrival.clear();
		Total.clear();
		Start.clear();
		End.clear();
		Turnaround.clear();
		pl("Quitting program...");
	}
	
	public static void prep() {
		Start.clear();
		End.clear();
		Turnaround.clear();
		for(int i = 0; i <ID.size();i++) {
			Start.add(0);
			End.add(0);
			Turnaround.add(0);
		}
	}
	public static void printall() {
	pn("");
	pn("ID      Arrival Total   Start   End     Turnaround");
	pn("--------------------------------------------------");
		for(int i = 0; i<ID.size();i++) {
			int dummy;
			String holder;
			
			
			dummy = ID.get(i);
			holder = Integer.toString(dummy);
			pl(dummy);
			spaceout(holder);
			
			dummy = Arrival.get(i);
			holder = Integer.toString(dummy);
			pl(dummy);
			spaceout(holder);
			
			dummy = Total.get(i);
			holder = Integer.toString(dummy);
			pl(dummy);
			spaceout(holder);
			
			if(Start.size()>0) {
				dummy = Start.get(i);
				holder = Integer.toString(dummy);
				pl(dummy);
				spaceout(holder);
			}else{
				spaceout("0");
			}
			
			if(End.size()>0) {
				dummy = End.get(i);
				holder = Integer.toString(dummy);
				pl(dummy);
				spaceout(holder);
			}else{
				spaceout("0");
			}
			
			if(Turnaround.size()>0) {
				dummy = Turnaround.get(i);
				holder = Integer.toString(dummy);
				pl(dummy);
				spaceout(holder);
			}else{
				spaceout("0");
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
	
	public static int smallestindex(List<Integer> list) {
		int index = 0;
		for(int i = 0; i < list.size();i++) {
			if(list.get(index)>list.get(i)) {
				index = i;
			}
		}
		return index;
	}
	
	public static int largestindex(List<Integer> list) {
		int index = 0;
		for(int i = 0; i < list.size();i++) {
			if(list.get(index)>list.get(i)) {
				index = i;
			}
		}
		return index;
	}
	public static int smallestindex(List<Integer> list,List<Integer> skip) {
		int index = 0;
		while(skip.contains(index)) {
			index++;
		}
		for(int i = 0; i < list.size();i++) {
			if(skip.contains(i)) {
				continue;
			}
			if(list.get(index)>list.get(i)) {
				index = i;
			}
		}
		return index;
	}
	
	public static int largestindex(List<Integer> list,List<Integer> skip) {
		int index = 0;
		while(skip.contains(index)) {
			index++;
		}
		for(int i = 0; i < list.size();i++) {
			if(list.get(index)>list.get(i)) {
				index = i;
			}
		}
		return index;
	}
	public static <E> void pl(E item){
		System.out.print(item + " ");
	}	
	
	public static <E> void pn(E item){
		System.out.println(item);
	}
}