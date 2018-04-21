package com.graph.search.algo;

import java.util.*;

class Node {

	String label;
	ArrayList<Node> adjList = new ArrayList<Node>();
	ArrayList<Integer> cost = new ArrayList<Integer>();
	int goalDist;

	Node bestAdj() {

		int cheap = cheapest(this.adjList, this.cost);

		return adjList.get(cheap);

	}

	int cheapest(ArrayList<Node> node, ArrayList<Integer> costs) {

		int ans = 0;

		for (int i = 1; i < costs.size(); i++) {

			if ((node.get(i).goalDist + costs.get(i)) > (node.get(i - 1).goalDist + costs.get(i - 1))) {

				ans = i - 1;
			} else {
				ans = i;
			}
		}

		return ans;

	}

}

public class AStar {

	public static void main(String args[]) {

		Node n1 = new Node();
		n1.label = "A";
		Node n2 = new Node();
		n2.label = "B";
		Node n3 = new Node();
		n3.label = "C";
		Node n4 = new Node();
		n4.label = "D";
		Node n5 = new Node();
		n5.label = "E";

		n1.goalDist = 10;
		n1.adjList.add(n2);
		n1.cost.add(5);
		n1.adjList.add(n3);
		n1.cost.add(2);

		n2.goalDist = 10;
		n2.adjList.add(n4);
		n2.cost.add(1);

		n3.goalDist = 15;
		n3.adjList.add(n4);
		n3.cost.add(3);
		n3.adjList.add(n5);
		n3.cost.add(5);

		n4.adjList.add(n5);
		n4.cost.add(3);
		n4.goalDist = 5;

		n5.goalDist = 0;

		Node n = n1;
		System.out.print(n.label);
		while (n.goalDist != 0) {
			n = n.bestAdj();
			System.out.print(" -> " + n.label);
		}

	}

}
