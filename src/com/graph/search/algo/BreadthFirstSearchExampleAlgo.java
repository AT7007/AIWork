package com.graph.search.algo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearchExampleAlgo {

	private Queue<TreeElementNode> queue;
	static ArrayList<TreeElementNode> nodes = new ArrayList<TreeElementNode>();

	static class TreeElementNode {
		int information;
		boolean nodeElementVisitStatus;
		List<TreeElementNode> connectingNodeOrNeighbour;

		TreeElementNode(int information) {
			this.information = information;
			this.connectingNodeOrNeighbour = new ArrayList<>();

		}

		public void addneighbours(TreeElementNode neighbourNode) {
			this.connectingNodeOrNeighbour.add(neighbourNode);
		}

		public List<TreeElementNode> getNeighbours() {
			return connectingNodeOrNeighbour;
		}

		public void setNeighbours(List<TreeElementNode> neighbours) {
			this.connectingNodeOrNeighbour = neighbours;
		}
	}

	public BreadthFirstSearchExampleAlgo() {
		queue = new LinkedList<TreeElementNode>();
	}

	/**
	 * breadth first search algo. goes here
	 * 
	 * @param node
	 */
	public void breadthFirstSearchAlgo(TreeElementNode node) {
		queue.add(node);
		node.nodeElementVisitStatus = true;
		while (!queue.isEmpty()) {

			TreeElementNode element = queue.remove();
			System.out.print(element.information + "t");
			List<TreeElementNode> neighbours = element.getNeighbours();
			for (int i = 0; i < neighbours.size(); i++) {
				TreeElementNode n = neighbours.get(i);
				if (n != null && !n.nodeElementVisitStatus) {
					queue.add(n);
					n.nodeElementVisitStatus = true;

				}
			}

		}
	}

	public static void main(String arg[]) {

		TreeElementNode node40 = new TreeElementNode(40);
		TreeElementNode node10 = new TreeElementNode(10);
		TreeElementNode node20 = new TreeElementNode(20);
		TreeElementNode node30 = new TreeElementNode(30);
		TreeElementNode node60 = new TreeElementNode(60);
		TreeElementNode node50 = new TreeElementNode(50);
		TreeElementNode node70 = new TreeElementNode(70);

		node40.addneighbours(node10);
		node40.addneighbours(node20);
		node10.addneighbours(node30);
		node20.addneighbours(node10);
		node20.addneighbours(node30);
		node20.addneighbours(node60);
		node20.addneighbours(node50);
		node30.addneighbours(node60);
		node60.addneighbours(node70);
		node50.addneighbours(node70);
		System.out.println("The BFS traversal of the graph is ");
		BreadthFirstSearchExampleAlgo bfsExample = new BreadthFirstSearchExampleAlgo();
		bfsExample.breadthFirstSearchAlgo(node40);

	}
}