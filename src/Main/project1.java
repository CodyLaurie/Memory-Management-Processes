package Main;

import java.io.ObjectInputStream.GetField;
import java.util.*;


class PCB{
	int parent;
	LinkedList<Integer> children;
}
public class project1{
	static int index;
	static Scanner scanner = new Scanner(System.in);
	static PCB[] PCBlist;
	public static void main(String[] args) {
		int reply = 1;
		while(reply!=4){
	        printf("Process creation and destruction \n");
	        printf("-------------------------------- \n");
	        printf("1) Enter parameters\n");
	        printf("2) Create a new childprocess\n");
	        printf("3) Destroy all descendants of a process\n");
	        printf("4) Quit program and free memory\n \n");
	        printf("Enter selection:");
	        reply = scanner.nextInt();
	        
	        switch (reply){
	        case 1:
	            parameters();
	            break;
	        case 2:
	            createchild();
	            break;
	        case 3:
	            destroy();
	            break;
	        case 4:
	        	totdestroy();
	        	System.exit(0);
	            break;
	        default:
	            printf("Please enter a valid input\n");
	            break;
	        }
	        printf("\n");
	    }
	}
	public static void parameters() {
		
		printf("Enter Maximum number of processes:");
		index = scanner.nextInt();
		PCBlist = new PCB[index];
		for (int i = 0; i < index; i++){
			PCBlist[i] = new PCB();
	        PCBlist[i].parent = -1;
	        PCBlist[i].children = new LinkedList<Integer>();
	    }
		PCBlist[0].parent = 0;
		return;
	}
	
	public static void createchild() {
		int q =0;
		int p;
		printf("Enter the parent process index:");
		p = scanner.nextInt();
		System.out.println();
		while(PCBlist[q].parent!=-1) {
			q++;
		}
		PCBlist[q].parent = p;
		PCBlist[q].children = new LinkedList<Integer>();
	    PCBlist[p].children.add(q);
	    
	    PrintPCBS();
	}
	public static void PrintPCBS() {
		for(int i = 0; i < index-1;i++) {
			System.out.println();
			if(PCBlist[i].children.size()>0 && PCBlist[i].parent!=-1) {
				printf("PCB["+i+"] is the parent of:");
				for(int c = 0; c < PCBlist[i].children.size();c++) {
					printf("  PCB["+PCBlist[i].children.get(c)+"]  ");
				}
			}
		}
	}
	public static void destroy() {
		printf("Enter the index of the process whose descendants are to be destroyed: ");
		int kill = scanner.nextInt();
		LinkedList<Integer> exterminate = PCBlist[kill].children;
		for (int i = 0; i < exterminate.size();i++) {
			PCBlist[exterminate.get(i)] = new PCB();
			PCBlist[exterminate.get(i)].parent = -1;
			PCBlist[exterminate.get(i)].children = new LinkedList<Integer>();
		}
		PCBlist[kill] = new PCB();
		PCBlist[kill].parent = -1;
		PCBlist[kill].children = new LinkedList<Integer>();
		PCBlist[0].parent = 0;
		
	}
	public static void totdestroy() {
		for (int i = 0; i < index; i++){
			PCBlist[i] = new PCB();
	        PCBlist[i].parent = -1;
	        PCBlist[i].children = new LinkedList<Integer>();
	    }
	}
	
	public static void printf(String e) {
		System.out.print(e);
	}
}