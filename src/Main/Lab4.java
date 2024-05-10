package Main;

import java.util.*;


class block{
	int id;
	int start;
	int end;
}
public class Lab4{
	static Scanner scanner = new Scanner(System.in);
	static LinkedList<block> blocks = new LinkedList<>();
	static int cap;
	static int approach;
	public static void main(String[] args) {
		while(true==true) {
			loop();
		}
	}
	public static void loop() {
		int reply;
		pn("Memory allocation");
		pn("-----------------");
		pn("1) Enter parameters");
		pn("2) Allocate memory for block");
		pn("3) Deallocate memory for block");
		pn("4) Defragment memory");
		pn("5) Quit program");
		pn("");
		pl("Enter selection:");
		reply = scanner.nextInt();
		switch (reply){
	    	case 1:
	    		perameters();
	            break;
	        case 2:
	        	allocate();
	            break;
	        case 3:
	        	deallocate();
	            break;
	        case 4:
	        	defragment();
	        	break;
	        case 5:
	        	pn("Quitting program...");
	        	System.exit(0);
	            break;
	        default:
	            pn("Please enter a valid input");
	            loop();
	            break;
	      }
	}
	public static void perameters(){
		pl("Enter size of physical memory:");
		cap = scanner.nextInt();
		pl("Enter hole-fitting algorithm (0=first fit, 1=best_fit):");
		approach = scanner.nextInt();
		pn("");
	}
	public static void allocate(){
		pl("Emter block id:");
		int n1 = scanner.nextInt();
		pl("Enter block size:");
		int n2 = scanner.nextInt();
		block dummy = new block();
		dummy.id = n1;
		if(blocks.isEmpty()) {
			if(n2 > cap) {
				pn("Limit Exceeded" + cap + n2);
				return;
			}
			dummy.start = 0;
			dummy.end = n2;
			blocks.add(dummy);
			print();
			return;
		}
		if (approach == 1) {
			int delta = -1;
			int bestfit = 0;
			for (int i = 0 ; i < blocks.size()-1;i++) {
				int b1 = blocks.get(i).end;
				int gap = blocks.get(i+1).start-b1;
				if(gap >= n2) {
					delta = gap;
					bestfit = i;
				}
			}
			if(delta == -1) {
				int b1 = blocks.getLast().end;
				dummy.start = b1;
				dummy.end = b1 + n2;
				if(dummy.end > cap) {
					pn("Limit Exceeded");
					return;
				}
				blocks.add(dummy);
				print();
				return;
			}else {
				int b1 = blocks.getLast().end;
				int gap = cap-b1;
				if(gap >= n2) {
					delta = gap;
					bestfit = blocks.size()-1;
				}
				b1 = blocks.get(bestfit).end;
				dummy.start = b1;
				dummy.end = b1 + n2;
				blocks.add(bestfit+1, dummy);
				print();
				return;
			}
////////////////////////////////////////////////////////////////////////
		}else {
			for (int i = 0 ; i < blocks.size()-1;i++) {
				int b1 = blocks.get(i).end;
				if(blocks.get(i+1).start-b1 >= n2) {
					dummy.start = b1;
					dummy.end = b1 + n2;
					blocks.add(i+1, dummy);
					print();
					return;
				}
			}
			int b1 = blocks.getLast().end;
			dummy.start = b1;
			dummy.end = b1 + n2;
			if(dummy.end > cap) {
				pn("Limit Exceeded");
				return;
			}
			blocks.add(dummy);
			print();
			return;
		}
	}
	public static void deallocate(){
		pl("Emter block id:");
		int n1 = scanner.nextInt();
		blocks.remove(n1);
		print();
		return;
	}
	public static void defragment(){
		for (int i = 0 ; i < blocks.size()-1;i++){
			if(blocks.get(i+1).start-blocks.get(i).end > 0) {
				int size = blocks.get(i+1).end - blocks.get(i+1).start ;
				blocks.get(i+1).start = blocks.get(i).end;
				blocks.get(i+1).end = blocks.get(i).end + size;
			}
		}
		print();
		return;
	}
	public static void print() {
		pn("");
		pn("ID    Start   End");
		pn("-------------------");
		for (int i = 0 ; i < blocks.size();i++) {
			spaceout(blocks.get(i).id,5);
			spaceout(blocks.get(i).start,7);
			pn(blocks.get(i).end);
		}
		pn("");
	}
	public static void spaceout(int holder,int space) {
		pl(holder);
		String dummy = Integer.toString(holder);
		space = space - dummy.length();
		for(int c = 0; c<space;c++) {
			pl("");
		}
	}
	public static <E> void pl(E item){
		System.out.print(item + " ");
	}	
	
	public static <E> void pn(E item){
		System.out.println(item);
	}
}